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
import javax.persistence.Lob;
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
@Table(name = "gl_address")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GlAddress.findAll", query = "SELECT g FROM GlAddress g")
    , @NamedQuery(name = "GlAddress.findById", query = "SELECT g FROM GlAddress g WHERE g.id = :id")
    , @NamedQuery(name = "GlAddress.findByMainstreet", query = "SELECT g FROM GlAddress g WHERE g.mainstreet = :mainstreet")
    , @NamedQuery(name = "GlAddress.findByCrossstreet", query = "SELECT g FROM GlAddress g WHERE g.crossstreet = :crossstreet")
    , @NamedQuery(name = "GlAddress.findByBlock", query = "SELECT g FROM GlAddress g WHERE g.block = :block")
    , @NamedQuery(name = "GlAddress.findByTelephone1", query = "SELECT g FROM GlAddress g WHERE g.telephone1 = :telephone1")
    , @NamedQuery(name = "GlAddress.findByTelephone2", query = "SELECT g FROM GlAddress g WHERE g.telephone2 = :telephone2")
    , @NamedQuery(name = "GlAddress.findByCreated", query = "SELECT g FROM GlAddress g WHERE g.created = :created")
    , @NamedQuery(name = "GlAddress.findByCreatedby", query = "SELECT g FROM GlAddress g WHERE g.createdby = :createdby")
    , @NamedQuery(name = "GlAddress.findByUpdated", query = "SELECT g FROM GlAddress g WHERE g.updated = :updated")
    , @NamedQuery(name = "GlAddress.findByUpdatedby", query = "SELECT g FROM GlAddress g WHERE g.updatedby = :updatedby")
    , @NamedQuery(name = "GlAddress.findByIsactive", query = "SELECT g FROM GlAddress g WHERE g.isactive = :isactive")
    , @NamedQuery(name = "GlAddress.findByResidencetimey", query = "SELECT g FROM GlAddress g WHERE g.residencetimey = :residencetimey")
    , @NamedQuery(name = "GlAddress.findByResidencetimem", query = "SELECT g FROM GlAddress g WHERE g.residencetimem = :residencetimem")})
public class GlAddress implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Long id;
    @Size(max = 255)
    @Column(name = "mainstreet")
    private String mainstreet;
    @Size(max = 255)
    @Column(name = "crossstreet")
    private String crossstreet;
    
    
    @Column(name = "number")
    private String number;
    
    @Size(max = 10)
    @Column(name = "block")
    private String block;
    @Size(max = 20)
    @Column(name = "telephone1")
    private String telephone1;
    @Size(max = 20)
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
    @Column(name = "residencetimey")
    private BigInteger residencetimey;
    @Column(name = "residencetimem")
    private BigInteger residencetimem;
    @JoinColumn(name = "ad_canton_id", referencedColumnName = "id")
    @ManyToOne
    private AdCanton adCantonId;
    @JoinColumn(name = "ad_sector_address_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private AdSectorAddress adSectorAddressId;
    @JoinColumn(name = "ad_type_address_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private AdTypeAddress adTypeAddressId;
    @JoinColumn(name = "ad_type_building_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private AdTypeBuilding adTypeBuildingId;
    @JoinColumn(name = "gl_people_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private GlPeople glPeopleId;

    public GlAddress() {
    }

    public GlAddress(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMainstreet() {
        return mainstreet;
    }

    public void setMainstreet(String mainstreet) {
        this.mainstreet = mainstreet;
    }

    public String getCrossstreet() {
        return crossstreet;
    }

    public void setCrossstreet(String crossstreet) {
        this.crossstreet = crossstreet;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
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

    public BigInteger getResidencetimey() {
        return residencetimey;
    }

    public void setResidencetimey(BigInteger residencetimey) {
        this.residencetimey = residencetimey;
    }

    public BigInteger getResidencetimem() {
        return residencetimem;
    }

    public void setResidencetimem(BigInteger residencetimem) {
        this.residencetimem = residencetimem;
    }

    public AdCanton getAdCantonId() {
        return adCantonId;
    }

    public void setAdCantonId(AdCanton adCantonId) {
        this.adCantonId = adCantonId;
    }

    public AdSectorAddress getAdSectorAddressId() {
        return adSectorAddressId;
    }

    public void setAdSectorAddressId(AdSectorAddress adSectorAddressId) {
        this.adSectorAddressId = adSectorAddressId;
    }

    public AdTypeAddress getAdTypeAddressId() {
        return adTypeAddressId;
    }

    public void setAdTypeAddressId(AdTypeAddress adTypeAddressId) {
        this.adTypeAddressId = adTypeAddressId;
    }

    public AdTypeBuilding getAdTypeBuildingId() {
        return adTypeBuildingId;
    }

    public void setAdTypeBuildingId(AdTypeBuilding adTypeBuildingId) {
        this.adTypeBuildingId = adTypeBuildingId;
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
        if (!(object instanceof GlAddress)) {
            return false;
        }
        GlAddress other = (GlAddress) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.waytechs.model.entities.GlAddress[ id=" + id + " ]";
    }
    
}
