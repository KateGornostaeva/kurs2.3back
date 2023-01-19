package ru.kate.kurs2back.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class GeoData {
    private List<Double> coordinates;
    private String type;
}
