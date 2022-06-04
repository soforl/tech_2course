package com.example.kotikiservices.services;

import com.example.kotikiservices.entities.Color;
import com.example.kotikiservices.entities.Kotik;
import com.example.kotikiservices.entities.Owner;
import com.example.kotikiservices.repositories.KotikRepository;
import com.example.kotikiservices.wrappers.KotikWrapper;
import com.google.gson.GsonBuilder;
import org.springframework.kafka.annotation.KafkaListener;
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

    @KafkaListener(id = "kotiki-addKotik", topics = {"kotiki.addKotik"}, containerFactory = "singleFactory")
    public void saveKotik(String kotikJson) {
        var data = new GsonBuilder().create().fromJson(kotikJson, KotikWrapper.class);
        var kot = new Kotik(data.getName(),
                data.getBirthday(),
                data.getColorId(),
                data.getBreedId(),
                data.getOwnerId());
        kotikRepository.save(kot);

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
