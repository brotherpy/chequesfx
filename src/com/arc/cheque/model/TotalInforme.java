/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arc.cheque.model;

import java.time.LocalDate;


/**
 *
 * @author jorge
 */
public class TotalInforme {

    private int semana;
    private LocalDate fecha;
    private double monto;
    private boolean pagado;

    public TotalInforme() {
    }

    public TotalInforme(int semana, LocalDate fecha, double monto, boolean pagado) {
        this.semana = semana;
        this.fecha = fecha;
        this.monto = monto;
        this.pagado = pagado;
    }

    public int getSemana() {
        return semana;
    }

    public void setSemana(int semana) {
        this.semana = semana;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public boolean isPagado() {
        return pagado;
    }

    public void setPagado(boolean pagado) {
        this.pagado = pagado;
    }
    
    

}