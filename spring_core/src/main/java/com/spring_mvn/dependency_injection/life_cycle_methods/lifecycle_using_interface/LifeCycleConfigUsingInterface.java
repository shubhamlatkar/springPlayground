package com.spring_mvn.dependency_injection.life_cycle_methods.lifecycle_using_interface;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LifeCycleConfigUsingInterface {
    public static void main(String[] args) {
//        ApplicationContext  context = new ClassPathXmlApplicationContext("dependency_injection/lifecycle_methods/lifecycle_config.xml");
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("dependency_injection/lifecycle_methods/lifecycle_config_using_interface.xml");
        Employee emp = (Employee) context.getBean("employee");
        System.out.println(emp);
        context.registerShutdownHook();
    }
}
