package ru.geekbrains.demo.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Component
@Aspect
public class TimerAspect {
    @Pointcut("within(@ru.geekbrains.demo.aspect.TimerAnnotation *)")
    public void timerClassPointcut() {

    }

    @Pointcut("@annotation(ru.geekbrains.demo.aspect.TimerAnnotation)")
    public void timerMethodPointcut() {
    }

    @Around("timerMethodPointcut() || timerClassPointcut()")
    public Object timerPointcut(ProceedingJoinPoint joinPoint) throws Throwable {

        long start = System.currentTimeMillis();
        Object returnValue;
        try {
            returnValue = joinPoint.proceed();
        } finally {
            long elapsedTime = System.currentTimeMillis() - start;
            log.info("{} - {} # ({})", joinPoint.getTarget().getClass().getName(),
                    joinPoint.getSignature().getName(), elapsedTime);
        }
        return returnValue;
    }

}
