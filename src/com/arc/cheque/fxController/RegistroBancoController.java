/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arc.cheque.fxController;

import com.arc.cheque.dao.DaoBanco;
import com.arc.cheque.fxModels.BancoFx;
import com.arc.cheque.model.Banco;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.controlsfx.validation.ValidationSupport;

/**
 * FXML Controller class
 *
 * @author Brother
 */
public class RegistroBancoController implements Initializable {

    @FXML
    private TableView<BancoFx> tablaBanco;

    @FXML
    private TableColumn<BancoFx, Integer> idCol;

    @FXML
    private TableColumn<BancoFx, String> bancoCol;

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
    private Label lblCodigo;

    @FXML
    private JFXTextField txBanco;

    private Banco banco;
    private DaoBanco daoBanco;
    private List<Banco> bancos;
    private BancoFx bancoFX;
    private FilteredList<BancoFx> filteredList;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        estadoInicial();

        cargarTablaBanco();
        validacion();

        filtrar();

    }

    @FXML
    void editar(ActionEvent event) {

    }

    @FXML
    void guardar(ActionEvent event) {
        
        
            banco = new Banco();

        banco.setBanco(txBanco.getText());
        banco.setEstado(true);
        try {
            daoBanco = new DaoBanco();
            daoBanco.insertar(banco);
            daoBanco.commit();

        } catch (Exception ex) {
            daoBanco.rollback();
            Logger.getLogger(RegistroBancoController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            daoBanco.closed();
        }

        
      
        
    }

    @FXML
    void nuevo(ActionEvent event) {
        tablaBanco.setDisable(true);
        txBusqueda.setDisable(true);
        btnCancelar.setDisable(false);
        txBanco.setDisable(false);
        btnGuardar.setDisable(false);
        txBanco.requestFocus();

    }

    @FXML
    void cancelar(ActionEvent event) {
        estadoInicial();
    }

    private void estadoInicial() {
        txBusqueda.requestFocus();
        btnCancelar.setDisable(true);
        btnEditar.setDisable(true);
        btnGuardar.setDisable(true);
        txBanco.setDisable(true);
        tablaBanco.setDisable(false);
        txBusqueda.setDisable(false);
        btnNuevo.setDisable(false);

        limpiarCampos();

    }

    private void cargarTablaBanco() {
        daoBanco = new DaoBanco();
        bancos = daoBanco.recuperarTodo();
        ObservableList<BancoFx> observableBanco = FXCollections.observableArrayList();
        for (int i = 0; i < bancos.size(); i++) {
            bancoFX = new BancoFx(bancos.get(i).getId(), bancos.get(i).getBanco());

            observableBanco.add(bancoFX);
        }

        this.idCol.setCellValueFactory(new PropertyValueFactory<BancoFx, Integer>("id"));
        this.bancoCol.setCellValueFactory(new PropertyValueFactory<BancoFx, String>("Banco"));

        tablaBanco.setItems(observableBanco);
        filteredList = new FilteredList<>(observableBanco);

        //filtrado
    }

    @FXML
    void seleccionarBanco(MouseEvent event) {
        bancoFX = tablaBanco.getSelectionModel().getSelectedItem();
        lblCodigo.setVisible(true);
        lblCodigo.setText("Código: " + String.valueOf(bancoFX.getId()));
        txBanco.setText(bancoFX.getBanco());
        btnEditar.setDisable(false);
        btnGuardar.setDisable(true);
        btnCancelar.setDisable(false);
        btnNuevo.setDisable(true);

    }

    private void filtrar() {
        txBusqueda.textProperty().addListener((observable, oldValue, newValue) -> {
            if (txBusqueda.textProperty().get().isEmpty()) {
                filteredList.setPredicate(t -> true);
                return;
            }

            filteredList.setPredicate((t) -> {
                String text = newValue.toLowerCase();
                for (TableColumn<BancoFx, ?> en : tablaBanco.getColumns()) {
                    String cellString = en.getCellData(bancoFX).toString();
                    cellString = cellString.toLowerCase();
                    if (cellString.contains(text)) {
                        return true;
                    }

                }
                return false;
            });
        });
    }

    private void limpiarCampos() {
        txBanco.setText(null);
        txBusqueda.setText(null);
    }

    private void validacion() {
   
    }

   

}
