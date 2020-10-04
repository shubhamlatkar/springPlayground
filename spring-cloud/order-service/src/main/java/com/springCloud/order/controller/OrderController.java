package com.springCloud.order.controller;

import com.springCloud.order.model.Orders;
import com.springCloud.order.model.dto.Order;
import com.springCloud.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Value("${greeting}")
    private String greeting;

    private final OrderRepository orderRepository;
    @Autowired
    private RestTemplate restTemplate;

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
    public ResponseEntity<?> postOrder(@RequestBody Order order) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        String username = restTemplate.exchange("http://USER-SERVICE-CLOUD/user/getName/" + order.getUserId(), HttpMethod.GET, entity, String.class).getBody();
        return ResponseEntity.ok(orderRepository.save(new Orders(order.getUserId(), username, order.getDesc())));
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
