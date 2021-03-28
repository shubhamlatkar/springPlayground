package com.spring_mvn.dependency_injection.setter_injection.property_injection;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SetterInjectionMain {
    public static void main(String[] args) {
        ApplicationContext  context = new ClassPathXmlApplicationContext("dependency_injection/setter_injection/property_injection_config.xml");
        Employee emp = (Employee) context.getBean("employee");
        System.out.println(emp);
    }
}
