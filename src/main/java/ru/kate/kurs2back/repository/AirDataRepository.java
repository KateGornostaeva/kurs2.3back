package ru.kate.kurs2back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.kate.kurs2back.entity.AirData;

import java.util.List;

public interface AirDataRepository extends JpaRepository<AirData, Long> {
    
    @Query(nativeQuery = true,
        value = "SELECT * FROM data WHERE data.lat > ? AND data.lat < ? AND data.lon > ? AND data.lon < ?")
    List<AirData> findAllInSquare(double minLat, double maxLat, double minLon, double maxLon);
}
