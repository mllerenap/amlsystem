/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.waytechs.model.ejb.facades;

import com.waytechs.model.entities.AdMenuRole;
import com.waytechs.model.entities.AdPermission;
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
public class AdPermissionFacade extends AbstractFacade<AdPermission> {

    @PersistenceContext(unitName = "aml-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AdPermissionFacade() {
        super(AdPermission.class);
    }

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<AdPermission> findByAdRoleId(AdRole adRole) {
        List<AdPermission> result = null;
        try {
            Query query = em.createNamedQuery("AdPermission.findByAdRoleId");
            query.setParameter("adRoleId", adRole);
            query.setParameter("isactive", YesNo.SI);
            result = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public AdPermission findByAdActionIdAndAdRoleId(AdPermission adPermission) {
        List<AdPermission> result = null;
        try {
            Query query = em.createNamedQuery("AdPermission.findByAdActionIdAndAdRoleId");
            query.setParameter("adRoleId", adPermission.getAdRoleId());
            query.setParameter("adActionId", adPermission.getAdActionId());
            query.setParameter("isactive", YesNo.SI);
            result = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result != null && !result.isEmpty() ? result.get(0) : null;
    }

    public void validateExistence(AdPermission item) throws ExistException {
        AdPermission temp = findByAdActionIdAndAdRoleId(item);

        if (temp == null) {
            return;
        }
        throw new ExistException();
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void save(AdPermission item) throws ExecuteRollbackException {
        try {

            validateExistence(item);

            if (item.getId() == null) {
                this.create(item);
            } else {
                this.edit(item);
            }

        } catch (ExistException e) {
            throw new ExecuteRollbackException("La asignacion " + item.getAdActionId().getName() + " / " + item.getAdRoleId().getName() + " ya existe.");
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExecuteRollbackException("Error al guardar el registro!");
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void delete(AdPermission item) throws ExecuteRollbackException {
        try {
            if (item == null) {
                throw new ProcessOperationException("El parámetro permiso no puede ser null.");
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
