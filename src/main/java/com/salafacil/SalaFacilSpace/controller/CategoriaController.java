// Pacote onde está essa classe Controller (organiza o código em pastas lógicas)
package com.salafacil.SalaFacilSpace.controller;

// Importando anotações e classes úteis do Spring e do Jakarta para nosso controller
import org.springframework.beans.factory.annotation.Autowired; // Injeta dependências automaticamente
import org.springframework.http.ResponseEntity; // Classe usada pra retornar respostas HTTP com status e corpo
import org.springframework.web.bind.annotation.*; // Conjunto de anotações para construir APIs REST
import jakarta.validation.Valid; // Valida os dados que chegam no corpo da requisição

// Importando a entidade Categoria e o service que vamos usar nesse controller
import com.salafacil.SalaFacilSpace.entity.Categoria;
import com.salafacil.SalaFacilSpace.services.CategoriaService;

// Diz ao Spring que essa classe é um controller REST, que lida com requisições HTTP e retorna JSON
@RestController
// Define a rota base da API. Tudo aqui começa com /api/categorias
@RequestMapping("/api/categorias")
public class CategoriaController {

    // Injeta automaticamente a instância de CategoriaService (pra usar os métodos que fazem o "trabalho pesado")
    @Autowired
    private CategoriaService categoriaService;

    // -------------------- CRUD - Create --------------------

    // Define que esse método responde a requisiç221112ões POST em /api/categorias
    // É usado pra criar uma nova categoria
    @PostMapping
    public ResponseEntity<Categoria> createCategoria(@Valid @RequestBody Categoria categoria) {
        // Chama o service pra criar a categoria (a lógica está lá no service)
        Categoria categoriaCriada = categoriaService.createCategoria(categoria);
        // Retorna a categoria criada com status 200 OK
        return ResponseEntity.ok(categoriaCriada);
    }

    // -------------------- CRUD - Read (GET All) --------------------

    // Define que esse método responde a requisições GET em /api/categorias
    // É usado pra listar todas as categorias
    @GetMapping
    public ResponseEntity<Iterable<Categoria>> getAllCategorias() {
        // Pega a lista de categorias do service
        Iterable<Categoria> categorias = categoriaService.getAllCategorias();
        // Retorna a lista com status 200 OK
        return ResponseEntity.ok(categorias);
    }

    // -------------------- CRUD - Read (GET by ID) --------------------

    // Define que esse método responde a GET em /api/categorias/{id}
    // Ou seja, busca uma categoria específica pelo ID informado na URL
    @GetMapping("/{id}")
    public ResponseEntity<Categoria> getCategoriaById(@PathVariable Long id) {
        // Busca a categoria pelo ID
        Categoria categoria = categoriaService.getCategoriaById(id);
        // Se achou, retorna 200 OK com a categoria, senão 404 Not Found
        return categoria != null ? ResponseEntity.ok(categoria) : ResponseEntity.notFound().build();
    }

    // -------------------- CRUD - Update --------------------

    // Define que esse método responde a PUT em /api/categorias/{id}
    // Serve pra atualizar uma categoria existente
    @PutMapping("/{id}")
    public ResponseEntity<Categoria> updateCategoria(@PathVariable Long id, @Valid @RequestBody Categoria categoria) {
        // Chama o service pra atualizar com o ID e os novos dados enviados
        Categoria categoriaAtualizada = categoriaService.updateCategoria(id, categoria);
        // Se atualizou com sucesso, retorna 200 OK, senão 404 Not Found
        return categoriaAtualizada != null ? ResponseEntity.ok(categoriaAtualizada) : ResponseEntity.notFound().build();
    }

    // -------------------- CRUD - Delete --------------------

    // Define que esse método responde a DELETE em /api/categorias/{id}
    // Serve pra deletar uma categoria específica
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoria(@PathVariable Long id) {
        // Chama o service pra tentar deletar a categoria
        boolean deletado = categoriaService.deleteCategoria(id);
        // Se deletou, retorna 204 No Content (sem corpo), senão 404 Not Found
        return deletado ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
