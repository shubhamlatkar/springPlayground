package com.spring_mvn.dependency_injection.setter_injection.list_injection;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ListInjectionMain {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("dependency_injection/setter_injection/list_injection_config.xml");
        School school = (School) context.getBean("school");
        System.out.println(school);
    }
}
