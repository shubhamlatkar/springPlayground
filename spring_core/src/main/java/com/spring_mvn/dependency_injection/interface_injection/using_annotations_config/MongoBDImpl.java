package com.spring_mvn.dependency_injection.interface_injection.using_annotations_config;

import org.springframework.stereotype.Component;

@Component("MongoImpl")
public class MongoBDImpl implements DB {
    public MongoBDImpl() {
    }

    @Override
    public void insert() {
        System.out.println("Inserting in mongo db");
    }
}
