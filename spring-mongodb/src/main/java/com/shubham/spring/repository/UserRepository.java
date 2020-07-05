package com.shubham.spring.repository;

import com.shubham.spring.document.Users;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<Users, Integer> {
}
