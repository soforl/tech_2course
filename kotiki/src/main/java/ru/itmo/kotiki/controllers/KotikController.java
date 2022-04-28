package ru.itmo.kotiki.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itmo.kotiki.entities.Color;
import ru.itmo.kotiki.entities.Friends;
import ru.itmo.kotiki.entities.Kotik;
import ru.itmo.kotiki.repositories.FriendsRepository;
import ru.itmo.kotiki.services.FriendsServiceImpl;
import ru.itmo.kotiki.services.KotikServiceImpl;
import ru.itmo.kotiki.services.OwnerServiceImpl;
import ru.itmo.kotiki.wrappers.KotikWrapper;
import ru.itmo.kotiki.wrappers.WrapperCreator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

// http://localhost:8081/kotiki/

@RestController
@RequestMapping(value = "kotiki")
public class KotikController {
    @Autowired
    private KotikServiceImpl kotikService;

    @Autowired
    private OwnerServiceImpl ownerService;

    @Autowired
    private FriendsServiceImpl friendsService;

    @PostMapping(value = "/add")
    public ResponseEntity addKotik(@RequestParam String name,
                                   @RequestParam String birthday,
                                   @RequestParam String color,
                                   @RequestParam String breed,
                                   @RequestParam Integer ownerId
    ) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            kotikService.saveKotik(
                    name,
                    LocalDate.parse(birthday, formatter),
                    Color.valueOf(color),
                    breed,
                    ownerService.findOwner(ownerId)
            );
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(value = "/get")
    public ResponseEntity getKotik(@RequestParam Integer id) {
        Kotik kot = kotikService.findKotik(id);
        return ResponseEntity.ok().body(new KotikWrapper(
                kot.getId(),
                kot.getName(),
                kot.getBirthday(),
                kot.getBreedId(),
                kot.getColorId(),
                kot.getOwnerId().getId()
        ));
    }

    @DeleteMapping(value = "/delete")
    public ResponseEntity deleteKotik(@RequestParam Integer id) {
        try {
            kotikService.deleteKotik(id);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(value = "/findAll")
    public ResponseEntity  findAll() {
        try {
            List<Kotik> kotiks = kotikService.findAll();
            List<KotikWrapper> kotikWrappers = kotiks.stream()
                    .map(kotik -> WrapperCreator.createKotikWrapper(kotik))
                    .collect(Collectors.toList());
            return new ResponseEntity(kotikWrappers, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/getFriends")
    public ResponseEntity getFriends(@RequestParam Integer id){
        try {
            Kotik kotik = kotikService.findKotik(id);
            List<KotikWrapper> kotikWrappers = kotik.getFriends().stream()
                    .map(kot -> WrapperCreator.createKotikWrapper(kot))
                    .collect(Collectors.toList());
            return new ResponseEntity(kotikWrappers, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/addFriend")
    public ResponseEntity addFriend(@RequestParam Integer id1,
                                    @RequestParam Integer id2){
        try {
            friendsService.saveFriends(id1, id2);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(value = "/getByColor")
    public ResponseEntity getByColor(@RequestParam String color){
        try {
            Collection<Kotik> colorKotik = kotikService.findByColorId(Color.valueOf(color));
            Collection<KotikWrapper> kotikWrappers = colorKotik.stream()
                    .map(kotik -> WrapperCreator.createKotikWrapper(kotik))
                    .collect(Collectors.toList());
            return new ResponseEntity(kotikWrappers, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/getByOwner")
    public ResponseEntity getByColor(@RequestParam Integer id){
        try {
            Collection<Kotik> colorKotik = kotikService.findByOwnerId(ownerService.findOwner(id));
            Collection<KotikWrapper> kotikWrappers = colorKotik.stream()
                    .map(kotik -> WrapperCreator.createKotikWrapper(kotik))
                    .collect(Collectors.toList());
            return new ResponseEntity(kotikWrappers, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
