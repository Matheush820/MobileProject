// CategoriaController.java (ajustes mínimos, só pra usar o novo service sem Optional)
package com.salafacil.SalaFacilSpace.controller;

import com.salafacil.SalaFacilSpace.dto.CategoriaDTO;
import com.salafacil.SalaFacilSpace.exception.ResourceNotFoundException;
import com.salafacil.SalaFacilSpace.services.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @PostMapping
    public ResponseEntity<CategoriaDTO> createCategoria(@Valid @RequestBody CategoriaDTO categoriaDTO) {
        CategoriaDTO criado = categoriaService.createCategoria(categoriaDTO);
        URI location = URI.create("/api/categorias/" + criado.getId());
        return ResponseEntity.created(location).body(criado);
    }

    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> getAllCategorias() {
        List<CategoriaDTO> lista = categoriaService.getAllCategorias();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTO> getCategoriaById(@PathVariable Long id) {
        CategoriaDTO categoria = categoriaService.getCategoriaById(id);
        return ResponseEntity.ok(categoria);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDTO> updateCategoria(@PathVariable Long id,
                                                        @Valid @RequestBody CategoriaDTO categoriaDTO) {
        if (!id.equals(categoriaDTO.getId())) {
            return ResponseEntity.badRequest().build();
        }
        CategoriaDTO atualizado = categoriaService.updateCategoria(id, categoriaDTO);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoria(@PathVariable Long id) {
        categoriaService.deleteCategoria(id);
        return ResponseEntity.noContent().build();
    }
}
