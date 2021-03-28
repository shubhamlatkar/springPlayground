package com.spring_mvn.dependency_injection.setter_injection.ref_injection;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class RefInjectionMain {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("dependency_injection/setter_injection/ref_injection_config.xml");
        Student student = (Student) context.getBean("student");
        System.out.println(student);
    }
}
