package com.hstn.aop.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(1)
// С помощью этой аннотации устанавливается порядок вызова
// методов, помеченных @Aspect (не только методов этого класса)
// В параметре аннотации может быть и отрицательное число
// А если в параметрах аннотации @Order двух или более методов
// будет указано одно число, то эти методы будут вызываться в естественном порядке
public class MyLoggingAspect {
    @Before("MyPointcutExpression.pointcutForMethods()")
    public void beforeAddUserData() {
        System.out.println("    1 Before");
    }
}
