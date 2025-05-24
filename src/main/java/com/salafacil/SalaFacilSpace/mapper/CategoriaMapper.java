// CategoriaMapper.java
package com.salafacil.SalaFacilSpace.mapper;

import com.salafacil.SalaFacilSpace.dto.CategoriaDTO;
import com.salafacil.SalaFacilSpace.entity.Categoria;
import org.springframework.stereotype.Component;

@Component
public class CategoriaMapper {

    // Conversão explícita para evitar código duplicado e facilitar manutenção
    public CategoriaDTO toDTO(Categoria categoria) {
        if (categoria == null) return null;
        return new CategoriaDTO(categoria.getId(), categoria.getNome());
    }

    public Categoria toEntity(CategoriaDTO dto) {
        if (dto == null) return null;
        Categoria categoria = new Categoria();
        categoria.setId(dto.getId());
        categoria.setNome(dto.getNome());
        return categoria;
    }

    // Método para atualizar entidade existente com dados do DTO (útil no update)
    public void updateEntityFromDTO(CategoriaDTO dto, Categoria categoria) {
        // Aqui só atualiza o nome porque o ID não deve mudar
        categoria.setNome(dto.getNome());
    }
}
