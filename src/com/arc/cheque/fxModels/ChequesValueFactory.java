/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arc.cheque.fxModels;

import com.arc.cheque.model.Cheque;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

/**
 *
 * @author Brother
 */
public class ChequesValueFactory implements Callback<TableColumn.CellDataFeatures<Cheque, CheckBox>, ObservableValue<CheckBox>> {

    @Override
    public ObservableValue<CheckBox> call(TableColumn.CellDataFeatures<Cheque, CheckBox> param) {
        Cheque cheque = param.getValue();
        CheckBox checkBox = new CheckBox();
        checkBox.selectedProperty().setValue(cheque.getPagado());
        checkBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            cheque.setPagado(newValue);
        });
        return new SimpleObjectProperty<>(checkBox);
    }
    
}
