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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
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
@Table(name = "gl_agency")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GlAgency.findAll", query = "SELECT g FROM GlAgency g")
    , @NamedQuery(name = "GlAgency.findById", query = "SELECT g FROM GlAgency g WHERE g.id = :id")
    , @NamedQuery(name = "GlAgency.findByName", query = "SELECT g FROM GlAgency g WHERE g.name like :name and g.isactive = :isactive")
    , @NamedQuery(name = "GlAgency.findByDescription", query = "SELECT g FROM GlAgency g WHERE g.description = :description")
    , @NamedQuery(name = "GlAgency.findByStartdate", query = "SELECT g FROM GlAgency g WHERE g.startdate = :startdate")
    , @NamedQuery(name = "GlAgency.findByCreated", query = "SELECT g FROM GlAgency g WHERE g.created = :created")
    , @NamedQuery(name = "GlAgency.findByCreatedby", query = "SELECT g FROM GlAgency g WHERE g.createdby = :createdby")
    , @NamedQuery(name = "GlAgency.findByUpdated", query = "SELECT g FROM GlAgency g WHERE g.updated = :updated")
    , @NamedQuery(name = "GlAgency.findByUpdatedby", query = "SELECT g FROM GlAgency g WHERE g.updatedby = :updatedby")
    , @NamedQuery(name = "GlAgency.findByIsactive", query = "SELECT g FROM GlAgency g WHERE g.isactive = :isactive")
    , @NamedQuery(name = "GlAgency.findByGlCompanyId", query = "SELECT g FROM GlAgency g WHERE g.glCompanyId = :glCompanyId and g.isactive = :isactive")
})
public class GlAgency extends AbstractEntityModel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    @SequenceGenerator(name = "GlAgency_seq",
            sequenceName = "gl_gency_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GlAgency_seq")
    private BigInteger id;
    
    @Size(max = 255)
    @Column(name = "name")
    private String name;
    @Size(max = 255)
    @Column(name = "description")
    private String description;
    @Column(name = "startdate")
    @Temporal(TemporalType.DATE)
    private Date startdate;
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
    
    
    @JoinColumn(name = "ad_type_office_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private AdTypeOffice adTypeOfficeId;
    @JoinColumn(name = "gl_company_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private GlCompany glCompanyId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "glAgencyId")
    private List<GlAddressAgency> glAddressAgencyList;

    public GlAgency() {
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
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

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
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

    public AdTypeOffice getAdTypeOfficeId() {
        return adTypeOfficeId;
    }

    public void setAdTypeOfficeId(AdTypeOffice adTypeOfficeId) {
        this.adTypeOfficeId = adTypeOfficeId;
    }

    public GlCompany getGlCompanyId() {
        return glCompanyId;
    }

    public void setGlCompanyId(GlCompany glCompanyId) {
        this.glCompanyId = glCompanyId;
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
        if (!(object instanceof GlAgency)) {
            return false;
        }
        GlAgency other = (GlAgency) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.waytechs.model.entities.GlAgency[ id=" + id + " ]";
    }
    
}
