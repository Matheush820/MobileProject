package com.salafacil.SalaFacilSpace.config;

import com.salafacil.SalaFacilSpace.entity.Categoria;
import com.salafacil.SalaFacilSpace.entity.Horario;
import com.salafacil.SalaFacilSpace.repository.CategoriaRepository;
import com.salafacil.SalaFacilSpace.repository.CursoRepository;
import com.salafacil.SalaFacilSpace.repository.HorarioRepository;
import com.salafacil.SalaFacilSpace.repository.LaboratorioRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataSeeder {

    private final CategoriaRepository categoriaRepository;
    private final CursoRepository cursoRepository;
    private final LaboratorioRepository laboratorioRepository;
    private final HorarioRepository horarioRepository;

    @PostConstruct
    public void seedDatabase() {
        log.info("🚀 Iniciando DataSeeder...");
        seedCategorias();
        seedHorarios();
        log.info("✅ DataSeeder finalizado com sucesso.");
    }

    private void seedCategorias() {
        if (categoriaRepository.count() == 0) {
            log.info("📦 Inserindo categorias...");
            categoriaRepository.saveAll(List.of(
                    new Categoria(null, "Tecnologia da Informação", "Cursos ligados à área de computação e sistemas."),
                    new Categoria(null, "Saúde", "Cursos da área da saúde e bem-estar."),
                    new Categoria(null, "Engenharia", "Cursos voltados para engenharia e construção."),
                    new Categoria(null, "Ciências Humanas", "Cursos relacionados à área de humanas."),
                    new Categoria(null, "Ciências Exatas", "Cursos de matemática, física, química e correlatos."),
                    new Categoria(null, "Ciências Biológicas", "Cursos de biologia, biomedicina, genética e afins.")
            ));
            log.info("✅ Categorias inseridas.");
        } else {
            log.info("🔸 Categorias já existentes. Seed ignorado.");
        }
    }

    private void seedHorarios() {
        if (horarioRepository.count() == 0) {
            log.info("📅 Inserindo horários...");
            horarioRepository.saveAll(List.of(
                    // Manhã
                    criarHorario("Segunda-feira", 7, 30, 11, 30, "MANHA"),
                    criarHorario("Terça-feira", 7, 30, 11, 30, "MANHA"),
                    criarHorario("Quarta-feira", 7, 30, 11, 30, "MANHA"),
                    criarHorario("Quinta-feira", 7, 30, 11, 30, "MANHA"),
                    criarHorario("Sexta-feira", 7, 30, 11, 30, "MANHA"),

                    // Noite
                    criarHorario("Segunda-feira", 18, 30, 22, 0, "NOITE"),
                    criarHorario("Terça-feira", 18, 30, 22, 0, "NOITE"),
                    criarHorario("Quarta-feira", 19, 30, 22, 0, "NOITE"),
                    criarHorario("Quinta-feira", 19, 30, 22, 0, "NOITE"),
                    criarHorario("Sexta-feira", 18, 30, 22, 0, "NOITE")
            ));
            log.info("✅ Horários inseridos.");
        } else {
            log.info("🔸 Horários já existentes. Seed ignorado.");
        }
    }

    // Método auxiliar pra não repetir código até a eternidade
    private Horario criarHorario(String dia, int horaInicio, int minutoInicio, int horaFim, int minutoFim, String turno) {
        return new Horario(
                null,
                dia,
                LocalTime.of(horaInicio, minutoInicio),
                LocalTime.of(horaFim, minutoFim),
                turno
        );
    }
}
