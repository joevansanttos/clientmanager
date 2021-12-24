package com.joevan.clientmanager.controller;

import com.joevan.clientmanager.form.ClientForm;
import com.joevan.clientmanager.model.Client;
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

    /**
     * Esse método esta sendo utilizado para listar todos os clientes cadastrados
     * @return Retorna todos os clientes
     */
    @GetMapping("/all")
    public ResponseEntity<List<Client>> getAllClients(){
        List<Client> clients = clientService.findAllClients();
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    /**
     * Esse método esta sendo utilizado para adicionar um cliente
     * @param clientForm Parâmetro no formato de cliente para ser cadastrado
     * @return Retorna OK caso cliente possa ser cadastrado
     */
    @PostMapping("/add")
    @Transactional
    public ResponseEntity<Client> addClient(@RequestBody @Valid ClientForm clientForm){
        Client client = clientForm.convert();
        Client newClient = clientService.addClient(client);
        return new ResponseEntity<>(newClient, HttpStatus.CREATED);
    }

    /**
     * Esse método esta sendo utilizado para excluir um cliente
     * @param id Parâmetro passado para encontrar cliente
     * @return Retorna OK caso o cliente seja excluído
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable("id") Long id) {
        clientService.deleteClient(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    /**
     * Esse método esta sendo utilizado para atualizar um cliente
     * @param client Parâmetro passado para encontrar e atualizar cliente
     * @return Retorna OK caso o cliente seja atualizado
     */
    @PutMapping("/update")
    public ResponseEntity<Client> updateClient(@RequestBody Client client){
        System.out.println(client.getId());
        Client findClient = clientService.findClientById(client.getId());
        findClient.setFirstName(client.getFirstName());
        findClient.setLastName(client.getLastName());
        findClient.setAddress(client.getAddress());
        findClient.setDistrict(client.getDistrict());
        Client updateClient = clientService.updateClient(findClient);
        return new ResponseEntity<>(updateClient, HttpStatus.OK);
    }
}
