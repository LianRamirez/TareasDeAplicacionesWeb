package com.juego.casino.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class RecargarSaldoDTO {
    @NotNull(message = "El monto es obligatorio")
    @Min(value = 1, message = "El monto mínimo es 1")
    private Double monto;

    public Double getMonto() { return monto; }
    public void setMonto(Double monto) { this.monto = monto; }
}