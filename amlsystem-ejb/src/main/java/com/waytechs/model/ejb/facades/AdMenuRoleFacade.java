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
    
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public AdMenuRole findByAdMenuIdAndAdRoleId(AdMenuRole adMenuRole) {
        List<AdMenuRole> result = null;
        try {
            Query query = em.createNamedQuery("AdMenuRole.findByAdMenuIdAndAdRoleId");
            query.setParameter("adRoleId", adMenuRole.getAdRoleId());
            query.setParameter("adMenuId", adMenuRole.getAdMenuId());
            query.setParameter("isactive", YesNo.SI);
            result = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result != null && !result.isEmpty() ? result.get(0) : null;
    }

    public void validateExistence(AdMenuRole item) throws ExistException {
        AdMenuRole temp = findByAdMenuIdAndAdRoleId(item); 

        if (temp == null) {
            return;
        }

        if (temp.getAdMenuId().getId().intValue() == item.getAdMenuId().getId().intValue() ) {
            throw new ExistException();
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void save(AdMenuRole item) throws ExecuteRollbackException {
        try {

            validateExistence(item);

            if (item.getId() == null) {
                this.create(item);
            } else {
                this.edit(item);
            }

        } catch (ExistException e) {
            throw new ExecuteRollbackException("La asignacion " + item.getAdMenuId().getName() + " / " + item.getAdRoleId().getName() + " ya existe.");
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExecuteRollbackException("Error al guardar el registro!");
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void delete(AdMenuRole item) throws ExecuteRollbackException {
        try {
            if (item == null) {
                throw new ProcessOperationException("El parámetro menurole no puede ser null.");
            }

            item.setIsactive(YesNo.NO);
            
            AdMenuRole temp = findByAdMenuIdAndAdRoleId(item); 
            
            if(  temp == null ){
                throw new ExistException();
            }
            
            this.edit(item);
            
        } catch (ExistException e) {
            throw new ExecuteRollbackException("La asignacion " + item.getAdMenuId().getName() + " / " + item.getAdRoleId().getName() + " no existe para borrar.");
        } catch (ProcessOperationException e) {
            throw new ExecuteRollbackException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExecuteRollbackException("Error al borrar el registro!");
        }
    }

}
