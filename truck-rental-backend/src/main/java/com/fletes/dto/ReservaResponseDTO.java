package com.fletes.dto;

import java.sql.Timestamp;

public class ReservaResponseDTO {

    private Integer id;
    private Timestamp fechaReserva;
    private Integer idCliente;
    private Integer idCamion;
    private Timestamp fechaDesde;
    private Timestamp fechaHasta;
    private String lugarDesde;
    private String lugarHasta;
    private float pesoCargaKg;
    private Boolean estado;

    // Getters and Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Timestamp getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(Timestamp fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

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

    public void setFechaDesde(Timestamp fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    public Timestamp getFechaHasta() {
        return fechaHasta;
    }

    public void setFechaHasta(Timestamp fechaHasta) {
        this.fechaHasta = fechaHasta;
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

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
}