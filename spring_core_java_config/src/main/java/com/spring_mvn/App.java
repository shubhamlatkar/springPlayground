package com.spring_mvn;

import com.spring_mvn.config.SpringConfig;
import com.spring_mvn.dao.EmployeeDao;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        EmployeeDao employeeDao = (EmployeeDao) context.getBean(EmployeeDao.class);
        employeeDao.create();
        context.close();
    }
}
