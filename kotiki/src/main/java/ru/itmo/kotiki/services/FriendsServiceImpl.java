package ru.itmo.kotiki.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmo.kotiki.entities.Friends;
import ru.itmo.kotiki.entities.Kotik;
import ru.itmo.kotiki.repositories.FriendsRepository;
import ru.itmo.kotiki.repositories.KotikRepository;

import java.util.List;

@Service
public class FriendsServiceImpl{
    @Autowired
    private KotikRepository kotikRepository;
    @Autowired
    private FriendsRepository friendsRepository;
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
