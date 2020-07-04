package com.shubham.spring.repository;

import com.shubham.spring.model.User;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, Long> {
}