package ru.itmo.kotiki.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itmo.kotiki.entities.Friends;

public interface FriendsRepository extends JpaRepository<Friends, Integer> {
}
