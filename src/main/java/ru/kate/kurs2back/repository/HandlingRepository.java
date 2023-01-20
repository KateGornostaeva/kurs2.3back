package ru.kate.kurs2back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kate.kurs2back.entity.Handling;

public interface HandlingRepository extends JpaRepository<Handling, Long> {
}
