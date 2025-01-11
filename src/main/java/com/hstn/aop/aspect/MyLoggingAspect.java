package com.hstn.aop.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyLoggingAspect {

    @Pointcut("execution(public void add*())")
    private void pointcutForMethods() {
    }

    @Before("pointcutForMethods()")
// Эта аннотация показывает перед каким методом будет вызван этот метод,
// в параметрах этой аннотации указывается точка входа и сам тот метод
// @Before - это эдвайс, а то, что дальше point (точечный) cut (разрез)
    public void beforeAddUserData() {
        System.out.println("111111111111111111111\nBefore addUserData");
    }
}
