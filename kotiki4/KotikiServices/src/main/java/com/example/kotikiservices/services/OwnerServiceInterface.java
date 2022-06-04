package com.example.kotikiservices.services;

import com.example.kotikiservices.entities.Owner;

import java.util.List;

public interface OwnerServiceInterface {
    Owner findOwner(Integer id);

    void saveOwner(String ownerJson);

    void deleteOwner(Integer id);

    Owner findOwnerByUsername(String username);

    List<Owner> findAll();
}
