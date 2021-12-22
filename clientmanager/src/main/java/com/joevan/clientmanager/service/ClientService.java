package com.joevan.clientmanager.service;

import com.joevan.clientmanager.model.Client;
import com.joevan.clientmanager.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ClientService {

    private ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }


    public List<Client> findAllClients() {
        return clientRepository.findAll();
    }

    public Client addClient(Client client) {
        return clientRepository.save(client);
    }

    public void deleteClient(Long id) {
        clientRepository.deleteClientById(id);
    }

    public Client findClientById(Long id) {
        return clientRepository.findClientById(id);
    }

    public Client updateClient(Client client) {
        return clientRepository.save(client);
    }

}
