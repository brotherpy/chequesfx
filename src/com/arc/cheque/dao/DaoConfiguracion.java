/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arc.cheque.dao;


import com.arc.cheque.model.Configuracion;
import java.util.List;

/**
 *
 * @author Jorge Fabio
 */
public class DaoConfiguracion extends DaoGenerico<Configuracion> {

    public DaoConfiguracion() {
        super(Configuracion.class);
    }

    @Override
    public List<Configuracion> recuperarPorFiltro(String filtro) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
