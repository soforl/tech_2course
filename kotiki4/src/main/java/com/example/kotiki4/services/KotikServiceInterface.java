package com.example.kotiki4.services;

import com.example.kotiki4.entities.Color;
import com.example.kotiki4.entities.Kotik;
import com.example.kotiki4.entities.Owner;

import java.util.Collection;
import java.util.List;

public interface KotikServiceInterface {
    Kotik findKotik(Integer id);

    void saveKotik(Kotik kotik);

    void deleteKotik(Integer id);

    List<Kotik> findAll();

    Collection<Kotik> findByColorId(Color color);

    Collection<Kotik> findByOwnerId(Owner owner);
}
