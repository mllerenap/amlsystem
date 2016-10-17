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
@Table(name = "ad_menu")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AdMenu.findAll", query = "SELECT a FROM AdMenu a")
    , @NamedQuery(name = "AdMenu.findById", query = "SELECT a FROM AdMenu a WHERE a.id = :id")
    , @NamedQuery(name = "AdMenu.findByName", query = "SELECT a FROM AdMenu a WHERE a.name = :name")
    , @NamedQuery(name = "AdMenu.findByCreated", query = "SELECT a FROM AdMenu a WHERE a.created = :created")
    , @NamedQuery(name = "AdMenu.findByCreatedby", query = "SELECT a FROM AdMenu a WHERE a.createdby = :createdby")
    , @NamedQuery(name = "AdMenu.findByUpdated", query = "SELECT a FROM AdMenu a WHERE a.updated = :updated")
    , @NamedQuery(name = "AdMenu.findByUpdatedby", query = "SELECT a FROM AdMenu a WHERE a.updatedby = :updatedby")
    , @NamedQuery(name = "AdMenu.findByIsactive", query = "SELECT a FROM AdMenu a WHERE a.isactive = :isactive")
    , @NamedQuery(name = "AdMenu.findByAdMenuParentId", query = "SELECT a FROM AdMenu a WHERE a.adMenuParentId = :adMenuParentId and a.isactive = :isactive order by a.orden")

})
public class AdMenu implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Long id;
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
    
    @OneToMany(mappedBy = "adMenuParentId")
    private List<AdMenu> adMenuList;
    @JoinColumn(name = "ad_menu_parent_id", referencedColumnName = "id")
    @ManyToOne
    private AdMenu adMenuParentId;
    @OneToMany(mappedBy = "adMenuId")
    private List<AdMenuRole> adMenuRoleList;
    
    @Transient
    private AdMenuRole adMenuRole;
    
    @Size(max = 500)
    @Column(name = "url")
    private String url;
    
    @Size(max = 500)
    @Column(name = "icon")
    private String icon;
    
    
    @Column(name = "orden")
    private Long orden;

    public AdMenu() {
    }

    public AdMenu(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Long getOrden() {
        return orden;
    }

    public void setOrden(Long orden) {
        this.orden = orden;
    }

    @XmlTransient
    public List<AdMenu> getAdMenuList() {
        return adMenuList;
    }

    public void setAdMenuList(List<AdMenu> adMenuList) {
        this.adMenuList = adMenuList;
    }

    public AdMenu getAdMenuParentId() {
        return adMenuParentId;
    }

    public void setAdMenuParentId(AdMenu adMenuParentId) {
        this.adMenuParentId = adMenuParentId;
    }

    @XmlTransient
    public List<AdMenuRole> getAdMenuRoleList() {
        return adMenuRoleList;
    }

    public void setAdMenuRoleList(List<AdMenuRole> adMenuRoleList) {
        this.adMenuRoleList = adMenuRoleList;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public AdMenuRole getAdMenuRole() {
        return adMenuRole;
    }

    public void setAdMenuRole(AdMenuRole adMenuRole) {
        this.adMenuRole = adMenuRole;
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
        if (!(object instanceof AdMenu)) {
            return false;
        }
        AdMenu other = (AdMenu) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.waytechs.model.entities.AdMenu[ id=" + id + " ]";
    }
    
}
