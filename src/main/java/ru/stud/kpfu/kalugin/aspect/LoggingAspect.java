package ru.stud.kpfu.kalugin.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ru.stud.kpfu.kalugin.dto.UserDto;


@Component
@Aspect
public class LoggingAspect {

    public static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);

//    @Pointcut("execution(* ru.stud.kpfu.kalugin.controller.WeatherController.getWeather())")
//    public void logUserEmail() {
//    }

    @Pointcut("@annotation(Loggable)")
    public void logUserEmail() {

    }

    @Around("logUserEmail()")
    public Object logAllMethods(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        UserDto user = (UserDto) proceedingJoinPoint.getArgs()[1];

        Object result = proceedingJoinPoint.proceed();

        LOGGER.info("User: {}", user.getEmail());

        return result;
    }
}
