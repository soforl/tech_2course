package com.example.kotiki4.controllers;

import com.example.kotiki4.entities.Owner;
import com.example.kotiki4.services.OwnerService;
import com.example.kotiki4.wrappers.OwnerWrapper;
import com.example.kotiki4.wrappers.WrapperCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping
public class OwnerController {
    @Autowired
    private OwnerService ownerService;

    @GetMapping(value="/")
    public String hello(){
        return "Hello";
    }

    @RequestMapping(value = "/admin/addOwner")
    public ResponseEntity addOwner(@RequestParam String name,
                                   @RequestParam String birthday,
                                   @RequestParam String username,
                                   @RequestParam String role,
                                   @RequestParam String password
    ) {
        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            Owner owner = new Owner(name,
                    LocalDate.parse(birthday, formatter),
                    role,
                    username,
                    password);
            ownerService.saveOwner(owner);
        } catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(value = "/admin/getOwner")
    public ResponseEntity getOwner(@RequestParam Integer id){
        Owner owner = ownerService.findOwner(id);
        return ResponseEntity.ok().body(new OwnerWrapper(
                owner.getId(),
                owner.getName(),
                owner.getBirthday()
        ));
    }

    @RequestMapping(value = "/admin/deleteOwner")
    public ResponseEntity deleteOwner(@RequestParam Integer id){
        try {
            ownerService.deleteOwner(id);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(value = "/admin/findAllOwners")
    public ResponseEntity findAllOwners(){
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
