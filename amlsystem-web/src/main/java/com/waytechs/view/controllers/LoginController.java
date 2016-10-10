/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.waytechs.view.controllers;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import com.waytechs.model.ejb.facades.AdUserFacade;
import com.waytechs.model.entities.AdUser;
import com.waytechs.model.utils.DateUtils;
import com.waytechs.model.utils.SecurityUtils;
import com.waytechs.view.utils.JsfUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

/**
 *
 * @author Usuario
 */
@ManagedBean(name = "login")
@SessionScoped
public class LoginController implements Serializable {

    @Inject
    private AdUserFacade adUserFacade;
    
    
    @Inject
    private SecurityUtils security;

    private AdUser adUser;

    private String pass1;

    private String pass2;
    
     private String username;
    private String password;
    private Boolean rememberMe;


    @PostConstruct
    public void initialize() {
        
        //prPeople = prPeopleFacade.findByAdUser(getAdUser());
        setPass1(null);
        setPass2(null);
    }

    public void errorInicioSesion() {
        try {
            JsfUtils.messageError(null, "Usuario y/o Contraseña incorrectos", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void actualizarUsuario() {
        System.out.println("actualizarUsuario: "+getPass1()+" - "+getPass2());
        
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
        if( adUser == null ){
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
    
    private Integer random = null;
    
    public String numberRandom(){
        if( random != null )
            return ""+random;
        int min =1, max=4;
        Random r = new Random();
        random = r.nextInt((max - min)+1) + min;
        return ""+random;
    }
    
     public String timeZoneBackground(){
        
        Date fechaActual=  new Date();
         
        int hora = DateUtils.obtenerHora(fechaActual);
        int minutos = DateUtils.obtenerMinutos(fechaActual);
        
        System.out.println("hora: "+hora+" minutos: "+minutos);
        
        if( (hora >  17 || (hora >  17 && minutos > 0)) && (hora <  18 || (hora <  18 && minutos < 25)) ){
            random = 1;//atardeciendo
        }else if( (hora >  18 || (hora >  18 && minutos > 25)) &&  (hora <  18 || (hora <  18 && minutos < 45)) ){
            random = 3;//anocheciendo
        }else if( (hora >  18 || (hora >  18 && minutos > 45)) && (hora <  24 || (hora <  24 && minutos < 59)) ){
            random = 4;//noche
        }else if( (hora >  0 || (hora >  0 && minutos > 0)) && (hora <  6 || (hora <  6 && minutos < 5)) ){
            random = 4;//noche
        }else if(  (hora >  6 || (hora >  6 && minutos > 5)) && (hora <  7 || (hora <  7 && minutos < 30)) ){
            random = 1;//amaneciendo
        }else if((hora >  7 || (hora >  7 && minutos > 30)) && (hora <  17 || (hora <  17 && minutos < 0)) ){
            random = 2;//de dia
        }
        
        
        return ""+random;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(Boolean rememberMe) {
        this.rememberMe = rememberMe;
    }
    
    

}
