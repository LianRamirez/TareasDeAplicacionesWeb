package com.ejemplo.tickets.controller;

import com.ejemplo.tickets.model.Usuario;
import com.ejemplo.tickets.service.TicketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final TicketService ticketService;

    public UsuarioController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping
    public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario) {
        return new ResponseEntity<>(ticketService.crearUsuario(usuario), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}/desactivar")
    public ResponseEntity<Usuario> desactivarUsuario(@PathVariable Long id) {
        return ResponseEntity.ok(ticketService.desactivarUsuario(id));
    }
}
