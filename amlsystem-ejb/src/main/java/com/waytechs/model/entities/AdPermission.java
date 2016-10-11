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
@Table(name = "ad_permission")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AdPermission.findAll", query = "SELECT a FROM AdPermission a")
    , @NamedQuery(name = "AdPermission.findById", query = "SELECT a FROM AdPermission a WHERE a.id = :id")
    , @NamedQuery(name = "AdPermission.findByCreated", query = "SELECT a FROM AdPermission a WHERE a.created = :created")
    , @NamedQuery(name = "AdPermission.findByCreatedby", query = "SELECT a FROM AdPermission a WHERE a.createdby = :createdby")
    , @NamedQuery(name = "AdPermission.findByUpdated", query = "SELECT a FROM AdPermission a WHERE a.updated = :updated")
    , @NamedQuery(name = "AdPermission.findByUpdatedby", query = "SELECT a FROM AdPermission a WHERE a.updatedby = :updatedby")
    , @NamedQuery(name = "AdPermission.findByIsactive", query = "SELECT a FROM AdPermission a WHERE a.isactive = :isactive")})
public class AdPermission implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Long id;
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
    @JoinColumn(name = "ad_action_id", referencedColumnName = "id")
    @ManyToOne
    private AdAction adActionId;

    public AdPermission() {
    }

    public AdPermission(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public AdAction getAdActionId() {
        return adActionId;
    }

    public void setAdActionId(AdAction adActionId) {
        this.adActionId = adActionId;
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
        if (!(object instanceof AdPermission)) {
            return false;
        }
        AdPermission other = (AdPermission) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.waytechs.model.entities.AdPermission[ id=" + id + " ]";
    }
    
}
