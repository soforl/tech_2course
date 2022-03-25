package services;

import dao.OwnerDao;
import entities.Owner;

import java.util.List;

public class OwnerService {
    private OwnerDao ownerDao;

    public OwnerService(OwnerDao ownerDao) {
        this.ownerDao = ownerDao;
    }

    public Owner findOwner(int id){
        return ownerDao.findById(id);
    }

    public Owner saveOwner(Owner owner) {
        ownerDao.save(owner);
        return owner;
    }

    public void deleteOwner(Owner owner) {
        ownerDao.delete(owner);
    }

    public void updateOwner(Owner owner) {
        ownerDao.update(owner);
    }

    public List<Owner> findAll() {
        return ownerDao.getAll();
    }
    public OwnerDao getOwnerDao() {
        return ownerDao;
    }

    public void setOwnerDao(OwnerDao ownerDao) {
        this.ownerDao = ownerDao;
    }
}
