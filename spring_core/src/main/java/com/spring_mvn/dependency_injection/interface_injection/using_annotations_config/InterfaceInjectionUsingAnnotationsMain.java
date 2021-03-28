package com.spring_mvn.dependency_injection.interface_injection.using_annotations_config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class InterfaceInjectionUsingAnnotationsMain {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("dependency_injection/interface_injection/using_annotations_config/interface_injection_config_annotations.xml");
        User user = (User) context.getBean("userImpl");
        user.saveToDB();

    }
}
