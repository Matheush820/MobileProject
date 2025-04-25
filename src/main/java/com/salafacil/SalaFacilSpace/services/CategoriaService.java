package com.salafacil.SalaFacilSpace.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.salafacil.SalaFacilSpace.entity.Categoria;
import com.salafacil.SalaFacilSpace.repository.CategoriaRepository;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    // Criar categoria
    public Categoria createCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    // Obter todas as categorias
    public Iterable<Categoria> getAllCategorias() {
        return categoriaRepository.findAll();
    }

    // Obter uma categoria específica pelo ID
    public Categoria getCategoriaById(Long id) {
        return categoriaRepository.findById(id).orElse(null);
    }

    // Atualizar categoria
    public Categoria updateCategoria(Long id, Categoria categoria) {
        if (categoriaRepository.existsById(id)) {
            categoria.setId(id);  // Garantir que o ID da categoria seja mantido
            return categoriaRepository.save(categoria);
        }
        return null;
    }

    // Deletar categoria
    public boolean deleteCategoria(Long id) {
        if (categoriaRepository.existsById(id)) {
            categoriaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
