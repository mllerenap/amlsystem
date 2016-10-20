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
@Table(name = "gl_people")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GlPeople.findAll", query = "SELECT g FROM GlPeople g")
    , @NamedQuery(name = "GlPeople.findById", query = "SELECT g FROM GlPeople g WHERE g.id = :id")
    , @NamedQuery(name = "GlPeople.findByFirstname", query = "SELECT g FROM GlPeople g WHERE g.firstname = :firstname")
    , @NamedQuery(name = "GlPeople.findBySecondname", query = "SELECT g FROM GlPeople g WHERE g.secondname = :secondname")
    , @NamedQuery(name = "GlPeople.findByLastname", query = "SELECT g FROM GlPeople g WHERE g.lastname = :lastname")
    , @NamedQuery(name = "GlPeople.findBySecondlastname", query = "SELECT g FROM GlPeople g WHERE g.secondlastname = :secondlastname")
    , @NamedQuery(name = "GlPeople.findByIde", query = "SELECT g FROM GlPeople g WHERE g.ide = :ide")
    , @NamedQuery(name = "GlPeople.findByBusinessname", query = "SELECT g FROM GlPeople g WHERE g.businessname = :businessname")
    , @NamedQuery(name = "GlPeople.findByComercialname", query = "SELECT g FROM GlPeople g WHERE g.comercialname = :comercialname")
    , @NamedQuery(name = "GlPeople.findByStartdate", query = "SELECT g FROM GlPeople g WHERE g.startdate = :startdate")
    , @NamedQuery(name = "GlPeople.findByDatebirthday", query = "SELECT g FROM GlPeople g WHERE g.datebirthday = :datebirthday")
    , @NamedQuery(name = "GlPeople.findByPep", query = "SELECT g FROM GlPeople g WHERE g.pep = :pep")
    , @NamedQuery(name = "GlPeople.findByEmail1", query = "SELECT g FROM GlPeople g WHERE g.email1 = :email1")
    , @NamedQuery(name = "GlPeople.findByEmail2", query = "SELECT g FROM GlPeople g WHERE g.email2 = :email2")
    , @NamedQuery(name = "GlPeople.findByCelular1", query = "SELECT g FROM GlPeople g WHERE g.celular1 = :celular1")
    , @NamedQuery(name = "GlPeople.findByCelular2", query = "SELECT g FROM GlPeople g WHERE g.celular2 = :celular2")
    , @NamedQuery(name = "GlPeople.findByCreated", query = "SELECT g FROM GlPeople g WHERE g.created = :created")
    , @NamedQuery(name = "GlPeople.findByCreatedby", query = "SELECT g FROM GlPeople g WHERE g.createdby = :createdby")
    , @NamedQuery(name = "GlPeople.findByUpdated", query = "SELECT g FROM GlPeople g WHERE g.updated = :updated")
    , @NamedQuery(name = "GlPeople.findByUpdatedby", query = "SELECT g FROM GlPeople g WHERE g.updatedby = :updatedby")
    , @NamedQuery(name = "GlPeople.findByIsactive", query = "SELECT g FROM GlPeople g WHERE g.isactive = :isactive")
    , @NamedQuery(name = "GlPeople.findByIselderly", query = "SELECT g FROM GlPeople g WHERE g.iselderly = :iselderly")
    , @NamedQuery(name = "GlPeople.findByIsespecial", query = "SELECT g FROM GlPeople g WHERE g.isespecial = :isespecial")})
public class GlPeople implements Serializable {

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
    @Column(name = "secondname")
    private String secondname;
    @Size(max = 255)
    @Column(name = "lastname")
    private String lastname;
    @Size(max = 255)
    @Column(name = "secondlastname")
    private String secondlastname;
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
    @Column(name = "datebirthday")
    @Temporal(TemporalType.DATE)
    private Date datebirthday;
    @Size(max = 1)
    @Column(name = "pep")
    private String pep;
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
    @Column(name = "iselderly")
    private BigInteger iselderly;
    @Column(name = "isespecial")
    private BigInteger isespecial;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "glPeopleId")
    private List<GlAddress> glAddressList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "glPeopleId")
    private List<GlDocumentPeople> glDocumentPeopleList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "glPeopleId")
    private List<GlReference> glReferenceList;
    @JoinColumn(name = "ad_canton_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private AdCanton adCantonId;
    @JoinColumn(name = "ad_civil_status_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private AdCivilStatus adCivilStatusId;
    @JoinColumn(name = "ad_credit_activity_subject_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private AdCreditActivitySubject adCreditActivitySubjectId;
    @JoinColumn(name = "ad_economic_activity_homo_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private AdEconomicActivityHomo adEconomicActivityHomoId;
    @JoinColumn(name = "ad_gender_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private AdGender adGenderId;
    @JoinColumn(name = "ad_type_ide_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private AdTypeIde adTypeIdeId;
    @JoinColumn(name = "ad_type_people_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private AdTypePeople adTypePeopleId;
    @JoinColumn(name = "ad_type_pep_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private AdTypePep adTypePepId;
    @OneToMany(mappedBy = "idref")
    private List<GlPeople> glPeopleList;
    @JoinColumn(name = "idref", referencedColumnName = "id")
    @ManyToOne
    private GlPeople idref;
    
    @JoinColumn(name = "gl_company_id", referencedColumnName = "id")
    @ManyToOne(fetch = javax.persistence.FetchType.LAZY)
    private GlCompany glCompanyId; 

    public GlPeople() {
    }

    public GlPeople(Long id) {
        this.id = id;
    }

    public GlPeople(Long id, String ide) {
        this.id = id;
        this.ide = ide;
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

    public String getSecondname() {
        return secondname;
    }

    public void setSecondname(String secondname) {
        this.secondname = secondname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getSecondlastname() {
        return secondlastname;
    }

    public void setSecondlastname(String secondlastname) {
        this.secondlastname = secondlastname;
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

    public Date getDatebirthday() {
        return datebirthday;
    }

    public void setDatebirthday(Date datebirthday) {
        this.datebirthday = datebirthday;
    }

    public String getPep() {
        return pep;
    }

    public void setPep(String pep) {
        this.pep = pep;
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

    public BigInteger getIselderly() {
        return iselderly;
    }

    public void setIselderly(BigInteger iselderly) {
        this.iselderly = iselderly;
    }

    public BigInteger getIsespecial() {
        return isespecial;
    }

    public void setIsespecial(BigInteger isespecial) {
        this.isespecial = isespecial;
    }

    @XmlTransient
    public List<GlAddress> getGlAddressList() {
        return glAddressList;
    }

    public void setGlAddressList(List<GlAddress> glAddressList) {
        this.glAddressList = glAddressList;
    }

    @XmlTransient
    public List<GlDocumentPeople> getGlDocumentPeopleList() {
        return glDocumentPeopleList;
    }

    public void setGlDocumentPeopleList(List<GlDocumentPeople> glDocumentPeopleList) {
        this.glDocumentPeopleList = glDocumentPeopleList;
    }

    @XmlTransient
    public List<GlReference> getGlReferenceList() {
        return glReferenceList;
    }

    public void setGlReferenceList(List<GlReference> glReferenceList) {
        this.glReferenceList = glReferenceList;
    }

    public AdCanton getAdCantonId() {
        return adCantonId;
    }

    public void setAdCantonId(AdCanton adCantonId) {
        this.adCantonId = adCantonId;
    }

    public AdCivilStatus getAdCivilStatusId() {
        return adCivilStatusId;
    }

    public void setAdCivilStatusId(AdCivilStatus adCivilStatusId) {
        this.adCivilStatusId = adCivilStatusId;
    }

    public AdCreditActivitySubject getAdCreditActivitySubjectId() {
        return adCreditActivitySubjectId;
    }

    public void setAdCreditActivitySubjectId(AdCreditActivitySubject adCreditActivitySubjectId) {
        this.adCreditActivitySubjectId = adCreditActivitySubjectId;
    }

    public AdEconomicActivityHomo getAdEconomicActivityHomoId() {
        return adEconomicActivityHomoId;
    }

    public void setAdEconomicActivityHomoId(AdEconomicActivityHomo adEconomicActivityHomoId) {
        this.adEconomicActivityHomoId = adEconomicActivityHomoId;
    }

    public AdGender getAdGenderId() {
        return adGenderId;
    }

    public void setAdGenderId(AdGender adGenderId) {
        this.adGenderId = adGenderId;
    }

    public AdTypeIde getAdTypeIdeId() {
        return adTypeIdeId;
    }

    public void setAdTypeIdeId(AdTypeIde adTypeIdeId) {
        this.adTypeIdeId = adTypeIdeId;
    }

    public AdTypePeople getAdTypePeopleId() {
        return adTypePeopleId;
    }

    public void setAdTypePeopleId(AdTypePeople adTypePeopleId) {
        this.adTypePeopleId = adTypePeopleId;
    }

    public AdTypePep getAdTypePepId() {
        return adTypePepId;
    }

    public void setAdTypePepId(AdTypePep adTypePepId) {
        this.adTypePepId = adTypePepId;
    }

    @XmlTransient
    public List<GlPeople> getGlPeopleList() {
        return glPeopleList;
    }

    public void setGlPeopleList(List<GlPeople> glPeopleList) {
        this.glPeopleList = glPeopleList;
    }

    public GlPeople getIdref() {
        return idref;
    }

    public void setIdref(GlPeople idref) {
        this.idref = idref;
    }

    public GlCompany getGlCompanyId() {
        return glCompanyId;
    }

    public void setGlCompanyId(GlCompany glCompanyId) {
        this.glCompanyId = glCompanyId;
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
        if (!(object instanceof GlPeople)) {
            return false;
        }
        GlPeople other = (GlPeople) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.waytechs.model.entities.GlPeople[ id=" + id + " ]";
    }
    
}
