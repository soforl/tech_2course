package ru.itmo.kotiki.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itmo.kotiki.entities.Kotik;
import ru.itmo.kotiki.entities.Owner;

import java.util.Collection;

public interface KotikRepository extends JpaRepository<Kotik, Integer> {
    Collection<Kotik> findByColorId(String color);
    Collection<Kotik> findByOwnerId(Owner owner);
}
