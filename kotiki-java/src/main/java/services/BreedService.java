package services;

import dao.BreedDao;
import entities.Breed;

import java.util.List;

public class BreedService {
    private BreedDao breedDao;

    public BreedService(BreedDao breedDao) {
        this.breedDao = breedDao;
    }

    public Breed findBreed(int id){
        return breedDao.findById(id);
    }

    public void saveBreed(Breed breed) {
        breedDao.save(breed);
    }

    public void deleteBreed(Breed breed) {
        breedDao.delete(breed);
    }

    public void updateBreed(Breed breed) {
        breedDao.update(breed);
    }

    public List<Breed> findAll() {
        return breedDao.getAll();
    }

    public BreedDao getBreedDao() {
        return breedDao;
    }

    public void setBreedDao(BreedDao breedDao) {
        this.breedDao = breedDao;
    }
}
