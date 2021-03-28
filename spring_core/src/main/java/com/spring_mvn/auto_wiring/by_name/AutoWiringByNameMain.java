package com.spring_mvn.auto_wiring.by_name;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AutoWiringByNameMain {
    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("auto_wiring/by_name  _config.xml");
        Student student = (Student) context.getBean("student");
        System.out.println(student);
    }
}
