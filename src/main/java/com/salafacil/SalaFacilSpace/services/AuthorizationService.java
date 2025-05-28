package com.salafacil.SalaFacilSpace.services;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.salafacil.SalaFacilSpace.repository.ProfessorRepository;

//@Service
public class AuthorizationService implements UserDetailsService {

    private final ProfessorRepository repository;

    public AuthorizationService(ProfessorRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Professor não encontrado"));
    }
}
