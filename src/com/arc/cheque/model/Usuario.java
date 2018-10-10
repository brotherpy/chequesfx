/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arc.cheque.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author jorge
 */
@Entity
@Table(name = "usuario", catalog = "cheque", schema = "public")

public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "usuario", nullable = false, length = 30)
    private String usuario;

    @Column(name = "password", nullable = false, length = 12)
    private String password;

    @Column(name = "admin", nullable = false)
    private boolean admin;

    @Column(name = "estado", nullable = false)
    private boolean estado;

    public Usuario() {
    }

    public Usuario(Integer id) {
        this.id = id;
    }

    public Usuario(String usuario, String password, boolean admin, boolean estado) {
        this.usuario = usuario;
        this.password = password;
        this.admin = admin;
        this.estado = estado;
    }

    public Usuario(Integer id, String usuario, String password, boolean admin, boolean estado) {
        this.id = id;
        this.usuario = usuario;
        this.password = password;
        this.admin = admin;
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
}
