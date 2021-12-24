package com.joevan.clientmanager.repository;

import com.joevan.clientmanager.model.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository da classe Phone relacionada aos telefones
 */
@Repository
public interface PhoneRepository extends JpaRepository<Phone, Long> {
    void deletePhoneById(Long id);

    Phone findPhoneByNumbers(String numbers);

    Phone findPhoneById(Long id);
}
