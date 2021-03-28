package com.spring_mvn.dependency_injection.bean_scope;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanScopeMain {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("dependency_injection/bean_scope/bean_scope_config.xml");
        Student student1 = (Student) context.getBean("student");
        System.out.println(student1.hashCode());

        Student student2 = (Student) context.getBean("student");
        System.out.println(student2.hashCode());
    }
}
