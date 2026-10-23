package com.ejemplo.biblioteca.service;
import com.ejemplo.biblioteca.model.*;
import com.ejemplo.biblioteca.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Service
public class BibliotecaService {
    @Autowired private LectorRepository lectorRepo;
    @Autowired private LibroRepository libroRepo;
    @Autowired private PrestamoRepository prestamoRepo;

    public Prestamo solicitarPrestamo(Long lectorId, Long libroId) {
        Lector lector = lectorRepo.findById(lectorId).orElseThrow(() -> new RuntimeException("Lector no existe"));
        if (!lector.isActivo()) throw new RuntimeException("Lector inactivo");

        Libro libro = libroRepo.findById(libroId).orElseThrow(() -> new RuntimeException("Libro no existe"));
        if (libro.getEjemplaresDisponibles() <= 0) throw new RuntimeException("Sin ejemplares disponibles");

        int maxPrestamos = lector.isMembresiaPremium() ? 5 : 3;
        int prestamosActivos = prestamoRepo.countByLectorIdAndEstadoIn(lectorId, Arrays.asList(EstadoPrestamo.PRESTADO, EstadoPrestamo.VENCIDO));
        if (prestamosActivos >= maxPrestamos) throw new RuntimeException("Límite de préstamos alcanzado");

        libro.setEjemplaresDisponibles(libro.getEjemplaresDisponibles() - 1);
        libroRepo.save(libro);

        Prestamo prestamo = new Prestamo();
        prestamo.setLector(lector);
        prestamo.setLibro(libro);
        prestamo.setFechaPrestamo(LocalDate.now());
        prestamo.setFechaDevolucionMaxima(LocalDate.now().plusDays(14));
        prestamo.setEstado(EstadoPrestamo.PRESTADO);
        return prestamoRepo.save(prestamo);
    }

    public Prestamo devolverLibro(Long prestamoId) {
        Prestamo prestamo = prestamoRepo.findById(prestamoId).orElseThrow(() -> new RuntimeException("Préstamo no existe"));
        if (prestamo.getEstado() == EstadoPrestamo.DEVUELTO) throw new RuntimeException("El libro ya fue devuelto");

        prestamo.setEstado(EstadoPrestamo.DEVUELTO);
        
        Libro libro = prestamo.getLibro();
        libro.setEjemplaresDisponibles(libro.getEjemplaresDisponibles() + 1);
        libroRepo.save(libro);

        if (LocalDate.now().isAfter(prestamo.getFechaDevolucionMaxima())) {
            Lector lector = prestamo.getLector();
            lector.setActivo(false);
            lectorRepo.save(lector);
        }
        return prestamoRepo.save(prestamo);
    }

    public Prestamo renovarPrestamo(Long prestamoId) {
        Prestamo prestamo = prestamoRepo.findById(prestamoId).orElseThrow(() -> new RuntimeException("Préstamo no existe"));
        if (prestamo.getEstado() != EstadoPrestamo.PRESTADO) throw new RuntimeException("Solo se pueden renovar libros PRESTADOS (no vencidos ni devueltos)");

        prestamo.setFechaDevolucionMaxima(prestamo.getFechaDevolucionMaxima().plusDays(7));
        return prestamoRepo.save(prestamo);
    }

    public Lector rehabilitarLector(Long lectorId) {
        Lector lector = lectorRepo.findById(lectorId).orElseThrow(() -> new RuntimeException("Lector no existe"));
        lector.setActivo(true);
        return lectorRepo.save(lector);
    }

    public List<Prestamo> listarPrestamosActivos(Long lectorId) {
        return prestamoRepo.findByLectorIdAndEstadoIn(lectorId, Arrays.asList(EstadoPrestamo.PRESTADO, EstadoPrestamo.VENCIDO));
    }
}
