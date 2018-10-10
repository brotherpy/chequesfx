/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arc.cheque.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author jorge
 */
@Entity
@Table(name = "cuenta", catalog = "cheque", schema = "public")
@NamedQueries({
    @NamedQuery(name = "Cuenta.findAll", query = "SELECT c FROM Cuenta c")})
public class Cuenta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "cuenta", nullable = false, length = 25)
    private String cuenta;
    @Basic(optional = false)
    @Column(name = "moneda", nullable = false)
    private Integer moneda;
    @Basic(optional = false)
    @Column(name = "estado", nullable = false)
    private boolean estado;
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "cuenta")
    private List<Chequera> chequeraList;
    @JoinColumn(name = "banco", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Banco banco;
   

    public Cuenta() {
    }

    public Cuenta(Integer id) {
        this.id = id;
    }

    public Cuenta(Integer id, String cuenta, boolean estado) {
        this.id = id;
        this.cuenta = cuenta;
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public List<Chequera> getChequeraList() {
        return chequeraList;
    }

    public void setChequeraList(List<Chequera> chequeraList) {
        this.chequeraList = chequeraList;
    }

    public Banco getBanco() {
        return banco;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }

    public Integer getMoneda() {
        return moneda;
    }

    public void setMoneda(Integer moneda) {
        this.moneda = moneda;
    }

  
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cuenta)) {
            return false;
        }
        Cuenta other = (Cuenta) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.arc.test.Cuenta[ id=" + id + " ]";
    }
}
