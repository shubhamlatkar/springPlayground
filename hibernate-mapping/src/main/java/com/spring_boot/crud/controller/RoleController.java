package com.spring_boot.crud.controller;

import com.spring_boot.crud.entity.Employee;
import com.spring_boot.crud.entity.Role;
import com.spring_boot.crud.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RoleController {

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("/role")
    public ResponseEntity<?> getRoles() {
        return new ResponseEntity<List<Role>>(roleRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/role/employees/{name}")
    public ResponseEntity<?> getEmployeesByRole(@PathVariable String name) {
        Role role = roleRepository.findByRole(name);
        if (role != null)
            return new ResponseEntity<List<Employee>>(role.getEmployees(), HttpStatus.OK);
        else
            return new ResponseEntity<String>("Not Found", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/role/{name}")
    public ResponseEntity<?> addRole(@PathVariable String name) {
        roleRepository.save(new Role(name));
        return new ResponseEntity<List<Role>>(roleRepository.findAll(), HttpStatus.OK);
    }

    @DeleteMapping("/role/{name}")
    public ResponseEntity<?> deleteRole(@PathVariable String name) {
        Role role = roleRepository.findByRole(name);
        if (role != null) {
            roleRepository.delete(role);
            return new ResponseEntity<List<Role>>(roleRepository.findAll(), HttpStatus.OK);
        } else
            return new ResponseEntity<String>("Not Found", HttpStatus.NOT_FOUND);
    }

}