package com.juego.casino.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Partida {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime fecha;
    
    @Enumerated(EnumType.STRING)
    private EstadoPartida estado;
    
    private Double apuesta;

    @ManyToOne
    @JoinColumn(name = "jugador_id")
    private Jugador jugador;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }
    public EstadoPartida getEstado() { return estado; }
    public void setEstado(EstadoPartida estado) { this.estado = estado; }
    public Double getApuesta() { return apuesta; }
    public void setApuesta(Double apuesta) { this.apuesta = apuesta; }
    public Jugador getJugador() { return jugador; }
    public void setJugador(Jugador jugador) { this.jugador = jugador; }
}