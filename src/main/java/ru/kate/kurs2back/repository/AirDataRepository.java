package ru.kate.kurs2back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.kate.kurs2back.entity.AirData;

import java.util.List;
//для получения данных из бд
public interface AirDataRepository extends JpaRepository<AirData, Long> {
    //отображение данных на карте (смотрим какой участок видно на карте и именно там показываем данные)
    @Query(nativeQuery = true,
        value = "SELECT * FROM data WHERE data.lat > ? AND data.lat < ? AND data.lon > ? AND data.lon < ?")
    List<AirData> findAllInSquare(double minLat, double maxLat, double minLon, double maxLon);
}
