/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.waytechs.model.ejb.facades;

import com.waytechs.model.entities.GlAgency;
import com.waytechs.model.entities.GlAgency;
import com.waytechs.model.entities.GlCompany;
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
public class GlAgencyFacade extends AbstractFacade<GlAgency> {

    @PersistenceContext(unitName = "aml-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GlAgencyFacade() {
        super(GlAgency.class);
    }
    
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<GlAgency> findByGlCompanyId(GlCompany company) {
        List<GlAgency> result = null;
        try {
            Query query = em.createNamedQuery("GlAgency.findByGlAgencyId");
            query.setParameter("glCompanyId", company); 
            query.setParameter("isactive", YesNo.SI);
            result = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    
    public void validateExistence(GlAgency item) throws ExistException {

        GlAgency temp = findByName(item.getName());

        if (temp == null) {
            return;
        }

        if (temp.getName().equals(item.getName())) {
            return;
        }

        if (temp != null) {
            throw new ExistException();
        }
    }

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public GlAgency findByName(String name) {
        List<GlAgency> result = null;
        try {
            Query query = em.createNamedQuery("GlAgency.findByName");
            query.setParameter("name", "%" + name + "%");
            query.setParameter("isactive", YesNo.SI);
            result = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.isEmpty() || result == null ? null : result.get(0);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void save(GlAgency item) throws ExecuteRollbackException {
        try {

            if (item.getId() == null) {
                validateExistence(item);
                this.create(item);
            } else {
                validateExistence(item);
                this.edit(item);
            }

        } catch (ExistException e) {
            throw new ExecuteRollbackException("La compañia " + item.getName()+ " ya existe.");
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExecuteRollbackException("Error al guardar el registro!");
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void delete(GlAgency item) throws ExecuteRollbackException {
        try {
            if (item == null) {
                throw new ProcessOperationException("El parámetro usuario no puede ser null.");
            }

            item.setIsactive(YesNo.NO);

            validateExistence(item);
            this.edit(item);

        } catch (ProcessOperationException e) {
            throw new ExecuteRollbackException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExecuteRollbackException("Error al borrar el registro!");
        }
    }
    
}
