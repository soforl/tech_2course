package com.example.kotiki4.repositories;

import com.example.kotiki4.entities.Kotik;
import com.example.kotiki4.entities.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface KotikRepository extends JpaRepository<Kotik, Integer> {
    Collection<Kotik> findByColorId(String color);
    Collection<Kotik> findByOwnerId(Owner owner);
}
