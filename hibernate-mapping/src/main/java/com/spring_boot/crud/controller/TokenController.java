package com.spring_boot.crud.controller;

import com.spring_boot.crud.dto.EmployeeDTO;
import com.spring_boot.crud.entity.Employee;
import com.spring_boot.crud.entity.Token;
import com.spring_boot.crud.repository.EmployeeRepository;
import com.spring_boot.crud.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class TokenController {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private TokenRepository tokenRepository;

    @PutMapping("/employee/token")
    public ResponseEntity<?> addToken(@RequestBody EmployeeDTO employeeDTO) {
        AtomicInteger flag = new AtomicInteger();
        employeeRepository.findById(employeeDTO.getId()).ifPresent(employee -> {
            try {
                employee.addToken(new Token(employeeDTO.getToken()));
                employeeRepository.save(employee);
                //
            } catch (Exception e) {
                flag.set(1);
                //
                //
            }
        });
        if (flag.get() == 1)
            return new ResponseEntity<String>("Duplicate Token", HttpStatus.FORBIDDEN);

        if (employeeRepository.existsById(employeeDTO.getId()))
            return new ResponseEntity<Employee>(employeeRepository.findById(employeeDTO.getId()).orElse(null), HttpStatus.OK);
        else return new ResponseEntity<String>("not found", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/employee/token")
    public ResponseEntity<?> deleteToken(@RequestBody EmployeeDTO employeeDTO) {
        employeeRepository.findById(employeeDTO.getId()).ifPresent(employee -> {
            Token token = tokenRepository.findByToken(employeeDTO.getToken());
            if (token != null)
                tokenRepository.delete(token);
        });
        if (employeeRepository.existsById(employeeDTO.getId()))
            return new ResponseEntity<Employee>(employeeRepository.findById(employeeDTO.getId()).orElse(null), HttpStatus.OK);
        else return new ResponseEntity<String>("not found", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/employee/token")
    public ResponseEntity<?> getTokens(@RequestBody EmployeeDTO employeeDTO) {
        Employee employee = employeeRepository.findById(employeeDTO.getId()).orElse(null);
        if (employee != null)
            return new ResponseEntity<List<Token>>(employee.getTokens(), HttpStatus.OK);
        else return new ResponseEntity<String>("not found", HttpStatus.NOT_FOUND);
    }

}
