package com.example.kotiki4.services;

import com.example.kotiki4.entities.Owner;

import java.util.List;

public interface OwnerServiceInterface {
    Owner findOwner(Integer id);

    void saveOwner(Owner owner);

    void deleteOwner(Integer id);

    Owner findOwnerByUsername(String username);

    List<Owner> findAll();
}
