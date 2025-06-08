package org.informatics.graduation.controller;


import jakarta.validation.Valid;
import org.informatics.graduation.model.Application;
import org.informatics.graduation.service.ApplicationService;
import org.informatics.graduation.service.StudentService;
import org.informatics.graduation.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/applications")
public class ApplicationWebController {

    private final ApplicationService service;
    private final StudentService studentService;
    private final TeacherService teacherService;

    @Autowired
    public ApplicationWebController(ApplicationService service,
                                    StudentService studentService,
                                    TeacherService teacherService) {
        this.service = service;
        this.studentService = studentService;
        this.teacherService = teacherService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("applications", service.getAllApplications());
        return "applications";
    }

    @GetMapping("/new")
    public String showForm(Model model) {
        model.addAttribute("application", new Application());
        model.addAttribute("students", studentService.getAllStudents());
        model.addAttribute("teachers", teacherService.getAll());
        return "application_form";
    }

    @PostMapping
    public String create(@ModelAttribute @Valid Application application,
                         BindingResult result) {
        if (result.hasErrors()) {
            return "application_form";
        }
        service.create(application);
        return "redirect:/applications";
    }

    @GetMapping("/{id}")
    public String view(@PathVariable Long id, Model model) {
        service.getById(id).ifPresent(app -> model.addAttribute("application", app));
        return "application_detail";
    }

    @PostMapping("/{id}/approve")
    public String approve(@PathVariable Long id) {
        service.approve(id);
        return "redirect:/applications";
    }

    @PostMapping("/{id}/reject")
    public String reject(@PathVariable Long id) {
        service.reject(id);
        return "redirect:/applications";
    }
}
