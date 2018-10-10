/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arc.cheque.Main;


import com.arc.cheque.utilidad.ConexionBD;
import com.arc.cheque.utilidad.Constants;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Brother
 */
public class FXARC_CHEQUE extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource(Constants.MAINVIEW));
         ConexionBD.setUp();
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
       
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        launch(args);
    }

}
