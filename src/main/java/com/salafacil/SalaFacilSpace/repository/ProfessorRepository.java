package com.salafacil.SalaFacilSpace.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salafacil.SalaFacilSpace.entity.Professor;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {
    // Métodos customizados se necessário
}