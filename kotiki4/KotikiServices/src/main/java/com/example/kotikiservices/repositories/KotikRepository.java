package com.example.kotikiservices.repositories;

import com.example.kotikiservices.entities.Kotik;
import com.example.kotikiservices.entities.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface KotikRepository extends JpaRepository<Kotik, Integer> {
    Collection<Kotik> findByColorId(String color);
    Collection<Kotik> findByOwnerId(Owner owner);
}
