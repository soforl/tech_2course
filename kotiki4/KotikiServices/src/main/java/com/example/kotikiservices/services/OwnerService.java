package com.example.kotikiservices.services;

import com.example.kotikiservices.entities.Kotik;
import com.example.kotikiservices.entities.Owner;
import com.example.kotikiservices.repositories.OwnerRepository;
import com.example.kotikiservices.wrappers.KotikWrapper;
import com.example.kotikiservices.wrappers.OwnerWrapper;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OwnerService implements OwnerServiceInterface {
    private OwnerRepository ownerRepository;

    @Autowired
    public OwnerService(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    public Owner findOwner(Integer id) {
        return ownerRepository.getById(id);
    }

    @KafkaListener(id = "owners-addOwner", topics = {"owners.addOwner"}, containerFactory = "singleFactory")
    public void saveOwner(String ownerJson) {
        var data = new GsonBuilder().create().fromJson(ownerJson, OwnerWrapper.class);
        var owner = new Owner(data.getName(),
                data.getBirthday(),
                data.getRole(),
                data.getUsername(),
                data.getPassword());
        ownerRepository.save(owner);
    }

    public void deleteOwner(Integer id) {
        Owner owner = ownerRepository.getById(id);
        ownerRepository.delete(owner);
    }

    public Owner findOwnerByUsername(String username) {
        return ownerRepository.findByUsername(username);
    }

    public List<Owner> findAll() {
        return ownerRepository.findAll();
    }
}
