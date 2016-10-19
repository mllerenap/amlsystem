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
@Table(name = "ad_canton")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AdCanton.findAll", query = "SELECT a FROM AdCanton a")
    , @NamedQuery(name = "AdCanton.findById", query = "SELECT a FROM AdCanton a WHERE a.id = :id")
    , @NamedQuery(name = "AdCanton.findByCodcanton", query = "SELECT a FROM AdCanton a WHERE a.codcanton = :codcanton")
    , @NamedQuery(name = "AdCanton.findByName", query = "SELECT a FROM AdCanton a WHERE a.name = :name")
    , @NamedQuery(name = "AdCanton.findByDescription", query = "SELECT a FROM AdCanton a WHERE a.description = :description")
    , @NamedQuery(name = "AdCanton.findByCreated", query = "SELECT a FROM AdCanton a WHERE a.created = :created")
    , @NamedQuery(name = "AdCanton.findByCreatedby", query = "SELECT a FROM AdCanton a WHERE a.createdby = :createdby")
    , @NamedQuery(name = "AdCanton.findByUpdated", query = "SELECT a FROM AdCanton a WHERE a.updated = :updated")
    , @NamedQuery(name = "AdCanton.findByUpdatedby", query = "SELECT a FROM AdCanton a WHERE a.updatedby = :updatedby")
    , @NamedQuery(name = "AdCanton.findByIsactive", query = "SELECT a FROM AdCanton a WHERE a.isactive = :isactive")
    , @NamedQuery(name = "AdCanton.findByIscity", query = "SELECT a FROM AdCanton a WHERE a.iscity = :iscity")})
public class AdCanton implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "codcanton")
    private String codcanton;
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
    @Column(name = "iscity")
    private BigInteger iscity;
    @OneToMany(mappedBy = "adCantonId")
    private List<GlAddress> glAddressList;
    @JoinColumn(name = "ad_province_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private AdProvince adProvinceId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "adCantonId")
    private List<GlPeople> glPeopleList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "adCantonId")
    private List<GlAddressAgency> glAddressAgencyList;

    public AdCanton() {
    }

    public AdCanton(Long id) {
        this.id = id;
    }

    public AdCanton(Long id, String codcanton) {
        this.id = id;
        this.codcanton = codcanton;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodcanton() {
        return codcanton;
    }

    public void setCodcanton(String codcanton) {
        this.codcanton = codcanton;
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

    public BigInteger getIscity() {
        return iscity;
    }

    public void setIscity(BigInteger iscity) {
        this.iscity = iscity;
    }

    @XmlTransient
    public List<GlAddress> getGlAddressList() {
        return glAddressList;
    }

    public void setGlAddressList(List<GlAddress> glAddressList) {
        this.glAddressList = glAddressList;
    }

    public AdProvince getAdProvinceId() {
        return adProvinceId;
    }

    public void setAdProvinceId(AdProvince adProvinceId) {
        this.adProvinceId = adProvinceId;
    }

    @XmlTransient
    public List<GlPeople> getGlPeopleList() {
        return glPeopleList;
    }

    public void setGlPeopleList(List<GlPeople> glPeopleList) {
        this.glPeopleList = glPeopleList;
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
        if (!(object instanceof AdCanton)) {
            return false;
        }
        AdCanton other = (AdCanton) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.waytechs.model.entities.AdCanton[ id=" + id + " ]";
    }
    
}
