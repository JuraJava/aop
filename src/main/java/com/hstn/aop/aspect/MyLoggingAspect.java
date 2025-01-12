package com.hstn.aop.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyLoggingAspect {

    @Pointcut("execution(* com.hstn.aop.dao.*.*(..))")
    private void pointcutForMethods() {
    }

    @Pointcut("execution(* com.hstn.aop.dao.*.get*(..))")
    private void pointcutForGetter() {
    }

    @Pointcut("execution(* com.hstn.aop.dao.*.set*(..))")
    private void pointcutForSetter() {
    }

    @Pointcut("pointcutForSetter() || pointcutForGetter()")
    private void pointcutForSetterAndGetter() {
    }

    @Pointcut("pointcutForMethods() && !(pointcutForSetter() || pointcutForGetter())")
    private void pointcutNotForSetterAndGetter() {
    }

//    @Before("pointcutForMethods()")
//    @Before("pointcutForGetter()")
//    @Before("pointcutForSetter()")
//    @Before("pointcutForSetterAndGetter()")
    @Before("pointcutNotForSetterAndGetter()")
    public void beforeAddUserData() {
        System.out.println("    1 Before");
    }

//    @Before("pointcutForMethods()")
//    public void beforeAdmin() {
//        System.out.println("    3 Before");
//    }
}
