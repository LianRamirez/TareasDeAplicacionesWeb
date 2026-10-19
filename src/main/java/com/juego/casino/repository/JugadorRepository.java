package com.juego.casino.repository;
import com.juego.casino.model.Jugador;
import org.springframework.data.jpa.repository.JpaRepository;
public interface JugadorRepository extends JpaRepository<Jugador, Long> { }