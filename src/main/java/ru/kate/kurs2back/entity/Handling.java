package ru.kate.kurs2back.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

//структура данных в бд
@Data
@Entity
@Table(name = "handling")
public class Handling {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
    private String message;
    private String type;
    private LocalDate date;
}
