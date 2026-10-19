package com.juego.casino.service;

import com.juego.casino.dto.IniciarPartidaDTO;
import com.juego.casino.exception.JuegoException;
import com.juego.casino.model.*;
import com.juego.casino.repository.*;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
public class JuegoService {

    private final JugadorRepository jugadorRepo;
    private final PartidaRepository partidaRepo;
    private final TiroRepository tiroRepo;

    public JuegoService(JugadorRepository jugadorRepo, PartidaRepository partidaRepo, TiroRepository tiroRepo) {
        this.jugadorRepo = jugadorRepo;
        this.partidaRepo = partidaRepo;
        this.tiroRepo = tiroRepo;
    }

    public Partida iniciarPartida(IniciarPartidaDTO dto) {
        Jugador jugador = jugadorRepo.findById(dto.getJugadorId())
                .orElseThrow(() -> new JuegoException("El jugador no existe."));

        if (!jugador.isActivo()) {
            throw new JuegoException("El jugador no está activo.");
        }

        if (jugador.getSaldo() < dto.getApuesta()) {
            throw new JuegoException("Saldo insuficiente.");
        }

        jugador.setSaldo(jugador.getSaldo() - dto.getApuesta());
        jugadorRepo.save(jugador);

        Partida partida = new Partida();
        partida.setJugador(jugador);
        partida.setApuesta(dto.getApuesta());
        partida.setFecha(LocalDateTime.now());
        partida.setEstado(EstadoPartida.EN_JUEGO);

        return partidaRepo.save(partida);
    }

    public Tiro realizarTiro(Long partidaId) {
        Partida partida = partidaRepo.findById(partidaId)
                .orElseThrow(() -> new JuegoException("La partida no existe."));

        if (partida.getEstado() != EstadoPartida.EN_JUEGO) {
            throw new JuegoException("La partida no está en juego. Estado actual: " + partida.getEstado());
        }

        Random random = new Random();
        int dado1 = random.nextInt(6) + 1;
        int dado2 = random.nextInt(6) + 1;
        int suma = dado1 + dado2;

        Tiro tiro = new Tiro();
        tiro.setPartida(partida);
        tiro.setValorDado1(dado1);
        tiro.setValorDado2(dado2);
        tiro.setSuma(suma);

        if (suma == 7 || suma == 11) {
            tiro.setEsGanador(true);
            partida.setEstado(EstadoPartida.FINALIZADA);
            
            Jugador jugador = partida.getJugador();
            jugador.setSaldo(jugador.getSaldo() + (partida.getApuesta() * 2));
            jugadorRepo.save(jugador);
            
        } else if (suma == 2 || suma == 3 || suma == 12) {
            tiro.setEsGanador(false);
            partida.setEstado(EstadoPartida.FINALIZADA);
        } else {
            tiro.setEsGanador(false);
        }

        partidaRepo.save(partida);
        return tiroRepo.save(tiro);
    }

    public Partida finalizarPartidaManual(Long partidaId) {
        Partida partida = partidaRepo.findById(partidaId)
                .orElseThrow(() -> new JuegoException("La partida no existe."));
        
        if (partida.getEstado() == EstadoPartida.FINALIZADA) {
            throw new JuegoException("La partida ya estaba finalizada.");
        }
        
        partida.setEstado(EstadoPartida.FINALIZADA);
        return partidaRepo.save(partida);
    }

    public Jugador recargarSaldo(Long jugadorId, Double monto) {
        Jugador jugador = jugadorRepo.findById(jugadorId)
                .orElseThrow(() -> new JuegoException("El jugador no existe."));
        jugador.setSaldo(jugador.getSaldo() + monto);
        return jugadorRepo.save(jugador);
    }

    public List<Partida> obtenerHistorial(Long jugadorId) {
        jugadorRepo.findById(jugadorId)
                .orElseThrow(() -> new JuegoException("El jugador no existe."));
        return partidaRepo.findByJugadorId(jugadorId);
    }

    public Jugador crearJugador(Jugador jugador) {
        jugador.setActivo(true);
        return jugadorRepo.save(jugador);
    }
}