package com.ejemplo.tickets.service;

import com.ejemplo.tickets.dto.CrearTicketDTO;
import com.ejemplo.tickets.exception.NegocioException;
import com.ejemplo.tickets.exception.RecursoNoEncontradoException;
import com.ejemplo.tickets.model.*;
import com.ejemplo.tickets.repository.TicketRepository;
import com.ejemplo.tickets.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final UsuarioRepository usuarioRepository;

    public TicketService(TicketRepository ticketRepository, UsuarioRepository usuarioRepository) {
        this.ticketRepository = ticketRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public Ticket crearTicket(CrearTicketDTO dto) {
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RecursoNoEncontradoException("Usuario no encontrado"));

        if (!Boolean.TRUE.equals(usuario.getActivo())) {
            throw new NegocioException("El usuario está inactivo");
        }

        Ticket ticket = new Ticket();
        ticket.setDescripcion(dto.getDescripcion());
        ticket.setPrioridad(dto.getPrioridad());
        ticket.setEstado(EstadoTicket.ABIERTO);
        ticket.setUsuario(usuario);

        return ticketRepository.save(ticket);
    }

    @Transactional
    public Ticket actualizarEstadoTicket(Long ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RecursoNoEncontradoException("Ticket no encontrado"));

        if (ticket.getEstado() == EstadoTicket.ABIERTO) {
            ticket.setEstado(EstadoTicket.EN_PROCESO);
        } else if (ticket.getEstado() == EstadoTicket.EN_PROCESO) {
            ticket.setEstado(EstadoTicket.CERRADO);
        } else {
            throw new NegocioException("El ticket ya está CERRADO");
        }

        return ticketRepository.save(ticket);
    }

    @Transactional
    public Ticket reasignarPrioridad(Long ticketId, PrioridadTicket nuevaPrioridad) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RecursoNoEncontradoException("Ticket no encontrado"));

        if (ticket.getEstado() == EstadoTicket.CERRADO) {
            throw new NegocioException("No se puede cambiar prioridad a ticket CERRADO");
        }

        ticket.setPrioridad(nuevaPrioridad);
        return ticketRepository.save(ticket);
    }

    @Transactional
    public Usuario desactivarUsuario(Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RecursoNoEncontradoException("Usuario no encontrado"));

        usuario.setActivo(false);
        return usuarioRepository.save(usuario);
    }

    public List<Ticket> obtenerTicketsUsuario(Long usuarioId) {
        if (!usuarioRepository.existsById(usuarioId)) {
            throw new RecursoNoEncontradoException("Usuario no encontrado");
        }
        return ticketRepository.findByUsuarioId(usuarioId);
    }

    @Transactional
    public Usuario crearUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }
}
