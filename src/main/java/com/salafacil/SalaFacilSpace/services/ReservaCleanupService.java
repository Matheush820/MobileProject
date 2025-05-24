package com.salafacil.SalaFacilSpace.services;

import com.salafacil.SalaFacilSpace.entity.Reserva;
import com.salafacil.SalaFacilSpace.repository.ReservaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j // Logger bonito, estruturado
@Service
@RequiredArgsConstructor // Injeta por construtor, mais limpo e testável
public class ReservaCleanupService {

    private final ReservaRepository reservaRepository;

    @Scheduled(fixedRate = 3600000) // Executa a cada 1 hora
    public void deletarReservasExpiradas() {
        try {
            LocalDateTime agora = LocalDateTime.now();
            List<Reserva> expiradas = reservaRepository.findByDataHoraFimBefore(agora);

            if (!expiradas.isEmpty()) {
                reservaRepository.deleteAll(expiradas);
                log.info("Reservas expiradas removidas: {}", expiradas.size());
            } else {
                log.info("Nenhuma reserva expirada encontrada para remoção.");
            }
        } catch (Exception e) {
            log.error("Erro ao tentar remover reservas expiradas", e);
            // Se quiser, pode lançar uma exception específica ou deixar o log
        }
    }
}
