package com.waytechs.view.security;

import com.waytechs.view.beans.GlobalBean;
import javax.inject.Inject;
import org.apache.shiro.web.servlet.ShiroFilter;

import javax.servlet.annotation.WebFilter;
import org.apache.shiro.web.filter.mgt.FilterChainResolver;

/**
 * Created by nebrass on 17/11/2015.
 */
@WebFilter("/*")
public class ShiroFilterActivator extends ShiroFilter {
    
    @Inject private GlobalBean appGlobal;

    private ShiroFilterActivator() {
    }

    @Override
    public void init() throws Exception {
        System.out.println("ShiroFilterActivator..... ");
        super.init(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public FilterChainResolver getFilterChainResolver() {
        System.out.println("ShiroFilterActivator.....getFilterChainResolver: "+appGlobal.getFilterChainResolver());
        
        if( appGlobal.getFilterChainResolver() == null){
            System.out.println("ShiroFilterActivator.....getFilterChainResolver value super: "+super.getFilterChainResolver());
            return super.getFilterChainResolver();
        }
        
        return appGlobal.getFilterChainResolver(); 
    }
    
    
    
    
    
    
    
}
