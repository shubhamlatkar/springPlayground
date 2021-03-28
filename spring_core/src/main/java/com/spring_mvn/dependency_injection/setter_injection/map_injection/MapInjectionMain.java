package com.spring_mvn.dependency_injection.setter_injection.map_injection;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MapInjectionMain {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("dependency_injection/setter_injection/map_injection_config.xml");
        Customer customer = (Customer) context.getBean("customer");
        System.out.println(customer);
    }
}
