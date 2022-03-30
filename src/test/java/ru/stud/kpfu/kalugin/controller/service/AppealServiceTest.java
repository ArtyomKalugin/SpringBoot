package ru.stud.kpfu.kalugin.controller.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import ru.stud.kpfu.kalugin.dto.AppealDto;
import ru.stud.kpfu.kalugin.model.Appeal;
import ru.stud.kpfu.kalugin.model.User;
import ru.stud.kpfu.kalugin.model.Weather;
import ru.stud.kpfu.kalugin.repository.AppealRepository;
import ru.stud.kpfu.kalugin.service.AppealService;
import ru.stud.kpfu.kalugin.service.impl.AppealServiceImpl;

import java.util.List;

@RunWith(SpringRunner.class)
public class AppealServiceTest {

    @TestConfiguration
    static class UserServiceTestContextConfiguration {

        @MockBean
        private AppealRepository appealRepository;

        @Bean
        public AppealService AppealService(AppealRepository appealRepository) {
            return new AppealServiceImpl(appealRepository);
        }
    }

    @Autowired
    private AppealService appealService;

    @Test
    public void testGetAppealsByUserId() {
        List<AppealDto> result = appealService.getAppealsByUserId(1);
        Assert.assertNotNull(result);
        Assert.assertTrue(result.isEmpty());
    }

//    @Test
//    public void testSave() {
//        User user = new User();
//        user.setEmail("test@mail.ru");
//        user.setName("Ivan");
//        user.setPassword("testTEST");
//        user.setVerificationCode("123");
//        user.setId(111);
//
//        Weather weather = new Weather();
//        weather.setEmail("test@mail.ru");
//        weather.setCity("Kazan");
//        weather.setId(111);
//        weather.setDescription("aa");
//        weather.setHumidity("11");
//        weather.setTemp("111");
//
//        Appeal appeal = new Appeal();
//        appeal.setDate("30.03.2022");
//        appeal.setUser(user);
//        appeal.setWeather(weather);
//        appeal.setId(111);
//
//        AppealDto result = appealService.save(appeal);
//        Assert.assertNotNull(result);
//    }

    @Test
    public void testGetAppealsByWeatherCity() {
        List<AppealDto> result = appealService.getAppealsByWeatherCity("Moscow");
        Assert.assertNotNull(result);
        Assert.assertTrue(result.isEmpty());
    }
}
