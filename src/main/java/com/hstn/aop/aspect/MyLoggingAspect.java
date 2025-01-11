package com.hstn.aop.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyLoggingAspect {

    @Before("execution(public void addUserData())")
// Эта аннотация показывает перед каким методом будет вызван этот метод,
// в параметрах этой аннотации указывается точка входа и сам тот метод
// @Before - это эдвайс, а то, что дальше поинт кат
    public void beforeAddUserData() {
        System.out.println("111111111111111111111\nBefore addUserData");
    }
}
