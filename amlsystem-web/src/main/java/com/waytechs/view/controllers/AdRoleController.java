/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.waytechs.view.controllers;

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

    //@Inject WebSecurityManager securityManager;
    
    //@Inject FilterChainResolver filterChainResolver;
    
    @Inject ShiroConfiguration sc;

    @PostConstruct
    public void initialize() {
    }
   
    public void actionActualizar(ActionEvent action){
        try {
            System.out.println("actionActualizar ...");
            
            JsfUtils.getServletContext().addListener(ShiroListener.class);
            
            /*
            DefaultWebEnvironment webEnvironment = (DefaultWebEnvironment) WebUtils.getRequiredWebEnvironment(JsfUtils.getServletContext());
            System.out.println("webEnvironment: "+webEnvironment);
            
            DefaultWebSecurityManager sm = (DefaultWebSecurityManager) webEnvironment.getSecurityManager();
            //LifecycleUtils.destroy(sm);
            System.out.println("getSecurityManager acc: "+sm);
            LifecycleUtils.destroy(webEnvironment.getFilterChainResolver());
            System.out.println("getFilterChainResolver destroy: "+webEnvironment.getFilterChainResolver());
            */
            
            
            /*
            DefaultWebSecurityManager sm = (DefaultWebSecurityManager) webEnvironment.getSecurityManager();
            EhCacheManager cm = (EhCacheManager) sm.getCacheManager();
            cm.destroy();
            
            //System.out.println("EhCacheManager destroy: "+cm);
            
            //webEnvironment = (DefaultWebEnvironment) createEnvironment(JsfUtils.getServletContext());
            
            //webEnvironment.setSecurityManager(securityManager);
            
            //filterChainResolver
            
            //ShiroConfiguration sc = new ShiroConfiguration();
            
                */

            
            /*
            System.out.println("ShiroConfiguration "+sc);
            
            webEnvironment.setFilterChainResolver(sc.getFilterChainResolver());
            
            LifecycleUtils.init(webEnvironment);
            */
            
            System.out.println("webEnvironment init");
            
        } catch (Exception ex) {
            Logger.getLogger(AdRoleController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    

}
