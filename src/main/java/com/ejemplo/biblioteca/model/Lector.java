package com.ejemplo.biblioteca.model;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Lector {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private boolean activo = true;
    private boolean membresiaPremium;
}
