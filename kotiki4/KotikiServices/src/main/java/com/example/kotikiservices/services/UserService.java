package com.example.kotikiservices.services;

import com.example.kotikiservices.entities.Owner;
import com.example.kotikiservices.repositories.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    private OwnerRepository ownerRepository;

    @Autowired
    public UserService(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Owner owner = ownerRepository.findByUsername(username);
        if (owner == null) {
            throw new UsernameNotFoundException("Unknown user: " + username);
        }
        return User.builder()
                .username(owner.getUsername())
                .password(owner.getPassword())
                .roles(owner.getRole())
                .build();
    }
}
