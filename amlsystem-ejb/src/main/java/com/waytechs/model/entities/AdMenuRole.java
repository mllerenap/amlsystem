/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.waytechs.model.entities;

import com.waytechs.model.converters.YesNoConverter;
import com.waytechs.model.enums.YesNo;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Convert;
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
@Table(name = "ad_menu_role")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AdMenuRole.findAll", query = "SELECT a FROM AdMenuRole a")
    , @NamedQuery(name = "AdMenuRole.findById", query = "SELECT a FROM AdMenuRole a WHERE a.id = :id")
    , @NamedQuery(name = "AdMenuRole.findByCreated", query = "SELECT a FROM AdMenuRole a WHERE a.created = :created")
    , @NamedQuery(name = "AdMenuRole.findByCreatedby", query = "SELECT a FROM AdMenuRole a WHERE a.createdby = :createdby")
    , @NamedQuery(name = "AdMenuRole.findByUpdated", query = "SELECT a FROM AdMenuRole a WHERE a.updated = :updated")
    , @NamedQuery(name = "AdMenuRole.findByUpdatedby", query = "SELECT a FROM AdMenuRole a WHERE a.updatedby = :updatedby")
    , @NamedQuery(name = "AdMenuRole.findByIsactive", query = "SELECT a FROM AdMenuRole a WHERE a.isactive = :isactive")
    , @NamedQuery(name = "AdMenuRole.findByAdRoleId", query = "SELECT a FROM AdMenuRole a WHERE a.adRoleId = :adRoleId and a.isactive = :isactive")    
    , @NamedQuery(name = "AdMenuRole.findByAdMenuId", query = "SELECT a FROM AdMenuRole a WHERE a.adMenuId = :adMenuId and a.isactive = :isactive")        
        
}) 
public class AdMenuRole implements Serializable {

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
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "isactive")
    @Convert(converter = YesNoConverter.class)
    private YesNo isactive;
    
    
    @JoinColumn(name = "ad_menu_id", referencedColumnName = "id")
    @ManyToOne
    private AdMenu adMenuId;
    
    @JoinColumn(name = "ad_role_id", referencedColumnName = "id")
    @ManyToOne(fetch=javax.persistence.FetchType.EAGER,optional = false)
    private AdRole adRoleId; 

    public AdMenuRole() {
    }

    public AdMenuRole(Long id) {
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

    public YesNo getIsactive() {
        return isactive;
    }

    public void setIsactive(YesNo isactive) {
        this.isactive = isactive;
    }

    

    public AdMenu getAdMenuId() {
        return adMenuId;
    }

    public void setAdMenuId(AdMenu adMenuId) {
        this.adMenuId = adMenuId;
    }

    public AdRole getAdRoleId() {
        return adRoleId;
    }

    public void setAdRoleId(AdRole adRoleId) {
        this.adRoleId = adRoleId;
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
        if (!(object instanceof AdMenuRole)) {
            return false;
        }
        AdMenuRole other = (AdMenuRole) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.waytechs.model.entities.AdMenuRole[ id=" + id + " ]";
    }
    
}
