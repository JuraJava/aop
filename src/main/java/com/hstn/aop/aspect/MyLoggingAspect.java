package com.hstn.aop.aspect;

import com.hstn.aop.Admin;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
@Order(10)
// С помощью этой аннотации устанавливается порядок вызова
// методов, помеченных @Aspect (не только методов этого класса)
// В параметре аннотации может быть и отрицательное число
// А если в параметрах аннотации @Order двух или более методов
// будет указано одно число, то эти методы будут вызываться в естественном порядке
public class MyLoggingAspect {

    @Around("execution(* getCr*(..))")
    public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        // ProceedingJoinPoint необходимо только для метода, помеченного @Around

        String method = joinPoint.getSignature().getName();
        System.out.println("    Around = " + method);

        long startTime = System.currentTimeMillis();

        Object result = joinPoint.proceed();
        // Это необходимо только для метода, помеченного @Around

        long duration = System.currentTimeMillis() - startTime;
        System.out.println("    Around duration: = " + duration);

        return result;
        // Это необходимо только для метода, помеченного @Around
    }
    // Этот метод (который выше) срабатывает до методов, указанных
    // в параметрах, и после

    @After("execution(* find*(..))")
    public void afterAdvice(JoinPoint joinPoint) {
        String method = joinPoint.getSignature().getName();
        System.out.println("    After = " + method);
    }
    // Этот метод (который выше) срабатывает если методы, указанные
    // в параметрах, либо завершаются успешно, либо если выбрасывают exeption

    @AfterThrowing(pointcut = "execution(* find*(..))",
            throwing = "exeption")
    public void afterThrowing(JoinPoint joinPoint, Throwable exeption) {

        String method = joinPoint.getSignature().getName();
        System.out.println("    AfterThrowing = " + method);
        System.out.println("    AfterThrowing exeption = " + exeption.getMessage());
    }
    // Этот метод (который выше) срабатывает если методы, указанные
    // в параметрах, выбросили какие-то exeption

    @AfterReturning(pointcut = "execution(* find*(..))",
            returning = "result")
    public void afterReturning(JoinPoint joinPoint, List<Admin> result) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("    AfterReturning methodName = " + methodName);
        System.out.println("    AfterReturning result = " + result);

        // Далее мы можем не просто получать результат,
        // но и перед выводом как-то его изменять

        changeResult(result);
    }
    // Этот метод (который выше) срабатывает если методы, указанные
    // в параметрах, успешно завершаются

    private void changeResult(List<Admin> result) {
        for (Admin admin : result) {
            admin.setName(admin.getName().toUpperCase());
        }
    }

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
