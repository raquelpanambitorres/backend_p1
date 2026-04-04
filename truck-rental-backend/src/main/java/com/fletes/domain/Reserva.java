package com.fletes.domain;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "reserva")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "fecha_reserva", updatable = false)
    private Timestamp fechaReserva;

    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente idCliente;

    @ManyToOne
    @JoinColumn(name = "id_camion", nullable = false)
    private Camion idCamion;

    @Column(name = "fecha_desde", nullable = false)
    private Timestamp fechaDesde;

    @Column(name = "fecha_hasta", nullable = false)
    private Timestamp fechaHasta;

    @Column(name = "lugar_desde", nullable = false)
    private String lugarDesde;

    @Column(name = "lugar_hasta", nullable = false)
    private String lugarHasta;

    @Column(name = "peso_carga_kg", nullable = false)
    private float pesoCargaKg;

    @Column(name = "estado", nullable = false)
    private Boolean estado;

    // Métodos de ciclo de vida
    @PrePersist
    protected void onCreate() {
        this.fechaReserva = new Timestamp(System.currentTimeMillis());
        this.estado = true;
    }

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

    public Cliente getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Cliente idCliente) {
        this.idCliente = idCliente;
    }

    public Camion getIdCamion() {
        return idCamion;
    }

    public void setIdCamion(Camion idCamion) {
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