package ru.itmo.kotiki.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itmo.kotiki.entities.Owner;

public interface OwnerRepository extends JpaRepository<Owner, Integer> {
}
