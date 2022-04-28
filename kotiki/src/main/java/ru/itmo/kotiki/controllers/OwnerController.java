package ru.itmo.kotiki.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itmo.kotiki.entities.Kotik;
import ru.itmo.kotiki.entities.Owner;
import ru.itmo.kotiki.services.OwnerServiceImpl;
import ru.itmo.kotiki.wrappers.KotikWrapper;
import ru.itmo.kotiki.wrappers.OwnerWrapper;
import ru.itmo.kotiki.wrappers.WrapperCreator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "owners")
public class OwnerController {
    @Autowired
    private OwnerServiceImpl ownerService;

    @PostMapping(value = "/add")
    public ResponseEntity addOwner(@RequestParam String name,
                                   @RequestParam String birthday
    ) {
        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            ownerService.saveOwner(name,
                    LocalDate.parse(birthday, formatter));
        } catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(value = "/get")
    public ResponseEntity getOwner(@RequestParam Integer id){
        Owner owner = ownerService.findOwner(id);
        return ResponseEntity.ok().body(new OwnerWrapper(
                owner.getId(),
                owner.getName(),
                owner.getBirthday()
        ));
    }

    @DeleteMapping(value = "/delete")
    public ResponseEntity deleteOwner(@RequestParam Integer id){
        try {
            ownerService.deleteOwner(id);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(value = "/findAll")
    public ResponseEntity findAll(){
        try {
            List<Owner> owners = ownerService.findAll();
            List<OwnerWrapper> ownerWrappers = owners.stream()
                    .map(owner -> WrapperCreator.createOwnerWrapper(owner))
                    .collect(Collectors.toList());
            return new ResponseEntity(ownerWrappers, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
