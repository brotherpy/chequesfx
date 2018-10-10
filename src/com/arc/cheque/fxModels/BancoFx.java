/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arc.cheque.fxModels;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Brother
 */
public class BancoFx {
    private final IntegerProperty id;
    private final StringProperty banco;

    public BancoFx(Integer dataId, String dataBanco) {
        this.id = new SimpleIntegerProperty(dataId);
        this.banco = new SimpleStringProperty(dataBanco);
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

    public final String getBanco() {
        return banco.get();
    }

    public final void setBanco(String value) {
        banco.set(value);
    }

    public StringProperty bancoProperty() {
        return banco;
    }


   
    
    
    
}
