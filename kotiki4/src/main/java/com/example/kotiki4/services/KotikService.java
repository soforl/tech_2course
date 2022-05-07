package com.example.kotiki4.services;

import com.example.kotiki4.entities.Color;
import com.example.kotiki4.entities.Kotik;
import com.example.kotiki4.entities.Owner;
import com.example.kotiki4.repositories.KotikRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class KotikService implements KotikServiceInterface{
    private final KotikRepository kotikRepository;

    public KotikService(KotikRepository kotikRepository) {
        this.kotikRepository = kotikRepository;
    }

    public Kotik findKotik(Integer id) {
        return kotikRepository.getById(id);
    }

    public void saveKotik(Kotik kotik) {
        kotikRepository.save(kotik);
    }

    public void deleteKotik(Integer id) {
        Kotik kotik = kotikRepository.getById(id);
        kotikRepository.delete(kotik);
    }

    public List<Kotik> findAll() {
        return kotikRepository.findAll();
    }

    public Collection<Kotik> findByColorId(Color color) {
        return kotikRepository.findByColorId(color.text);
    }

    public Collection<Kotik> findByOwnerId(Owner owner) {
        return kotikRepository.findByOwnerId(owner);
    }
}
