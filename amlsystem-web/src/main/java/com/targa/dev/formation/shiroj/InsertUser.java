/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.targa.dev.formation.shiroj;

import com.targa.dev.formation.shiroj.security.configuration.BCryptPasswordService;
import com.waytechs.model.ejb.facades.AdUserFacade;
import com.waytechs.model.entities.AdUser;
import com.waytechs.model.enums.YesNo;
import com.waytechs.model.exceptions.ExecuteRollbackException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

/**
 *
 * @author mllerena
 */
@Singleton
@Startup
public class InsertUser {
    
    @Inject
    AdUserFacade us;
    @Inject
    BCryptPasswordService passwordService;

    @PostConstruct
    public void insert() {
        //User ahmed = new User("ahmed", passwordService.encryptPassword("hello"));
        //Role role = new Role();role.setName("ADMIN");
        //ahmed.setRole(role);
        //this.us.save(ahmed);
        
        
        //user.setPassword(passwordService.encryptPassword("123"));
        
        System.out.println("InsertUser: "+passwordService.encryptPassword("123"));
        //$2a$10$ED5o1MMpZdUXBmxaISkfWOtzKuKguWw46HM1ldBBcdp9wdQ4jpaIK
        
        //user.setIsactive(YesNo.SI);
        
        
        
    }
    
}
