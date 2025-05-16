package com.salafacil.SalaFacilSpace.services;

import com.salafacil.SalaFacilSpace.entity.Professor;
import com.salafacil.SalaFacilSpace.repository.ProfessorRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final ProfessorRepository professorRepository;

    public MyUserDetailsService(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        // Aqui, o username é o email do professor
    	System.out.println("Tentando autenticar: ");

        Professor professor = professorRepository.findByEmail(username)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

        return User.builder()
                .username(professor.getEmail())
                .password(professor.getSenha())
                .authorities(professor.getAuthorities())
                .build();
    }
}
