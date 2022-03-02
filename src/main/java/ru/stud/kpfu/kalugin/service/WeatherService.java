package ru.stud.kpfu.kalugin.service;

import ru.stud.kpfu.kalugin.dto.WeatherDto;
import ru.stud.kpfu.kalugin.model.Weather;

import java.util.List;

public interface WeatherService {
    List<WeatherDto> findAll();

    WeatherDto save(Weather weather);

    List<WeatherDto> getWeathersByCity(String city);
}
