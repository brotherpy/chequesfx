/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arc.cheque.fxController;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;


/**
 *
 * @author Brother
 */
public class LoginController implements Initializable {
    
    @FXML
    private JFXTextField txUsuario;

    @FXML
    private JFXPasswordField txPassword;

    @FXML
    private Button btnAceptar;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    
   
  
    
    @FXML
    void goToMain(ActionEvent event) {
        if (isLoginCorrect()) {
                System.out.println("login ok");
            }
            else{
                System.out.println("wrong credentials");
            }
    }
    
    
    private boolean isLoginCorrect(){
        //Falta aplicar la logica de la BD
        System.out.println("ok llegamos");
        boolean check = false;
        if (txUsuario.getText().equals("admin") && txPassword.getText().equals("admin")){
            check = true;
        }
        return check;
    }
}
