package com.spring_mvn.dependency_injection.life_cycle_methods.lifecycle_using_xml;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LifeCycleConfigUsingXml {
    public static void main(String[] args) {
//        ApplicationContext  context = new ClassPathXmlApplicationContext("dependency_injection/lifecycle_methods/lifecycle_config.xml");
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("dependency_injection/lifecycle_methods/lifecycle_config.xml");
        Employee emp = (Employee) context.getBean("employee");
        System.out.println(emp);
        context.registerShutdownHook();
    }
}
