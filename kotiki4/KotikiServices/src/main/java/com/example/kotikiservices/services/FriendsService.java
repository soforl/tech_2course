package com.example.kotikiservices.services;

import com.example.kotikiservices.entities.Friends;
import com.example.kotikiservices.entities.Kotik;
import com.example.kotikiservices.repositories.FriendsRepository;
import com.example.kotikiservices.repositories.KotikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FriendsService implements FriendsServiceInterface {

    private final KotikRepository kotikRepository;

    private final FriendsRepository friendsRepository;

    @Autowired
    public FriendsService(KotikRepository kotikRepository, FriendsRepository friendsRepository) {
        this.kotikRepository = kotikRepository;
        this.friendsRepository = friendsRepository;
    }

    public Friends findFriends(Integer id) {
        return friendsRepository.getById(id);
    }

    public void saveFriends(Integer id1, Integer id2) {
        Kotik kotik1 = kotikRepository.getById(id1);
        Kotik kotik2 = kotikRepository.getById(id2);
        Friends friends1 = new Friends(kotik1.getId(), kotik2.getId());
        Friends friends2 = new Friends(kotik2.getId(), kotik1.getId());
        friendsRepository.save(friends1);
        friendsRepository.save(friends2);
    }
}
