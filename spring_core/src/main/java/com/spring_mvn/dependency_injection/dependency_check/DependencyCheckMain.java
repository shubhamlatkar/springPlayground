package com.spring_mvn.dependency_injection.dependency_check;

import com.spring_mvn.dependency_injection.setter_injection.list_injection.School;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DependencyCheckMain {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("dependency_injection/dependency_check/dependency_check.xml");
        Student student= (Student) context.getBean("student");
        System.out.println(student);
    }
}
