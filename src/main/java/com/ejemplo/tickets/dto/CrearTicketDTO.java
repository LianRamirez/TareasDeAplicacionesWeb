package com.ejemplo.tickets.dto;
import com.ejemplo.tickets.model.PrioridadTicket;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CrearTicketDTO {
    @NotBlank(message = "La descripción no puede estar vacía")
    private String descripcion;
    @NotNull(message = "La prioridad es requerida")
    private PrioridadTicket prioridad;
    @NotNull(message = "El ID de usuario es requerido")
    private Long usuarioId;
}
