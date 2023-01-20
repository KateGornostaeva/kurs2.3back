package ru.kate.kurs2back.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kate.kurs2back.dto.MosData;
import ru.kate.kurs2back.service.ImportData;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    
    private final ImportData importData;
    
    public UserController(ImportData importData) {
        this.importData = importData;
    }
    
    @GetMapping("/test")
    public ResponseEntity<List<MosData>> test(){
        return ResponseEntity.ok(importData.get());
    }
}
