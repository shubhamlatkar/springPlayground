package com.springCloud.order.controller;

import com.springCloud.order.model.Orders;
import com.springCloud.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping("/insert")
    public String insertData() {
        orderRepository.saveAll(Arrays.asList(new Orders(1l, "1 veg sandwich"), new Orders(2l, "1 cold coffee")));
        return "Inserted...";
    }


    @GetMapping("/")
    public ResponseEntity<List<Orders>> getDefault() {
        return ResponseEntity.ok(orderRepository.findAll());
    }
}
