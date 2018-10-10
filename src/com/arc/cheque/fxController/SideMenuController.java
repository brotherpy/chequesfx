/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arc.cheque.fxController;

import com.arc.cheque.utilidad.Constants;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author Brother
 */
public class SideMenuController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private JFXButton btnInicio;

    @FXML
    private JFXButton btnMovimiento;

    @FXML
    private JFXButton btnMoneda;

    private StackPane spane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            spane = FXMLLoader.load(getClass().getResource(Constants.HOMEVIEW));
            MainController.temporaryPane.getChildren().setAll(spane);
        } catch (IOException ex) {
            Logger.getLogger(SideMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    void openHome(ActionEvent event) {
        switchPane(Constants.HOMEVIEW);
    }

    @FXML
    private void openMovimiento(ActionEvent event) {
        switchPane(Constants.MOVIMIENTOSVIEW);
        
    }

    @FXML
    private void openRegistros
        (ActionEvent event) {
            switchPane(Constants.REGISTROSVIEW);
    }

    private void switchPane(String pane) {
        try {
            MainController.temporaryPane.getChildren().clear();
            StackPane spane = FXMLLoader.load(getClass().getResource(pane));
            ObservableList<Node> elements = spane.getChildren();
            MainController.temporaryPane.getChildren().setAll(elements);
        } catch (IOException ex) {
            Logger.getLogger(SideMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
