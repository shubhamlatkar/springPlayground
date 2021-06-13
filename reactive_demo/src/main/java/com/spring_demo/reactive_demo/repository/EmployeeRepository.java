package com.spring_demo.reactive_demo.repository;

import com.spring_demo.reactive_demo.entity.Employee;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.Tailable;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface EmployeeRepository extends ReactiveMongoRepository<Employee, String> {

    @Tailable
    public Flux<Employee> findByDept(String dept);
}
