package com.springCloud.order.repository;

import com.springCloud.order.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Orders, Long> {
    public List<OrderRepository> findByUserId(Long userId);
}
