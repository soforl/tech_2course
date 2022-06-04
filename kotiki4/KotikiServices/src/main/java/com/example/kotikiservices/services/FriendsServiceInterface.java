package com.example.kotikiservices.services;

import com.example.kotikiservices.entities.Friends;

public interface FriendsServiceInterface {
    Friends findFriends(Integer id);
    void saveFriends(Integer id1, Integer id2);
}
