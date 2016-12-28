package com.waytechs.view.converters;

import com.waytechs.model.ejb.facades.AdCountryFacade;
import com.waytechs.model.ejb.facades.AdProvinceFacade;
import java.io.Serializable;
import java.math.BigInteger;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;

import com.waytechs.model.entities.AdCountry;
import com.waytechs.model.entities.AdProvince;

@ManagedBean
@ApplicationScoped
public class AdProvinceConverter implements Converter, Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Inject
    AdProvinceFacade adProvinceFacade;

    // ESTE CONVIERTE EL SELECTITEM SELECCIONADO EN OBJETO PARA ENVIAR AL OBEJETO PARA PERSISTIR
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        AdProvince objRes = null;
        if (!string.trim().equals("") && string != null) {
            BigInteger id = new BigInteger(string);
            objRes = (AdProvince) adProvinceFacade.find(id);
        }
//        return (string.trim().equals("") || string == null) ? null : objRes;
        return objRes;
    }

    // ESTE ES EL QUE SE EJECUTA AL INICIO Y HACE QUE EL CATALOGO MUESTRE LOS IDS
    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        return (o == null || o.equals("")) ? "" : ((AdProvince) o).getId() + "";

    }
}
