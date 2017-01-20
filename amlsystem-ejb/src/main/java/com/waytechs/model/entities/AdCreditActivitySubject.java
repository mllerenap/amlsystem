/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.waytechs.model.entities;

import com.waytechs.model.converters.YesNoConverter;
import com.waytechs.model.enums.YesNo;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
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
@Table(name = "ad_credit_activity_subject")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AdCreditActivitySubject.findAll", query = "SELECT a FROM AdCreditActivitySubject a")
    , @NamedQuery(name = "AdCreditActivitySubject.findById", query = "SELECT a FROM AdCreditActivitySubject a WHERE a.id = :id")
    , @NamedQuery(name = "AdCreditActivitySubject.findByCodcreditactivitysubject", query = "SELECT a FROM AdCreditActivitySubject a WHERE a.codcreditactivitysubject = :codcreditactivitysubject")
    , @NamedQuery(name = "AdCreditActivitySubject.findByName", query = "SELECT a FROM AdCreditActivitySubject a WHERE a.name = :name")
    , @NamedQuery(name = "AdCreditActivitySubject.findByDescription", query = "SELECT a FROM AdCreditActivitySubject a WHERE a.description = :description")
    , @NamedQuery(name = "AdCreditActivitySubject.findByCreated", query = "SELECT a FROM AdCreditActivitySubject a WHERE a.created = :created")
    , @NamedQuery(name = "AdCreditActivitySubject.findByCreatedby", query = "SELECT a FROM AdCreditActivitySubject a WHERE a.createdby = :createdby")
    , @NamedQuery(name = "AdCreditActivitySubject.findByUpdated", query = "SELECT a FROM AdCreditActivitySubject a WHERE a.updated = :updated")
    , @NamedQuery(name = "AdCreditActivitySubject.findByUpdatedby", query = "SELECT a FROM AdCreditActivitySubject a WHERE a.updatedby = :updatedby")
    , @NamedQuery(name = "AdCreditActivitySubject.findByIsactive", query = "SELECT a FROM AdCreditActivitySubject a WHERE a.isactive = :isactive")
        , @NamedQuery(name = "AdCreditActivitySubject.findByAdTypeEconomicActivityId", query = "SELECT a FROM AdCreditActivitySubject a WHERE a.adTypeEconomicActivityId = :adTypeEconomicActivityId and a.isactive = :isactive")
})
public class AdCreditActivitySubject implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private BigInteger id;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "codcreditactivitysubject")
    private String codcreditactivitysubject;
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
    @Basic(optional = false)
    @Column(name = "isactive")
    @Convert(converter = YesNoConverter.class)
    private YesNo isactive;
    
    @JoinColumn(name = "ad_type_economic_activity_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private AdTypeEconomicActivity adTypeEconomicActivityId;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "adCreditActivitySubjectId")
    private List<GlPeople> glPeopleList;

    public AdCreditActivitySubject() {
    }

    public AdCreditActivitySubject(BigInteger id) {
        this.id = id;
    }

    public AdCreditActivitySubject(BigInteger id, String codcreditactivitysubject) {
        this.id = id;
        this.codcreditactivitysubject = codcreditactivitysubject;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getCodcreditactivitysubject() {
        return codcreditactivitysubject;
    }

    public void setCodcreditactivitysubject(String codcreditactivitysubject) {
        this.codcreditactivitysubject = codcreditactivitysubject;
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

    public YesNo getIsactive() {
        return isactive;
    }

    public void setIsactive(YesNo isactive) {
        this.isactive = isactive;
    }

    public AdTypeEconomicActivity getAdTypeEconomicActivityId() {
        return adTypeEconomicActivityId;
    }

    public void setAdTypeEconomicActivityId(AdTypeEconomicActivity adTypeEconomicActivityId) {
        this.adTypeEconomicActivityId = adTypeEconomicActivityId;
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
        if (!(object instanceof AdCreditActivitySubject)) {
            return false;
        }
        AdCreditActivitySubject other = (AdCreditActivitySubject) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.waytechs.model.entities.AdCreditActivitySubject[ id=" + id + " ]";
    }
    
}
