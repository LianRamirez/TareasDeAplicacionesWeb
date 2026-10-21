package com.ejemplo.tickets.controller;

import com.ejemplo.tickets.dto.CambiarPrioridadDTO;
import com.ejemplo.tickets.dto.CrearTicketDTO;
import com.ejemplo.tickets.model.Ticket;
import com.ejemplo.tickets.service.TicketService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping
    public ResponseEntity<Ticket> crearTicket(@Valid @RequestBody CrearTicketDTO dto) {
        return new ResponseEntity<>(ticketService.crearTicket(dto), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}/siguiente-estado")
    public ResponseEntity<Ticket> actualizarEstadoTicket(@PathVariable Long id) {
        return ResponseEntity.ok(ticketService.actualizarEstadoTicket(id));
    }

    @PatchMapping("/{id}/prioridad")
    public ResponseEntity<Ticket> reasignarPrioridad(
            @PathVariable Long id,
            @Valid @RequestBody CambiarPrioridadDTO dto) {
        return ResponseEntity.ok(ticketService.reasignarPrioridad(id, dto.getNuevaPrioridad()));
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Ticket>> obtenerTicketsUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(ticketService.obtenerTicketsUsuario(usuarioId));
    }
}
