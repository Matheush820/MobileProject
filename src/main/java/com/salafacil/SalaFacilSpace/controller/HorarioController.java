package com.salafacil.SalaFacilSpace.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.salafacil.SalaFacilSpace.entity.Horario;
import com.salafacil.SalaFacilSpace.services.HorarioService;

@RestController  // Define a classe como um controlador REST (vai lidar com requisições HTTP)
@RequestMapping("/api/horarios")  // Define o caminho base da API, nesse caso, /api/horarios
public class HorarioController {

    @Autowired  // O Spring vai injetar o serviço HorarioService aqui automaticamente
    private HorarioService horarioService;

    // Cria um novo horário - Vai ser chamado quando alguém fizer um POST em /api/horarios
    @PostMapping  
    public ResponseEntity<Horario> createHorario(@Valid @RequestBody Horario horario) {
        // Cria o horário, passando os dados recebidos no corpo da requisição
        Horario horarioCriado = horarioService.createHorario(horario);  
        // Retorna o horário criado dentro de um ResponseEntity com o status HTTP 200 (OK)
        return ResponseEntity.ok(horarioCriado);
    }

    // Pega todos os horários - Vai ser chamado quando alguém fizer um GET em /api/horarios
    @GetMapping  
    public ResponseEntity<List<Horario>> getAllHorarios() {
        // Retorna todos os horários dentro de um ResponseEntity com status HTTP 200 (OK)
        return ResponseEntity.ok(horarioService.getAllHorarios());  
    }

    // Pega um horário específico - Vai ser chamado quando alguém fizer um GET em /api/horarios/{id}
    @GetMapping("/{id}")  
    public ResponseEntity<Horario> getHorarioById(@PathVariable Long id) {
        // Tenta buscar o horário com o id passado na URL
        Optional<Horario> horario = horarioService.getHorarioById(id);  
        // Se achar o horário, retorna com status 200 (OK). Se não, retorna status 404 (NOT FOUND)
        return horario.map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
    }

    // Atualiza um horário existente - Vai ser chamado quando alguém fizer um PUT em /api/horarios/{id}
    @PutMapping("/{id}")  
    public ResponseEntity<Horario> updateHorario(@PathVariable Long id, @Valid @RequestBody Horario horario) {
        // Tenta atualizar o horário com o id passado
        Optional<Horario> atualizado = horarioService.updateHorario(id, horario);  
        // Se achar e atualizar, retorna com status 200 (OK). Se não, retorna 404 (NOT FOUND)
        return atualizado.map(ResponseEntity::ok)
                         .orElse(ResponseEntity.notFound().build());
    }

    // Deleta um horário - Vai ser chamado quando alguém fizer um DELETE em /api/horarios/{id}
    @DeleteMapping("/{id}")  
    public ResponseEntity<Void> deleteHorario(@PathVariable Long id) {
        // Tenta deletar o horário com o id passado
        boolean deletado = horarioService.deleteHorario(id);  
        // Se o horário foi deletado com sucesso, retorna status 204 (NO CONTENT). Se não, retorna 404 (NOT FOUND)
        return deletado ? ResponseEntity.noContent().build()
                        : ResponseEntity.notFound().build();
    }
    
    @GetMapping("/disponiveis")
    public ResponseEntity<List<Horario>> listarHorariosDisponiveisPorDataTurnoECursoELab(
            @RequestParam("data") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data,
            @RequestParam("turno") String turno,
            @RequestParam("cursoId") Long cursoId,
            @RequestParam("laboratorioId") Long laboratorioId) {

        List<Horario> horarios = horarioService.buscarHorariosDisponiveis(data, turno, cursoId, laboratorioId);

        if (horarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(horarios);
    }

    @GetMapping("/curso/{cursoId}")
    public ResponseEntity<List<Horario>> listarHorariosPorCurso(@PathVariable Long cursoId) {
        List<Horario> horarios = horarioService.listarHorariosPorCurso(cursoId);
        return ResponseEntity.ok(horarios);
    }
}
