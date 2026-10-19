package com.juego.casino.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class IniciarPartidaDTO {
    @NotNull(message = "El ID del jugador es obligatorio")
    private Long jugadorId;

    @NotNull(message = "La apuesta es obligatoria")
    @Min(value = 1, message = "La apuesta mínima es 1")
    private Double apuesta;

    public Long getJugadorId() { return jugadorId; }
    public void setJugadorId(Long jugadorId) { this.jugadorId = jugadorId; }
    public Double getApuesta() { return apuesta; }
    public void setApuesta(Double apuesta) { this.apuesta = apuesta; }
}