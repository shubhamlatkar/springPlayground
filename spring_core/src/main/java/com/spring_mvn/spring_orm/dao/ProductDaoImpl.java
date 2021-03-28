package com.spring_mvn.spring_orm.dao;

import com.spring_mvn.spring_orm.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component("product")
public class ProductDaoImpl implements ProductDao {
    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Override
    @Transactional
    public int create(Product product) {
        return (Integer) hibernateTemplate.save(product);
    }

    @Override
    @Transactional
    public void update(Product product) {
        hibernateTemplate.update(product);
    }

    @Override
    @Transactional
    public void delete(Product product) {
        hibernateTemplate.delete(product);
    }

    @Override
    public Product find(int id) {
        return (Product) hibernateTemplate.get(Product.class, id);
    }

    @Override
    public List<Product> findAll() {
        return hibernateTemplate.loadAll(Product.class);
    }
}
