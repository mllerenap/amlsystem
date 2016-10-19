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
@Table(name = "gl_address_agency")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GlAddressAgency.findAll", query = "SELECT g FROM GlAddressAgency g")
    , @NamedQuery(name = "GlAddressAgency.findById", query = "SELECT g FROM GlAddressAgency g WHERE g.id = :id")
    , @NamedQuery(name = "GlAddressAgency.findByMainstreet", query = "SELECT g FROM GlAddressAgency g WHERE g.mainstreet = :mainstreet")
    , @NamedQuery(name = "GlAddressAgency.findByCrossstreet", query = "SELECT g FROM GlAddressAgency g WHERE g.crossstreet = :crossstreet")
    , @NamedQuery(name = "GlAddressAgency.findByBlock", query = "SELECT g FROM GlAddressAgency g WHERE g.block = :block")
    , @NamedQuery(name = "GlAddressAgency.findByTelephone1", query = "SELECT g FROM GlAddressAgency g WHERE g.telephone1 = :telephone1")
    , @NamedQuery(name = "GlAddressAgency.findByTelephone2", query = "SELECT g FROM GlAddressAgency g WHERE g.telephone2 = :telephone2")
    , @NamedQuery(name = "GlAddressAgency.findByCreated", query = "SELECT g FROM GlAddressAgency g WHERE g.created = :created")
    , @NamedQuery(name = "GlAddressAgency.findByCreatedby", query = "SELECT g FROM GlAddressAgency g WHERE g.createdby = :createdby")
    , @NamedQuery(name = "GlAddressAgency.findByUpdated", query = "SELECT g FROM GlAddressAgency g WHERE g.updated = :updated")
    , @NamedQuery(name = "GlAddressAgency.findByUpdatedby", query = "SELECT g FROM GlAddressAgency g WHERE g.updatedby = :updatedby")
    , @NamedQuery(name = "GlAddressAgency.findByIsactive", query = "SELECT g FROM GlAddressAgency g WHERE g.isactive = :isactive")
    , @NamedQuery(name = "GlAddressAgency.findByStardate", query = "SELECT g FROM GlAddressAgency g WHERE g.stardate = :stardate")})
public class GlAddressAgency implements Serializable {

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
    @Column(name = "stardate")
    @Temporal(TemporalType.DATE)
    private Date stardate;
    @JoinColumn(name = "ad_canton_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
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
    @JoinColumn(name = "gl_agency_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private GlAgency glAgencyId;

    public GlAddressAgency() {
    }

    public GlAddressAgency(Long id) {
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

    public Date getStardate() {
        return stardate;
    }

    public void setStardate(Date stardate) {
        this.stardate = stardate;
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

    public GlAgency getGlAgencyId() {
        return glAgencyId;
    }

    public void setGlAgencyId(GlAgency glAgencyId) {
        this.glAgencyId = glAgencyId;
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
        if (!(object instanceof GlAddressAgency)) {
            return false;
        }
        GlAddressAgency other = (GlAddressAgency) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.waytechs.model.entities.GlAddressAgency[ id=" + id + " ]";
    }
    
}
