package com.juego.casino.repository;
import com.juego.casino.model.Partida;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface PartidaRepository extends JpaRepository<Partida, Long> {
    List<Partida> findByJugadorId(Long jugadorId); // Esto busca el historial
}