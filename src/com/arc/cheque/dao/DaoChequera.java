/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arc.cheque.dao;

import com.arc.cheque.model.Chequera;
import java.util.List;
import org.hibernate.query.Query;

/**
 *
 * @author Jorge Fabio
 */
public class DaoChequera extends DaoGenerico<Chequera> {

    public DaoChequera() {
        super(Chequera.class);
    }

    @Override
    public List<Chequera> recuperarPorFiltro(String filtro) {
        session.beginTransaction();
        Query<Chequera> query = session.createQuery("from " + clase.getName()
                + " where estado = true"
                + " or lower (cuenta) like :cuenta"
                + " order by cuenta");
        query.setParameter("cuenta", "%" + filtro.toLowerCase() + "%");
        List<Chequera> results = query.getResultList();
        close();
        return results;
    }

    public List<Chequera> recuperarPorBanco(int id) {
        session.beginTransaction();
        Query<Chequera> query = session.createQuery("from " + clase.getName()
                + " d where d.estado = true and d.banco = " + id
                + " order by d.cuenta");
        List<Chequera> results = query.getResultList();
        close();
        return results;
    }

    public List<Chequera> recuperarPorCuenta(int id) {
        session.beginTransaction();
        Query<Chequera> query = session.createQuery("from " + clase.getName()
                + " d where d.cuenta = " + id);
        List<Chequera> results = query.getResultList();
        close();
        return results;
    }

    public List<Chequera> recuperarPorCuenta(int id, boolean estado) {
        session.beginTransaction();
        Query<Chequera> query = session.createQuery("from " + clase.getName()
                + " d where d.estado = " + estado + " and d.cuenta = " + id);
        List<Chequera> results = query.getResultList();
        close();
        return results;
    }

}
