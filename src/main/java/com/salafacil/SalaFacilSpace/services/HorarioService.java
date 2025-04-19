package com.salafacil.SalaFacilSpace.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.salafacil.SalaFacilSpace.entity.Horario;
import com.salafacil.SalaFacilSpace.repository.HorarioRepository;

@Service
public class HorarioService {

    @Autowired
    private HorarioRepository horarioRepository;

    public Horario createHorario(Horario horario) {
        return horarioRepository.save(horario);
    }
}
