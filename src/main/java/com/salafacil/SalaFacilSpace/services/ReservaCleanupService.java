package com.salafacil.SalaFacilSpace.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.salafacil.SalaFacilSpace.entity.Reserva;
import com.salafacil.SalaFacilSpace.repository.ReservaRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReservaCleanupService {

    @Autowired
    private ReservaRepository reservaRepository;

    @Scheduled(fixedRate = 3600000) // a cada 1 hora
    public void deletarReservasExpiradas() {
        LocalDateTime agora = LocalDateTime.now();
        List<Reserva> expiradas = reservaRepository.findByDataHoraFimBefore(agora);

        if (!expiradas.isEmpty()) {
            reservaRepository.deleteAll(expiradas);
            System.out.println("Reservas expiradas removidas: " + expiradas.size());
        }
    }
}
