package ru.kate.kurs2back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.kate.kurs2back.entity.Handling;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

//для получения данных и бд
public interface HandlingRepository extends JpaRepository<Handling, UUID> {

    Optional<Handling> findByIdAndUserId(UUID handlingId, UUID userId);

    @Query(nativeQuery = true,
    value = "SELECT * FROM handling WHERE user_id = ?;")
    List<Handling> findAllByUserId(UUID userId);

}
