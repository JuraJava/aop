package com.hstn.aop.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class MyPointcutExpression {

//    @Pointcut("execution(* com.hstn.aop.dao.*.*(..))")
    @Pointcut("execution(* com.hstn.aop.dao.*.test*(..))")
//    Это добавили, а предыдущее закомментировали чтобы при
//    изучении @AfterReturning ничего лишнего не отображалось
    public void pointcutForMethods() {
    }

    @Pointcut("execution(* com.hstn.aop.dao.*.get*(..))")
    public void pointcutForGetter() {
    }

    @Pointcut("execution(* com.hstn.aop.dao.*.set*(..))")
    public void pointcutForSetter() {
    }

    @Pointcut("pointcutForSetter() || pointcutForGetter()")
    public void pointcutForSetterAndGetter() {
    }

    @Pointcut("pointcutForMethods() && !(pointcutForSetter() || pointcutForGetter())")
    public void pointcutNotForSetterAndGetter() {
    }

    // Эти все методы которые выше мы перенесли из класса MyLoggingAspect, сделав их публичными
}
