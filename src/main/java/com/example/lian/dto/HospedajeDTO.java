package com.example.lian.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class HospedajeDTO {
    @NotBlank
    private String nombreHuesped;
    @NotBlank
    private String tipoHabitacion;
    @NotNull @Min(1)
    private Integer numeroNoches;
    @NotNull @Min(1)
    private Integer numeroHuespedes;
    @NotBlank
    private String temporada;
    @NotNull
    private Boolean incluyeDesayuno;
    @NotNull
    private Boolean incluyeEstacionamiento;
}