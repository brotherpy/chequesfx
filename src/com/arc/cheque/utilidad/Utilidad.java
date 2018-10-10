/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arc.cheque.utilidad;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 * @author Jorge Fabio
 */
public class Utilidad extends PlainDocument {

    @Override
    public void insertString(int offset, String str, AttributeSet atrr) throws BadLocationException {
        super.insertString(offset, str.toUpperCase(), atrr);
    }
    //Establece el formato de fecha a ser mostrado en el FormatedTexField
    final private static SimpleDateFormat FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    //Metodo que obtiene el valor String del FormatedTextField y lo convierte a 
    //date para ser guardado en la base de datos
    public static Date formatoFechaStringADate(String s) {
        try {
            return FORMAT.parse(s);
        } catch (ParseException e) {
            return null;
        }
    }

    //Metodo que obtiene una fecha guardada en la Base de Datos y lo convierte
    //en string para mostrarlo en el FormatedTextField
    public static String formatoFechaDateAString(Date d) {
        return FORMAT.format(d);
    }

    public static String formatoFechaLocalDateString(LocalDate fecha) {
        String formato = "dd/LL/yyyy";
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(formato);
        return fecha.format(dtf);
    }

    //Metodo que obtiene el valor String de un TextFiel y lo convierte a double
    //para ser guardado en la base de datos
    public static Double formatoNumStringDouble(String valor) {
        DecimalFormatSymbols simbolo = new DecimalFormatSymbols();
        simbolo.setDecimalSeparator(',');
        simbolo.setGroupingSeparator('.');
        DecimalFormat formato = new DecimalFormat("###,###.###", simbolo);
        Number numero = 0;

        try {
            numero = formato.parse(valor);
        } catch (ParseException ex) {
            Logger.getLogger(Utilidad.class.getName()).log(Level.SEVERE, null, ex);
        }

        return numero.doubleValue();
    }

    //Metodo que obtiene el valor double de una Base de datos y lo convierte a 
    // Sting para ser seteado en un TextField
    public static String formatoNumDoubleStringDecimal(Double valor) {
        DecimalFormatSymbols simbolo = new DecimalFormatSymbols();
        simbolo.setDecimalSeparator(',');
        simbolo.setGroupingSeparator('.');
        DecimalFormat formato = new DecimalFormat("###,###.###", simbolo);

        return formato.format(valor);
    }

    public static String formatoNumDoubleString(Double valor) {
        DecimalFormatSymbols simbolo = new DecimalFormatSymbols();
        simbolo.setDecimalSeparator(',');
        simbolo.setGroupingSeparator('.');
        DecimalFormat formato = new DecimalFormat("###,###", simbolo);

        return formato.format(valor);
    }

    //Metodo que valida el ingreso de datos en un TextFiel para que solo se 
    //pueda ingresar letras
    public static final void soloLetras(JTextField a) {
        a.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (Character.isDigit(c)) {
//                    getToolkit().beep();
                    e.consume();
                }
            }
        });
    }

    //Metodo que valida el ingreso de datos en un TextFiel para que solo se 
    //pueda ingresar numeros
    public static final void soloNumeros(JTextField a) {
        a.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (Character.isLetter(c)) {
                    e.consume();
                }
            }
        });
    }

    //Metodo que permite pasar de un TextField a otro presionando enter
    public static void moverConEnter(java.awt.event.KeyEvent evt, JComponent source) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            source.requestFocus();
        }
    }

    //Metodo que acciona un boto al presionar enter
    public static void hacerClicConEnter(java.awt.event.KeyEvent evt, JButton buttom) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            buttom.doClick();
        }
    }

}
