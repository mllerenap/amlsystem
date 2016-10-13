/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.waytechs.view.controllers;

import java.io.Serializable;
import java.util.Date;
import java.util.Random;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import com.waytechs.model.ejb.facades.AdUserFacade;
import com.waytechs.model.entities.AdUser;
import com.waytechs.model.utils.DateUtils;
import com.waytechs.model.utils.SecurityUtils;
import com.waytechs.view.utils.JsfUtils;
import org.primefaces.model.menu.MenuModel;

/**
 *
 * @author Usuario
 */
@ManagedBean(name = "login")
@SessionScoped
public class LoginController implements Serializable {


    @PostConstruct
    public void initialize() {
    }

    public void errorInicioSesion() {
        try {
            JsfUtils.messageError(null, "Usuario y/o Contraseña incorrectos", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        }else{
            random = 2;//por defecto
        }
        
        
        return ""+random;
    }
    
    

}
