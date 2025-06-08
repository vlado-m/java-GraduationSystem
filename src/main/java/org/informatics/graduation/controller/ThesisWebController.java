package org.informatics.graduation.controller;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.informatics.graduation.model.Thesis;
import org.informatics.graduation.service.ApplicationService;
import org.informatics.graduation.service.ThesisService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/theses")
public class ThesisWebController {

    private final ThesisService service;
    private final ApplicationService applicationService;
    public ThesisWebController(ThesisService service, ApplicationService applicationService) {
        this.service = service;
        this.applicationService = applicationService;
    }

    @GetMapping("/new")
    public String showThesisForm(@RequestParam Long applicationId, Model model) {
        Thesis thesis = new Thesis();
        thesis.setApplication(applicationService.getById(applicationId)
                .orElseThrow(() -> new EntityNotFoundException("Application not found: " + applicationId)));
        model.addAttribute("thesis", thesis);
        return "thesis_form";
    }

    @PostMapping
    public String handleThesisSubmit(@ModelAttribute @Valid Thesis thesis,
                                     BindingResult br,
                                     @RequestParam("file") MultipartFile file,
                                     Model model) throws IOException {
        if (br.hasErrors()) {
            return "thesis_form";
        }
        service.saveThesis(thesis, file);
        return "redirect:/theses";
    }

    @GetMapping
    public String listTheses(Model model) {
        model.addAttribute("theses", service.getAll());
        return "theses";
    }

    @GetMapping("/{id}")
    public String showThesisDetail(@PathVariable Long id, Model model) {
        Thesis thesis = service.getById(id);
        model.addAttribute("thesis", thesis);
        return "thesis_detail";
    }
}
