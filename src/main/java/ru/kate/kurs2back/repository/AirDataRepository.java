package ru.kate.kurs2back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kate.kurs2back.entity.AirData;

public interface AirDataRepository extends JpaRepository<AirData, Long> {
}
