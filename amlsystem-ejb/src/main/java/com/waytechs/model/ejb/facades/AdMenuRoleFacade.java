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
public class AdMenuRoleFacade extends AbstractFacade<AdMenuRole> {

    @PersistenceContext(unitName = "aml-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AdMenuRoleFacade() {
        super(AdMenuRole.class);
    }
    
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED) 
	public List<AdMenuRole> findByAdRoleId(AdRole adRole) {
        List<AdMenuRole> result = null;
        try {
            Query query = em.createNamedQuery("AdMenuRole.findByAdRoleId");
            query.setParameter("adRoleId", adRole);
            query.setParameter("isactive", YesNo.SI);
            result = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace(); 
        }
        return result;
    }
        
        @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED) 
	public List<AdMenuRole> findByAdMenuId(AdMenu adMenu) {
        List<AdMenuRole> result = null;
        try {
            Query query = em.createNamedQuery("AdMenuRole.findByAdMenuId");
            query.setParameter("adMenuId", adMenu);
            query.setParameter("isactive", YesNo.SI);
            result = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace(); 
        }
        return result;
    }

    
}
