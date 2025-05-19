package com.salafacil.SalaFacilSpace.repository;

import com.salafacil.SalaFacilSpace.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

 
}
