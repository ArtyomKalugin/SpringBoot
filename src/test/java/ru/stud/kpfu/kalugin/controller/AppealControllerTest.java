package ru.stud.kpfu.kalugin.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.stud.kpfu.kalugin.dto.AppealDto;
import ru.stud.kpfu.kalugin.model.Appeal;
import ru.stud.kpfu.kalugin.model.User;
import ru.stud.kpfu.kalugin.model.Weather;
import ru.stud.kpfu.kalugin.repository.AppealRepository;
import ru.stud.kpfu.kalugin.service.AppealService;

import java.util.Arrays;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(AppealController.class)
public class AppealControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AppealService appealService;

    @MockBean
    private AppealRepository appealRepository;

    @Before
    public void init() {
        User user = new User();
        user.setEmail("test@mail.ru");
        user.setName("Ivan");
        user.setPassword("testTEST");
        user.setVerificationCode("123");

        User user2 = new User();
        user2.setEmail("test2@mail.ru");
        user2.setName("Ivan");
        user2.setPassword("testTEST");
        user2.setVerificationCode("1234");

        Weather weather = new Weather();
        weather.setEmail("test@mail.ru");
        weather.setCity("Kazan");

        Weather weather2 = new Weather();
        weather2.setEmail("test2@mail.ru");
        weather2.setCity("Moscow");

        Appeal appeal = new Appeal();
        appeal.setDate("30.03.2022");
        appeal.setUser(user);
        appeal.setWeather(weather);

        Appeal appeal2 = new Appeal();
        appeal2.setDate("29.03.2022");
        appeal2.setUser(user2);
        appeal2.setWeather(weather2);

        given(appealService.getAppealsByUserId(1)).willReturn(Arrays.asList(AppealDto.fromModel(appeal)));
    }

    @Test
    public void testGetAppealsByUserId() throws Exception {
        mockMvc.perform(get("/appeals/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)));
//                .andExpect(jsonPath("$[0].").value("testName"));
    }


}
