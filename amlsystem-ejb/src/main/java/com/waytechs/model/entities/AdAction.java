/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.waytechs.model.entities;

import com.waytechs.model.converters.YesNoConverter;
import com.waytechs.model.enums.YesNo;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author mllerena
 */
@Entity
@Table(name = "ad_action")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AdAction.findAll", query = "SELECT a FROM AdAction a")
    , @NamedQuery(name = "AdAction.findById", query = "SELECT a FROM AdAction a WHERE a.id = :id")
    , @NamedQuery(name = "AdAction.findByName", query = "SELECT a FROM AdAction a WHERE a.name = :name")
    , @NamedQuery(name = "AdAction.findByCreated", query = "SELECT a FROM AdAction a WHERE a.created = :created")
    , @NamedQuery(name = "AdAction.findByCreatedby", query = "SELECT a FROM AdAction a WHERE a.createdby = :createdby")
    , @NamedQuery(name = "AdAction.findByUpdated", query = "SELECT a FROM AdAction a WHERE a.updated = :updated")
    , @NamedQuery(name = "AdAction.findByUpdatedby", query = "SELECT a FROM AdAction a WHERE a.updatedby = :updatedby")
    , @NamedQuery(name = "AdAction.findByIsactive", query = "SELECT a FROM AdAction a WHERE a.isactive = :isactive")
    , @NamedQuery(name = "AdAction.findByAdModuleId", query = "SELECT a FROM AdAction a WHERE a.adModuleId = :adModuleId and a.isactive = :isactive")
})
public class AdAction implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private BigInteger id;
    
    @Size(max = 255)
    @Column(name = "name")
    private String name;
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
    
    
    @OneToMany(mappedBy = "adActionId")
    private List<AdPermission> adPermissionList;
    
    @JoinColumn(name = "ad_module_id", referencedColumnName = "id")
    @ManyToOne
    private AdModule adModuleId;
    
    @Transient
    private boolean nuevo;

    public AdAction() {
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    

    public AdModule getAdModuleId() {
        return adModuleId;
    }

    public void setAdModuleId(AdModule adModuleId) {
        this.adModuleId = adModuleId;
    }
    
    

    @XmlTransient
    public List<AdPermission> getAdPermissionList() {
        return adPermissionList;
    }

    public void setAdPermissionList(List<AdPermission> adPermissionList) {
        this.adPermissionList = adPermissionList;
    }
    
    public boolean isNuevo() {
        return nuevo;
    }

    public void setNuevo(boolean nuevo) {
        this.nuevo = nuevo;
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
        if (!(object instanceof AdAction)) {
            return false;
        }
        AdAction other = (AdAction) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.waytechs.model.entities.AdAction[ id=" + id + " ]";
    }
    
}
