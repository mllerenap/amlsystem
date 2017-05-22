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
import com.waytechs.model.ejb.facades.AdSectorAddressFacade;
import com.waytechs.model.entities.AdAction;
import com.waytechs.model.entities.AdModule;
import com.waytechs.model.entities.AdPermission;
import com.waytechs.model.entities.AdRole;
import com.waytechs.model.entities.AdSectorAddress;

@ManagedBean
@ApplicationScoped
public class AdSectorAddressConverter implements Converter, Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Inject
    AdSectorAddressFacade adSectorAddressFacade;

    // ESTE CONVIERTE EL SELECTITEM SELECCIONADO EN OBJETO PARA ENVIAR AL OBEJETO PARA PERSISTIR
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        AdSectorAddress objRes = null;
        if (!string.trim().equals("") && string != null) {
            BigInteger id = new BigInteger(string);
            objRes = (AdSectorAddress) adSectorAddressFacade.find(id);
        }
//        return (string.trim().equals("") || string == null) ? null : objRes;
        return objRes;
    }

    // ESTE ES EL QUE SE EJECUTA AL INICIO Y HACE QUE EL CATALOGO MUESTRE LOS IDS
    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        return (o == null || o.equals("")) ? "" : ((AdSectorAddress) o).getId() + "";

    }
}
