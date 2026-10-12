package com.example.lian.controller;

import com.example.lian.dto.*;
import com.example.lian.service.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/cotizaciones")
@RequiredArgsConstructor
public class CotizadorController {

    private final EnvioService envioService;
    private final RentaService rentaService;
    private final HospedajeService hospedajeService;

    @PostMapping("/envio")
    public ResponseEntity<Map<String, Object>> cotizarEnvio(@Valid @RequestBody EnvioDTO dto) {
        double total = envioService.cotizarEnvio(dto);
        return responder(total);
    }

    @PostMapping("/renta")
    public ResponseEntity<Map<String, Object>> cotizarRenta(@Valid @RequestBody RentaDTO dto) {
        double total = rentaService.cotizarRenta(dto);
        return responder(total);
    }

    @PostMapping("/hospedaje")
    public ResponseEntity<Map<String, Object>> cotizarHospedaje(@Valid @RequestBody HospedajeDTO dto) {
        double total = hospedajeService.cotizarHospedaje(dto);
        return responder(total);
    }

    private ResponseEntity<Map<String, Object>> responder(double total) {
        Map<String, Object> response = new HashMap<>();
        response.put("mensaje", "Cotización exitosa");
        response.put("costoTotal", total);
        return ResponseEntity.ok(response);
    }
}