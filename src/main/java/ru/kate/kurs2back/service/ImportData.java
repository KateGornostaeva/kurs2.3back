package ru.kate.kurs2back.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.kate.kurs2back.dto.MosData;

import java.util.Arrays;
import java.util.List;

@Service
public class ImportData {
    
    private final static String url = "https://apidata.mos.ru/v1/datasets/2457/rows?api_key=4e8b32409a3d3ae780d3cac89581df7c&projection=null&$top=3";
    
    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper().disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    
    public List<MosData> get(){
        ResponseEntity<MosData[]> response = restTemplate.getForEntity(url, MosData[].class);
        List<MosData> mosDataList = Arrays.stream(response.getBody()).toList();
        return mosDataList;
    }
}
