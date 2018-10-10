/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arc.cheque.dao;


import com.arc.cheque.utilidad.ConexionBD;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author Jorge Fabio
 */
abstract public class DaoGenerico<T> {

    protected Session session;
    protected Class<T> clase;

    public DaoGenerico(Class<T> clase) {
        this.clase = clase;
        ConexionBD.getSessionFactory().openSession();
        session = ConexionBD.getSessionFactory().getCurrentSession();
    }

    public void insertar(T entity) throws Exception {
        session.beginTransaction();
        session.save(entity);
    }

    public void actualizar(T entity) throws Exception {
        session.beginTransaction();
        session.update(entity);
    }

    public void eliminar(T entity) throws Exception {
        session.beginTransaction();
        session.delete(entity);
    }

    public T recuperarPorId(Integer id) {
        session.beginTransaction();
        T result = (T) session.get(clase, id);
        close();
        return result;
    }

    public List<T> recuperarTodo() {
        session.beginTransaction();
        Query<T> q = session.createQuery("from " + clase.getName()+" where estado = true");
        List<T> results = q.list();
        close();
        return results;
    }

    abstract public List<T> recuperarPorFiltro(String filtro);

    public void commit() {
        session.getTransaction().commit();
    }

    public void rollback() {
        session.getTransaction().rollback();
    }

    public void closed() {
        if (session.isOpen()) {
            System.out.println("Cerrando la sesion");
            session.close();
        }
        if (!ConexionBD.getSessionFactory().isClosed()) {
            System.out.println("cerrando session factory");
            ConexionBD.getSessionFactory().close();
        }
    }
       protected void close() {
        session.close();
    }

}
