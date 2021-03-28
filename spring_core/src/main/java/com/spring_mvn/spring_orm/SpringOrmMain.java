package com.spring_mvn.spring_orm;

import com.spring_mvn.spring_orm.dao.ProductDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringOrmMain {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring_hibernate/spring_hibernate_config.xml");
        ProductDao productDao = (ProductDao) context.getBean("product");
//        System.out.println(productDao.create(new Product(1, "toy1", "test toy", 12.2)));
//        productDao.update(new Product(1, "toy1", "test toy", 13.2));
//        productDao.delete(new Product(1, "toy1", "test toy", 13.2));
//        System.out.println(productDao.find(1));
        System.out.println(productDao.findAll());

    }
}
