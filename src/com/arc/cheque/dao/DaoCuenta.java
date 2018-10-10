/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arc.cheque.dao;

import com.arc.cheque.model.Cuenta;
import java.util.List;

import org.hibernate.query.Query;

/**
 *
 * @author Jorge Fabio
 */
public class DaoCuenta extends DaoGenerico<Cuenta> {

    public DaoCuenta() {
        super(Cuenta.class);
    }

    @Override
    public List<Cuenta> recuperarPorFiltro(String filtro) {
        session.beginTransaction();
        Query<Cuenta> query = session.createQuery("from " + clase.getName() + " "
                + "where lower (cuenta) like :cuenta "
                + "and estado = true "
                + "order by cuenta");
        query.setParameter("cuenta", "%" + filtro.toLowerCase() + "%");
        List<Cuenta> results = query.getResultList();
        close();
        return results;
    }

    public List<Cuenta> recuperarPorFiltro(String filtro, int banco) {
        session.beginTransaction();
        Query<Cuenta> query = session.createQuery("from " + clase.getName() + " "
                + "where lower (cuenta) like :cuenta "
                + "and banco = " + banco + " "
                + "and estado = true "
                + "order by cuenta");
        query.setParameter("cuenta", "%" + filtro.toLowerCase() + "%");
        List<Cuenta> results = query.getResultList();
        close();
        return results;
    }

    public List<Cuenta> recuperarPorBanco(int filtro) {
        session.beginTransaction();
        Query query = session.createQuery("from " + clase.getName() + " "
                + "where banco = " + filtro + " "
                + "and estado = true "
                + "order by cuenta");
        List<Cuenta> results = query.getResultList();
        close();
        return results;
    }

    public Cuenta recuperarPorCuenta(String filtro) {
        session.beginTransaction();
        Query<Cuenta> query = session.createQuery("from " + clase.getName() + " "
                + "where lower (cuenta) like :cuenta "
                + "and estado = true "
                + "order by cuenta");
        query.setParameter("cuenta", "%" + filtro.toLowerCase() + "%");
        Cuenta results = new Cuenta();
        try {
            results = query.getSingleResult();
        } catch (Exception e) {
        }
        close();
        return results;
    }
}
