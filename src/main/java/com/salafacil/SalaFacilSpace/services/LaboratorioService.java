package com.salafacil.SalaFacilSpace.services;

import com.salafacil.SalaFacilSpace.entity.Laboratorio;
import com.salafacil.SalaFacilSpace.exception.ResourceNotFoundException;
import com.salafacil.SalaFacilSpace.repository.LaboratorioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LaboratorioService {

    @Autowired
    private LaboratorioRepository laboratorioRepository;

    public List<Laboratorio> getAll() {
        return laboratorioRepository.findAll();
    }

    public Laboratorio getById(Long id) {
        return laboratorioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Laboratório não encontrado com ID: " + id));
    }

    public Laboratorio create(Laboratorio laboratorio) {
        return laboratorioRepository.save(laboratorio);
    }

    public Laboratorio update(Long id, Laboratorio laboratorio) {
        Laboratorio existente = getById(id);
        laboratorio.setId(existente.getId());
        return laboratorioRepository.save(laboratorio);
    }

    public void delete(Long id) {
        Laboratorio existente = getById(id);
        laboratorioRepository.delete(existente);
    }
}
