/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arc.cheque.fxController;

import com.arc.cheque.utilidad.Constants;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXToolbar;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Brother
 */
public class MainController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private StackPane mainStackPane;

    @FXML
    private JFXToolbar toolbar;

    @FXML
    private JFXHamburger hamburger;

    @FXML
    private AnchorPane contentPane;

    @FXML
    private JFXDrawer drawer;
    public static AnchorPane temporaryPane;
    
    @FXML
    private MenuItem menuRegistrar;

  

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        temporaryPane = contentPane;
        initHamburger();

    }

    private void initHamburger() {
        try {

            VBox menu = FXMLLoader.load(getClass().getResource(Constants.SIDEMENUVIEW));
            drawer.setSidePane(menu);
            drawer.setDefaultDrawerSize(150);
            HamburgerBackArrowBasicTransition transition
                    = new HamburgerBackArrowBasicTransition(hamburger);

            transition.setRate(-1);
            hamburger.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event event) -> {
                transition.setRate(transition.getRate() * -1);
                transition.play();

                if (drawer.isClosed()) {
                    drawer.open();
                } else {
                    drawer.close();
                }
            });

        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    @FXML
    void registrarBanco(ActionEvent event) {
        loadWindow("/com/arc/cheque/fxViews/RegistroBanco.fxml", "Consulta y registro de banco");

    }
     @FXML
    void registrarCuenta(ActionEvent event) {
          loadWindow("/com/arc/cheque/fxViews/RegistroCuenta.fxml", "Consulta y registro de cuenta");
    }
    
        @FXML
    void registrarChequera(ActionEvent event) {
         loadWindow("/com/arc/cheque/fxViews/RegistroChequera.fxml", "Registrar chequera");
    }
      void loadWindow(String loc, String title){
        try {
            Parent parent = FXMLLoader.load(getClass().getResource(loc));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle(title);
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
