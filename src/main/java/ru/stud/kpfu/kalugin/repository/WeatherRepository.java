package ru.stud.kpfu.kalugin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.stud.kpfu.kalugin.model.Weather;

public interface WeatherRepository extends JpaRepository<Weather, Integer> {
}
