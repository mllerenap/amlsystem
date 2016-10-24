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
@Table(name = "ad_gender")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AdGender.findAll", query = "SELECT a FROM AdGender a")
    , @NamedQuery(name = "AdGender.findById", query = "SELECT a FROM AdGender a WHERE a.id = :id")
    , @NamedQuery(name = "AdGender.findByCodgender", query = "SELECT a FROM AdGender a WHERE a.codgender = :codgender")
    , @NamedQuery(name = "AdGender.findByName", query = "SELECT a FROM AdGender a WHERE a.name = :name")
    , @NamedQuery(name = "AdGender.findByDescription", query = "SELECT a FROM AdGender a WHERE a.description = :description")
    , @NamedQuery(name = "AdGender.findByCreated", query = "SELECT a FROM AdGender a WHERE a.created = :created")
    , @NamedQuery(name = "AdGender.findByCreatedby", query = "SELECT a FROM AdGender a WHERE a.createdby = :createdby")
    , @NamedQuery(name = "AdGender.findByUpdated", query = "SELECT a FROM AdGender a WHERE a.updated = :updated")
    , @NamedQuery(name = "AdGender.findByUpdatedby", query = "SELECT a FROM AdGender a WHERE a.updatedby = :updatedby")
    , @NamedQuery(name = "AdGender.findByIsactive", query = "SELECT a FROM AdGender a WHERE a.isactive = :isactive")})
public class AdGender implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private BigInteger id;    
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "codgender")
    private String codgender;
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
    
    @Basic(optional = false)
    @Column(name = "isactive")
    @Convert(converter = YesNoConverter.class)
    private YesNo isactive;
     
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "adGenderId")
    private List<GlPeople> glPeopleList;

    public AdGender() {
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }
    
    public String getCodgender() {
        return codgender;
    }

    public void setCodgender(String codgender) {
        this.codgender = codgender;
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
        if (!(object instanceof AdGender)) {
            return false;
        }
        AdGender other = (AdGender) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.waytechs.model.entities.AdGender[ id=" + id + " ]";
    }
    
}
