/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.waytechs.model.entities;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mllerena
 */
@Entity
@Table(name = "gl_document_people")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GlDocumentPeople.findAll", query = "SELECT g FROM GlDocumentPeople g")
    , @NamedQuery(name = "GlDocumentPeople.findById", query = "SELECT g FROM GlDocumentPeople g WHERE g.id = :id")
    , @NamedQuery(name = "GlDocumentPeople.findByExpirationdate", query = "SELECT g FROM GlDocumentPeople g WHERE g.expirationdate = :expirationdate")
    , @NamedQuery(name = "GlDocumentPeople.findByYear", query = "SELECT g FROM GlDocumentPeople g WHERE g.year = :year")
    , @NamedQuery(name = "GlDocumentPeople.findByMounth", query = "SELECT g FROM GlDocumentPeople g WHERE g.mounth = :mounth")
    , @NamedQuery(name = "GlDocumentPeople.findByCreated", query = "SELECT g FROM GlDocumentPeople g WHERE g.created = :created")
    , @NamedQuery(name = "GlDocumentPeople.findByCreatedby", query = "SELECT g FROM GlDocumentPeople g WHERE g.createdby = :createdby")
    , @NamedQuery(name = "GlDocumentPeople.findByUpdated", query = "SELECT g FROM GlDocumentPeople g WHERE g.updated = :updated")
    , @NamedQuery(name = "GlDocumentPeople.findByUpdatedby", query = "SELECT g FROM GlDocumentPeople g WHERE g.updatedby = :updatedby")
    , @NamedQuery(name = "GlDocumentPeople.findByIsactive", query = "SELECT g FROM GlDocumentPeople g WHERE g.isactive = :isactive")})
public class GlDocumentPeople implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Long id;
    @Column(name = "expirationdate")
    @Temporal(TemporalType.DATE)
    private Date expirationdate;
    @Column(name = "year")
    private BigInteger year;
    @Column(name = "mounth")
    private BigInteger mounth;
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
    @JoinColumn(name = "gl_document_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private GlDocument glDocumentId;
    @JoinColumn(name = "gl_people_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private GlPeople glPeopleId;

    public GlDocumentPeople() {
    }

    public GlDocumentPeople(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getExpirationdate() {
        return expirationdate;
    }

    public void setExpirationdate(Date expirationdate) {
        this.expirationdate = expirationdate;
    }

    public BigInteger getYear() {
        return year;
    }

    public void setYear(BigInteger year) {
        this.year = year;
    }

    public BigInteger getMounth() {
        return mounth;
    }

    public void setMounth(BigInteger mounth) {
        this.mounth = mounth;
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

    public GlDocument getGlDocumentId() {
        return glDocumentId;
    }

    public void setGlDocumentId(GlDocument glDocumentId) {
        this.glDocumentId = glDocumentId;
    }

    public GlPeople getGlPeopleId() {
        return glPeopleId;
    }

    public void setGlPeopleId(GlPeople glPeopleId) {
        this.glPeopleId = glPeopleId;
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
        if (!(object instanceof GlDocumentPeople)) {
            return false;
        }
        GlDocumentPeople other = (GlDocumentPeople) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.waytechs.model.entities.GlDocumentPeople[ id=" + id + " ]";
    }
    
}
