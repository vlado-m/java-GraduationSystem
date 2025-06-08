//package org.informatics.graduation.controller;
//
//import org.informatics.graduation.dto.RegistrationDTO;
//import org.informatics.graduation.service.UserService;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//@Controller
//@RequestMapping("/register")
//public class RegistrationController {
//    private final UserService userService;
//
//    public RegistrationController(UserService userService) {
//        this.userService = userService;
//    }
//
//    @GetMapping
//    public String showRegistrationForm(Model model) {
//        model.addAttribute("user", new RegistrationDTO());
//        return "register";
//    }
//
//    @PostMapping
//    public String registerUser(RegistrationDTO registrationDTO, BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            return "register";
//        }
//        userService.registerUser(registrationDTO);
//        return "redirect:/login?registered";
//    }
//}