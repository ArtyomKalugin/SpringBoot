package ru.stud.kpfu.kalugin.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ru.stud.kpfu.kalugin.dto.UserDto;

import java.util.Optional;


@Component
@Aspect
public class LoggingAspect {

    public static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);

//    @Pointcut("execution(* ru.stud.kpfu.kalugin.controller.WeatherController.getWeather())")
//    public void logCityRequest() {
//    }

    @Pointcut("@annotation(Loggable)")
    public void logCityRequest() {

    }

    @After("logCityRequest()")
    public Object logAllMethods(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Optional<String> city = (Optional<String>) proceedingJoinPoint.getArgs()[0];

        Object result = proceedingJoinPoint.proceed();

        LOGGER.info("City request: {}", city.get());

        return result;
    }
}
