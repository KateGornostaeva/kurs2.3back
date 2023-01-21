package ru.kate.kurs2back.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.kate.kurs2back.entity.AirData;
import ru.kate.kurs2back.repository.AirDataRepository;

import java.util.List;
//endpoint для получения данных качества воздуха
@Slf4j
@RestController
@RequestMapping("/data")
public class AirDataController {
    
    private final AirDataRepository repository;
    
    public AirDataController(AirDataRepository repository) {
        this.repository = repository;
    }
    
    @GetMapping("/area")
    public ResponseEntity<List<AirData>> getArea(@RequestParam Double lat,
                                                 @RequestParam Double lon,
                                                 @RequestParam Double size) {

        //для вычисление поля для отображения качества воздуха
        double minLat = lat - size / 2;
        double maxLat = lat + size / 2;
        double minLon = lon - size / 2;
        double maxLon = lon + size / 2;

        //получает список объектов из базы данных
        List<AirData> airDataList = repository.findAllInSquare(minLat, maxLat, minLon, maxLon);
        return ResponseEntity.ok(airDataList);
    }
}
