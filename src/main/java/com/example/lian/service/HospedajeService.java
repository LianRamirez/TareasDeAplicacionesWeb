package com.example.lian.service;

import com.example.lian.dto.HospedajeDTO;
import com.example.lian.exception.CotizacionException;
import org.springframework.stereotype.Service;

@Service
public class HospedajeService {
    public double cotizarHospedaje(HospedajeDTO dto) {
        if (dto.getNumeroNoches() > 30) throw new CotizacionException("Máximo 30 noches");
        
        int capacidad = switch (dto.getTipoHabitacion().toUpperCase()) {
            case "INDIVIDUAL" -> 1;
            case "DOBLE" -> 2;
            case "SUITE" -> 4;
            default -> throw new CotizacionException("Tipo de habitación inválido");
        };

        if (dto.getNumeroHuespedes() > capacidad) throw new CotizacionException("Excede la capacidad de la habitación");

        double costoPorNoche = switch (dto.getTipoHabitacion().toUpperCase()) {
            case "INDIVIDUAL" -> 700.0;
            case "DOBLE" -> 1100.0;
            case "SUITE" -> 1800.0;
            default -> 0.0;
        };

        double subtotal = costoPorNoche * dto.getNumeroNoches();

        if (dto.getTemporada().equalsIgnoreCase("BAJA")) subtotal *= 0.90;
        else if (dto.getTemporada().equalsIgnoreCase("ALTA")) subtotal *= 1.25;

        if (dto.getNumeroNoches() >= 7) subtotal *= 0.92; // 8% descuento adicional

        double extras = 0;
        if (dto.getIncluyeDesayuno()) extras += 150 * dto.getNumeroHuespedes() * dto.getNumeroNoches();
        if (dto.getIncluyeEstacionamiento()) extras += 100 * dto.getNumeroNoches();

        double totalSinImpuestos = subtotal + extras;
        double impuesto = totalSinImpuestos * 0.04;

        return totalSinImpuestos + impuesto;
    }
}