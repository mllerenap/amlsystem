package com.waytechs.view.converters;

import com.waytechs.model.ejb.facades.AdCantonFacade;
import com.waytechs.model.ejb.facades.AdCountryFacade;
import com.waytechs.model.ejb.facades.AdProvinceFacade;
import com.waytechs.model.entities.AdCanton;
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
public class AdCantonConverter implements Converter, Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Inject
    AdCantonFacade adCantonFacade;

    // ESTE CONVIERTE EL SELECTITEM SELECCIONADO EN OBJETO PARA ENVIAR AL OBEJETO PARA PERSISTIR
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        AdCanton objRes = null;
        if (!string.trim().equals("") && string != null) {
            BigInteger id = new BigInteger(string);
            objRes = (AdCanton) adCantonFacade.find(id);
        }
//        return (string.trim().equals("") || string == null) ? null : objRes;
        return objRes;
    }

    // ESTE ES EL QUE SE EJECUTA AL INICIO Y HACE QUE EL CATALOGO MUESTRE LOS IDS
    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        return (o == null || o.equals("")) ? "" : ((AdCanton) o).getId() + "";

    }
}
