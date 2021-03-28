package com.spring_mvn.dependency_injection.constructor_injection;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ConstructorInjectionMain {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("dependency_injection/constructor_injection/constructor_injection_config.xml");
        Hospital hospital = (Hospital) context.getBean("hospital");
        System.out.println(hospital);
    }
}
