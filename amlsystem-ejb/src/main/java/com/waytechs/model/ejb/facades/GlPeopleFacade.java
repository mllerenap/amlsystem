/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.waytechs.model.ejb.facades;

import com.waytechs.model.entities.GlCompany;
import com.waytechs.model.entities.GlPeople;
import com.waytechs.model.enums.YesNo;
import com.waytechs.model.exceptions.ExecuteRollbackException;
import com.waytechs.model.exceptions.ExistException;
import com.waytechs.model.exceptions.ProcessOperationException;
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
    
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void save(GlPeople item) throws ExecuteRollbackException {
        try {

            if (item.getId() == null) {
                this.create(item);
            } else {
                this.edit(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExecuteRollbackException("Error al guardar el registro!");
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void delete(GlPeople item) throws ExecuteRollbackException {
        try {
            if (item == null) {
                throw new ProcessOperationException("El parámetro item no puede ser null.");
            }

            item.setIsactive(YesNo.NO);

            this.edit(item);

        } catch (ProcessOperationException e) {
            throw new ExecuteRollbackException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExecuteRollbackException("Error al borrar el registro!");
        }
    }
    
}
