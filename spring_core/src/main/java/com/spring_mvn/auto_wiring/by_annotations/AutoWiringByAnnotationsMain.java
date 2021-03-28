package com.spring_mvn.auto_wiring.by_annotations;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AutoWiringByAnnotationsMain {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("auto_wiring/by_annotations_config.xml");
        Student student = (Student) context.getBean("student");
        System.out.println(student);
    }
}
