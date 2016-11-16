/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.waytechs.model.ejb.facades;

import com.waytechs.model.entities.GlCompany;
import com.waytechs.model.entities.GlPeople;
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
public class GlPeopleFacade extends AbstractFacade<GlPeople> {

    @PersistenceContext(unitName = "aml-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GlPeopleFacade() {
        super(GlPeople.class);
    }
    
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public GlPeople findByGlCompanyId(GlCompany glCompany) {
        List<GlPeople> result = null;
        try {
            Query query = em.createNamedQuery("GlPeople.findByGlCompanyId");
            query.setParameter("glCompanyId", glCompany);
            result = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.isEmpty() || result == null ? null : result.get(0);
    }
    
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public GlPeople findByIdref(GlPeople glPeople) { 
        List<GlPeople> result = null;
        try {
            Query query = em.createNamedQuery("GlPeople.findByIdref");
            query.setParameter("idref", glPeople);
            result = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.isEmpty() || result == null ? null : result.get(0);
    }
    
}
