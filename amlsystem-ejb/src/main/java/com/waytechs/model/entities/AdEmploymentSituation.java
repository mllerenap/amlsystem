/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.waytechs.model.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author mllerena
 */
@Entity
@Table(name = "ad_employment_situation")
@NamedQueries({
    @NamedQuery(name = "AdEmploymentSituation.findAll", query = "SELECT a FROM AdEmploymentSituation a")
    , @NamedQuery(name = "AdEmploymentSituation.findById", query = "SELECT a FROM AdEmploymentSituation a WHERE a.id = :id")
    , @NamedQuery(name = "AdEmploymentSituation.findByCodemploymentsituation", query = "SELECT a FROM AdEmploymentSituation a WHERE a.codemploymentsituation = :codemploymentsituation")
    , @NamedQuery(name = "AdEmploymentSituation.findByName", query = "SELECT a FROM AdEmploymentSituation a WHERE a.name = :name")
    , @NamedQuery(name = "AdEmploymentSituation.findByDescription", query = "SELECT a FROM AdEmploymentSituation a WHERE a.description = :description")
    , @NamedQuery(name = "AdEmploymentSituation.findByCreated", query = "SELECT a FROM AdEmploymentSituation a WHERE a.created = :created")
    , @NamedQuery(name = "AdEmploymentSituation.findByCreatedby", query = "SELECT a FROM AdEmploymentSituation a WHERE a.createdby = :createdby")
    , @NamedQuery(name = "AdEmploymentSituation.findByUpdated", query = "SELECT a FROM AdEmploymentSituation a WHERE a.updated = :updated")
    , @NamedQuery(name = "AdEmploymentSituation.findByUpdatedby", query = "SELECT a FROM AdEmploymentSituation a WHERE a.updatedby = :updatedby")
    , @NamedQuery(name = "AdEmploymentSituation.findByIsactive", query = "SELECT a FROM AdEmploymentSituation a WHERE a.isactive = :isactive")})
public class AdEmploymentSituation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "codemploymentsituation")
    private String codemploymentsituation;
    @Size(max = 50)
    @Column(name = "name")
    private String name;
    @Size(max = 255)
    @Column(name = "description")
    private String description;
    @Column(name = "created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    @Size(max = 255)
    @Column(name = "createdby")
    private String createdby;
    @Column(name = "updated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updated;
    @Size(max = 255)
    @Column(name = "updatedby")
    private String updatedby;
    @Size(max = 255)
    @Column(name = "isactive")
    private String isactive;

    public AdEmploymentSituation() {
    }

    public AdEmploymentSituation(Long id) {
        this.id = id;
    }

    public AdEmploymentSituation(Long id, String codemploymentsituation) {
        this.id = id;
        this.codemploymentsituation = codemploymentsituation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodemploymentsituation() {
        return codemploymentsituation;
    }

    public void setCodemploymentsituation(String codemploymentsituation) {
        this.codemploymentsituation = codemploymentsituation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getCreatedby() {
        return createdby;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public String getUpdatedby() {
        return updatedby;
    }

    public void setUpdatedby(String updatedby) {
        this.updatedby = updatedby;
    }

    public String getIsactive() {
        return isactive;
    }

    public void setIsactive(String isactive) {
        this.isactive = isactive;
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
        if (!(object instanceof AdEmploymentSituation)) {
            return false;
        }
        AdEmploymentSituation other = (AdEmploymentSituation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.waytechs.model.entities.AdEmploymentSituation[ id=" + id + " ]";
    }
    
}
