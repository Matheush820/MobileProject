package com.salafacil.SalaFacilSpace.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import com.salafacil.SalaFacilSpace.entity.Categoria;
import com.salafacil.SalaFacilSpace.services.CategoriaService;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    // -------------------- CREATE --------------------
    @PostMapping
    public ResponseEntity<Categoria> createCategoria(@Valid @RequestBody Categoria categoria) {
        Categoria categoriaCriada = categoriaService.createCategoria(categoria);
        return ResponseEntity.ok(categoriaCriada);
    }

    // -------------------- READ ALL --------------------
    @GetMapping
    public ResponseEntity<List<Categoria>> getAllCategorias() {
        List<Categoria> categorias = categoriaService.getAllCategorias();
        return ResponseEntity.ok(categorias);
    }

    // -------------------- READ BY ID --------------------
    @GetMapping("/{id}")
    public ResponseEntity<Categoria> getCategoriaById(@PathVariable Long id) {
        Categoria categoria = categoriaService.getCategoriaById(id);
        return ResponseEntity.ok(categoria);
    }

    // -------------------- UPDATE --------------------
    @PutMapping("/{id}")
    public ResponseEntity<Categoria> updateCategoria(@PathVariable Long id, @Valid @RequestBody Categoria categoria) {
        Categoria categoriaAtualizada = categoriaService.updateCategoria(id, categoria);
        return ResponseEntity.ok(categoriaAtualizada);
    }

    // -------------------- DELETE --------------------
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoria(@PathVariable Long id) {
        categoriaService.deleteCategoria(id);
        return ResponseEntity.noContent().build();
    }
}
