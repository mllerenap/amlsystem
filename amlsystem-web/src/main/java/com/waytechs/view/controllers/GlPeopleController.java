/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.waytechs.view.controllers;

import com.waytechs.model.ejb.facades.AdGenderFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import com.waytechs.model.ejb.facades.AdTypeCompanyFacade;
import com.waytechs.model.ejb.facades.AdTypeIdeFacade;
import com.waytechs.model.ejb.facades.AdTypeOfficeFacade;
import com.waytechs.model.ejb.facades.AdTypePeopleFacade;
import com.waytechs.model.ejb.facades.GlAgencyFacade;
import com.waytechs.model.ejb.facades.GlCompanyFacade;
import com.waytechs.model.ejb.facades.GlPeopleFacade;
import com.waytechs.model.entities.AdGender;
import com.waytechs.model.entities.AdTypeCompany;
import com.waytechs.model.entities.AdTypeIde;
import com.waytechs.model.entities.AdTypeOffice;
import com.waytechs.model.entities.AdTypePeople;
import com.waytechs.model.entities.AdUserRoles;
import com.waytechs.model.entities.GlAgency;
import com.waytechs.model.entities.GlCompany;
import com.waytechs.model.entities.GlPeople;
import com.waytechs.model.exceptions.ExecuteRollbackException;
import com.waytechs.view.components.DataList;
import com.waytechs.view.components.DataView;
import com.waytechs.view.components.DataViewType;
import com.waytechs.view.utils.JsfUtils;
import java.math.BigInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 *
 * @author Usuario
 */
@ManagedBean(name = "glPeople")
@ViewScoped
public class GlPeopleController implements Serializable { 
    
    
    @Inject
    private AdTypeCompanyFacade adTypeCompanyFacade;
    
    @Inject
    private AdTypeOfficeFacade adTypeOfficeFacade;
    
    private List<AdTypeCompany> listaTiposCompania;
    
    private List<AdTypeOffice> listaTiposOficinas;
    
    private TemplateController template;
    
    private GlPeople activeItem;
    
    @Inject
    private GlPeopleFacade glPeopleFacade;
    
    
    @Inject
    private AdTypeIdeFacade adTypeIdeFacade;
    
    private List<AdTypeIde> listaTiposIdentificacion;
    
    @Inject
    private AdGenderFacade adGenderFacade;
    
    private AdTypePeople adTypePeople;
    
    @Inject 
    private AdTypePeopleFacade adTypePeopleFacade;

    @PostConstruct
    public void initialize() {
        
        listaSocios.load();
        
        template = (TemplateController) JsfUtils.getManagedBean("template");
        
        System.out.println(getClass().getSimpleName()+" - "+template.getCurrentMenu()); 
        
        
        switch (template.getCurrentMenu().getId().intValue()) {
            case 10:
                adTypePeople = adTypePeopleFacade.find(1L);
                break;
            case 11:
                adTypePeople = adTypePeopleFacade.find(2L);
                break;
            case 12:
                adTypePeople = adTypePeopleFacade.find(3L);
                break;
            case 13:
                adTypePeople = adTypePeopleFacade.find(4L);
                break;
            default:
                break;
        }
        
        System.out.println("initialize adTypePeople: "+adTypePeople);
        
        
        listaTiposCompania = adTypeCompanyFacade.findAll();
        
        listaTiposOficinas = adTypeOfficeFacade.findAll();
        
        
        listaTiposIdentificacion = adTypeIdeFacade.findAll();
        
        
    }

    public AdTypePeople getAdTypePeople() {
        return adTypePeople;
    }

    public void setAdTypePeople(AdTypePeople adTypePeople) {
        this.adTypePeople = adTypePeople;
    }
    
    

    public GlPeople getActiveItem() {
        return activeItem;
    }

    public void setActiveItem(GlPeople activeItem) {
        this.activeItem = activeItem;
    }

    

    private DataView<GlPeople> listaSocios = new DataView<GlPeople>() {
        
         @Override
        protected void initialize() {
            setId("dvSocios");
            
            System.out.println("initialize DataView: "+getId());
            
            Subject s =  SecurityUtils.getSubject();
            
            
            setHasPermmissionCreate(s.isPermitted("1:1"));
            setHasPermmissionEdit(s.isPermitted("1:2"));
            setHasPermmissionDelete(s.isPermitted("1:3"));
            setHasPermmissionSave(s.isPermitted("1:4"));
            
        }
        
        
         @Override
        public List<DataViewType> viewTypes() { 
            List<DataViewType> list =  new ArrayList<>();
            list.add(DataViewType.TABLE);
            list.add(DataViewType.ROW);
            return list;
        }

        @Override
        protected void rowSelected(GlPeople item) {
            //setPass1(item.getPassword());    
            setActiveItem(item);
            
        }
        
        

       

        @Override
        public List<GlPeople> findAll() {
            return glPeopleFacade.findByAdTypePeopleId(getAdTypePeople());
        }

        @Override
        protected GlPeople create() {
            System.out.println("create GlCompany");
            setActiveItem(new GlPeople());
            //getListaUsuarioRoles().load();
            return getActiveItem();
        }

        @Override
        protected GlPeople save(GlPeople item) {
            
             try {
                 
                 
                 item = getActiveItem();
                 
                 glPeopleFacade.save(item);
                 setSelectedItem(item);
                 
                
                 
                 JsfUtils.messageInfo(null, "Socio guardado correctamente.", null);
                 
                 return item;
             } catch (Exception e) {
                JsfUtils.messageError(null, e.getMessage(), null);
                return null;
            }
        }

        @Override
        protected void cancel() {
            //getListaUsuarioRoles().load();
        }

        @Override
        protected void delete(List<GlPeople> items) {

            JsfUtils.messageInfo(null, "Socio eliminado correctamente.", null);
        }
        
        
        
        

       

    };

    public DataView<GlPeople> getListaSocios() {
        return listaSocios;
    }

    public void setListaSocios(DataView<GlPeople> listaSocios) {
        this.listaSocios = listaSocios;
    }

    

    public List<AdTypeCompany> getListaTiposCompania() {
        return listaTiposCompania;
    }

    public void setListaTiposCompania(List<AdTypeCompany> listaTiposCompania) {
        this.listaTiposCompania = listaTiposCompania;
    }
    
    
    

    public List<AdTypeOffice> getListaTiposOficinas() {
        return listaTiposOficinas;
    }

    public void setListaTiposOficinas(List<AdTypeOffice> listaTiposOficinas) {
        this.listaTiposOficinas = listaTiposOficinas;
    }

    public List<AdTypeIde> getListaTiposIdentificacion() {
        return listaTiposIdentificacion;
    }

    public void setListaTiposIdentificacion(List<AdTypeIde> listaTiposIdentificacion) {
        this.listaTiposIdentificacion = listaTiposIdentificacion;
    }
    
    
    
    
    
    
    

}
