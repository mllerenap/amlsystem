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
@Table(name = "ad_politics")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AdPolitics.findAll", query = "SELECT a FROM AdPolitics a")
    , @NamedQuery(name = "AdPolitics.findById", query = "SELECT a FROM AdPolitics a WHERE a.id = :id")
    , @NamedQuery(name = "AdPolitics.findByCodpolitic", query = "SELECT a FROM AdPolitics a WHERE a.codpolitic = :codpolitic")
    , @NamedQuery(name = "AdPolitics.findByName", query = "SELECT a FROM AdPolitics a WHERE a.name = :name")
    , @NamedQuery(name = "AdPolitics.findByDescription", query = "SELECT a FROM AdPolitics a WHERE a.description = :description")
    , @NamedQuery(name = "AdPolitics.findByCreated", query = "SELECT a FROM AdPolitics a WHERE a.created = :created")
    , @NamedQuery(name = "AdPolitics.findByCreatedby", query = "SELECT a FROM AdPolitics a WHERE a.createdby = :createdby")
    , @NamedQuery(name = "AdPolitics.findByUpdated", query = "SELECT a FROM AdPolitics a WHERE a.updated = :updated")
    , @NamedQuery(name = "AdPolitics.findByUpdatedby", query = "SELECT a FROM AdPolitics a WHERE a.updatedby = :updatedby")
    , @NamedQuery(name = "AdPolitics.findByIsactive", query = "SELECT a FROM AdPolitics a WHERE a.isactive = :isactive")})
public class AdPolitics implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "codpolitic")
    private String codpolitic;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "adPoliticsId")
    private List<GlDocument> glDocumentList;

    public AdPolitics() {
    }

    public AdPolitics(Long id) {
        this.id = id;
    }

    public AdPolitics(Long id, String codpolitic) {
        this.id = id;
        this.codpolitic = codpolitic;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodpolitic() {
        return codpolitic;
    }

    public void setCodpolitic(String codpolitic) {
        this.codpolitic = codpolitic;
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
    public List<GlDocument> getGlDocumentList() {
        return glDocumentList;
    }

    public void setGlDocumentList(List<GlDocument> glDocumentList) {
        this.glDocumentList = glDocumentList;
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
        if (!(object instanceof AdPolitics)) {
            return false;
        }
        AdPolitics other = (AdPolitics) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.waytechs.model.entities.AdPolitics[ id=" + id + " ]";
    }
    
}
