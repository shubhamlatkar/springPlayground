package com.spring_mvn.auto_wiring.by_constructor;

import com.spring_mvn.auto_wiring.by_name.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AutoWiringByConstructor {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("auto_wiring/by_constructor_config.xml");
        Student student = (Student) context.getBean("student");
        System.out.println(student);
    }
}
