/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.waytechs.view.controllers;

import com.waytechs.view.beans.GlobalBean;
import com.targa.dev.formation.shiroj.security.configuration.ShiroConfiguration;
import com.targa.dev.formation.shiroj.security.configuration.ShiroListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.Part;
import com.waytechs.model.ejb.facades.AdRoleFacade;
import com.waytechs.model.ejb.facades.AdUserFacade;
import com.waytechs.model.ejb.facades.AdUserRolesFacade;
import com.waytechs.model.entities.AdRole;
import com.waytechs.model.entities.AdUser;
import com.waytechs.model.entities.AdUserRoles;
import com.waytechs.view.components.DataList;
import com.waytechs.view.components.DataTable;
import com.waytechs.view.components.DataView;
import com.waytechs.view.components.DataViewType;
import com.waytechs.view.utils.JsfUtils;
import javax.faces.event.ActionEvent;
import org.apache.commons.io.IOUtils;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.util.LifecycleUtils;
import org.apache.shiro.web.env.DefaultWebEnvironment;
import org.apache.shiro.web.filter.mgt.FilterChainResolver;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.mgt.WebSecurityManager;
import org.apache.shiro.web.util.WebUtils;

/**
 *
 * @author mllerenap
 */
@ManagedBean(name = "adRole")
@ViewScoped
public class AdRoleController implements Serializable {

    @Inject private GlobalBean appGlobal;

    @PostConstruct
    public void initialize() {
    }
   
    public void actionActualizar(ActionEvent action){
        try {
            System.out.println("actionActualizar ...");
            appGlobal.loadFilterChainResolver();
            System.out.println("actionActualizar ... fin");
        } catch (Exception ex) {
            Logger.getLogger(AdRoleController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    

}
