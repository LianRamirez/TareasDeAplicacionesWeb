package com.juego.casino.model;

import jakarta.persistence.*;

@Entity
public class Tiro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer valorDado1;
    private Integer valorDado2;
    private Integer suma;
    private boolean esGanador;

    @ManyToOne
    @JoinColumn(name = "partida_id")
    private Partida partida;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Integer getValorDado1() { return valorDado1; }
    public void setValorDado1(Integer valorDado1) { this.valorDado1 = valorDado1; }
    public Integer getValorDado2() { return valorDado2; }
    public void setValorDado2(Integer valorDado2) { this.valorDado2 = valorDado2; }
    public Integer getSuma() { return suma; }
    public void setSuma(Integer suma) { this.suma = suma; }
    public boolean isEsGanador() { return esGanador; }
    public void setEsGanador(boolean esGanador) { this.esGanador = esGanador; }
    public Partida getPartida() { return partida; }
    public void setPartida(Partida partida) { this.partida = partida; }
}