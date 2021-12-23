package com.joevan.clientmanager.controller;

import com.joevan.clientmanager.form.ClientForm;
import com.joevan.clientmanager.form.PhoneForm;
import com.joevan.clientmanager.model.Client;
import com.joevan.clientmanager.model.Phone;
import com.joevan.clientmanager.repository.ClientRepository;
import com.joevan.clientmanager.service.ClientService;
import com.joevan.clientmanager.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/phone")
public class PhoneController {

    @Autowired
    private ClientRepository clientRepository;

    private final PhoneService phoneService;

    public PhoneController(PhoneService phoneService) {
        this.phoneService = phoneService;
    }



    @PostMapping("/add")
    @Transactional
    public ResponseEntity<Phone> addPhone(@RequestBody @Valid PhoneForm phoneForm){
        Phone phone = phoneForm.convert(clientRepository);
        if(phone != null){
            Phone newPhone = phoneService.addPhone(phone);
            return new ResponseEntity<>(newPhone, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(phone, HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletePhone(@PathVariable("id") Long id) {
        phoneService.deletePhone(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/exists/{numbers}")
    public ResponseEntity<Phone> phoneExist(@PathVariable("numbers") String numbers) {
        Phone phone = phoneService.findPhone(numbers);
        return new ResponseEntity<>(phone, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Phone> updateEmployee(@RequestBody Phone phone){
        Phone phoneFind = phoneService.findPhoneById(phone.getId());
        phoneFind.setNumbers(phone.getNumbers());
        Phone updatePhone = phoneService.updatePhone(phoneFind);
        return new ResponseEntity<>(updatePhone, HttpStatus.OK);
    }
}
