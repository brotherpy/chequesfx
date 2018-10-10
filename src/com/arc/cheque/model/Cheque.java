/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arc.cheque.model;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 *
 * @author jorge
 */
@Entity
@Table(name = "cheque", catalog = "cheque", schema = "public")
@NamedQueries({
    @NamedQuery(name = "Cheque.findAll", query = "SELECT c FROM Cheque c")})
public class Cheque implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "nro", nullable = false)
    private Integer nro;
    @Basic(optional = false)
    @Column(name = "cheque", nullable = false)
    private int cheque;
    @Column(name = "emision")
    private LocalDate emision;
    @Column(name = "cobro")
    private LocalDate cobro;
    @Column(name = "orden", length = 60)
    private String orden;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "monto", precision = 17, scale = 17)
    private Double monto;
    @Basic(optional = false)
    @Column(name = "situacion", nullable = false, length = 30)
    private String situacion;
    @Basic(optional = false)
    @Column(name = "observacion", nullable = false, length = 200)
    private String observacion;
    @Basic(optional = false)
    @Column(name = "pagado", nullable = false)
    private boolean pagado;
    @Basic(optional = false)
    @Column(name = "estado", nullable = false)
    private boolean estado;
    @JoinColumn(name = "chequera", referencedColumnName = "nro", nullable = false)
    @ManyToOne(optional = false)
    private Chequera chequera;

    public Cheque() {
    }

    public Cheque(Integer nro) {
        this.nro = nro;
    }

    public Cheque(Integer nro, int cheque, String situacion, String observacion, boolean pagado, boolean estado) {
        this.nro = nro;
        this.cheque = cheque;
        this.situacion = situacion;
        this.observacion = observacion;
        this.pagado = pagado;
        this.estado = estado;
    }

    public Integer getNro() {
        return nro;
    }

    public void setNro(Integer nro) {
        this.nro = nro;
    }

    public int getCheque() {
        return cheque;
    }

    public void setCheque(int cheque) {
        this.cheque = cheque;
    }

    public LocalDate getEmision() {
        return emision;
    }

    public void setEmision(LocalDate emision) {
        this.emision = emision;
    }

    public LocalDate getCobro() {
        return cobro;
    }

    public void setCobro(LocalDate cobro) {
        this.cobro = cobro;
    }

    public String getOrden() {
        return orden;
    }

    public void setOrden(String orden) {
        this.orden = orden;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public String getSituacion() {
        return situacion;
    }

    public void setSituacion(String situacion) {
        this.situacion = situacion;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public boolean getPagado() {
        return pagado;
    }

    public void setPagado(boolean pagado) {
        this.pagado = pagado;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Chequera getChequera() {
        return chequera;
    }

    public void setChequera(Chequera chequera) {
        this.chequera = chequera;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nro != null ? nro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cheque)) {
            return false;
        }
        Cheque other = (Cheque) object;
        if ((this.nro == null && other.nro != null) || (this.nro != null && !this.nro.equals(other.nro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.arc.test.Cheque[ nro=" + nro + " ]";
    }
    
}
