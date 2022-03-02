package ru.stud.kpfu.kalugin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.stud.kpfu.kalugin.model.Appeal;

import java.util.List;

public interface AppealRepository extends JpaRepository<Appeal, Integer> {
    List<Appeal> getAppealsByUserId(Integer id);

    List<Appeal> getAppealsByWeatherCityIgnoreCase(String city);
}
