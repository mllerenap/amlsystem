/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.waytechs.model.ejb.facades;

import com.waytechs.model.entities.AdCountry;
import com.waytechs.model.entities.AdMenu;
import com.waytechs.model.entities.AdProvince;
import com.waytechs.model.enums.YesNo;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author mllerena
 */
@Stateless
public class AdProvinceFacade extends AbstractFacade<AdProvince> {

    @PersistenceContext(unitName = "aml-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AdProvinceFacade() {
        super(AdProvince.class);
    }
    
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<AdProvince> findByAdCountryId(AdCountry adCountryId) {
        List<AdProvince> result = null; 
        try {
            Query query = em.createNamedQuery("AdProvince.findByAdCountryId");
            query.setParameter("adCountryId", adCountryId);
            query.setParameter("isactive", YesNo.SI);
            result = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
}
