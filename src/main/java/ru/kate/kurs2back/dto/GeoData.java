package ru.kate.kurs2back.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
//формат данных с mosdata
@Data
@NoArgsConstructor
public class GeoData {
    private List<Double> coordinates;
    private String type;
}
