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
@Table(name = "ad_category_pep")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AdCategoryPep.findAll", query = "SELECT a FROM AdCategoryPep a")
    , @NamedQuery(name = "AdCategoryPep.findById", query = "SELECT a FROM AdCategoryPep a WHERE a.id = :id")
    , @NamedQuery(name = "AdCategoryPep.findByCodcategorypep", query = "SELECT a FROM AdCategoryPep a WHERE a.codcategorypep = :codcategorypep")
    , @NamedQuery(name = "AdCategoryPep.findByName", query = "SELECT a FROM AdCategoryPep a WHERE a.name = :name")
    , @NamedQuery(name = "AdCategoryPep.findByDescription", query = "SELECT a FROM AdCategoryPep a WHERE a.description = :description")
    , @NamedQuery(name = "AdCategoryPep.findByCreated", query = "SELECT a FROM AdCategoryPep a WHERE a.created = :created")
    , @NamedQuery(name = "AdCategoryPep.findByCreatedby", query = "SELECT a FROM AdCategoryPep a WHERE a.createdby = :createdby")
    , @NamedQuery(name = "AdCategoryPep.findByUpdated", query = "SELECT a FROM AdCategoryPep a WHERE a.updated = :updated")
    , @NamedQuery(name = "AdCategoryPep.findByUpdatedby", query = "SELECT a FROM AdCategoryPep a WHERE a.updatedby = :updatedby")
    , @NamedQuery(name = "AdCategoryPep.findByIsactive", query = "SELECT a FROM AdCategoryPep a WHERE a.isactive = :isactive")})
public class AdCategoryPep implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "codcategorypep")
    private String codcategorypep;
    @Size(max = 100)
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
    @OneToMany(mappedBy = "adCategoryPepId")
    private List<AdTypePep> adTypePepList;

    public AdCategoryPep() {
    }

    public AdCategoryPep(Long id) {
        this.id = id;
    }

    public AdCategoryPep(Long id, String codcategorypep) {
        this.id = id;
        this.codcategorypep = codcategorypep;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodcategorypep() {
        return codcategorypep;
    }

    public void setCodcategorypep(String codcategorypep) {
        this.codcategorypep = codcategorypep;
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
    public List<AdTypePep> getAdTypePepList() {
        return adTypePepList;
    }

    public void setAdTypePepList(List<AdTypePep> adTypePepList) {
        this.adTypePepList = adTypePepList;
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
        if (!(object instanceof AdCategoryPep)) {
            return false;
        }
        AdCategoryPep other = (AdCategoryPep) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.waytechs.model.entities.AdCategoryPep[ id=" + id + " ]";
    }
    
}
