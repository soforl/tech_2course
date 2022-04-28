package ru.itmo.kotiki.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmo.kotiki.entities.Owner;
import ru.itmo.kotiki.repositories.OwnerRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class OwnerServiceImpl {
    @Autowired
    private OwnerRepository ownerRepository;

    public Owner findOwner(Integer id) {
        return ownerRepository.getById(id);
    }

    public void saveOwner(String name,
                          LocalDate birthday
    ) {
        Owner owner = new Owner(name, birthday);
        ownerRepository.save(owner);
    }

    public void deleteOwner(Integer id) {
        Owner owner = ownerRepository.getById(id);
        ownerRepository.delete(owner);
    }

    public List<Owner> findAll() {
        return ownerRepository.findAll();
    }
}
