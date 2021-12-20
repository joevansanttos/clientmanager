package com.joevan.clientmanager.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.joevan.clientmanager.model.Client;
import com.joevan.clientmanager.model.Phone;
import com.joevan.clientmanager.repository.ClientRepository;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Optional;

public class PhoneForm {

    @NotNull
    @NotEmpty
    @NotBlank
    private String numbers;

    private Long clientId;

    public String getNumbers() {
        return numbers;
    }

    public void setNumbers(String numbers) {
        this.numbers = numbers;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Phone convert(ClientRepository clientRepository) {

        Optional<Client> optional = clientRepository.findById(Long.valueOf(clientId));

        boolean validPhone = validatePhoneNumber(numbers);


        if (optional.isPresent() && validPhone) {
            Client newClient = optional.get();
            return new Phone(numbers, newClient);
        }else{
            return null;
        }
    }

    private static boolean validatePhoneNumber(String phoneNo) {
        return phoneNo.chars().filter(c -> c != '.' && c != '-' && c != ' ').distinct().count() > 1;
    }
}
