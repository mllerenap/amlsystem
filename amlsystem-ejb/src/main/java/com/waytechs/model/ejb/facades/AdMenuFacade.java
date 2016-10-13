/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.waytechs.model.ejb.facades;

import com.waytechs.model.entities.AdMenu;
import com.waytechs.model.entities.AdMenuRole;
import com.waytechs.model.entities.AdRole;
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
public class AdMenuFacade extends AbstractFacade<AdMenu> {

    @PersistenceContext(unitName = "aml-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AdMenuFacade() {
        super(AdMenu.class);
    }

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<AdMenu> findByAdMenuParentId(AdMenu adMenuParentId) {
        List<AdMenu> result = null;
        try {
            Query query = em.createNamedQuery("AdMenu.findByAdMenuParentId");
            query.setParameter("adMenuParentId", adMenuParentId);
            query.setParameter("isactive", YesNo.SI);
            result = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
