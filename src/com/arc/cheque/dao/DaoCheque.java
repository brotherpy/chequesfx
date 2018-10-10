/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arc.cheque.dao;


import com.arc.cheque.model.Cheque;
import java.time.LocalDate;
import java.util.List;
import org.hibernate.query.Query;

/**
 *
 * @author Jorge Fabio
 */
public class DaoCheque extends DaoGenerico<Cheque> {

    public DaoCheque() {
        super(Cheque.class);
    }

    @Override
    public List<Cheque> recuperarPorFiltro(String filtro) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Cheque> recuperarChequesPorChequera(int id) {
        session.beginTransaction();
        Query<Cheque> query = session.createQuery("from " + clase.getName()
                + " d where d.chequera = " + id + " and estado=true order by d.cheque");
        List<Cheque> results = query.getResultList();
        close();
        return results;
    }
    
    public List<Object[]> resumenDeChequesEmitidosPorDia(LocalDate desde, LocalDate hasta) {
        session.beginTransaction();
        Query<Object[]> query = session.createNativeQuery("SELECT cheque.cobro, "
                + "SUM(cheque.monto) AS Monto, "
                + "cheque.pagado AS Pagado "
                + "FROM cheque WHERE cheque.cobro "
                + "BETWEEN '" + desde + "' AND '" + hasta + "' "
                + "AND cheque.pagado = false "
                + "GROUP BY cheque.cobro, cheque.pagado "
                + "ORDER BY cheque.cobro;");
        List<Object[]> results = query.getResultList();
        close();
        return results;
    }

    public Double TotalDeChequesPendientesPorDia(LocalDate desde, LocalDate hasta) {
        session.beginTransaction();
        Query<Double> query = session.createQuery("select sum(cheque.monto) "
                + "from Cheque cheque "
                + "where cheque.cobro "
                + "between '" + desde + "' AND '" + hasta + "'  "
                + "and pagado = false");
        Double results = 0.0;
        try {
            results = query.getSingleResult();
        } catch (Exception e) {
        }
        close();
        return results;
    }

    public Double TotalDeChequesEmitidosPorDia(LocalDate desde, LocalDate hasta) {
        session.beginTransaction();
        Query<Double> query = session.createQuery("select sum(cheque.monto) "
                + "from Cheque cheque "
                + "where cheque.cobro "
                + "between '" + desde + "' AND '" + hasta + "'");
        Double results = 0.0;
        try {
            results = query.getSingleResult();
        } catch (Exception e) {
        }
        close();
        return results;
    }

    public List<Cheque> recuperarPorChequera(int nro, boolean estado) {
        session.beginTransaction();
        Query<Cheque> query = session.createQuery("FROM " + clase.getName() + " WHERE chequera = " + nro + " "
                + "AND estado = "+estado+" "
                + "ORDER BY cobro");
        List<Cheque> results = query.getResultList();
        close();
        return results;
    }

    public List<Cheque> recuperarPorChequera(int nro, boolean estado, boolean pagado) {
        session.beginTransaction();
        Query<Cheque> query = session.createQuery("FROM " + clase.getName() + " WHERE chequera = " + nro + " "
                + "AND estado = "+estado+" "
                + "AND pagado = "+pagado+" "
                + "ORDER BY cobro");
        List<Cheque> results = query.getResultList();
        close();
        return results;
    }
}
