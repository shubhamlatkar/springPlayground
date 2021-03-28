package com.spring_mvn.dependency_injection.properties_injection;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PropertiesInjectionMain {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext(" properties_injection_config.xml");
        DBConfig dbConfig = (DBConfig) context.getBean("DBConfig");
        System.out.println(dbConfig);
    }
}
