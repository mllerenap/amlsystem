package com.waytechs.view.converters;

import com.waytechs.model.ejb.facades.AdActionFacade;
import com.waytechs.model.ejb.facades.AdModuleFacade;
import com.waytechs.model.ejb.facades.AdPermissionFacade;
import java.io.Serializable;
import java.math.BigInteger;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;

import com.waytechs.model.ejb.facades.AdRoleFacade;
import com.waytechs.model.entities.AdAction;
import com.waytechs.model.entities.AdModule;
import com.waytechs.model.entities.AdPermission;
import com.waytechs.model.entities.AdRole;

@ManagedBean
@ApplicationScoped
public class AdActionConverter implements Converter, Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Inject
    AdActionFacade adActionFacade;

    // ESTE CONVIERTE EL SELECTITEM SELECCIONADO EN OBJETO PARA ENVIAR AL OBEJETO PARA PERSISTIR
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        AdAction objRes = null;
        if (!string.trim().equals("") && string != null) {
            BigInteger id = new BigInteger(string);
            objRes = (AdAction) adActionFacade.find(id);
        }
//        return (string.trim().equals("") || string == null) ? null : objRes;
        return objRes;
    }

    // ESTE ES EL QUE SE EJECUTA AL INICIO Y HACE QUE EL CATALOGO MUESTRE LOS IDS
    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        return (o == null || o.equals("")) ? "" : ((AdAction) o).getId() + "";

    }
}
