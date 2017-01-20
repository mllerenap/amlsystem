/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.waytechs.model.ejb.facades;

import com.waytechs.model.entities.AdCreditActivitySubject;
import com.waytechs.model.entities.AdMenu;
import com.waytechs.model.entities.AdTypeEconomicActivity;
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
public class AdCreditActivitySubjectFacade extends AbstractFacade<AdCreditActivitySubject> {

    @PersistenceContext(unitName = "aml-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AdCreditActivitySubjectFacade() {
        super(AdCreditActivitySubject.class);
    }

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<AdCreditActivitySubject> findByAdTypeEconomicActivityId(AdTypeEconomicActivity adTypeEconomicActivity) {
        List<AdCreditActivitySubject> result = null;
        try {
            Query query = em.createNamedQuery("AdCreditActivitySubject.findByAdTypeEconomicActivityId");
            query.setParameter("adTypeEconomicActivityId", adTypeEconomicActivity);
            query.setParameter("isactive", YesNo.SI);
            result = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
