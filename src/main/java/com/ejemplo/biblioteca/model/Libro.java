package com.ejemplo.biblioteca.model;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Libro {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private int ejemplaresDisponibles;
}
