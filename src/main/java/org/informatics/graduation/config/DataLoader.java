//package org.informatics.graduation.config;
//
//import org.informatics.graduation.model.Role;
//import org.informatics.graduation.model.User;
//import org.informatics.graduation.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Profile;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//@Component
//@Profile("dev")
//public class DataLoader implements CommandLineRunner {
//    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
//
//    @Autowired
//    public DataLoader(UserRepository userRepository, PasswordEncoder passwordEncoder) {
//        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        if (userRepository.count() == 0) {
//            User teacher = new User("APetrov", passwordEncoder.encode("password"), Role.TEACHER);
//            User student = new User("BDimov", passwordEncoder.encode("password"), Role.STUDENT);
//            userRepository.save(teacher);
//            userRepository.save(student);
//        }
//    }
//}
