package com.example.kotiki4.repositories;

import com.example.kotiki4.entities.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<Owner, Integer> {
    Owner findByUsername(String username);
}
