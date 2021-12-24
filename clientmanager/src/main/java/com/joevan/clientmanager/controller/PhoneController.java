package com.joevan.clientmanager.controller;

import com.joevan.clientmanager.form.PhoneForm;
import com.joevan.clientmanager.model.Phone;
import com.joevan.clientmanager.repository.ClientRepository;
import com.joevan.clientmanager.service.PhoneService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

/**
 * Classe de RestController de telefones
 */
@RestController
@RequestMapping("/phone")
public class PhoneController {

    /**
     * Interface de JPARepository relacionada e entidade Client
     */
    private final ClientRepository clientRepository;

    /**
     * Classe de Serviços que busca em Repositorys dados dos telefones
     */
    private final PhoneService phoneService;

    public PhoneController(PhoneService phoneService, ClientRepository clientRepository) {
        this.phoneService = phoneService;
        this.clientRepository = clientRepository;
    }


    /**
     * Esse metodo esta sendo utilizado para adicionar um telefone
     * @param phoneForm Parametro para ser addicionado
     * @return 201 se o telefone for adicionado e 400 caso nao seja
     */
    @PostMapping("/add")
    @Transactional
    public ResponseEntity<Phone> addPhone(@RequestBody @Valid PhoneForm phoneForm){
        Phone phone = phoneForm.convert(clientRepository);
        if(phone != null){
            Phone newPhone = phoneService.addPhone(phone);
            return new ResponseEntity<>(newPhone, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

    }

    /**
     * Esse metodo esta sendo utilizado para excluir um telefone
     * @param id Parametro recebido com id de telefone para ser exlcluido
     * @return Retorna ok caso cosniga deletar
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletePhone(@PathVariable("id") Long id) {
        phoneService.deletePhone(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Esse metodo esta sendo utilizado para verificar se o telefone existe pelos seus numeros
     * @param numbers Parametro do tipo string para encontrar telefone
     * @return Retorna ok caso telefone exista
     */
    @GetMapping("/exists/{numbers}")
    public ResponseEntity<Phone> phoneExist(@PathVariable("numbers") String numbers) {
        Phone phone = phoneService.findPhone(numbers);
        return new ResponseEntity<>(phone, HttpStatus.OK);
    }

    /**
     * Esse metodo esta sendo utilizado para atualizar um telefone
     * @param phone Parametro passado para ser atualizado
     * @return Retorna Ok caso consiga exluir
     */
    @PutMapping("/update")
    public ResponseEntity<Phone> updateEmployee(@RequestBody Phone phone){
        Phone phoneFind = phoneService.findPhoneById(phone.getId());
        phoneFind.setNumbers(phone.getNumbers());
        Phone updatePhone = phoneService.updatePhone(phoneFind);
        return new ResponseEntity<>(updatePhone, HttpStatus.OK);
    }
}
