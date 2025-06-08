//package org.informatics.graduation.service;
//
//import org.informatics.graduation.dto.RegistrationDTO;
//import org.informatics.graduation.model.Role;
//import org.informatics.graduation.model.User;
//import org.informatics.graduation.repository.UserRepository;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//@Service
//public class UserService {
//    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
//
//    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
//        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    public void registerUser(RegistrationDTO registrationDTO) {
//        User user = new User();
//        user.setUsername(registrationDTO.getUsername());
//        user.setPassword(passwordEncoder.encode(registrationDTO.getPassword()));
//        user.setRole(Role.STUDENT); // Default role
//        userRepository.save(user);
//    }
//}