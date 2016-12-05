/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.waytechs.view.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import org.primefaces.component.inputtext.InputText;

/**
 *
 * @author mllerena
 */
@FacesValidator("emailValidator")
public class EmailValidator implements Validator{
    
    private final static String EMAIL_PATTERN =
        "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    private final static Pattern EMAIL_COMPILED_PATTERN = Pattern.compile(EMAIL_PATTERN);


    public EmailValidator() {
        super();
    }

    @Override
    public void validate(FacesContext fc, UIComponent c, Object o) throws ValidatorException {
        
        
         InputText cmp = (InputText) c;
         
         
        
        // TODO Implement this method
        // No value is not ok
        if (o == null || "".equals((String) o)) {
            
            if(cmp.isRequired()){
                System.out.println("debe validar mail: "+o);
                FacesMessage msg = new FacesMessage("Valor del email vacío", "Por favor ingresar un email válido");
                msg.setSeverity(FacesMessage.SEVERITY_ERROR);
                throw new ValidatorException(msg);
             
            }
            
            
        }
        
        
            
        // The email matcher
        Matcher matcher = EMAIL_COMPILED_PATTERN.matcher((String) o);

        if (!matcher.matches()) { // Email doesn't match
            FacesMessage msg = new FacesMessage("Formato email incorrecto", "Por favor ingresar un email válido");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }

    }
    
}
