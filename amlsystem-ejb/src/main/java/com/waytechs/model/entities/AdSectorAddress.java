/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.waytechs.model.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
 * @author mllerena
 */
@Entity
@Table(name = "ad_sector_address")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AdSectorAddress.findAll", query = "SELECT a FROM AdSectorAddress a")
    , @NamedQuery(name = "AdSectorAddress.findById", query = "SELECT a FROM AdSectorAddress a WHERE a.id = :id")
    , @NamedQuery(name = "AdSectorAddress.findByCodtypeaddress", query = "SELECT a FROM AdSectorAddress a WHERE a.codtypeaddress = :codtypeaddress")
    , @NamedQuery(name = "AdSectorAddress.findByName", query = "SELECT a FROM AdSectorAddress a WHERE a.name = :name")
    , @NamedQuery(name = "AdSectorAddress.findByDescription", query = "SELECT a FROM AdSectorAddress a WHERE a.description = :description")
    , @NamedQuery(name = "AdSectorAddress.findByCreated", query = "SELECT a FROM AdSectorAddress a WHERE a.created = :created")
    , @NamedQuery(name = "AdSectorAddress.findByCreatedby", query = "SELECT a FROM AdSectorAddress a WHERE a.createdby = :createdby")
    , @NamedQuery(name = "AdSectorAddress.findByUpdated", query = "SELECT a FROM AdSectorAddress a WHERE a.updated = :updated")
    , @NamedQuery(name = "AdSectorAddress.findByUpdatedby", query = "SELECT a FROM AdSectorAddress a WHERE a.updatedby = :updatedby")
    , @NamedQuery(name = "AdSectorAddress.findByIsactive", query = "SELECT a FROM AdSectorAddress a WHERE a.isactive = :isactive")})
public class AdSectorAddress implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "codtypeaddress")
    private String codtypeaddress;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "adSectorAddressId")
    private List<GlAddress> glAddressList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "adSectorAddressId")
    private List<GlAddressAgency> glAddressAgencyList;

    public AdSectorAddress() {
    }

    public AdSectorAddress(Long id) {
        this.id = id;
    }

    public AdSectorAddress(Long id, String codtypeaddress) {
        this.id = id;
        this.codtypeaddress = codtypeaddress;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodtypeaddress() {
        return codtypeaddress;
    }

    public void setCodtypeaddress(String codtypeaddress) {
        this.codtypeaddress = codtypeaddress;
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

    @XmlTransient
    public List<GlAddress> getGlAddressList() {
        return glAddressList;
    }

    public void setGlAddressList(List<GlAddress> glAddressList) {
        this.glAddressList = glAddressList;
    }

    @XmlTransient
    public List<GlAddressAgency> getGlAddressAgencyList() {
        return glAddressAgencyList;
    }

    public void setGlAddressAgencyList(List<GlAddressAgency> glAddressAgencyList) {
        this.glAddressAgencyList = glAddressAgencyList;
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
        if (!(object instanceof AdSectorAddress)) {
            return false;
        }
        AdSectorAddress other = (AdSectorAddress) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.waytechs.model.entities.AdSectorAddress[ id=" + id + " ]";
    }
    
}
