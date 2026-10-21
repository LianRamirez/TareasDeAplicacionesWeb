package com.ejemplo.tickets.dto;
import com.ejemplo.tickets.model.PrioridadTicket;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CambiarPrioridadDTO {
    @NotNull(message = "La nueva prioridad es requerida")
    private PrioridadTicket nuevaPrioridad;
}
