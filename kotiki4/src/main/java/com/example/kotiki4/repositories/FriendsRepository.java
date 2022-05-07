package com.example.kotiki4.repositories;


import com.example.kotiki4.entities.Friends;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendsRepository extends JpaRepository<Friends, Integer> {
}
