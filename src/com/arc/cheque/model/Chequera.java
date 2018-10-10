/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arc.cheque.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.Fetch;

/**
 *
 * @author jorge
 */
@Entity
@Table(name = "chequera", catalog = "cheque", schema = "public")

public class Chequera implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nro", nullable = false)
    private Integer nro;

    @Column(name = "hojas", nullable = false)
    private int hojas;

    @Column(name = "estado", nullable = false)
    private boolean estado;

    @JoinColumn(name = "cuenta", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Cuenta cuenta;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "chequera", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Cheque> chequeList;

    public Chequera() {
    }

    public Chequera(Integer nro) {
        this.nro = nro;
    }

    public Chequera(Integer nro, int hojas, boolean estado) {
        this.nro = nro;
        this.hojas = hojas;
        this.estado = estado;
    }

    public Integer getNro() {
        return nro;
    }

    public void setNro(Integer nro) {
        this.nro = nro;
    }

    public int getHojas() {
        return hojas;
    }

    public void setHojas(int hojas) {
        this.hojas = hojas;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    public List<Cheque> getChequeList() {
        return chequeList;
    }

    public void setChequeList(List<Cheque> chequeList) {
        this.chequeList = chequeList;
    }

}
