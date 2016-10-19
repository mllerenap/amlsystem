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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "ad_type_transaction")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AdTypeTransaction.findAll", query = "SELECT a FROM AdTypeTransaction a")
    , @NamedQuery(name = "AdTypeTransaction.findById", query = "SELECT a FROM AdTypeTransaction a WHERE a.id = :id")
    , @NamedQuery(name = "AdTypeTransaction.findByCodtypetransaction", query = "SELECT a FROM AdTypeTransaction a WHERE a.codtypetransaction = :codtypetransaction")
    , @NamedQuery(name = "AdTypeTransaction.findByName", query = "SELECT a FROM AdTypeTransaction a WHERE a.name = :name")
    , @NamedQuery(name = "AdTypeTransaction.findByDescription", query = "SELECT a FROM AdTypeTransaction a WHERE a.description = :description")
    , @NamedQuery(name = "AdTypeTransaction.findByCreated", query = "SELECT a FROM AdTypeTransaction a WHERE a.created = :created")
    , @NamedQuery(name = "AdTypeTransaction.findByCreatedby", query = "SELECT a FROM AdTypeTransaction a WHERE a.createdby = :createdby")
    , @NamedQuery(name = "AdTypeTransaction.findByUpdated", query = "SELECT a FROM AdTypeTransaction a WHERE a.updated = :updated")
    , @NamedQuery(name = "AdTypeTransaction.findByUpdatedby", query = "SELECT a FROM AdTypeTransaction a WHERE a.updatedby = :updatedby")
    , @NamedQuery(name = "AdTypeTransaction.findByIsactive", query = "SELECT a FROM AdTypeTransaction a WHERE a.isactive = :isactive")})
public class AdTypeTransaction implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "codtypetransaction")
    private String codtypetransaction;
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
    @JoinColumn(name = "ad_type_business_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private AdTypeBusiness adTypeBusinessId;

    public AdTypeTransaction() {
    }

    public AdTypeTransaction(Long id) {
        this.id = id;
    }

    public AdTypeTransaction(Long id, String codtypetransaction) {
        this.id = id;
        this.codtypetransaction = codtypetransaction;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodtypetransaction() {
        return codtypetransaction;
    }

    public void setCodtypetransaction(String codtypetransaction) {
        this.codtypetransaction = codtypetransaction;
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

    public AdTypeBusiness getAdTypeBusinessId() {
        return adTypeBusinessId;
    }

    public void setAdTypeBusinessId(AdTypeBusiness adTypeBusinessId) {
        this.adTypeBusinessId = adTypeBusinessId;
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
        if (!(object instanceof AdTypeTransaction)) {
            return false;
        }
        AdTypeTransaction other = (AdTypeTransaction) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.waytechs.model.entities.AdTypeTransaction[ id=" + id + " ]";
    }
    
}
