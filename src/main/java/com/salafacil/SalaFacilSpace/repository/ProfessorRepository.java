package com.salafacil.SalaFacilSpace.repository;

import com.salafacil.SalaFacilSpace.entity.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {

    // Adiciona o método que busca o Professor pelo email
    Optional<Professor> findByEmail(String email);
}
