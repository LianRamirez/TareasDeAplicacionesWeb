package com.ejemplo.tickets.model;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tickets")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Ticket {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String descripcion;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoTicket estado;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PrioridadTicket prioridad;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;
}
