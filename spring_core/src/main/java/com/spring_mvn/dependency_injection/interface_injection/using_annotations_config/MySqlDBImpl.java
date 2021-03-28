package com.spring_mvn.dependency_injection.interface_injection.using_annotations_config;

import org.springframework.stereotype.Component;

@Component("MySql")
public class MySqlDBImpl implements DB{
    public MySqlDBImpl() {
    }

    @Override
    public void insert() {
        System.out.println("Insert in to My sql DB");
    }
}
