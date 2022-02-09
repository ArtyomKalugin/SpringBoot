package ru.stud.kpfu.kalugin.repository;

import org.springframework.data.repository.CrudRepository;
import ru.stud.kpfu.kalugin.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {
}
