/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.waytechs.view.beans;

import com.targa.dev.formation.shiroj.security.configuration.ShiroConfiguration;
import javax.ejb.Singleton;
import javax.inject.Inject;
import org.apache.shiro.web.filter.mgt.FilterChainResolver;

/**
 *
 * @author mllerena
 */
@Singleton
public class GlobalBean {
    
    @Inject private ShiroConfiguration sc;
    
    private FilterChainResolver filterChainResolver;

    public FilterChainResolver getFilterChainResolver() {
        return filterChainResolver;
    }
    
    public void loadFilterChainResolver(){
       this.filterChainResolver = sc.getFilterChainResolver();
    }
    
    
    
}
