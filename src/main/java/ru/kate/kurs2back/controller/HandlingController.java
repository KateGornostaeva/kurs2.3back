package ru.kate.kurs2back.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.kate.kurs2back.entity.Handling;
import ru.kate.kurs2back.entity.User;
import ru.kate.kurs2back.repository.HandlingRepository;
import ru.kate.kurs2back.repository.UserRepository;

import java.time.LocalDate;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/handling")
public class HandlingController {
    
    private final UserRepository userRepository;
    private final HandlingRepository handlingRepository;
    
    public HandlingController(UserRepository userRepository, HandlingRepository handlingRepository) {
        this.userRepository = userRepository;
        this.handlingRepository = handlingRepository;
    }
    
    @GetMapping("/send")
    public ResponseEntity<String> register(@RequestParam Long userId,
                                           @RequestParam String message,
                                           @RequestParam String type) {
        Optional<User> optional = userRepository.findById(userId);
        if (optional.isPresent()) {
            User user = optional.get();
            Handling handling = new Handling();
            handling.setDate(LocalDate.now());
            handling.setUser(user);
            handling.setType(type);
            handling.setMessage(message);
            handlingRepository.save(handling);
            return ResponseEntity.ok("Ok");
        } else {
            return ResponseEntity.badRequest().body("User not found");
        }
    }
}
