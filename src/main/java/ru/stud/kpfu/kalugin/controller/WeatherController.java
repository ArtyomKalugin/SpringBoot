package ru.stud.kpfu.kalugin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.stud.kpfu.kalugin.service.HttpWeatherService;

import java.io.IOException;
import java.util.Optional;

@RestController
public class WeatherController {
    private HttpWeatherService httpWeatherService = new HttpWeatherService();

    @GetMapping("/weather")
    public String hello(@RequestParam Optional<String> city) throws IOException {
        String result = httpWeatherService.get(city.orElse("Kazan"));

        if (result != null) {
            return result;
        } else {
            return "This city is not found!";
        }
    }
}
