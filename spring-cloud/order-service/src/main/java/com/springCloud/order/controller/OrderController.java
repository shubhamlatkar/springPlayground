package com.springCloud.order.controller;

import com.springCloud.order.model.Orders;
import com.springCloud.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Value("${greeting}")
    private String greeting;

    private final OrderRepository orderRepository;

    @Autowired
    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    @GetMapping("/")
    public ResponseEntity<List<Orders>> getDefault() {
        return ResponseEntity.ok(orderRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Orders>> getByUserId(@PathVariable Long id) {
        return ResponseEntity.ok(orderRepository.findByUserId(id));
    }

    @PostMapping("/")
    public ResponseEntity<?> postOrder(@RequestBody Orders order) {
        return ResponseEntity.ok(orderRepository.save(order));
    }

    @PutMapping("/")
    public ResponseEntity<?> putOrder(@RequestBody Orders order) {
        Orders found = orderRepository.findById(order.getId()).orElse(null);
        if (found != null) {
            found.setDesc(order.getDesc());
            found.setUserId(order.getUserId());
            return ResponseEntity.ok(orderRepository.save(found));
        } else
            return ResponseEntity.ok(orderRepository.save(order));
    }

    @DeleteMapping("/")
    public ResponseEntity<?> deleteOrder(@RequestBody Orders order) {
        orderRepository.delete(order);
        return ResponseEntity.ok("Deleted....");
    }

    @GetMapping("/greeting")
    public ResponseEntity<?> getGreeting() {
        return ResponseEntity.ok(greeting);
    }
}
