/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arc.cheque.model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author jorge
 */

public class ReporteChequera implements Serializable {

    private int nro;
    private int cheque;
    private Date cobro;
    private String orden;
    private Double monto;
    private String pago;
    private Double emitidos;
    private Double pendientes;
    private Double cobrados;
    
    public ReporteChequera() {
    }

    public ReporteChequera(Integer nro) {
        this.nro = nro;
    }

    public int getNro() {
        return nro;
    }

    public void setNro(int nro) {
        this.nro = nro;
    }

    public int getCheque() {
        return cheque;
    }

    public void setCheque(int cheque) {
        this.cheque = cheque;
    }

    public Date getCobro() {
        return cobro;
    }

    public void setCobro(Date cobro) {
        this.cobro = cobro;
    }

    public String getOrden() {
        return orden;
    }

    public void setOrden(String orden) {
        this.orden = orden;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public String getPago() {
        return pago;
    }

    public void setPago(String pago) {
        this.pago = pago;
    }

    public Double getEmitidos() {
        return emitidos;
    }

    public void setEmitidos(Double emitidos) {
        this.emitidos = emitidos;
    }

    public Double getPendientes() {
        return pendientes;
    }

    public void setPendientes(Double pendientes) {
        this.pendientes = pendientes;
    }

    public Double getCobrados() {
        return cobrados;
    }

    public void setCobrados(Double cobrados) {
        this.cobrados = cobrados;
    }
    
}