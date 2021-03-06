package ru.stud.kpfu.kalugin.dto;

import javax.validation.constraints.NotBlank;

public class CreateUserDto {

    @NotBlank(message = "Name shouldn't be blank!")
    private String name;

    @NotBlank(message = "Email shouldn't be blank!")
    private String email;

    @NotBlank(message = "Password shouldn't be blank!")
    private String password;

    public CreateUserDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public CreateUserDto(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
