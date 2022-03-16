package ru.stud.kpfu.kalugin.service;

import ru.stud.kpfu.kalugin.dto.CreateUserDto;
import ru.stud.kpfu.kalugin.dto.UserDto;
import ru.stud.kpfu.kalugin.model.User;

import java.util.List;

public interface UserService {
    User getByEmail(String email);

    UserDto getById(Integer id);

    List<UserDto> getAll();

    UserDto save(CreateUserDto createUserDto, String url);

    boolean verify(String verificationCode);

    void sendVerificationMail(String mail, String name, String code, String url);
}
