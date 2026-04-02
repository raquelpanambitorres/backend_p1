package com.fletes.domain;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "camion")
public class Camion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre", length = 50, nullable = false)
    private String nombre;

    @Column(name = "capacidad_carga_kg", nullable = false)
    private Float capacidadCargaKg;

    @Column(name = "estado", nullable = false)
    private Boolean estado;

    @Column(name = "fecha_alta", updatable = false)
    private Timestamp fechaAlta;

    @Column(name = "fecha_ultima_modificacion")
    private Timestamp fechaUltimaModificacion;

    // Métodos de ciclo de vida
    @PrePersist
    protected void onCreate() {
        this.fechaAlta = new Timestamp(System.currentTimeMillis());
        this.fechaUltimaModificacion = this.fechaAlta;
        if (this.estado == null) {
            this.estado = true; // Por defecto activo
        }
    }

    @PreUpdate
    protected void onUpdate() {
        this.fechaUltimaModificacion = new Timestamp(System.currentTimeMillis());
    }

    // Getters and Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Timestamp getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Timestamp fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public Timestamp getFechaUltimaModificacion() {
        return fechaUltimaModificacion;
    }

    public void setFechaUltimaModificacion(Timestamp fechaUltimaModificacion) {
        this.fechaUltimaModificacion = fechaUltimaModificacion;
    }
}
