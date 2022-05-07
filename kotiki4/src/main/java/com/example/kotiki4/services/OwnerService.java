package com.example.kotiki4.services;

import com.example.kotiki4.entities.Owner;
import com.example.kotiki4.repositories.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public void saveOwner(Owner owner) {
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
