package ru.stud.kpfu.kalugin.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.stud.kpfu.kalugin.aspect.Loggable;
import ru.stud.kpfu.kalugin.dto.WeatherDto;
import ru.stud.kpfu.kalugin.helper.JsonHelper;
import ru.stud.kpfu.kalugin.model.Appeal;
import ru.stud.kpfu.kalugin.model.User;
import ru.stud.kpfu.kalugin.model.Weather;
import ru.stud.kpfu.kalugin.service.AppealService;
import ru.stud.kpfu.kalugin.service.HttpWeatherService;
import ru.stud.kpfu.kalugin.service.UserService;
import ru.stud.kpfu.kalugin.service.WeatherService;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class WeatherController {

    private final HttpWeatherService httpWeatherService = new HttpWeatherService();
    private final static JsonHelper jsonHelper = new JsonHelper();
    private final UserService userService;
    private final WeatherService weatherService;
    private final AppealService appealService;

    @Autowired
    public WeatherController(UserService userService, WeatherService weatherService, AppealService appealService) {
        this.userService = userService;
        this.weatherService = weatherService;
        this.appealService = appealService;
    }

    @Operation(summary = "Returns all weather history")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Weather was get",
            content = {@Content(mediaType = "application/json")})})
    @GetMapping("/allWeather")
    public Iterable<WeatherDto> getAll() {
        return weatherService.findAll();
    }

    @Operation(summary = "Returns all weather history by city")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Weather was get",
            content = {@Content(mediaType = "application/json")})})
    @GetMapping("/history/weather/{city}")
    public List<WeatherDto> getWeatherByCity(@PathVariable String city) {
        return weatherService.getWeathersByCity(city);
    }

    @Operation(summary = "Returns weather by city")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Weather was get",
            content = {@Content(mediaType = "application/json")})})
    @Loggable
    @GetMapping("/weather")
    public String getWeather(@RequestParam Optional<String> city, Authentication authentication)
            throws IOException {
        try {
            String email = authentication.getName();

            User user = userService.getByEmail(email);
            String result = httpWeatherService.get(city.orElse("Kazan"));

            if (result != null) {
                Map<String, String> params = jsonHelper.parseJson(result);
                Weather weather = new Weather(params.get("description"), params.get("humidity"),
                        params.get("temp"), params.get("name"), email);
                weatherService.save(weather);

                LocalDateTime dateTime = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

                Appeal appeal = new Appeal(dateTime.format(formatter), weather, user);
                appealService.save(appeal);

                return result;
            } else {
                return "This city is not found!";
            }
        } catch (NullPointerException e) {
            return "null";
        }
    }
}
