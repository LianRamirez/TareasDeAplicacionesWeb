package com.example.lian.service;

import com.example.lian.dto.EnvioDTO;
import com.example.lian.exception.CotizacionException;
import org.springframework.stereotype.Service;

@Service
public class EnvioService {
    public double cotizarEnvio(EnvioDTO dto) {
        if (dto.getPesoKg() > 50) throw new CotizacionException("El peso no puede superar los 50 kg");
        if (dto.getLargoCm() > 150 || dto.getAnchoCm() > 150 || dto.getAltoCm() > 150) throw new CotizacionException("Ninguna dimensión puede superar 150 cm");
        
        double volumen = dto.getLargoCm() * dto.getAnchoCm() * dto.getAltoCm();
        if (volumen > 1000000) throw new CotizacionException("El volumen no puede superar 1,000,000 cm3");

        double costo = 80.0; // Costo base
        costo += 12 * Math.ceil(dto.getPesoKg()); // $12 por kg

        if (volumen > 50000) costo += 100;

        if (dto.getTipoEnvio().equalsIgnoreCase("EXPRESS")) costo *= 1.40;
        else if (dto.getTipoEnvio().equalsIgnoreCase("MISMO_DIA")) costo *= 1.70;

        if (dto.getValorDeclarado() > 10000) costo += dto.getValorDeclarado() * 0.02;

        return costo;
    }
}