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
import java.util.List;
import java.util.Optional;
import java.util.UUID;

//endpoint для отправки заявок
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

    @GetMapping("/get")
    public ResponseEntity<List<Handling>> get(@RequestParam UUID userId) {
        return ResponseEntity.ok(handlingRepository.findAllByUserId(userId));
    }

    @GetMapping("/send")
    public ResponseEntity<String> send(@RequestParam UUID userId,
                                       @RequestParam String message,
                                       @RequestParam String type) {
        Optional<User> optional = userRepository.findById(userId);//проверка пользователя на существование в бд
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

    @GetMapping("/update")
    public ResponseEntity<String> update(@RequestParam UUID userId,
                                         @RequestParam UUID handlingId,
                                         @RequestParam String message,
                                         @RequestParam String type) {
        Optional<User> optional = userRepository.findById(userId);//проверка пользователя на существование в бд
        if (optional.isPresent()) {
            User user = optional.get();
            Optional<Handling> optional1 = handlingRepository.findByIdAndUserId(handlingId, userId);
            if (optional.isPresent()) {
                Handling handling = optional1.get();
                handling.setDate(LocalDate.now());
                handling.setType(type);
                handling.setMessage(message);
                handlingRepository.save(handling);
                return ResponseEntity.ok("Ok");
            }
        }
        return ResponseEntity.badRequest().body("User not found");
    }

    @GetMapping("/delete")
    public ResponseEntity<String> delete(@RequestParam UUID hadlingId) {
        handlingRepository.deleteById(hadlingId);
        return ResponseEntity.ok("Ok");
    }
}
