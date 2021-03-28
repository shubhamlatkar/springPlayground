package com.spring_mvn.dependency_injection.interface_injection.using_annotations_config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("userImpl")
public class UserImpl implements User{
    @Autowired
    @Qualifier("MongoImpl")
    private DB db;

    @Override
    public void saveToDB() {
        System.out.println("Saving data");
        db.insert();
    }

    public UserImpl() {
    }

    public UserImpl(DB db) {
        this.db = db;
    }

    public DB getDb() {
        return db;
    }

    public void setDb(DB db) {
        this.db = db;
    }

}
