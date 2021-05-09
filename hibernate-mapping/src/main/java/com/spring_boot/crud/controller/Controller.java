package com.spring_boot.crud.controller;

import com.spring_boot.crud.dto.EmployeeDTO;
import com.spring_boot.crud.entity.*;
import com.spring_boot.crud.repository.ClientRepository;
import com.spring_boot.crud.repository.EmployeeRepository;
import com.spring_boot.crud.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class Controller {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("/employee")
    public ResponseEntity<?> getEmployees() {
        return new ResponseEntity<List<Employee>>(employeeRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping("/employee")
    public ResponseEntity<?> addEmployee(@RequestBody EmployeeDTO employeeDTO) {

        Aadhaar aadhaar = new Aadhaar(employeeDTO.getAadhaarNumber(), employeeDTO.getDob());

        Employee employee = new Employee(employeeDTO.getName());

        Token token = new Token(employeeDTO.getToken());

        Client client = clientRepository.findByName(employeeDTO.getClientName());

        if (client != null)
            employee.setClient(client);

        Role role = roleRepository.findByRole(employeeDTO.getRoleName());

        if (role != null)
            employee.addRole(role);

        employee.addToken(token);

        employee.addAadhaar(aadhaar);

        employeeRepository.save(employee);

        return new ResponseEntity<List<Employee>>(employeeRepository.findAll(), HttpStatus.CREATED);
    }


    @DeleteMapping("/employee/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable long id) {
        employeeRepository.findById(id).ifPresent(employee -> employeeRepository.delete(employee));
        return new ResponseEntity<List<Employee>>(employeeRepository.findAll(), HttpStatus.OK);
    }

    @PatchMapping("/employee")
    public ResponseEntity<?> updateEmployee(@RequestBody EmployeeDTO employeeDTO) {

        employeeRepository.findById(employeeDTO.getId()).ifPresent(employee1 -> {

            employee1.setName(employeeDTO.getName());

            Aadhaar aadhaar = employee1.getAadhaar();

            aadhaar.setDob(employeeDTO.getDob());

            aadhaar.setNumber(employeeDTO.getAadhaarNumber());

            employee1.setAadhaar(aadhaar);

            Token token = new Token(employeeDTO.getToken());

            employee1.addToken(token);

            Client client = clientRepository.findByName(employeeDTO.getClientName());

            if (client != null)
                employee1.setClient(client);

            Role role = roleRepository.findByRole(employeeDTO.getRoleName());

            if (role != null)
                employee1.addRole(role);

            employeeRepository.save(employee1);
        });

        return new ResponseEntity<List<Employee>>(employeeRepository.findAll(), HttpStatus.OK);
    }

    @PatchMapping("/employee/role")
    public ResponseEntity<?> deleteRole(@RequestBody EmployeeDTO employeeDTO) {

        employeeRepository.findById(employeeDTO.getId()).ifPresent(employee1 -> {

            Role role = roleRepository.findByRole(employeeDTO.getRoleName());

            if (role != null)
                employee1.removeRole(role);

            employeeRepository.save(employee1);
        });

        return new ResponseEntity<List<Employee>>(employeeRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/employee/{size}/{page}")
    public ResponseEntity<?> getEmployeesByPage(@PathVariable int size, @PathVariable int page) {
        Page<Employee> employees = employeeRepository.findAll(PageRequest.of(page, size));

//        Page<Employee> employees = employeeRepository.findAll(PageRequest.of(page, size, Sort.Direction.DESC, "name"));

        List<Employee> employees_list = new ArrayList<>();

        employees.forEach(employees_list::add);

        return new ResponseEntity<List<Employee>>(employees_list, HttpStatus.OK);
    }

    @GetMapping("/employee/{attr}")
    public ResponseEntity<?> getEmployeesBySort(@PathVariable String attr) {
        return new ResponseEntity<List<Employee>>(employeeRepository.findAll(Sort.by(Sort.Direction.DESC, attr)), HttpStatus.OK);
    }

    @GetMapping("/employee/native")
    public ResponseEntity<?> getEmployeesByNameAndId() {
        return new ResponseEntity<List<Employee>>(employeeRepository.findByNameAndId(), HttpStatus.OK);
    }


}
