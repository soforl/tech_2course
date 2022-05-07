package com.example.kotiki4.controllers;

import com.example.kotiki4.entities.Color;
import com.example.kotiki4.entities.Kotik;
import com.example.kotiki4.entities.Owner;
import com.example.kotiki4.services.FriendsService;
import com.example.kotiki4.services.KotikService;
import com.example.kotiki4.services.OwnerService;
import com.example.kotiki4.wrappers.KotikWrapper;
import com.example.kotiki4.wrappers.WrapperCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

// http://localhost:8081/kotiki/

@RestController
@RequestMapping
public class KotikController {
    @Autowired
    private KotikService kotikService;

    @Autowired
    private OwnerService ownerService;

    @Autowired
    private FriendsService friendsService;

    @RequestMapping(value = "/admin/addKotik")
    public ResponseEntity addKotik(@RequestParam String name,
                                   @RequestParam String birthday,
                                   @RequestParam String color,
                                   @RequestParam String breed,
                                   @RequestParam Integer ownerId
    ) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            Kotik kotik = new Kotik(name, LocalDate.parse(birthday, formatter), color, breed,
                    ownerService.findOwner(ownerId));
            kotikService.saveKotik(kotik);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(value = "/admin/getKotik")
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

    @GetMapping(value = "/admin/getOwnersKotik")
    public ResponseEntity getOwnersKotik(@RequestParam Integer kotikId,
                                         @RequestParam Integer ownerId) {
        try {
            Kotik kot = kotikService.findKotik(kotikId);
            if (kot.getOwnerId().equals(ownerId)){
                return ResponseEntity.ok().body(new KotikWrapper(
                        kot.getId(),
                        kot.getName(),
                        kot.getBirthday(),
                        kot.getBreedId(),
                        kot.getColorId(),
                        kot.getOwnerId().getId()
                ));
            }
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/admin/deleteKotik")
    public ResponseEntity deleteKotik(@RequestParam Integer id) {
        try {
            kotikService.deleteKotik(id);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(value = "/admin/findAllKotiks")
    public ResponseEntity  findAllKotiks() {
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

    @GetMapping(value = "/admin/getFriends")
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

    @RequestMapping(value = "/admin/addFriend")
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

    @GetMapping(value = "/admin/getByColor")
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

    @GetMapping(value = "/user/getByColor")
    public ResponseEntity getByColorForOwner(@RequestParam String color){
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            Owner owner = ownerService.findOwnerByUsername(auth.getName());
            Collection<Kotik> colorKotik = kotikService.findByColorId(Color.valueOf(color));
            Collection<KotikWrapper> kotikWrappers = colorKotik.stream()
                    .filter(kotik -> kotik.getOwnerId().equals(owner.getId()))
                    .map(kotik -> WrapperCreator.createKotikWrapper(kotik))
                    .collect(Collectors.toList());
            return new ResponseEntity(kotikWrappers, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/user/getOwnersKotiks")
    public ResponseEntity getOwnersKotiks(){
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            Owner owner = ownerService.findOwnerByUsername(auth.getName());
            Collection<Kotik> colorKotik = kotikService.findByOwnerId(owner);
            Collection<KotikWrapper> kotikWrappers = colorKotik.stream()
                    .map(kotik -> WrapperCreator.createKotikWrapper(kotik))
                    .collect(Collectors.toList());
            return new ResponseEntity(kotikWrappers, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/admin/getByOwner")
    public ResponseEntity getByOwner(@RequestParam Integer id){
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
