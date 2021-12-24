package com.joevan.clientmanager.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Entidade que Representa um cliente
 */
@Entity
public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private String district;

    /**
     * Lista onde relaciona 1 cliente a diversos telefones e diversos telefones para 1 cliente
     * Tipo de mapeamento Cascade serve para atualizar as classes relacionadas ao Cliente
     */
    @OneToMany (cascade=CascadeType.ALL, mappedBy="client", fetch=FetchType.LAZY)
    private List<Phone> phones = new ArrayList<>();


    public Client() {
    }

    /**
     * Cria um cliente, com específicos nome, sobrenome, endereço e bairro
     * @param firstName Nome do Cliente
     * @param lastName Sobrenome do Cliente
     * @param address Endereço do Cliente
     * @param district Bairro do Cliente
     */
    public Client(String firstName, String lastName,  String address, String district) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.district = district;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }
}
