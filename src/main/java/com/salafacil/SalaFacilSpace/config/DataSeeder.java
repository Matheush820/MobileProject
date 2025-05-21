package com.salafacil.SalaFacilSpace.config;

import com.salafacil.SalaFacilSpace.entity.*;
import com.salafacil.SalaFacilSpace.repository.*;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataSeeder {

    private final CategoriaRepository categoriaRepository;
    private final CursoRepository cursoRepository;
    private final LaboratorioRepository laboratorioRepository;
    private final HorarioRepository horarioRepository;

    @PostConstruct
    public void seedDatabase() {
        if (categoriaRepository.count() == 0) {
            System.out.println("DataSeeder foi instanciado!");

            // CATEGORIAS
            Categoria catTI = categoriaRepository.save(new Categoria(null, "Tecnologia da Informação", "Cursos ligados à área de computação e sistemas."));
            Categoria catSaude = categoriaRepository.save(new Categoria(null, "Saúde", "Cursos da área da saúde e bem-estar."));
            Categoria catEng = categoriaRepository.save(new Categoria(null, "Engenharia", "Cursos voltados para engenharia e construção."));
            Categoria catHumanas = categoriaRepository.save(new Categoria(null, "Ciências Humanas", "Cursos relacionados à área de humanas."));
            Categoria catExatas = categoriaRepository.save(new Categoria(null, "Ciências Exatas", "Cursos de matemática, física, química e correlatos."));
            Categoria catBiologicas = categoriaRepository.save(new Categoria(null, "Ciências Biológicas", "Cursos de biologia, biomedicina, genética e afins."));
            System.out.println(">>> DataSeeder.seedDatabase() iniciado!");

            // CURSOS (você pode preencher depois)

            // LABORATÓRIOS (preencher depois)

            // HORÁRIOS
            horarioRepository.saveAll(List.of(
                    // Manhã
                    new Horario(null, "Segunda-feira", LocalTime.of(7, 30), LocalTime.of(11, 30), "MANHA"),
                    new Horario(null, "Terça-feira", LocalTime.of(7, 30), LocalTime.of(11, 30), "MANHA"),
                    new Horario(null, "Quarta-feira", LocalTime.of(7, 30), LocalTime.of(11, 30), "MANHA"),
                    new Horario(null, "Quinta-feira", LocalTime.of(7, 30), LocalTime.of(11, 30), "MANHA"),
                    new Horario(null, "Sexta-feira", LocalTime.of(7, 30), LocalTime.of(11, 30), "MANHA"),

                    // Noite
                    new Horario(null, "Segunda-feira", LocalTime.of(18, 30), LocalTime.of(22, 0), "NOITE"),
                    new Horario(null, "Terça-feira", LocalTime.of(18, 30), LocalTime.of(22, 0), "NOITE"),
                    new Horario(null, "Quarta-feira", LocalTime.of(19, 30), LocalTime.of(22, 0), "NOITE"),
                    new Horario(null, "Quinta-feira", LocalTime.of(19, 30), LocalTime.of(22, 0), "NOITE"),
                    new Horario(null, "Sexta-feira", LocalTime.of(18, 30), LocalTime.of(22, 0), "NOITE")
            ));

            System.out.println("✅ Dados iniciais inseridos com sucesso.");
        }
    }
}
