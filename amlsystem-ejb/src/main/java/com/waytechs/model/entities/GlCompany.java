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
@Table(name = "gl_company")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GlCompany.findAll", query = "SELECT g FROM GlCompany g")
    , @NamedQuery(name = "GlCompany.findById", query = "SELECT g FROM GlCompany g WHERE g.id = :id")
    , @NamedQuery(name = "GlCompany.findByIde", query = "SELECT g FROM GlCompany g WHERE g.ide = :ide")
    , @NamedQuery(name = "GlCompany.findByBusinessname", query = "SELECT g FROM GlCompany g WHERE g.businessname = :businessname")
    , @NamedQuery(name = "GlCompany.findByComercialname", query = "SELECT g FROM GlCompany g WHERE g.comercialname = :comercialname")
    , @NamedQuery(name = "GlCompany.findByStartdate", query = "SELECT g FROM GlCompany g WHERE g.startdate = :startdate")
    , @NamedQuery(name = "GlCompany.findByEmail1", query = "SELECT g FROM GlCompany g WHERE g.email1 = :email1")
    , @NamedQuery(name = "GlCompany.findByEmail2", query = "SELECT g FROM GlCompany g WHERE g.email2 = :email2")
    , @NamedQuery(name = "GlCompany.findByCelular1", query = "SELECT g FROM GlCompany g WHERE g.celular1 = :celular1")
    , @NamedQuery(name = "GlCompany.findByCelular2", query = "SELECT g FROM GlCompany g WHERE g.celular2 = :celular2")
    , @NamedQuery(name = "GlCompany.findByCodsbs", query = "SELECT g FROM GlCompany g WHERE g.codsbs = :codsbs")
    , @NamedQuery(name = "GlCompany.findByWww", query = "SELECT g FROM GlCompany g WHERE g.www = :www")
    , @NamedQuery(name = "GlCompany.findByConespecial", query = "SELECT g FROM GlCompany g WHERE g.conespecial = :conespecial")})
public class GlCompany implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "ide")
    private String ide;
    @Size(max = 255)
    @Column(name = "businessname")
    private String businessname;
    @Size(max = 255)
    @Column(name = "comercialname")
    private String comercialname;
    @Column(name = "startdate")
    @Temporal(TemporalType.DATE)
    private Date startdate;
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
    @Size(max = 255)
    @Column(name = "codsbs")
    private String codsbs;
    @Size(max = 255)
    @Column(name = "www")
    private String www;
    @Size(max = 255)
    @Column(name = "conespecial")
    private String conespecial;
    @JoinColumn(name = "ad_type_company_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private AdTypeCompany adTypeCompanyId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "glCompanyId")
    private List<GlAgency> glAgencyList;

    public GlCompany() {
    }

    public GlCompany(Long id) {
        this.id = id;
    }

    public GlCompany(Long id, String ide) {
        this.id = id;
        this.ide = ide;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIde() {
        return ide;
    }

    public void setIde(String ide) {
        this.ide = ide;
    }

    public String getBusinessname() {
        return businessname;
    }

    public void setBusinessname(String businessname) {
        this.businessname = businessname;
    }

    public String getComercialname() {
        return comercialname;
    }

    public void setComercialname(String comercialname) {
        this.comercialname = comercialname;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
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

    public String getCodsbs() {
        return codsbs;
    }

    public void setCodsbs(String codsbs) {
        this.codsbs = codsbs;
    }

    public String getWww() {
        return www;
    }

    public void setWww(String www) {
        this.www = www;
    }

    public String getConespecial() {
        return conespecial;
    }

    public void setConespecial(String conespecial) {
        this.conespecial = conespecial;
    }

    public AdTypeCompany getAdTypeCompanyId() {
        return adTypeCompanyId;
    }

    public void setAdTypeCompanyId(AdTypeCompany adTypeCompanyId) {
        this.adTypeCompanyId = adTypeCompanyId;
    }

    @XmlTransient
    public List<GlAgency> getGlAgencyList() {
        return glAgencyList;
    }

    public void setGlAgencyList(List<GlAgency> glAgencyList) {
        this.glAgencyList = glAgencyList;
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
        if (!(object instanceof GlCompany)) {
            return false;
        }
        GlCompany other = (GlCompany) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.waytechs.model.entities.GlCompany[ id=" + id + " ]";
    }
    
}
