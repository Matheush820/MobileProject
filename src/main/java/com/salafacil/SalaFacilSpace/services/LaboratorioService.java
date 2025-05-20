package com.salafacil.SalaFacilSpace.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.salafacil.SalaFacilSpace.entity.Laboratorio;
import com.salafacil.SalaFacilSpace.repository.LaboratorioRepository;
import java.util.List;
import java.util.Optional;

@Service
public class LaboratorioService {

    @Autowired
    private LaboratorioRepository laboratorioRepository;

    // Método para listar todos os laboratórios
    public List<Laboratorio> getAllLaboratorios() {
        return laboratorioRepository.findAll();
    }

    // Método para buscar laboratório por ID
    public Optional<Laboratorio> getLaboratorioById(Long id) {
        return laboratorioRepository.findById(id);
    }

    // Método para criar um novo laboratório
    public Laboratorio createLaboratorio(Laboratorio laboratorio) {
        if (laboratorio.getCategoria() == null) {
            throw new IllegalArgumentException("Categoria do laboratório não pode ser nula.");
        }
        return laboratorioRepository.save(laboratorio);
    }

    public Laboratorio updateLaboratorio(Laboratorio laboratorio) {
        if (laboratorio.getCategoria() == null) {
            throw new IllegalArgumentException("Categoria do laboratório não pode ser nula.");
        }
        return laboratorioRepository.save(laboratorio);
    }


    // Método para excluir um laboratório
    public void deleteLaboratorio(Long id) {
        laboratorioRepository.deleteById(id);
    }
}
