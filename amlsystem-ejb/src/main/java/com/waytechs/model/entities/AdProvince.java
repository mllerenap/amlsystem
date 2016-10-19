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
 * @author mllerena
 */
@Entity
@Table(name = "ad_province")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AdProvince.findAll", query = "SELECT a FROM AdProvince a")
    , @NamedQuery(name = "AdProvince.findById", query = "SELECT a FROM AdProvince a WHERE a.id = :id")
    , @NamedQuery(name = "AdProvince.findByCodprovince", query = "SELECT a FROM AdProvince a WHERE a.codprovince = :codprovince")
    , @NamedQuery(name = "AdProvince.findByName", query = "SELECT a FROM AdProvince a WHERE a.name = :name")
    , @NamedQuery(name = "AdProvince.findByDescription", query = "SELECT a FROM AdProvince a WHERE a.description = :description")
    , @NamedQuery(name = "AdProvince.findByCreated", query = "SELECT a FROM AdProvince a WHERE a.created = :created")
    , @NamedQuery(name = "AdProvince.findByCreatedby", query = "SELECT a FROM AdProvince a WHERE a.createdby = :createdby")
    , @NamedQuery(name = "AdProvince.findByUpdated", query = "SELECT a FROM AdProvince a WHERE a.updated = :updated")
    , @NamedQuery(name = "AdProvince.findByUpdatedby", query = "SELECT a FROM AdProvince a WHERE a.updatedby = :updatedby")
    , @NamedQuery(name = "AdProvince.findByIsactive", query = "SELECT a FROM AdProvince a WHERE a.isactive = :isactive")})
public class AdProvince implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "codprovince")
    private String codprovince;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "adProvinceId")
    private List<AdCanton> adCantonList;
    @JoinColumn(name = "ad_country_id", referencedColumnName = "id")
    @ManyToOne
    private AdCountry adCountryId;

    public AdProvince() {
    }

    public AdProvince(Long id) {
        this.id = id;
    }

    public AdProvince(Long id, String codprovince) {
        this.id = id;
        this.codprovince = codprovince;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodprovince() {
        return codprovince;
    }

    public void setCodprovince(String codprovince) {
        this.codprovince = codprovince;
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
    public List<AdCanton> getAdCantonList() {
        return adCantonList;
    }

    public void setAdCantonList(List<AdCanton> adCantonList) {
        this.adCantonList = adCantonList;
    }

    public AdCountry getAdCountryId() {
        return adCountryId;
    }

    public void setAdCountryId(AdCountry adCountryId) {
        this.adCountryId = adCountryId;
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
        if (!(object instanceof AdProvince)) {
            return false;
        }
        AdProvince other = (AdProvince) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.waytechs.model.entities.AdProvince[ id=" + id + " ]";
    }
    
}
