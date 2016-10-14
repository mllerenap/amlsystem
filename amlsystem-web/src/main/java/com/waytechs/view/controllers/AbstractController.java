/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.waytechs.view.controllers;

import com.waytechs.model.ejb.facades.AdMenuFacade;
import com.waytechs.model.entities.AdMenu;
import com.waytechs.view.utils.JsfUtils;
import javax.inject.Inject;

/**
 *
 * @author Marcos Llerena <mllerenap@aeosolutions.com>
 */
public abstract class AbstractController {
    
    private String viewId;
    
    private AdMenu currentMenu;
    
    

    public String getViewId() {
        
        /*
        String uri = JsfUtils.getRequest().getRequestURI();
        int indice = uri.indexOf("//") +1 ;
        viewId = uri.substring(indice);
        
        System.out.println("getViewId: "+viewId);
        
        String idMenu = JsfUtils.getRequest().getParameter("id");
        
        //String idMenu = query.substring(indiceQuery);
        
        System.out.println("idMenu: "+idMenu);
        
        currentMenu = adMenuFacade.find( Long.getLong(idMenu) );
        */
        
        
        
        return viewId;
    }

    public AdMenu getCurrentMenu() {
        return currentMenu;
    }

    public void setCurrentMenu(AdMenu currentMenu) {
        this.currentMenu = currentMenu;
    }
    
    
    
    
    
}
