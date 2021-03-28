package com.spring_mvn.dependency_injection.interface_injection.using_xml_config;

public class OrderDAOImplNew implements OrderDAO {
    @Override
    public void createOrder() {
        System.out.println("New order DAO impl");
    }
}
