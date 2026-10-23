package com.ejemplo.biblioteca.repository;
import com.ejemplo.biblioteca.model.Prestamo;
import com.ejemplo.biblioteca.model.EstadoPrestamo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {
    int countByLectorIdAndEstadoIn(Long lectorId, List<EstadoPrestamo> estados);
    List<Prestamo> findByLectorIdAndEstadoIn(Long lectorId, List<EstadoPrestamo> estados);
}
