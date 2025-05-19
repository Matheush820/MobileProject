package com.salafacil.SalaFacilSpace.services;

import com.salafacil.SalaFacilSpace.entity.Categoria;
import com.salafacil.SalaFacilSpace.exception.ResourceNotFoundException;
import com.salafacil.SalaFacilSpace.repository.CategoriaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    // Criar categoria
    public Categoria createCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    // Obter todas as categorias
    public List<Categoria> getAllCategorias() {
        return categoriaRepository.findAll();
    }

    // Obter uma categoria específica pelo ID
    public Categoria getCategoriaById(Long id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada com ID: " + id));
    }

    // Atualizar categoria
    public Categoria updateCategoria(Long id, Categoria categoria) {
        if (!categoriaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Categoria não encontrada para atualização com ID: " + id);
        }
        categoria.setId(id);
        return categoriaRepository.save(categoria);
    }

    // Deletar categoria
    public void deleteCategoria(Long id) {
        if (!categoriaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Categoria não encontrada para exclusão com ID: " + id);
        }
        categoriaRepository.deleteById(id);
    }
}
