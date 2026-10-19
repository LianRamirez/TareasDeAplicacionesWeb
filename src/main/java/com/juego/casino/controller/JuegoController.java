package com.juego.casino.controller;

import com.juego.casino.dto.IniciarPartidaDTO;
import com.juego.casino.dto.RecargarSaldoDTO;
import com.juego.casino.model.Jugador;
import com.juego.casino.model.Partida;
import com.juego.casino.model.Tiro;
import com.juego.casino.service.JuegoService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/juego")
public class JuegoController {

    private final JuegoService juegoService;

    public JuegoController(JuegoService juegoService) {
        this.juegoService = juegoService;
    }

    // ruta pa crear un user
    @PostMapping("/jugador")
    public Jugador crearJugador(@RequestBody Jugador jugador) {
        return juegoService.crearJugador(jugador);
    }

    @PostMapping("/iniciar")
    public Partida iniciarPartida(@Valid @RequestBody IniciarPartidaDTO dto) {
        return juegoService.iniciarPartida(dto);
    }

    @PostMapping("/tiro/{partidaId}")
    public Tiro realizarTiro(@PathVariable Long partidaId) {
        return juegoService.realizarTiro(partidaId);
    }

    @PutMapping("/finalizar/{partidaId}")
    public Partida finalizarPartida(@PathVariable Long partidaId) {
        return juegoService.finalizarPartidaManual(partidaId);
    }

    @PutMapping("/recargar/{jugadorId}")
    public Jugador recargarSaldo(@PathVariable Long jugadorId, @Valid @RequestBody RecargarSaldoDTO dto) {
        return juegoService.recargarSaldo(jugadorId, dto.getMonto());
    }

    @GetMapping("/historial/{jugadorId}")
    public List<Partida> obtenerHistorial(@PathVariable Long jugadorId) {
        return juegoService.obtenerHistorial(jugadorId);
    }
}