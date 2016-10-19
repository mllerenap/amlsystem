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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mllerena
 */
@Entity
@Table(name = "ad_relationship_business")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AdRelationshipBusiness.findAll", query = "SELECT a FROM AdRelationshipBusiness a")
    , @NamedQuery(name = "AdRelationshipBusiness.findById", query = "SELECT a FROM AdRelationshipBusiness a WHERE a.id = :id")
    , @NamedQuery(name = "AdRelationshipBusiness.findByCodrelationshipb", query = "SELECT a FROM AdRelationshipBusiness a WHERE a.codrelationshipb = :codrelationshipb")
    , @NamedQuery(name = "AdRelationshipBusiness.findByName", query = "SELECT a FROM AdRelationshipBusiness a WHERE a.name = :name")
    , @NamedQuery(name = "AdRelationshipBusiness.findByDescription", query = "SELECT a FROM AdRelationshipBusiness a WHERE a.description = :description")
    , @NamedQuery(name = "AdRelationshipBusiness.findByCreated", query = "SELECT a FROM AdRelationshipBusiness a WHERE a.created = :created")
    , @NamedQuery(name = "AdRelationshipBusiness.findByCreatedby", query = "SELECT a FROM AdRelationshipBusiness a WHERE a.createdby = :createdby")
    , @NamedQuery(name = "AdRelationshipBusiness.findByUpdated", query = "SELECT a FROM AdRelationshipBusiness a WHERE a.updated = :updated")
    , @NamedQuery(name = "AdRelationshipBusiness.findByUpdatedby", query = "SELECT a FROM AdRelationshipBusiness a WHERE a.updatedby = :updatedby")
    , @NamedQuery(name = "AdRelationshipBusiness.findByIsactive", query = "SELECT a FROM AdRelationshipBusiness a WHERE a.isactive = :isactive")})
public class AdRelationshipBusiness implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "codrelationshipb")
    private String codrelationshipb;
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

    public AdRelationshipBusiness() {
    }

    public AdRelationshipBusiness(Long id) {
        this.id = id;
    }

    public AdRelationshipBusiness(Long id, String codrelationshipb) {
        this.id = id;
        this.codrelationshipb = codrelationshipb;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodrelationshipb() {
        return codrelationshipb;
    }

    public void setCodrelationshipb(String codrelationshipb) {
        this.codrelationshipb = codrelationshipb;
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
        if (!(object instanceof AdRelationshipBusiness)) {
            return false;
        }
        AdRelationshipBusiness other = (AdRelationshipBusiness) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.waytechs.model.entities.AdRelationshipBusiness[ id=" + id + " ]";
    }
    
}
