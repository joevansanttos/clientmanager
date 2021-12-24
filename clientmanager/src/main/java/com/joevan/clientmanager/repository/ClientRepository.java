package com.joevan.clientmanager.repository;

import com.joevan.clientmanager.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository da Entity Client relacionada a clientes
 */
@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    void deleteClientById(Long id);

    Client findClientById(Long id);

}
