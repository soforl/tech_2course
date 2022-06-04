package com.example.kotikiservices.repositories;

import com.example.kotikiservices.entities.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<Owner, Integer> {
    Owner findByUsername(String username);
}
