/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.waytechs.model.ejb.facades;

import com.waytechs.model.entities.AdCategoryPep;
import com.waytechs.model.entities.AdCreditActivitySubject;
import com.waytechs.model.entities.AdTypeEconomicActivity;
import com.waytechs.model.entities.AdTypePep;
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
public class AdTypePepFacade extends AbstractFacade<AdTypePep> {

    @PersistenceContext(unitName = "aml-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AdTypePepFacade() {
        super(AdTypePep.class);
    }
    
    
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<AdTypePep> findByAdCategoryPepId(AdCategoryPep adCategoryPep) {
        List<AdTypePep> result = null;
        try {
            Query query = em.createNamedQuery("AdTypePep.findByAdCategoryPepId");
            query.setParameter("adCategoryPepId", adCategoryPep);
            query.setParameter("isactive", YesNo.SI);
            result = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
}
