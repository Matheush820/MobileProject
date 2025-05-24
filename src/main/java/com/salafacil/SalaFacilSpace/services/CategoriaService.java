// CategoriaService.java
package com.salafacil.SalaFacilSpace.services;

import com.salafacil.SalaFacilSpace.dto.CategoriaDTO;
import com.salafacil.SalaFacilSpace.entity.Categoria;
import com.salafacil.SalaFacilSpace.exception.ResourceNotFoundException;
import com.salafacil.SalaFacilSpace.mapper.CategoriaMapper;
import com.salafacil.SalaFacilSpace.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final CategoriaMapper categoriaMapper;

    // Injeção por construtor para facilitar testes e imutabilidade
    public CategoriaService(CategoriaRepository categoriaRepository, CategoriaMapper categoriaMapper) {
        this.categoriaRepository = categoriaRepository;
        this.categoriaMapper = categoriaMapper;
    }

    public CategoriaDTO createCategoria(CategoriaDTO dto) {
        Categoria entidade = categoriaMapper.toEntity(dto);
        Categoria salvo = categoriaRepository.save(entidade);
        return categoriaMapper.toDTO(salvo);
    }

    public List<CategoriaDTO> getAllCategorias() {
        return categoriaRepository.findAll().stream()
                .map(categoriaMapper::toDTO)
                .collect(Collectors.toList());
    }

    public CategoriaDTO getCategoriaById(Long id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada com ID: " + id));
        return categoriaMapper.toDTO(categoria);
    }

    public CategoriaDTO updateCategoria(Long id, CategoriaDTO dto) {
        Categoria categoriaExistente = categoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada para atualização com ID: " + id));
        categoriaMapper.updateEntityFromDTO(dto, categoriaExistente);
        Categoria atualizado = categoriaRepository.save(categoriaExistente);
        return categoriaMapper.toDTO(atualizado);
    }

    public void deleteCategoria(Long id) {
        if (!categoriaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Categoria não encontrada para deleção com ID: " + id);
        }
        categoriaRepository.deleteById(id);
    }
}
