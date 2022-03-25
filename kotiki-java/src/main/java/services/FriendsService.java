package services;

import dao.FriendsDao;
import entities.Friends;

import java.util.List;

public class FriendsService {
    private FriendsDao friendsDao;

    public FriendsService(FriendsDao friendsDao) {
        this.friendsDao = friendsDao;
    }

    public Friends findFriends(int id){
        return friendsDao.findById(id);
    }

    public void saveFriends(Friends friends) {
        friendsDao.save(friends);
    }

    public void deleteFriends(Friends friends) {
        friendsDao.delete(friends);
    }

    public void updateFriends(Friends friends) {
        friendsDao.update(friends);
    }

    public List<Friends> findAll() {
        return friendsDao.getAll();
    }

    public FriendsDao getFriendsDao() {
        return friendsDao;
    }

    public void setFriendsDao(FriendsDao friendsDao) {
        this.friendsDao = friendsDao;
    }
}
