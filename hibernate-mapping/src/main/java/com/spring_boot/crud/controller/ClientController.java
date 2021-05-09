package com.spring_boot.crud.controller;

import com.spring_boot.crud.dto.ClientDTO;
import com.spring_boot.crud.entity.Client;
import com.spring_boot.crud.entity.Employee;
import com.spring_boot.crud.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @GetMapping("/client")
    public ResponseEntity<?> getClients() {
        return new ResponseEntity<List<Client>>(clientRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/client/employees/{name}")
    public ResponseEntity<?> getClientEmployees(@PathVariable String name) {
        Client client = clientRepository.findByName(name);
        if (client != null)
            return new ResponseEntity<List<Employee>>(client.getEmployees(), HttpStatus.OK);
        else
            return new ResponseEntity<String>("Not Found", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/client")
    public ResponseEntity<?> addClient(@RequestBody ClientDTO clientDTO) {
            clientRepository.save(new Client(clientDTO.getClientName(), clientDTO.getLocation()));
        return new ResponseEntity<List<Client>>(clientRepository.findAll(), HttpStatus.OK);
    }

    @DeleteMapping("/client")
    public ResponseEntity<?> deleteClient(@RequestBody ClientDTO clientDTO) {
        Client client = clientRepository.findByName(clientDTO.getClientName());
        if (client != null) {
            clientRepository.delete(client);
            return new ResponseEntity<List<Client>>(clientRepository.findAll(), HttpStatus.OK);
        } else
            return new ResponseEntity<String>("Not Found", HttpStatus.NOT_FOUND);
    }

    @PatchMapping("/client")
    public ResponseEntity<?> updateClient(@RequestBody ClientDTO clientDTO) {
        Client client = clientRepository.findByName(clientDTO.getClientName());
        if (client != null) {
            client.setLocation(clientDTO.getLocation());
            clientRepository.save(client);
            return new ResponseEntity<List<Client>>(clientRepository.findAll(), HttpStatus.OK);
        } else
            return new ResponseEntity<String>("Not Found", HttpStatus.NOT_FOUND);
    }
}
