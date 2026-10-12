package com.example.lian.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class EnvioDTO {
    @NotBlank
    private String codigoPostal;
    @NotNull @Positive
    private Double pesoKg;
    @NotNull @Positive
    private Double largoCm;
    @NotNull @Positive
    private Double anchoCm;
    @NotNull @Positive
    private Double altoCm;
    @NotBlank
    private String tipoEnvio;
    @NotNull @PositiveOrZero
    private Double valorDeclarado;
}