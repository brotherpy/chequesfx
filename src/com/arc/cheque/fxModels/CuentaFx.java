/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arc.cheque.fxModels;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javax.print.DocFlavor;

/**
 *
 * @author Brother
 */
public class CuentaFx {
    private final IntegerProperty id;
    private final StringProperty cuenta;
    private final StringProperty banco;
    private final IntegerProperty bancoId;
    private final IntegerProperty moneda;


   //Object dataBanco,

    public CuentaFx(Integer dataId, String dataCuenta, String dataBanco, Integer dataBancoId , Integer dataMoneda) {
         this.id = new SimpleIntegerProperty(dataId);
        this.cuenta = new SimpleStringProperty(dataCuenta);
        this.banco = new SimpleStringProperty(dataBanco);
        this.bancoId = new SimpleIntegerProperty(dataBancoId);
        this.moneda = new SimpleIntegerProperty(dataMoneda);

    }

    public final String getBanco() {
        return banco.get();
    }

    public final void setBanco(String value) {
        banco.set(value);
    }

    public StringProperty bancoProperty() {
        return banco;
    }

    public final int getBancoId() {
        return bancoId.get();
    }

    public final void setBancoId(int value) {
        bancoId.set(value);
    }

    public IntegerProperty bancoIdProperty() {
        return bancoId;
    }
    
    

    public final int getId() {
        return id.get();
    }

    public final void setId(int value) {
        id.set(value);
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public final String getCuenta() {
        return cuenta.get();
    }

    public final void setCuenta(String value) {
        cuenta.set(value);
    }

    public StringProperty cuentaProperty() {
        return cuenta;
    }

    public final int getMoneda() {
        return moneda.get();
    }

    public final void setMoneda(int value) {
        moneda.set(value);
    }

    public IntegerProperty monedaProperty() {
        return moneda;
    }
    
    
    
    
    
      
  
    
    
}
