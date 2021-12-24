package com.joevan.clientmanager.service;

import com.joevan.clientmanager.model.Client;
import com.joevan.clientmanager.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Serviço para listar, adicionar, atualizar, excluir clientes
 */
@Service
@Transactional
public class ClientService {

    /**
     * Repositório relacionado aos clientes
     */
    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    /**
     * Método que solicita todos os clientes
     * @return todos os clientes do banco
     */
    public List<Client> findAllClients() {
        return clientRepository.findAll();
    }

    /**
     * Método que adiciona o cliente
     * @param client cliente que sera adicionado
     * @return Retorna verdadeiro caso consiga salvar o cliente
     */
    public Client addClient(Client client) {
        return clientRepository.save(client);
    }


    /**
     * Método que exclui cliente selecionado
     * @param id Parâmetro usado para encontrar e excluir cliente
     */
    public void deleteClient(Long id) {
        clientRepository.deleteClientById(id);
    }

    /**
     * Método que encontra cliente
     * @param id Parâmetro usado para encontrar cliente
     * @return Retorna verdadeiro caso o cliente seja encontrado
     */
    public Client findClientById(Long id) {
        return clientRepository.findClientById(id);
    }


    /**
     * Método que atualiza o cliente
     * @param client Dados de cliente que sera atualizado
     * @return Retorna verdadeiro caso o cliente seja encontrado e atualizado
     */
    public Client updateClient(Client client) {
        return clientRepository.save(client);
    }

}
