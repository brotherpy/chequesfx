/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arc.cheque.dao;

import com.arc.cheque.model.Usuario;
import java.util.List;

import org.hibernate.query.Query;

/**
 *
 * @author Jorge Fabio
 */

public class DaoUsuario extends DaoGenerico<Usuario> {

    public DaoUsuario() {
        super(Usuario.class);
    }

    @Override
    public List<Usuario> recuperarPorFiltro(String filtro) {
        session.beginTransaction();
        Query<Usuario> query = session.createQuery("from " + clase.getName() + " "
                + "where lower (usuario) like :usuario "
                + "and estado = true "
                + "order by usuario");
        query.setParameter("usuario", "%" + filtro.toLowerCase() + "%");
        List<Usuario> results = query.getResultList();
        close();
        return results;
    }

    public Usuario recuperarPorUsuario(String filtro) {
        session.beginTransaction();
        Query<Usuario> query = session.createQuery("from " + clase.getName() + " "
                + "where lower (usuario) like :usuario "
                + "and estado = true "
                + "order by usuario");
        query.setParameter("usuario", "%" + filtro.toLowerCase() + "%");
        Usuario results = new Usuario();
        try {
            results = query.getSingleResult();
        } catch (Exception e) {
        }
        close();
        return results;
    }

    public Usuario validarUsuario(String filtro) {
        session.beginTransaction();
        Query<Usuario> query = session.createQuery("from " + clase.getName() + " "
                + "where lower (usuario) like :usuario "
                + "order by usuario");
        query.setParameter("usuario", "%" + filtro.toLowerCase() + "%");
        Usuario results = new Usuario();
        try {
            results = query.getSingleResult();
        } catch (Exception e) {
        }
        close();
        return results;
    }

}
