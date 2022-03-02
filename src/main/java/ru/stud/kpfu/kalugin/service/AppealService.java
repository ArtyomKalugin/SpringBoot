package ru.stud.kpfu.kalugin.service;

import ru.stud.kpfu.kalugin.dto.AppealDto;
import ru.stud.kpfu.kalugin.model.Appeal;

import java.util.List;

public interface AppealService {

    AppealDto save(Appeal appeal);

    List<AppealDto> getAppealsByUserId(Integer id);

    List<AppealDto> getAppealsByWeatherCity(String city);
}
