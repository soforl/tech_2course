package services;

import entities.Kotik;
import dao.KotikDao;

import java.util.List;

public class KotikService {
    private KotikDao kotikDao;

    public KotikService(KotikDao kotikDao) {
        this.kotikDao = kotikDao;
    }

    public Kotik findKotik(int id){
        return kotikDao.findById(id);
    }

    public void saveKotik(Kotik kotik) {
        kotikDao.save(kotik);
    }

    public void deleteKotik(Kotik kotik) {
        kotikDao.delete(kotik);
    }

    public void updateKotik(Kotik kotik) {
        kotikDao.update(kotik);
    }

    public List<Kotik> findAll() {
        return kotikDao.getAll();
    }

    public KotikDao getKotikDao() {
        return kotikDao;
    }

    public void setKotikDao(KotikDao kotikDao) {
        this.kotikDao = kotikDao;
    }
}
