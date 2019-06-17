/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exercicio.raul.entidades;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Raúl
 */
@Entity
@Table(name = "projeto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Projeto.findAll", query = "SELECT p FROM Projeto p")
    , @NamedQuery(name = "Projeto.findByIdProjeto", query = "SELECT p FROM Projeto p WHERE p.idProjeto = :idProjeto")
    , @NamedQuery(name = "Projeto.findByNomeProjeto", query = "SELECT p FROM Projeto p WHERE p.nomeProjeto = :nomeProjeto")
    , @NamedQuery(name = "Projeto.findByDataAtivacao", query = "SELECT p FROM Projeto p WHERE p.dataAtivacao = :dataAtivacao")
    , @NamedQuery(name = "Projeto.findByDataDesativacao", query = "SELECT p FROM Projeto p WHERE p.dataDesativacao = :dataDesativacao")
    , @NamedQuery(name = "UsuarioProjeto.findSome", query = "select p  from UsuarioProjeto up, Projeto p, Empresa e, Usuario u where u.idEmpresa = e.idEmpresa and u.idUsuario = up.idUsuario and p.idEmpresa = u.idEmpresa and u.idUsuario = up.idUsuario")})
public class Projeto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_projeto")
    private Short idProjeto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "nome_projeto")
    private String nomeProjeto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "data_ativacao")
    @Temporal(TemporalType.DATE)
    private Date dataAtivacao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "data_desativacao")
    @Temporal(TemporalType.DATE)
    private Date dataDesativacao;
    @JoinColumn(name = "id_empresa", referencedColumnName = "id_empresa")
    @ManyToOne
    private Empresa idEmpresa;
    @JoinColumn(name = "id_status", referencedColumnName = "id_status")
    @ManyToOne
    private Status idStatus;
    @OneToMany(mappedBy = "idProjeto")
    private Collection<UsuarioProjeto> usuarioProjetoCollection;

    public Projeto() {
    }

    public Projeto(Short idProjeto) {
        this.idProjeto = idProjeto;
    }

    public Projeto(Short idProjeto, String nomeProjeto, Date dataAtivacao, Date dataDesativacao) {
        this.idProjeto = idProjeto;
        this.nomeProjeto = nomeProjeto;
        this.dataAtivacao = dataAtivacao;
        this.dataDesativacao = dataDesativacao;
    }

    public Short getIdProjeto() {
        return idProjeto;
    }

    public void setIdProjeto(Short idProjeto) {
        this.idProjeto = idProjeto;
    }

    public String getNomeProjeto() {
        return nomeProjeto;
    }

    public void setNomeProjeto(String nomeProjeto) {
        this.nomeProjeto = nomeProjeto;
    }

    public Date getDataAtivacao() {
        return dataAtivacao;
    }

    public void setDataAtivacao(Date dataAtivacao) {
        this.dataAtivacao = dataAtivacao;
    }

    public Date getDataDesativacao() {
        return dataDesativacao;
    }

    public void setDataDesativacao(Date dataDesativacao) {
        this.dataDesativacao = dataDesativacao;
    }

    public Empresa getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Empresa idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public Status getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(Status idStatus) {
        this.idStatus = idStatus;
    }

    @XmlTransient
    public Collection<UsuarioProjeto> getUsuarioProjetoCollection() {
        return usuarioProjetoCollection;
    }

    public void setUsuarioProjetoCollection(Collection<UsuarioProjeto> usuarioProjetoCollection) {
        this.usuarioProjetoCollection = usuarioProjetoCollection;
    }
    
    public String erro(){
        return "Ocorreu um erro. Verifique o log para mais detalhes.";
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProjeto != null ? idProjeto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        
        if (!(object instanceof Projeto)) {
            return false;
        }
        Projeto other = (Projeto) object;
        if ((this.idProjeto == null && other.idProjeto != null) || (this.idProjeto != null && !this.idProjeto.equals(other.idProjeto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "[ " + nomeProjeto + " ]";
    }
    
}
