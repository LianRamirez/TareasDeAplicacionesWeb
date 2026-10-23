package com.ejemplo.biblioteca.model;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Data
public class Prestamo {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "lector_id")
    private Lector lector;
    
    @ManyToOne
    @JoinColumn(name = "libro_id")
    private Libro libro;
    
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucionMaxima;
    
    @Enumerated(EnumType.STRING)
    private EstadoPrestamo estado;
}
