package com.salafacil.SalaFacilSpace.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import com.salafacil.SalaFacilSpace.entity.Categoria;
import com.salafacil.SalaFacilSpace.services.CategoriaService;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    // POST - Criar categoria
    @PostMapping
    public ResponseEntity<Categoria> createCategoria(@Valid @RequestBody Categoria categoria) {
        Categoria categoriaCriada = categoriaService.createCategoria(categoria);
        return ResponseEntity.ok(categoriaCriada);
    }

    // GET - Obter todas as categorias
    @GetMapping
    public ResponseEntity<Iterable<Categoria>> getAllCategorias() {
        Iterable<Categoria> categorias = categoriaService.getAllCategorias();
        return ResponseEntity.ok(categorias);
    }

    // GET - Obter uma categoria específica pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<Categoria> getCategoriaById(@PathVariable Long id) {
        Categoria categoria = categoriaService.getCategoriaById(id);
        return categoria != null ? ResponseEntity.ok(categoria) : ResponseEntity.notFound().build();
    }

    // PUT - Atualizar categoria
    @PutMapping("/{id}")
    public ResponseEntity<Categoria> updateCategoria(@PathVariable Long id, @Valid @RequestBody Categoria categoria) {
        Categoria categoriaAtualizada = categoriaService.updateCategoria(id, categoria);
        return categoriaAtualizada != null ? ResponseEntity.ok(categoriaAtualizada) : ResponseEntity.notFound().build();
    }

    // DELETE - Deletar categoria
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoria(@PathVariable Long id) {
        boolean deletado = categoriaService.deleteCategoria(id);
        return deletado ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
