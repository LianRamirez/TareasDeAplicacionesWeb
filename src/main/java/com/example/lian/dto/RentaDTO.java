package com.example.lian.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class RentaDTO {
    @NotBlank
    private String nombreCliente;
    @NotNull @Min(18)
    private Integer edadConductor;
    @NotBlank
    private String tipoVehiculo;
    @NotNull @Min(1)
    private Integer diasRenta;
    @NotNull @PositiveOrZero
    private Double kilometrosEstimados;
    @NotNull
    private Boolean seguroCompleto;
}