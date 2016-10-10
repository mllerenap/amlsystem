package com.waytechs.model.listeners;

import java.util.Date;

import javax.ejb.EJB;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.waytechs.model.ejb.services.AdPackageService;
import com.waytechs.model.entities.AbstractEntityModel;
import com.waytechs.model.enums.YesNo;
import com.waytechs.model.utils.SecurityUtils;

public class EntityModelListener {

    @EJB
    private SecurityUtils security;

    @EJB
    private AdPackageService packageService;

    @PrePersist
    public void prePersist(AbstractEntityModel e) {
        //e.setId(packageService.nextSequence(e));
        e.setCreated(new Date());
        e.setCreatedby(security.getCurrentUser());
        e.setUpdated(new Date());
        e.setUpdatedby(security.getCurrentUser());
        e.setIsactive(YesNo.SI);
        System.out.println("prePersist..");
    }

    @PreUpdate
    public void preUpdate(AbstractEntityModel e) {
        e.setUpdated(new Date());
        e.setUpdatedby(security.getCurrentUser());
    }

}
