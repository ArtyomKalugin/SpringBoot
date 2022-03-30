package ru.stud.kpfu.kalugin.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.stud.kpfu.kalugin.dto.WeatherDto;
import ru.stud.kpfu.kalugin.model.Weather;
import ru.stud.kpfu.kalugin.repository.AppealRepository;
import ru.stud.kpfu.kalugin.repository.UserRepository;
import ru.stud.kpfu.kalugin.repository.WeatherRepository;
import ru.stud.kpfu.kalugin.service.AppealService;
import ru.stud.kpfu.kalugin.service.UserService;
import ru.stud.kpfu.kalugin.service.WeatherService;

import java.util.Arrays;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(WeatherController.class)
public class WeatherControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WeatherService weatherService;

    @MockBean
    private WeatherRepository weatherRepository;

    @MockBean
    private UserService userService;

    @MockBean
    private AppealService appealService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private AppealRepository appealRepository;

    @Before
    public void init() {
        Weather weather = new Weather();
        weather.setEmail("test@mail.ru");
        weather.setCity("Kazan");

        Weather weather2 = new Weather();
        weather2.setEmail("test2@mail.ru");
        weather2.setCity("Moscow");

        given(weatherService.findAll()).willReturn(Arrays.asList(WeatherDto.fromModel(weather),
                WeatherDto.fromModel(weather2)));
        given(weatherService.getWeathersByCity("Moscow")).willReturn(Arrays.asList(WeatherDto.fromModel(weather2)));
    }

    @Test
    public void testGetAll() throws Exception {
        mockMvc.perform(get("/allWeather")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].city").value("Kazan"));
    }

    @Test
    public void testGetWeatherByCity() throws Exception {
        mockMvc.perform(get("/history/weather/Moscow")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].city").value("Moscow"));
    }

}
