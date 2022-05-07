package com.example.kotiki4.config;

import com.example.kotiki4.entities.Owner;
import com.example.kotiki4.repositories.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class UserAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private OwnerRepository ownerRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        Owner owner = ownerRepository.findByUsername(username);
        if (owner == null) {
            throw new BadCredentialsException("Unknown user " + username);
        }
        if (!password.equals(owner.getPassword())) {
            throw new BadCredentialsException("Bad password");
        }
        UserDetails principal = User.builder()
                .username(owner.getUsername())
                .password(owner.getPassword())
                .roles(owner.getRole())
                .build();
        return new UsernamePasswordAuthenticationToken(principal, password, principal.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
