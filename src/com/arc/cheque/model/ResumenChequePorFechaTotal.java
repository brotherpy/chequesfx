/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arc.cheque.model;

/**
 *
 * @author Jorge Fabio
 */
public class ResumenChequePorFechaTotal {
    private String fecha;
    private String monto;
    private String diasVencimiento;

    public ResumenChequePorFechaTotal() {
    }

    public ResumenChequePorFechaTotal(String fecha, String monto, String diasVencimiento) {
        this.fecha = fecha;
        this.monto = monto;
        this.diasVencimiento = diasVencimiento;
    }

    public String getDiasVencimiento() {
        return diasVencimiento;
    }

    public void setDiasVencimiento(String diasVencimiento) {
        this.diasVencimiento = diasVencimiento;
    }



    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }
    
    
    
}
