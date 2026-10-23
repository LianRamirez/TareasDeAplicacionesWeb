package com.ejemplo.biblioteca.controller;
import com.ejemplo.biblioteca.dto.PrestamoRequestDTO;
import com.ejemplo.biblioteca.model.*;
import com.ejemplo.biblioteca.service.BibliotecaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/biblioteca")
public class BibliotecaController {
    @Autowired private BibliotecaService service;

    @PostMapping("/prestamos")
    public ResponseEntity<Prestamo> solicitarPrestamo(@Valid @RequestBody PrestamoRequestDTO dto) {
        return ResponseEntity.ok(service.solicitarPrestamo(dto.getLectorId(), dto.getLibroId()));
    }

    @PutMapping("/prestamos/{id}/devolver")
    public ResponseEntity<Prestamo> devolverLibro(@PathVariable Long id) {
        return ResponseEntity.ok(service.devolverLibro(id));
    }

    @PutMapping("/prestamos/{id}/renovar")
    public ResponseEntity<Prestamo> renovarPrestamo(@PathVariable Long id) {
        return ResponseEntity.ok(service.renovarPrestamo(id));
    }

    @PutMapping("/lectores/{id}/rehabilitar")
    public ResponseEntity<Lector> rehabilitarLector(@PathVariable Long id) {
        return ResponseEntity.ok(service.rehabilitarLector(id));
    }

    @GetMapping("/lectores/{id}/prestamos-activos")
    public ResponseEntity<List<Prestamo>> listarPrestamos(@PathVariable Long id) {
        return ResponseEntity.ok(service.listarPrestamosActivos(id));
    }
}
