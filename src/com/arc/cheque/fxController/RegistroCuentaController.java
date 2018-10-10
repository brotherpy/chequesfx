/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arc.cheque.fxController;

import com.arc.cheque.dao.DaoBanco;
import com.arc.cheque.dao.DaoCuenta;
import com.arc.cheque.fxModels.BancoFx;
import com.arc.cheque.fxModels.CuentaFx;
import com.arc.cheque.model.Banco;
import com.arc.cheque.model.Cuenta;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.StringConverter;

/**
 *
 * @author Brother
 */
public class RegistroCuentaController implements Initializable {

    @FXML
    private TableView<CuentaFx> tablaCuenta;

    @FXML
    private TableColumn<CuentaFx, String> cuentaCol;

    @FXML
    private TableColumn<CuentaFx, String> bancoCol;

    @FXML
    private JFXButton btnNuevo;

    @FXML
    private JFXButton btnCancelar;

    @FXML
    private JFXButton btnEditar;

    @FXML
    private JFXButton btnGuardar;

    @FXML
    private JFXTextField txBusqueda;

    @FXML
    private JFXTextField txCuenta;

    @FXML
    private Label lblCodigo;

    @FXML
    private JFXComboBox<BancoFx> cbBanco;

    @FXML
    private JFXComboBox<String> cbMoneda;
    
    ObservableList<BancoFx> items = FXCollections.observableArrayList();
    private DaoCuenta daoCuenta;
    private Cuenta cuenta;
    private List<Cuenta> cuentas;
    private CuentaFx cuentaFx;
    private BancoFx bancoFx;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //cargarBancos();
        cargarTablaCuentas();
       cbBanco.setItems(items);
        StringConverter<BancoFx> converter =  new StringConverter<BancoFx>() {
            @Override
            public String toString(BancoFx object) {
               return object.getBanco();
            }

            @Override
            public BancoFx fromString(String id) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        cbBanco.setConverter(converter);
        cbBanco.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
            System.out.println(cbBanco.getValue().getId());
        }));
       cbMoneda.getItems().addAll("Guaraníes", "Dólares");
       
       
    }

    @FXML
    void cancelar(ActionEvent event) {

    }

    @FXML
    void editar(ActionEvent event) {

    }

    @FXML
    void guardar(ActionEvent event) {
        daoCuenta = new DaoCuenta();
        cuenta = new Cuenta();
        cuenta.setBanco(new Banco(cbBanco.getValue().getId(), cbBanco.getValue().getBanco(), true));
        cuenta.setCuenta(txCuenta.getText());
        cuenta.setEstado(true);
        cuenta.setMoneda(cbMoneda.getSelectionModel().getSelectedIndex());
        
        try {
            daoCuenta.insertar(cuenta);
            daoCuenta.commit();
        } catch (Exception ex) {
            daoCuenta.rollback();
            Logger.getLogger(RegistroCuentaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        cargarTablaCuentas();

    }

    @FXML
    void nuevo(ActionEvent event) {

    }

    @FXML
    void seleccionarCuenta(MouseEvent event) {
        cargarBancos();
        cuentaFx = tablaCuenta.getSelectionModel().getSelectedItem();
        
        lblCodigo.setText(String.valueOf("Código: "+cuentaFx.getId()));
        txCuenta.setText(cuentaFx.getCuenta());
       
        cbBanco.getSelectionModel().select(cuentaFx.getBancoId()-1);
        cbMoneda.getSelectionModel().select(cuentaFx.getMoneda());
       
    }

    private void cargarBancos() {
        DaoBanco daoBanco = new DaoBanco();
        List<Banco> bancos = daoBanco.recuperarTodo();
        
        for (int i = 0; i < bancos.size(); i++) {
            bancoFx = new BancoFx(bancos.get(i).getId(), bancos.get(i).getBanco());
            items.add(bancoFx);
        }
       
    }

    private void cargarTablaCuentas() {
       daoCuenta =  new DaoCuenta();
       cuentas = daoCuenta.recuperarTodo();
       
        ObservableList<CuentaFx> observableCuenta = FXCollections.observableArrayList();
        for (int i = 0; i < cuentas.size(); i++) {
            cuentaFx = new CuentaFx(cuentas.get(i).getId(),
                    cuentas.get(i).getCuenta(), 
                    cuentas.get(i).getBanco().getBanco(), 
                    cuentas.get(i).getBanco().getId(), 
                    cuentas.get(i).getMoneda());
            observableCuenta.add(cuentaFx);
        }
        
        
        this.cuentaCol.setCellValueFactory(new PropertyValueFactory<CuentaFx, String>("cuenta"));
        this.bancoCol.setCellValueFactory(new PropertyValueFactory<CuentaFx, String>("banco"));
        
        tablaCuenta.setItems(observableCuenta);
        
    }

}
