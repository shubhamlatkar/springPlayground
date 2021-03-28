package com.spring_mvn.spring_jdbc;

import com.spring_mvn.spring_jdbc.dao.EmployeeDaoImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringJdbcMain {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring_jdbc/spring_jdbc_config.xml");
//        JdbcTemplate jdbcTemplate = (JdbcTemplate) context.getBean("jdbcTemplate");
//        String sql = "insert into Employee values(?,?,?)";
//        int result = jdbcTemplate.update(sql, 1, "test", "test");
//        System.out.println(result);

        EmployeeDaoImpl employeeDao = (EmployeeDaoImpl) context.getBean("employee");

//        System.out.println(employeeDao.create(new Employee(3, "Test3", "Test3")));

//        System.out.println(employeeDao.update(new Employee(2, "NEW_Test2", "NEW_Test2 ")));

//        System.out.println(employeeDao.read(2));

        System.out.println(employeeDao.realAll());
    }

}
