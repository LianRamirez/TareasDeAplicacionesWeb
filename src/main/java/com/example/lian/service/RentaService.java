package com.example.lian.service;

import com.example.lian.dto.RentaDTO;
import com.example.lian.exception.CotizacionException;
import org.springframework.stereotype.Service;

@Service
public class RentaService {
    public double cotizarRenta(RentaDTO dto) {
        if (dto.getEdadConductor() < 18) throw new CotizacionException("El conductor debe ser mayor de 18 años");
        if (dto.getDiasRenta() > 30) throw new CotizacionException("La renta no puede superar los 30 días");
        if (dto.getKilometrosEstimados() > 5000) throw new CotizacionException("Los kilómetros no pueden superar 5000");
        if (dto.getTipoVehiculo().equalsIgnoreCase("CAMIONETA") && dto.getEdadConductor() < 25) 
            throw new CotizacionException("Para CAMIONETA el conductor debe tener al menos 25 años");

        double costoDiario = switch (dto.getTipoVehiculo().toUpperCase()) {
            case "COMPACTO" -> 550.0;
            case "SEDAN" -> 700.0;
            case "SUV" -> 950.0;
            case "CAMIONETA" -> 1200.0;
            default -> throw new CotizacionException("Tipo de vehículo inválido");
        };

        double costoRenta = costoDiario * dto.getDiasRenta();
        double kmIncluidos = dto.getDiasRenta() * 100.0;
        double cargoKmAdicionales = 0;
        
        if (dto.getKilometrosEstimados() > kmIncluidos) {
            cargoKmAdicionales = (dto.getKilometrosEstimados() - kmIncluidos) * 4;
        }

        double total = costoRenta + cargoKmAdicionales;

        if (dto.getEdadConductor() >= 18 && dto.getEdadConductor() <= 24) {
            total *= 1.15; // Agregar 15% al costo de renta + km adicionales
        }

        if (dto.getSeguroCompleto()) {
            total += 180 * dto.getDiasRenta();
        }

        if (dto.getDiasRenta() >= 7) {
            total -= (costoRenta * 0.10); // Descuento 10% solo sobre el costo base de renta
        }

        return total;
    }
}