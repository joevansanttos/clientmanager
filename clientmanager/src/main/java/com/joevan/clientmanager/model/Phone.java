package com.joevan.clientmanager.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Representa um Telefone
 */
@Entity
public class Phone implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;
    private String numbers;

    @ManyToOne(fetch=FetchType.LAZY)
    private Client client;

    public Phone(){

    }

    public Phone(String numbers, Client client) {
        this.numbers = numbers;
        this.client = client;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumbers() {
        return numbers;
    }

    public void setNumbers(String numbers) {
        this.numbers = numbers;
    }

    
}
