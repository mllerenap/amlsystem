/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.waytechs.model.ejb.facades;

import java.util.List;
import java.util.logging.Level;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.waytechs.model.entities.AdRole;
import com.waytechs.model.entities.AdUser;
import com.waytechs.model.enums.YesNo;
import com.waytechs.model.exceptions.ExecuteRollbackException;
import com.waytechs.model.exceptions.ExistException;
import com.waytechs.model.exceptions.ProcessOperationException;

/**
 *
 * @author mllerena
 */
@Stateless
public class AdRoleFacade extends AbstractFacade<AdRole> {

    @PersistenceContext(unitName = "aml-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AdRoleFacade() {
        super(AdRole.class);
    }

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public AdRole findByName(String mnemonic) {
        List<AdRole> result = null;
        try {
            Query query = em.createNamedQuery("AdRole.findByMnemonic");
            query.setParameter("mnemonic", mnemonic);
            query.setParameter("isactive", YesNo.SI);
            result = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.isEmpty() || result == null ? null : result.get(0);
    }

    public void validateExistence(AdRole item) throws ExistException {
        AdRole temp = findByName(item.getMnemonic());

        if (temp == null) {
            return;
        }

        if (temp.getMnemonic().equals(item.getMnemonic())) {
            return;
        }

        if (temp != null) {
            throw new ExistException();
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void save(AdRole item) throws ExecuteRollbackException {
        try {

            validateExistence(item);

            if (item.getId() == null) {
                this.create(item);
            } else {
                this.edit(item);
            }

        } catch (ExistException e) {
            throw new ExecuteRollbackException("El rol " + item.getMnemonic() + " ya existe.");
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExecuteRollbackException("Error al guardar el registro!");
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void delete(AdRole item) throws ExecuteRollbackException {
        try {
            if (item == null) {
                throw new ProcessOperationException("El parámetro rol no puede ser null.");
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
