package com.fletes.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CamionRequestDTO {

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(max = 50, message = "El nombre no puede exceder los 50 caracteres")
    private String nombre;

    @NotNull(message = "La capacidad de carga es obligatoria")
    @Positive(message = "La capacidad de carga debe ser mayor a cero")
    private Float capacidadCargaKg;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Float getCapacidadCargaKg() {
        return capacidadCargaKg;
    }

    public void setCapacidadCargaKg(Float capacidadCargaKg) {
        this.capacidadCargaKg = capacidadCargaKg;
    }
}
