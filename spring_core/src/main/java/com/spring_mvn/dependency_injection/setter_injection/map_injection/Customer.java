package com.spring_mvn.dependency_injection.setter_injection.map_injection;

import java.util.Map;

public class Customer {
    private String name;
    private Map<Integer, String> products;

    public Customer() {
    }

    public Customer(String name, Map<Integer, String> products) {
        this.name = name;
        this.products = products;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<Integer, String> getProducts() {
        return products;
    }

    public void setProducts(Map<Integer, String> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", products=" + products +
                '}';
    }
}
