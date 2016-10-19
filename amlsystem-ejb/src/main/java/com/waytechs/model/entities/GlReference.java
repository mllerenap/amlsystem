/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.waytechs.model.entities;

import java.io.Serializable;
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
@Table(name = "gl_reference")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GlReference.findAll", query = "SELECT g FROM GlReference g")
    , @NamedQuery(name = "GlReference.findById", query = "SELECT g FROM GlReference g WHERE g.id = :id")
    , @NamedQuery(name = "GlReference.findByFirstname", query = "SELECT g FROM GlReference g WHERE g.firstname = :firstname")
    , @NamedQuery(name = "GlReference.findByLastname", query = "SELECT g FROM GlReference g WHERE g.lastname = :lastname")
    , @NamedQuery(name = "GlReference.findByBusinessname", query = "SELECT g FROM GlReference g WHERE g.businessname = :businessname")
    , @NamedQuery(name = "GlReference.findByData1", query = "SELECT g FROM GlReference g WHERE g.data1 = :data1")
    , @NamedQuery(name = "GlReference.findByData2", query = "SELECT g FROM GlReference g WHERE g.data2 = :data2")
    , @NamedQuery(name = "GlReference.findByEmail1", query = "SELECT g FROM GlReference g WHERE g.email1 = :email1")
    , @NamedQuery(name = "GlReference.findByEmail2", query = "SELECT g FROM GlReference g WHERE g.email2 = :email2")
    , @NamedQuery(name = "GlReference.findByCelular1", query = "SELECT g FROM GlReference g WHERE g.celular1 = :celular1")
    , @NamedQuery(name = "GlReference.findByCelular2", query = "SELECT g FROM GlReference g WHERE g.celular2 = :celular2")
    , @NamedQuery(name = "GlReference.findByTelephone1", query = "SELECT g FROM GlReference g WHERE g.telephone1 = :telephone1")
    , @NamedQuery(name = "GlReference.findByTelephone2", query = "SELECT g FROM GlReference g WHERE g.telephone2 = :telephone2")
    , @NamedQuery(name = "GlReference.findByCreated", query = "SELECT g FROM GlReference g WHERE g.created = :created")
    , @NamedQuery(name = "GlReference.findByCreatedby", query = "SELECT g FROM GlReference g WHERE g.createdby = :createdby")
    , @NamedQuery(name = "GlReference.findByUpdated", query = "SELECT g FROM GlReference g WHERE g.updated = :updated")
    , @NamedQuery(name = "GlReference.findByUpdatedby", query = "SELECT g FROM GlReference g WHERE g.updatedby = :updatedby")
    , @NamedQuery(name = "GlReference.findByIsactive", query = "SELECT g FROM GlReference g WHERE g.isactive = :isactive")})
public class GlReference implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Long id;
    @Size(max = 255)
    @Column(name = "firstname")
    private String firstname;
    @Size(max = 255)
    @Column(name = "lastname")
    private String lastname;
    @Size(max = 255)
    @Column(name = "businessname")
    private String businessname;
    @Size(max = 255)
    @Column(name = "data1")
    private String data1;
    @Size(max = 255)
    @Column(name = "data2")
    private String data2;
    @Size(max = 100)
    @Column(name = "email1")
    private String email1;
    @Size(max = 100)
    @Column(name = "email2")
    private String email2;
    @Size(max = 25)
    @Column(name = "celular1")
    private String celular1;
    @Size(max = 25)
    @Column(name = "celular2")
    private String celular2;
    @Size(max = 25)
    @Column(name = "telephone1")
    private String telephone1;
    @Size(max = 25)
    @Column(name = "telephone2")
    private String telephone2;
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
    @JoinColumn(name = "ad_relationship_personal_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private AdRelationshipPersonal adRelationshipPersonalId;
    @JoinColumn(name = "ad_type_reference_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private AdTypeReference adTypeReferenceId;
    @JoinColumn(name = "gl_people_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private GlPeople glPeopleId;

    public GlReference() {
    }

    public GlReference(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getBusinessname() {
        return businessname;
    }

    public void setBusinessname(String businessname) {
        this.businessname = businessname;
    }

    public String getData1() {
        return data1;
    }

    public void setData1(String data1) {
        this.data1 = data1;
    }

    public String getData2() {
        return data2;
    }

    public void setData2(String data2) {
        this.data2 = data2;
    }

    public String getEmail1() {
        return email1;
    }

    public void setEmail1(String email1) {
        this.email1 = email1;
    }

    public String getEmail2() {
        return email2;
    }

    public void setEmail2(String email2) {
        this.email2 = email2;
    }

    public String getCelular1() {
        return celular1;
    }

    public void setCelular1(String celular1) {
        this.celular1 = celular1;
    }

    public String getCelular2() {
        return celular2;
    }

    public void setCelular2(String celular2) {
        this.celular2 = celular2;
    }

    public String getTelephone1() {
        return telephone1;
    }

    public void setTelephone1(String telephone1) {
        this.telephone1 = telephone1;
    }

    public String getTelephone2() {
        return telephone2;
    }

    public void setTelephone2(String telephone2) {
        this.telephone2 = telephone2;
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

    public AdRelationshipPersonal getAdRelationshipPersonalId() {
        return adRelationshipPersonalId;
    }

    public void setAdRelationshipPersonalId(AdRelationshipPersonal adRelationshipPersonalId) {
        this.adRelationshipPersonalId = adRelationshipPersonalId;
    }

    public AdTypeReference getAdTypeReferenceId() {
        return adTypeReferenceId;
    }

    public void setAdTypeReferenceId(AdTypeReference adTypeReferenceId) {
        this.adTypeReferenceId = adTypeReferenceId;
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
        if (!(object instanceof GlReference)) {
            return false;
        }
        GlReference other = (GlReference) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.waytechs.model.entities.GlReference[ id=" + id + " ]";
    }
    
}
