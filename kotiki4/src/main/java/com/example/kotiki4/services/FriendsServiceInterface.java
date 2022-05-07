package com.example.kotiki4.services;

import com.example.kotiki4.entities.Friends;

public interface FriendsServiceInterface {
    Friends findFriends(Integer id);
    void saveFriends(Integer id1, Integer id2);
}
