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
@Table(name = "gl_document")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GlDocument.findAll", query = "SELECT g FROM GlDocument g")
    , @NamedQuery(name = "GlDocument.findById", query = "SELECT g FROM GlDocument g WHERE g.id = :id")
    , @NamedQuery(name = "GlDocument.findByCoddocument", query = "SELECT g FROM GlDocument g WHERE g.coddocument = :coddocument")
    , @NamedQuery(name = "GlDocument.findByName", query = "SELECT g FROM GlDocument g WHERE g.name = :name")
    , @NamedQuery(name = "GlDocument.findByDescription", query = "SELECT g FROM GlDocument g WHERE g.description = :description")
    , @NamedQuery(name = "GlDocument.findByCreated", query = "SELECT g FROM GlDocument g WHERE g.created = :created")
    , @NamedQuery(name = "GlDocument.findByCreatedby", query = "SELECT g FROM GlDocument g WHERE g.createdby = :createdby")
    , @NamedQuery(name = "GlDocument.findByUpdated", query = "SELECT g FROM GlDocument g WHERE g.updated = :updated")
    , @NamedQuery(name = "GlDocument.findByUpdatedby", query = "SELECT g FROM GlDocument g WHERE g.updatedby = :updatedby")
    , @NamedQuery(name = "GlDocument.findByIsactive", query = "SELECT g FROM GlDocument g WHERE g.isactive = :isactive")
    , @NamedQuery(name = "GlDocument.findByIsexpires", query = "SELECT g FROM GlDocument g WHERE g.isexpires = :isexpires")})
public class GlDocument implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "coddocument")
    private String coddocument;
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
    @Column(name = "isexpires")
    private BigInteger isexpires;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "glDocumentId")
    private List<GlDocumentPeople> glDocumentPeopleList;
    @JoinColumn(name = "ad_politics_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private AdPolitics adPoliticsId;

    public GlDocument() {
    }

    public GlDocument(Long id) {
        this.id = id;
    }

    public GlDocument(Long id, String coddocument) {
        this.id = id;
        this.coddocument = coddocument;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCoddocument() {
        return coddocument;
    }

    public void setCoddocument(String coddocument) {
        this.coddocument = coddocument;
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

    public BigInteger getIsexpires() {
        return isexpires;
    }

    public void setIsexpires(BigInteger isexpires) {
        this.isexpires = isexpires;
    }

    @XmlTransient
    public List<GlDocumentPeople> getGlDocumentPeopleList() {
        return glDocumentPeopleList;
    }

    public void setGlDocumentPeopleList(List<GlDocumentPeople> glDocumentPeopleList) {
        this.glDocumentPeopleList = glDocumentPeopleList;
    }

    public AdPolitics getAdPoliticsId() {
        return adPoliticsId;
    }

    public void setAdPoliticsId(AdPolitics adPoliticsId) {
        this.adPoliticsId = adPoliticsId;
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
        if (!(object instanceof GlDocument)) {
            return false;
        }
        GlDocument other = (GlDocument) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.waytechs.model.entities.GlDocument[ id=" + id + " ]";
    }
    
}
