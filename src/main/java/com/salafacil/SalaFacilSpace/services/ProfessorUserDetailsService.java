package com.salafacil.SalaFacilSpace.services;

import com.salafacil.SalaFacilSpace.entity.Professor;
import com.salafacil.SalaFacilSpace.repository.ProfessorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Serviço responsável por carregar os detalhes de autenticação do Professor.
 * O parâmetro username representa o email do professor.
 */
@Service
public class ProfessorUserDetailsService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(ProfessorUserDetailsService.class);

    private final ProfessorRepository professorRepository;

    public ProfessorUserDetailsService(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Busca o professor pelo email (username)
        Professor professor = professorRepository.findByEmail(email)
                .orElseThrow(() -> {
                    logger.warn("Tentativa de autenticação falhou. Usuário não encontrado: {}", email);
                    return new UsernameNotFoundException("Usuário não encontrado com email: " + email);
                });

        Collection<?> authorities = professor.getAuthorities();
        if (authorities == null || authorities.isEmpty()) {
            logger.warn("Usuário {} não possui authorities definidas.", email);
            // Pode lançar exceção ou retornar lista vazia, dependendo do caso
        }

        // Construindo UserDetails do Spring Security
        return User.builder()
                .username(professor.getEmail())
                .password(professor.getSenha()) // Certifique-se que a senha está criptografada (bcrypt)
                .authorities(professor.getAuthorities())
                .build();
    }
}
