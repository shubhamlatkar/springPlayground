package com.spring_mvn.dependency_injection.interface_injection.using_xml_config;

public class OrderDAOImpl implements OrderDAO {
    public OrderDAOImpl() {
    }

    @Override
    public void createOrder() {
        System.out.println("Inside Order DAO Impl");
    }
}
