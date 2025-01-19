package com.hstn.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(10)
// С помощью этой аннотации устанавливается порядок вызова
// методов, помеченных @Aspect (не только методов этого класса)
// В параметре аннотации может быть и отрицательное число
// А если в параметрах аннотации @Order двух или более методов
// будет указано одно число, то эти методы будут вызываться в естественном порядке
public class MyLoggingAspect {

    @Before("MyPointcutExpression.pointcutForMethods()")
    public void beforeAddUserData(JoinPoint joinPoint) {
// Мы добавили в этот метод в качестве аргументов JoinPoint joinPoint чтобы через него
// получать информацию о методе
        System.out.println("    1 Before");

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        System.out.println("        Method: " + methodSignature);

        Object[] args = joinPoint.getArgs();
        // Так как аргументы в методах, о которых мы получаем информацию могут быть разных типов
        // мы объявляем именно массив Object[]
        for (Object arg : args) {
            System.out.println("        args: " + arg.toString());
        }
    }
}
