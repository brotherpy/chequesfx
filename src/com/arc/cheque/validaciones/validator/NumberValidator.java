/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arc.cheque.validaciones.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Brother
 */
public class NumberValidator {
    private static Pattern pattern;
    private static Matcher matcher;
    
    /**
     * Acepta solo numeros
     */
    private static final String NUMBER_PATTERN = ("[0-9]*");
    //Acepta mumeros y guion medio
    private static final String BANK_PATTERN = ("^[\\d-]+$");
    
    public static boolean validate(final String number){
        pattern =  Pattern.compile(NUMBER_PATTERN);
        matcher = pattern.matcher(number.trim());
        return matcher.matches();
    }
    
    public static boolean validate(final String value, int valorMenor, int valorMayor){
        return (validate(value) && value.length()>= valorMenor && value.length()<= valorMayor);
    }
    
     public static boolean validateBank(final String number){
        pattern =  Pattern.compile(BANK_PATTERN);
        matcher = pattern.matcher(number.trim());
        return matcher.matches();
    }
    
}
