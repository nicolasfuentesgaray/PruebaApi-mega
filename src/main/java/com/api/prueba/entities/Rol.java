
package com.api.prueba.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Table(name = "roles")
@Entity
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Integer id;

    @Column(unique = true, nullable = false)
    @Enumerated(EnumType.STRING)
    private RolEnum nombre;

    @Column(nullable = false)
    private String descripcion;

    @CreationTimestamp
    @Column(updatable = false, name = "fecha_creacion")
    private Date fecha_creacion;

    @UpdateTimestamp
    @Column(name = "fecha_actualizacion")
    private Date fecha_actualizacion;

    public Integer getId() {
        return id;
    }

    public RolEnum getNombre() {
        return nombre;
    }

    public Rol setNombre(RolEnum name) {
        this.nombre = name;
        return this;
    }

    public String getDescription() {
        return descripcion;
    }

    public Rol setDescripcion(String description) {
        this.descripcion = description;
        return this;
    }

    public Date getFecha_creacion() {
        return fecha_creacion;
    }

    public Rol setFecha_creacion(Date fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
        return this;
    }

    public Date getFecha_actualizacion() {
        return fecha_actualizacion;
    }

    public Rol setFecha_actualizacion(Date fecha_actualizacion) {
        this.fecha_actualizacion = fecha_actualizacion;
        return this;
    }

    @Override
    public String toString() {
        return "Rol{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", fecha_creacion=" + fecha_creacion +
                ", fecha_actualizacion=" + fecha_actualizacion +
                '}';
    }
}

