/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arc.cheque.dao;

import com.arc.cheque.model.Banco;
import java.util.List;
import org.hibernate.query.Query;

/**
 *
 * @author Jorge Fabio
 */
public class DaoBanco extends DaoGenerico<Banco> {

    public DaoBanco() {
        super(Banco.class);
    }

    @Override
    public List<Banco> recuperarPorFiltro(String filtro) {
        session.beginTransaction();
        Query<Banco> query = session.createQuery("from " + clase.getName() + " "
                + "where lower (banco) like :banco "
                + "order by banco");
        query.setParameter("banco", "%" + filtro.toLowerCase() + "%");
        List<Banco> results = query.getResultList();
        close();
        return results;
    }

    public Banco recuperarPorBanco(String filtro) {
        session.beginTransaction();
        Query<Banco> query = session.createQuery("from " + clase.getName() + " "
                + "where lower (banco) like :banco "
                + "and estado = true "
                + "order by banco");
        query.setParameter("banco", "%" + filtro.toLowerCase() + "%");
        Banco results = new Banco();
        try {
            results = query.getSingleResult();
        } catch (Exception e) {
        }
        close();
        return results;
    }
}
