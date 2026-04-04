package com.fletes.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.sql.Timestamp;

public class ReservaRequestDTO {

    @NotNull(message = "El ID del cliente es obligatorio")
    private Integer idCliente;

    private Integer idCamion;

    @NotNull(message = "La fecha de inicio es obligatoria")
    private Timestamp fechaDesde;

    @NotNull(message = "La fecha de finalización es obligatoria")
    private Timestamp fechaHasta;

    @NotBlank(message = "El lugar de origen no puede estar vacío")
    @Size(max = 50, message = "El lugar de origen no puede exceder los 50 caracteres")
    private String lugarDesde;

    @NotBlank(message = "El lugar de destino no puede estar vacío")
    @Size(max = 50, message = "El lugar de destino no puede exceder los 50 caracteres")
    private String lugarHasta;

    @NotNull(message = "El peso de la carga es obligatorio")
    @Positive(message = "El peso de la carga debe ser mayor a cero")
    private float pesoCargaKg;


    // Getters and Setters

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public Integer getIdCamion() {
        return idCamion;
    }

    public void setIdCamion(Integer idCamion) {
        this.idCamion = idCamion;
    }

    public Timestamp getFechaDesde() {
        return fechaDesde;
    }

    public void setFechaDesde(String fechaDesde) {
        this.fechaDesde = Timestamp.valueOf(fechaDesde);
    }

    public Timestamp getFechaHasta() {
        return fechaHasta;
    }

    public void setFechaHasta(String fechaHasta) {
        this.fechaHasta = Timestamp.valueOf(fechaHasta);
    }

    public String getLugarDesde() {
        return lugarDesde;
    }

    public void setLugarDesde(String lugarDesde) {
        this.lugarDesde = lugarDesde;
    }

    public String getLugarHasta() {
        return lugarHasta;
    }

    public void setLugarHasta(String lugarHasta) {
        this.lugarHasta = lugarHasta;
    }

    public float getPesoCargaKg() {
        return pesoCargaKg;
    }

    public void setPesoCargaKg(float pesoCargaKg) {
        this.pesoCargaKg = pesoCargaKg;
    }
}