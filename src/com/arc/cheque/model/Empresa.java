/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arc.cheque.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author jorge
 */
@Entity
@Table(name = "empresa", catalog = "cheque", schema = "public")

public class Empresa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 30)
    private String nombre;

    @Column(name = "direccion", nullable = false, length = 120)
    private String direccion;

    @Column(name = "ruc", nullable = false, length = 13)
    private String ruc;

    @Column(name = "telefono", nullable = false, length = 13)
    private String telefono;

    public Empresa() {
    }

    public Empresa(Integer id) {
        this.id = id;
    }

    public Empresa(Integer id, String nombre, String direccion, String ruc, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.ruc = ruc;
        this.telefono = telefono;
    }

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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
}
