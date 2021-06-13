package com.spring_demo.reactive_demo.controller;

import com.spring_demo.reactive_demo.entity.Employee;
import com.spring_demo.reactive_demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@RestController
@CrossOrigin("*")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping(value = "/{dept}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ServerSentEvent<String>> getMovies(@PathVariable String dept) {
        return employeeRepository.findByDept(dept)
                .map(emp -> ServerSentEvent.<String>builder()
                        .id(emp.getId())
                        .event("notification")
                        .data(emp.toString())
                        .build())
                .subscribeOn(Schedulers.boundedElastic());
    }

    @PostMapping("/")
    public Mono<String> addEmployee(@RequestBody Employee employee) {
        return employeeRepository.save(employee).map(Employee::getId);
    }

}