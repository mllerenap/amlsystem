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
@Table(name = "ad_type_pep")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AdTypePep.findAll", query = "SELECT a FROM AdTypePep a")
    , @NamedQuery(name = "AdTypePep.findById", query = "SELECT a FROM AdTypePep a WHERE a.id = :id")
    , @NamedQuery(name = "AdTypePep.findByCodtypepep", query = "SELECT a FROM AdTypePep a WHERE a.codtypepep = :codtypepep")
    , @NamedQuery(name = "AdTypePep.findByName", query = "SELECT a FROM AdTypePep a WHERE a.name = :name")
    , @NamedQuery(name = "AdTypePep.findByDescription", query = "SELECT a FROM AdTypePep a WHERE a.description = :description")
    , @NamedQuery(name = "AdTypePep.findByCreated", query = "SELECT a FROM AdTypePep a WHERE a.created = :created")
    , @NamedQuery(name = "AdTypePep.findByCreatedby", query = "SELECT a FROM AdTypePep a WHERE a.createdby = :createdby")
    , @NamedQuery(name = "AdTypePep.findByUpdated", query = "SELECT a FROM AdTypePep a WHERE a.updated = :updated")
    , @NamedQuery(name = "AdTypePep.findByUpdatedby", query = "SELECT a FROM AdTypePep a WHERE a.updatedby = :updatedby")
    , @NamedQuery(name = "AdTypePep.findByIsactive", query = "SELECT a FROM AdTypePep a WHERE a.isactive = :isactive")})
public class AdTypePep implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "codtypepep")
    private String codtypepep;
    @Size(max = 150)
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
    @JoinColumn(name = "ad_category_pep_id", referencedColumnName = "id")
    @ManyToOne
    private AdCategoryPep adCategoryPepId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "adTypePepId")
    private List<GlPeople> glPeopleList;

    public AdTypePep() {
    }

    public AdTypePep(Long id) {
        this.id = id;
    }

    public AdTypePep(Long id, String codtypepep) {
        this.id = id;
        this.codtypepep = codtypepep;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodtypepep() {
        return codtypepep;
    }

    public void setCodtypepep(String codtypepep) {
        this.codtypepep = codtypepep;
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

    public AdCategoryPep getAdCategoryPepId() {
        return adCategoryPepId;
    }

    public void setAdCategoryPepId(AdCategoryPep adCategoryPepId) {
        this.adCategoryPepId = adCategoryPepId;
    }

    @XmlTransient
    public List<GlPeople> getGlPeopleList() {
        return glPeopleList;
    }

    public void setGlPeopleList(List<GlPeople> glPeopleList) {
        this.glPeopleList = glPeopleList;
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
        if (!(object instanceof AdTypePep)) {
            return false;
        }
        AdTypePep other = (AdTypePep) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.waytechs.model.entities.AdTypePep[ id=" + id + " ]";
    }
    
}
