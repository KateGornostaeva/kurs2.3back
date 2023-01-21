package ru.kate.kurs2back.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.kate.kurs2back.dto.MosData;
import ru.kate.kurs2back.entity.User;
import ru.kate.kurs2back.repository.UserRepository;
import ru.kate.kurs2back.service.ImportData;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    
    private final ImportData importData;
    private final UserRepository userRepository;
    
    public UserController(ImportData importData, UserRepository userRepository) {
        this.importData = importData;
        this.userRepository = userRepository;
    }
    
    @GetMapping("/register")
    public ResponseEntity<String> register(@RequestParam String login,
                                           @RequestParam String password,
                                           @RequestParam String name,
                                           @RequestParam String email) {
        
        if (userRepository.findByLogin(login) == null) {
            User user = new User();
            user.setLogin(login);
            user.setPassword(password);
            user.setName(name);
            user.setEmail(email);
            userRepository.save(user);
        } else {
            return ResponseEntity.badRequest().body("Login already exist");
        }
        return ResponseEntity.ok("Ok");
    }
    
    @GetMapping("/login")
    public ResponseEntity<Long> login(@RequestParam String login,
                                      @RequestParam String password) {
        User user = userRepository.findByLogin(login);
        if (user != null && user.getPassword().equals(password)) {
            return ResponseEntity.ok(user.getId());
        } else {
            return ResponseEntity.badRequest().body(0L);
        }
    }
    
    @GetMapping("/test")
    public ResponseEntity<List<MosData>> test() {
        return ResponseEntity.ok(importData.get());
    }
}
