package ru.stud.kpfu.kalugin.controller.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import ru.stud.kpfu.kalugin.config.MailConfig;
import ru.stud.kpfu.kalugin.dto.UserDto;
import ru.stud.kpfu.kalugin.model.User;
import ru.stud.kpfu.kalugin.repository.UserRepository;
import ru.stud.kpfu.kalugin.service.UserService;
import ru.stud.kpfu.kalugin.service.impl.UserServiceImpl;

import java.util.List;

@RunWith(SpringRunner.class)
public class UserServiceTest {

    @TestConfiguration
    static class UserServiceTestContextConfiguration {

        @MockBean
        private UserRepository userRepository;

        @MockBean
        private BCryptPasswordEncoder encoder;

        @MockBean
        private JavaMailSender javaMailSender;

        @MockBean
        private MailConfig mailConfig;


        @Bean
        public UserService userService() {
            return new UserServiceImpl(userRepository, encoder, javaMailSender, mailConfig);
        }
    }

    @Autowired
    private UserService userService;


    @Test
    public void testGetAll() {
        List<UserDto> result = userService.getAll();
        Assert.assertNotNull(result);
        Assert.assertTrue(result.isEmpty());
    }

    @Test
    public void testGetByEmail() {
        User result = userService.getByEmail("test@mail.ru");
        Assert.assertNull(result);
    }

    @Test
    public void testGetById() {
        UserDto result = userService.getById(1);
        Assert.assertNotNull(result);
        Assert.assertEquals("ivan", result.getName());
    }
}
