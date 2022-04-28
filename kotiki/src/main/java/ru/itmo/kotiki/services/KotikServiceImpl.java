package ru.itmo.kotiki.services;

import org.springframework.stereotype.Service;
import ru.itmo.kotiki.entities.Color;
import ru.itmo.kotiki.entities.Kotik;
import ru.itmo.kotiki.entities.Owner;
import ru.itmo.kotiki.repositories.KotikRepository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Service
public class KotikServiceImpl {
    private final KotikRepository kotikRepository;

    public KotikServiceImpl(KotikRepository kotikRepository) {
        this.kotikRepository = kotikRepository;
    }

    public Kotik findKotik(Integer id) {
        return kotikRepository.getById(id);
    }

    public void saveKotik(String name,
                          LocalDate birthday,
                          Color colorId,
                          String breedId,
                          Owner ownerId
    ) {
        Kotik cat = new Kotik(name, birthday, colorId.text, breedId, ownerId);
        kotikRepository.save(cat);
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
