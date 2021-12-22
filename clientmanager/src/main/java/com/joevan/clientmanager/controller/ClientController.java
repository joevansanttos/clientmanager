package com.joevan.clientmanager.controller;

import com.joevan.clientmanager.form.ClientForm;
import com.joevan.clientmanager.model.Client;
import com.joevan.clientmanager.model.Phone;
import com.joevan.clientmanager.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Client>> getAllClients(){
        List<Client> clients = clientService.findAllClients();
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    @PostMapping("/add")
    @Transactional
    public ResponseEntity<Client> addClient(@RequestBody @Valid ClientForm clientForm){
        Client client = clientForm.convert();
        Client newClient = clientService.addClient(client);
        return new ResponseEntity<>(newClient, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable("id") Long id) {
        clientService.deleteClient(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Client> findClient(@PathVariable("id") Long id) {
        Client findClient = clientService.findClientById(id);
        return new ResponseEntity<Client>(HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Client> updateClient(@RequestBody Client client){
        System.out.println(client.getId());
        Client findClient = clientService.findClientById(client.getId());
        findClient.setFirstName(client.getFirstName());
        findClient.setLastName(client.getLastName());
        findClient.setAddress(client.getAddress());
        findClient.setDistrict(client.getDistrict());
        Client updateClient = clientService.updateClient(findClient);
        return new ResponseEntity<>(findClient, HttpStatus.OK);
    }
}
