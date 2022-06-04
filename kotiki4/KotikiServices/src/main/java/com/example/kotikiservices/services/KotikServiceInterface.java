package com.example.kotikiservices.services;

import com.example.kotikiservices.entities.Color;
import com.example.kotikiservices.entities.Kotik;
import com.example.kotikiservices.entities.Owner;

import java.util.Collection;
import java.util.List;

public interface KotikServiceInterface {
    Kotik findKotik(Integer id);

    void saveKotik(String kotikJson);

    void deleteKotik(Integer id);

    List<Kotik> findAll();

    Collection<Kotik> findByColorId(Color color);

    Collection<Kotik> findByOwnerId(Owner owner);
}
