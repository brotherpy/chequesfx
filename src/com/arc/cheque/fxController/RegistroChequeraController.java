/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arc.cheque.fxController;

import com.arc.cheque.dao.DaoBanco;
import com.arc.cheque.dao.DaoChequera;
import com.arc.cheque.dao.DaoCuenta;
import com.arc.cheque.fxModels.BancoFx;
import com.arc.cheque.model.Banco;
import com.arc.cheque.model.Cheque;
import com.arc.cheque.model.Chequera;
import com.arc.cheque.model.Cuenta;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.util.StringConverter;

/**
 *
 * @author Brother
 */
public class RegistroChequeraController implements Initializable {

    @FXML
    private JFXComboBox<BancoFx> cbBanco;

    @FXML
    private JFXComboBox<Cuenta> cbCuenta;

    @FXML
    private JFXTextField txCheque;

    @FXML
    private JFXTextField txHojas;

    @FXML
    private Label lblCodigo;

    @FXML
    private JFXButton btnNuevo;

    @FXML
    private JFXButton btnCancelar;

    @FXML
    private JFXButton btnEditar;

    @FXML
    private JFXButton btnGuardar;
    private BancoFx bancoFx;
    ObservableList<BancoFx> bancos = FXCollections.observableArrayList();
    private DaoCuenta daoCuenta;
    private Cheque cheque;
    private List<Cheque> cheques;
    private DaoChequera daoChequera;
    private Chequera chequera;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cargarBancos();
        cbBanco.setItems(bancos);
        StringConverter<BancoFx> converter = new StringConverter<BancoFx>() {
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
    }

    @FXML
    void cancelar(ActionEvent event) {

    }

    //metodo del CB Banco para cargar la cuenta
    @FXML
    void cargarCuenta(ActionEvent event) {
        daoCuenta = new DaoCuenta();
        List<Cuenta> cuentas = daoCuenta.recuperarPorBanco(cbBanco.getSelectionModel().getSelectedItem().getId());
        ObservableList<Cuenta> cuentasList = FXCollections.observableArrayList(cuentas);

        cbCuenta.setItems(cuentasList);
         StringConverter<Cuenta> converter = new StringConverter<Cuenta>() {
            @Override
            public String toString(Cuenta object) {
               return object.getCuenta();
            }

            @Override
            public Cuenta fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
           
        };
        cbCuenta.setConverter(converter);

    }

    @FXML
    void editar(ActionEvent event) {

    }

    @FXML
    void guardar(ActionEvent event) {
        generarHojasCheque();
        daoChequera = new DaoChequera();
        chequera = new Chequera();
        chequera.setCuenta(cbCuenta.getSelectionModel().getSelectedItem());
        chequera.setChequeList(cheques);
        chequera.setEstado(true);
        chequera.setHojas(Integer.parseInt(txHojas.getText()));
        for (int i = 0; i < cheques.size(); i++) {
                cheques.get(i).setChequera(chequera);
            }
            
        try {
            daoChequera.insertar(chequera);
            daoChequera.commit();
        } catch (Exception ex) {
            daoChequera.rollback();
            Logger.getLogger(RegistroChequeraController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void nuevo(ActionEvent event) {

    }

    private void cargarBancos() {
        DaoBanco daoBanco = new DaoBanco();
        List<Banco> bancos = daoBanco.recuperarTodo();

        for (int i = 0; i < bancos.size(); i++) {
            bancoFx = new BancoFx(bancos.get(i).getId(), bancos.get(i).getBanco());
            this.bancos.add(bancoFx);
        }
    }

    private void generarHojasCheque() {
        int chequeInicio = Integer.parseInt(txCheque.getText());
        int chequeFin = Integer.parseInt(txHojas.getText());
        cheques = new ArrayList<>();
        for (int i = 0; i < chequeFin; i++) {
          
            cheque = new Cheque();
            cheque.setCheque(chequeInicio);
            cheque.setOrden(" ");
            cheque.setMonto(0.0);
            cheque.setPagado(false);
            cheque.setSituacion("Vacío");
            cheque.setObservacion(" ");
            cheque.setOrden("No Determinado");
            cheque.setEstado(true);
            System.out.println("hoja:"+ i);
            cheques.add(cheque);
            chequeInicio += 1;
        }
    }

}
