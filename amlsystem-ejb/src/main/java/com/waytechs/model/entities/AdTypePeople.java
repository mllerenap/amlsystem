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
@Table(name = "ad_type_people")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AdTypePeople.findAll", query = "SELECT a FROM AdTypePeople a")
    , @NamedQuery(name = "AdTypePeople.findById", query = "SELECT a FROM AdTypePeople a WHERE a.id = :id")
    , @NamedQuery(name = "AdTypePeople.findByCodtypepeople", query = "SELECT a FROM AdTypePeople a WHERE a.codtypepeople = :codtypepeople")
    , @NamedQuery(name = "AdTypePeople.findByName", query = "SELECT a FROM AdTypePeople a WHERE a.name = :name")
    , @NamedQuery(name = "AdTypePeople.findByDescription", query = "SELECT a FROM AdTypePeople a WHERE a.description = :description")
    , @NamedQuery(name = "AdTypePeople.findByCreated", query = "SELECT a FROM AdTypePeople a WHERE a.created = :created")
    , @NamedQuery(name = "AdTypePeople.findByCreatedby", query = "SELECT a FROM AdTypePeople a WHERE a.createdby = :createdby")
    , @NamedQuery(name = "AdTypePeople.findByUpdated", query = "SELECT a FROM AdTypePeople a WHERE a.updated = :updated")
    , @NamedQuery(name = "AdTypePeople.findByUpdatedby", query = "SELECT a FROM AdTypePeople a WHERE a.updatedby = :updatedby")
    , @NamedQuery(name = "AdTypePeople.findByIsactive", query = "SELECT a FROM AdTypePeople a WHERE a.isactive = :isactive")})
public class AdTypePeople implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "codtypepeople")
    private String codtypepeople;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "adTypePeopleId")
    private List<GlPeople> glPeopleList;

    public AdTypePeople() {
    }

    public AdTypePeople(Long id) {
        this.id = id;
    }

    public AdTypePeople(Long id, String codtypepeople) {
        this.id = id;
        this.codtypepeople = codtypepeople;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodtypepeople() {
        return codtypepeople;
    }

    public void setCodtypepeople(String codtypepeople) {
        this.codtypepeople = codtypepeople;
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
        if (!(object instanceof AdTypePeople)) {
            return false;
        }
        AdTypePeople other = (AdTypePeople) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.waytechs.model.entities.AdTypePeople[ id=" + id + " ]";
    }
    
}
