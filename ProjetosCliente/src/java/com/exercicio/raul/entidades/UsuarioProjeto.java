/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exercicio.raul.entidades;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Raúl
 */
@Entity
@Table(name = "usuarioprojeto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsuarioProjeto.findAll", query = "SELECT u FROM UsuarioProjeto u"),
    @NamedQuery(name = "UsuarioProjeto.findByIdusuarioProjeto", query = "SELECT u FROM UsuarioProjeto u WHERE u.idusuarioProjeto = :idusuarioProjeto")})

public class UsuarioProjeto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idusuarioProjeto")
    private Short idusuarioProjeto;
    @JoinColumn(name = "id_projeto", referencedColumnName = "id_projeto")
    @ManyToOne
    private Projeto idProjeto;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne
    private Usuario idUsuario;

    public UsuarioProjeto() {
    }

    public UsuarioProjeto(Short idusuarioProjeto) {
        this.idusuarioProjeto = idusuarioProjeto;
    }

    public Short getIdusuarioProjeto() {
        return idusuarioProjeto;
    }

    public void setIdusuarioProjeto(Short idusuarioProjeto) {
        this.idusuarioProjeto = idusuarioProjeto;
    }

    public Projeto getIdProjeto() {
        return idProjeto;
    }

    public void setIdProjeto(Projeto idProjeto) {
        this.idProjeto = idProjeto;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idusuarioProjeto != null ? idusuarioProjeto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        
        if (!(object instanceof UsuarioProjeto)) {
            return false;
        }
        UsuarioProjeto other = (UsuarioProjeto) object;
        if ((this.idusuarioProjeto == null && other.idusuarioProjeto != null) || (this.idusuarioProjeto != null && !this.idusuarioProjeto.equals(other.idusuarioProjeto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "[ " + idusuarioProjeto + " ]";
    }
    
}
