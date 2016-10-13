/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.waytechs.view.controllers;

import com.waytechs.model.ejb.facades.AdMenuFacade;
import com.waytechs.model.ejb.facades.AdMenuRoleFacade;
import java.io.Serializable;
import java.util.Date;
import java.util.Random;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import com.waytechs.model.ejb.facades.AdUserFacade;
import com.waytechs.model.ejb.facades.AdUserRolesFacade;
import com.waytechs.model.entities.AdMenu;
import com.waytechs.model.entities.AdUser;
import com.waytechs.model.entities.AdUserRoles;
import com.waytechs.model.utils.DateUtils;
import com.waytechs.model.utils.SecurityUtils;
import com.waytechs.view.utils.JsfUtils;
import java.util.List;
import java.util.Map;
import javax.faces.event.ActionEvent;
import org.primefaces.event.MenuActionEvent;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

/**
 *
 * @author Usuario
 */
@ManagedBean(name = "template")
@SessionScoped
public class TemplateController implements Serializable {

    @Inject
    private AdUserFacade adUserFacade;

    @Inject
    private SecurityUtils security;

    private AdUser adUser;

    private String pass1;

    private String pass2;

    private MenuModel model;

    @Inject
    private AdUserRolesFacade adUserRolesFacade;

    @Inject
    private AdMenuRoleFacade adMenuRoleFacade;

    @Inject
    private AdMenuFacade adMenuFacade;

    @PostConstruct
    public void initialize() {

        model = new DefaultMenuModel();
        
        AdMenu rootMenu = adMenuFacade.find(1L);
        List<AdMenu> subMenues = adMenuFacade.findByAdMenuParentId(rootMenu);    
        
        if (subMenues != null && !subMenues.isEmpty()) {
            for (AdMenu sm : subMenues) {
                System.out.println("primer linea: "+sm.getName());
                childMenu(sm);
                
            }
        }
        

        List<AdUserRoles> listaUsuarioRoles = this.adUserRolesFacade.findByAdUser(getAdUser());
        if (listaUsuarioRoles != null && !listaUsuarioRoles.isEmpty()) {
            for (AdUserRoles us : listaUsuarioRoles) {

            }
        }

        //adMenuRoleFacade.findByAdRoleId(adRole)
        setPass1(null);
        setPass2(null);
    }
    
    public void childMenu(AdMenu menu){
        List<AdMenu> hijos = adMenuFacade.findByAdMenuParentId(menu);    
        if( hijos != null & !hijos.isEmpty() ){
            DefaultSubMenu submenu = new DefaultSubMenu(menu.getName(),menu.getIcon());
            for (AdMenu c : hijos) {
                System.out.println("childMenu padre "+menu.getName()+" hijo: "+c.getName());
                submenu = childMenuParent(submenu, c);
            }
            model.addElement(submenu);
        }else{
            DefaultMenuItem item = new DefaultMenuItem(menu.getName());
            item.setUrl(menu.getUrl());
            item.setIcon(menu.getIcon());
            item.setParam("item", menu);
            item.setCommand("#{template.executeMenu}");
            System.out.println("childMenu item: "+menu.getName());
            model.addElement(item);
        }
    }
    
    public DefaultSubMenu childMenuParent(DefaultSubMenu parent, AdMenu itemChild){
        
        List<AdMenu> hijos = adMenuFacade.findByAdMenuParentId(itemChild);  
        
        if( hijos != null & !hijos.isEmpty() ){
            DefaultSubMenu submenu = new DefaultSubMenu(itemChild.getName(),itemChild.getIcon());
            for (AdMenu c : hijos) {
                System.out.println("childMenuParent padre "+itemChild.getName()+" hijo: "+c.getName());
                submenu = childMenuParent(submenu, c);
            }
            parent.addElement(submenu);
        }else{
            DefaultMenuItem item = new DefaultMenuItem(itemChild.getName());
            item.setUrl(itemChild.getUrl());
            item.setIcon(itemChild.getIcon());
            item.setParam("item", itemChild);
            item.setCommand("#{template.executeMenu}");
            
            System.out.println("childMenuParent item: "+itemChild.getName());
            parent.addElement(item);
        } 
         return parent;
    }
    
    public void executeMenu(ActionEvent ev){
        System.out.println("executeMenu: "+ev);
    }

    public void actualizarUsuario() {
        System.out.println("actualizarUsuario: " + getPass1() + " - " + getPass2());

        System.out.println("actualizarUsuario aduser: " + getAdUser());
        try {
            adUserFacade.save(getAdUser());
        } catch (Exception e) {
            JsfUtils.messageError(null, e.getMessage(), null);
            return;
        }

        JsfUtils.messageInfo(null, "Usuario guardado correctamente.", null);
        JsfUtils.executeJS("PF('wvPerfilUsuario').hide();");

    }

    public AdUser getAdUser() {
        if (adUser == null) {
            adUser = security.getAdUser();
        }
        return adUser;
    }

    public void setAdUser(AdUser adUser) {
        this.adUser = adUser;
    }

    public String getPass1() {
        return pass1;
    }

    public void setPass1(String pass1) {
        this.pass1 = pass1;
    }

    public String getPass2() {
        return pass2;
    }

    public void setPass2(String pass2) {
        this.pass2 = pass2;
    }

    public MenuModel getModel() {
        return model;
    }

}
