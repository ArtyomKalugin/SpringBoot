package ru.stud.kpfu.kalugin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.stud.kpfu.kalugin.dto.UserDto;
import ru.stud.kpfu.kalugin.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    UserDto findByEmail(String email);
}
