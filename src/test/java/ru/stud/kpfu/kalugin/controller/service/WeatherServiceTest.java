package ru.stud.kpfu.kalugin.controller.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import ru.stud.kpfu.kalugin.dto.WeatherDto;
import ru.stud.kpfu.kalugin.repository.WeatherRepository;
import ru.stud.kpfu.kalugin.service.WeatherService;
import ru.stud.kpfu.kalugin.service.impl.WeatherServiceImpl;

import java.util.List;

@RunWith(SpringRunner.class)
public class WeatherServiceTest {

    @TestConfiguration
    static class UserServiceTestContextConfiguration {

        @MockBean
        private WeatherRepository weatherRepository;

        @Bean
        public WeatherService WeatherService(WeatherRepository weatherRepository) {
            return new WeatherServiceImpl(weatherRepository);
        }
    }

    @Autowired
    private WeatherService weatherService;

    @Test
    public void testFindAll() {
        List<WeatherDto> result = weatherService.findAll();
        Assert.assertNotNull(result);
        Assert.assertTrue(result.isEmpty());
    }

    @Test
    public void testGetWeathersByCity() {
        List<WeatherDto> result = weatherService.getWeathersByCity("Moscow");
        Assert.assertNotNull(result);
        Assert.assertTrue(result.isEmpty());
    }
}
