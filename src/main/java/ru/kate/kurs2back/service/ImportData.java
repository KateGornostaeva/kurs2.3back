package ru.kate.kurs2back.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.kate.kurs2back.dto.Cells;
import ru.kate.kurs2back.dto.MosData;
import ru.kate.kurs2back.entity.AirData;
import ru.kate.kurs2back.repository.AirDataRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
//импорт данных из mosdata
@Service
public class ImportData {
    
    private static final String url = "https://apidata.mos.ru/v1/datasets/2457/rows?api_key=4e8b32409a3d3ae780d3cac89581df7c&projection=null";
    //для преобразовния даты в LocalDate
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
   //объект для подключения к mosdata
    private final RestTemplate restTemplate = new RestTemplate();
    
    private final AirDataRepository airDataRepository;
    
    public ImportData(AirDataRepository airDataRepository) {
        this.airDataRepository = airDataRepository;
    }
    
    public List<MosData> get() {
        //запросить данные из mosdata
        ResponseEntity<MosData[]> response = restTemplate.getForEntity(url, MosData[].class);
        //преобразовать из массива в лист
        List<MosData> mosDataList = Arrays.stream(response.getBody()).toList();
        
        List<AirData> airDataList = new ArrayList<>();
        //перекладываем данные из формата mosdata в наш формат
        for (MosData row : mosDataList) {
            AirData airData = new AirData();
            airData.setId(row.getGlobal_id());
            Cells cells = row.getCells();
            airData.setDate(convertDate(cells.getDate()));
            airData.setAdmArea(cells.getAdmArea());
            airData.setDistrict(cells.getDistrict());
            airData.setLocation(cells.getLocation());
            airData.setLon(cells.getGeoData().getCoordinates().get(0));
            airData.setLat(cells.getGeoData().getCoordinates().get(1));
            airData.setResult(convertResult(cells.getResults()));
            airDataList.add(airData);
        }
        //сохраняем все данные в бд за один раз
        airDataRepository.saveAllAndFlush(airDataList);
        return mosDataList;
    }

    //преобразование даты из формата mosdata в LocalDate
    private LocalDate convertDate(String date) {
        return LocalDate.parse(date, formatter);
    }

    //находим фразу в описании воздуха и помечаем 
    private String convertResult(String rawStr) {
        if (rawStr.contains("не превысило")) {
            return "GOOD";
        } else {
            return "BAD";
        }
    }
}
