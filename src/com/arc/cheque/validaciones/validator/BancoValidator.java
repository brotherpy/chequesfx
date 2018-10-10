/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arc.cheque.validaciones.validator;

import com.arc.cheque.model.Banco;

/**
 *
 * @author Brother
 */
public class BancoValidator {
    public static boolean validate(Banco banco){
        if (!(NumberValidator.validateBank(banco.getBanco()))) 
            return false;
        
        return true;       
    }
}
