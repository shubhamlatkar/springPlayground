package com.spring_mvn.stereotype_annotations;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class StereotypeAnnotationMain {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("stereotype_annotation/stereotype_annotation_config.xml");
        Student student = (Student)   context.getBean("student");
        System.out.println(student);
    }
}
