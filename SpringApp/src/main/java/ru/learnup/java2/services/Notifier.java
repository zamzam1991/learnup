package ru.learnup.java2.services;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.util.Arrays;

@Aspect
@Component
public class Notifier {
    @Pointcut("@annotation(ru.learnup.java2.annotations.Notifable)")
    public void notifyByEmail() {}

    @AfterReturning("notifyByEmail()")
    public void beforeNotifyByEmail(JoinPoint point) {
        OperaEvent operaEvent = (OperaEvent) point.getArgs()[0];
        try {
            if ((int) point.getArgs()[1] > 0)
                System.out.println("Вы приобрели билет на оперу " + operaEvent.getName() + ". Номер Вашего места в зале - " + point.getArgs()[1]);
        } catch (ClassCastException e) {
            System.out.println("Вы приобрели билеты на оперу " + operaEvent.getName() + ". Номера Ваших мест в зале - " + Arrays.toString((int[])point.getArgs()[1]));
        }
    }
}
