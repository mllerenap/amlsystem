/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.waytechs.model.ejb.facades;

import com.waytechs.model.entities.AdUser;
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
public class GlCompanyFacade extends AbstractFacade<GlCompany> {

    @PersistenceContext(unitName = "aml-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GlCompanyFacade() {
        super(GlCompany.class);
    }

    public void validateExistence(GlCompany item) throws ExistException {

        GlCompany temp = findByBusinessname(item.getBusinessname());

        if (temp == null) {
            return;
        }

        if (temp.getBusinessname().equals(item.getBusinessname())) {
            return;
        }

        if (temp != null) {
            throw new ExistException();
        }
    }

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public GlCompany findByBusinessname(String name) {
        List<GlCompany> result = null;
        try {
            Query query = em.createNamedQuery("GlCompany.findByBusinessname");
            query.setParameter("businessname", "%" + name + "%");
            query.setParameter("isactive", YesNo.SI);
            result = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.isEmpty() || result == null ? null : result.get(0);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void save(GlCompany item) throws ExecuteRollbackException {
        try {

            if (item.getId() == null) {
                validateExistence(item);
                this.create(item);
            } else {
                validateExistence(item);
                this.edit(item);
            }

        } catch (ExistException e) {
            throw new ExecuteRollbackException("La compañia " + item.getBusinessname() + " ya existe.");
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExecuteRollbackException("Error al guardar el registro!");
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void delete(GlCompany item) throws ExecuteRollbackException {
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
