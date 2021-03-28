package com.spring_mvn.dependency_injection.setter_injection.set_injection;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SetInjectionMain {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("dependency_injection/setter_injection/set_injection_config.xml");
        Hospital hospital = (Hospital) context.getBean("hospital");
        System.out.println(hospital);
    }
}
