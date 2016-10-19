/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.waytechs.model.entities;

import java.io.Serializable;
import java.math.BigInteger;
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
@Table(name = "ad_type_building")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AdTypeBuilding.findAll", query = "SELECT a FROM AdTypeBuilding a")
    , @NamedQuery(name = "AdTypeBuilding.findById", query = "SELECT a FROM AdTypeBuilding a WHERE a.id = :id")
    , @NamedQuery(name = "AdTypeBuilding.findByCodtypebuilding", query = "SELECT a FROM AdTypeBuilding a WHERE a.codtypebuilding = :codtypebuilding")
    , @NamedQuery(name = "AdTypeBuilding.findByName", query = "SELECT a FROM AdTypeBuilding a WHERE a.name = :name")
    , @NamedQuery(name = "AdTypeBuilding.findByDescription", query = "SELECT a FROM AdTypeBuilding a WHERE a.description = :description")
    , @NamedQuery(name = "AdTypeBuilding.findByCreated", query = "SELECT a FROM AdTypeBuilding a WHERE a.created = :created")
    , @NamedQuery(name = "AdTypeBuilding.findByCreatedby", query = "SELECT a FROM AdTypeBuilding a WHERE a.createdby = :createdby")
    , @NamedQuery(name = "AdTypeBuilding.findByUpdated", query = "SELECT a FROM AdTypeBuilding a WHERE a.updated = :updated")
    , @NamedQuery(name = "AdTypeBuilding.findByUpdatedby", query = "SELECT a FROM AdTypeBuilding a WHERE a.updatedby = :updatedby")
    , @NamedQuery(name = "AdTypeBuilding.findByIsactive", query = "SELECT a FROM AdTypeBuilding a WHERE a.isactive = :isactive")
    , @NamedQuery(name = "AdTypeBuilding.findByIsoffice", query = "SELECT a FROM AdTypeBuilding a WHERE a.isoffice = :isoffice")})
public class AdTypeBuilding implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "codtypebuilding")
    private String codtypebuilding;
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
    @Column(name = "isoffice")
    private BigInteger isoffice;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "adTypeBuildingId")
    private List<GlAddress> glAddressList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "adTypeBuildingId")
    private List<GlAddressAgency> glAddressAgencyList;

    public AdTypeBuilding() {
    }

    public AdTypeBuilding(Long id) {
        this.id = id;
    }

    public AdTypeBuilding(Long id, String codtypebuilding) {
        this.id = id;
        this.codtypebuilding = codtypebuilding;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodtypebuilding() {
        return codtypebuilding;
    }

    public void setCodtypebuilding(String codtypebuilding) {
        this.codtypebuilding = codtypebuilding;
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

    public BigInteger getIsoffice() {
        return isoffice;
    }

    public void setIsoffice(BigInteger isoffice) {
        this.isoffice = isoffice;
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
        if (!(object instanceof AdTypeBuilding)) {
            return false;
        }
        AdTypeBuilding other = (AdTypeBuilding) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.waytechs.model.entities.AdTypeBuilding[ id=" + id + " ]";
    }
    
}
