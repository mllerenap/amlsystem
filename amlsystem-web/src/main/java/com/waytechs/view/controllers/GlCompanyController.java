/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.waytechs.view.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import com.waytechs.model.ejb.facades.AdTypeCompanyFacade;
import com.waytechs.model.ejb.facades.GlAgencyFacade;
import com.waytechs.model.ejb.facades.GlCompanyFacade;
import com.waytechs.model.entities.AdTypeCompany;
import com.waytechs.model.entities.GlCompany;
import com.waytechs.view.components.DataView;
import com.waytechs.view.components.DataViewType;
import com.waytechs.view.utils.JsfUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 *
 * @author Usuario
 */
@ManagedBean(name = "glCompany")
@ViewScoped
public class GlCompanyController implements Serializable {

    
     
    
    
    @Inject
    private GlCompanyFacade alCompanyFacade;
    
    @Inject
    private GlAgencyFacade glAgencyFacade;
    
    
    @Inject
    private AdTypeCompanyFacade adTypeCompanyFacade;
    
    
    private List<AdTypeCompany> listaTiposCompania;
    
    private TemplateController template;
    
    private GlCompany activeItem;

    @PostConstruct
    public void initialize() {
        listaCompanias.load();
        
        template = (TemplateController) JsfUtils.getManagedBean("template");
        
        System.out.println(getClass().getSimpleName()+" - "+template.getCurrentMenu());  
        
        
        listaTiposCompania = adTypeCompanyFacade.findAll();
        
    }

    
    
   

    public GlCompany getActiveItem() {
        return this.activeItem;
    }

    public void setActiveItem(GlCompany activeItem) {
        this.activeItem = activeItem;
    }
    
    
    

    private DataView<GlCompany> listaCompanias = new DataView<GlCompany>() {
        
         @Override
        protected void initialize() {
            setId("dvCompanies");
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
        protected void rowSelected(GlCompany item) {
            //setPass1(item.getPassword());    
            setActiveItem(item);
            /*
            getListaUsuarioRoles().load();
            setCompanySelected(item.getGlAgencyId() != null ? item.getGlAgencyId().getGlCompanyId() : null);
            agencies = glAgencyFacade.findByGlCompanyId(getCompanySelected());
            */
            
        }
        
        

       

        @Override
        public List<GlCompany> findAll() {
            return alCompanyFacade.findFull();
        }

        @Override
        protected GlCompany create() {
            System.out.println("create GlCompany");
            setActiveItem(new GlCompany());
            //getListaUsuarioRoles().load();
            return getActiveItem();
        }

        @Override
        protected GlCompany save(GlCompany item) {
            
            item = getActiveItem();
            

            JsfUtils.messageInfo(null, "Compañia guardada correctamente.", null);

            return item;
        }

        @Override
        protected void cancel() {
            //getListaUsuarioRoles().load();
        }

        @Override
        protected void delete(List<GlCompany> items) {

            JsfUtils.messageInfo(null, "Compañia eliminada correctamente.", null);
        }
        
        
        
        

       

    };

    public DataView<GlCompany> getListaCompanias() {
        return listaCompanias;
    }

    public void setListaCompanias(DataView<GlCompany> listaCompanias) {
        this.listaCompanias = listaCompanias;
    }

    public List<AdTypeCompany> getListaTiposCompania() {
        return listaTiposCompania;
    }

    public void setListaTiposCompania(List<AdTypeCompany> listaTiposCompania) {
        this.listaTiposCompania = listaTiposCompania;
    }
    
    
    
    

}
