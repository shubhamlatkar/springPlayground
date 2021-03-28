package com.spring_mvn.dependency_injection.interface_injection.using_xml_config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class InterfaceInjectionMain {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("dependency_injection/interface_injection/using_xml_config/interface_injection_config.xml");
        OrderBO orderBO = (OrderBO) context.getBean("orderBO");
        orderBO.placeOrder();
    }
}
