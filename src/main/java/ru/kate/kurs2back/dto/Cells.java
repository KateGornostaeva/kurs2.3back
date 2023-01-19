package ru.kate.kurs2back.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class Cells {
    
    @JsonProperty("Date")
    private String date;
    
    private Long global_id;
    
    @JsonProperty("AdmArea")
    private String admArea;
    
    @JsonProperty("District")
    private String district;
    
    @JsonProperty("Location")
    private String location;
    
    @JsonProperty("Results")
    private String results;
    
    private GeoData geoData;
    
    
}
