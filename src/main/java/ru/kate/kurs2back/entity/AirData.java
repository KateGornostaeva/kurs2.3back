package ru.kate.kurs2back.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDate;
//структура данных в бд
@Data
@Entity
@Table(name = "data")
public class AirData {
    @Id
    private Long id;
    private LocalDate date;
    private String admArea;
    private String district;
    private String location;
    private String result;
    private Double lon;
    private Double lat;
}
