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
@Table(name = "configuracion", catalog = "cheque", schema = "public")

public class Configuracion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "empresa", nullable = false, length = 30)
    private String empresa;

    @Column(name = "direccion", nullable = false, length = 120)
    private String direccion;

    @Column(name = "ruc", nullable = false, length = 12)
    private String ruc;

    @Column(name = "email", nullable = false, length = 30)
    private String email;

    @Column(name = "telefono", nullable = false, length = 12)
    private String telefono;

    public Configuracion() {
    }

    public Configuracion(Integer id) {
        this.id = id;
    }

    public Configuracion(Integer id, String empresa, String direccion, String ruc, String email, String telefono) {
        this.id = id;
        this.empresa = empresa;
        this.direccion = direccion;
        this.ruc = ruc;
        this.email = email;
        this.telefono = telefono;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
}
