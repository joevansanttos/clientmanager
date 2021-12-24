package com.joevan.clientmanager.service;

import com.joevan.clientmanager.model.Phone;
import com.joevan.clientmanager.repository.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Serviço para adicionar, atualizar e excluir telefones
 */
@Service
@Transactional
public class PhoneService {

    private final PhoneRepository phoneRepository;

    @Autowired
    public PhoneService(PhoneRepository phoneRepository) {
        this.phoneRepository = phoneRepository;
    }

    public Phone updatePhone(Phone phone) {
        return phoneRepository.save(phone);
    }

    public Phone addPhone(Phone phone){
        return phoneRepository.save(phone);
    }

    public void deletePhone(Long id){
        phoneRepository.deletePhoneById(id);
    }

    public Phone findPhone(String numbers) {
        return phoneRepository.findPhoneByNumbers(numbers);
    }

    public Phone findPhoneById(Long id) {
        return phoneRepository.findPhoneById(id);
    }

}
