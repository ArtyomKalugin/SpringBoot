package ru.stud.kpfu.kalugin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.stud.kpfu.kalugin.model.Weather;

import java.util.List;

public interface WeatherRepository extends JpaRepository<Weather, Integer> {
    List<Weather> getWeathersByCityIgnoreCase(String city);
}
