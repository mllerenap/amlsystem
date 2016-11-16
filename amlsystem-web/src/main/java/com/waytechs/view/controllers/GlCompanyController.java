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
import com.waytechs.model.ejb.facades.AdTypeOfficeFacade;
import com.waytechs.model.ejb.facades.GlAgencyFacade;
import com.waytechs.model.ejb.facades.GlCompanyFacade;
import com.waytechs.model.ejb.facades.GlPeopleFacade;
import com.waytechs.model.entities.AdTypeCompany;
import com.waytechs.model.entities.AdTypeOffice;
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
@ManagedBean(name = "glCompany")
@ViewScoped
public class GlCompanyController implements Serializable {

    
     
    
    
    @Inject
    private GlCompanyFacade glCompanyFacade;
    
    @Inject
    private GlAgencyFacade glAgencyFacade;
    
    
    @Inject
    private AdTypeCompanyFacade adTypeCompanyFacade;
    
    @Inject
    private AdTypeOfficeFacade adTypeOfficeFacade;
    
    private List<AdTypeCompany> listaTiposCompania;
    
    private List<AdTypeOffice> listaTiposOficinas;
    
    private TemplateController template;
    
    private GlCompany activeItem;
    
    @Inject
    private GlPeopleFacade glPeopleFacade;
    
    private GlPeople repLegal;

    @PostConstruct
    public void initialize() {
        listaCompanias.load();
        
        template = (TemplateController) JsfUtils.getManagedBean("template");
        
        System.out.println(getClass().getSimpleName()+" - "+template.getCurrentMenu());  
        
        
        listaTiposCompania = adTypeCompanyFacade.findAll();
        
        listaTiposOficinas = adTypeOfficeFacade.findAll();
        
        repLegal = new GlPeople();
        
    }

    public GlPeople getRepLegal() {
        return repLegal;
    }

    public void setRepLegal(GlPeople repLegal) {
        this.repLegal = repLegal;
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
            
            getListaAgencias().load();
            
            
            GlPeople empPeople = glPeopleFacade.findByGlCompanyId(item);
            GlPeople repPeople = glPeopleFacade.findByIdref(empPeople);
            
            setRepLegal(repPeople); 
            
            /*
            getListaUsuarioRoles().load();
            setCompanySelected(item.getGlAgencyId() != null ? item.getGlAgencyId().getGlCompanyId() : null);
            agencies = glAgencyFacade.findByGlCompanyId(getCompanySelected());
            */
            
        }
        
        

       

        @Override
        public List<GlCompany> findAll() {
            return glCompanyFacade.findFull();
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
            
             try {
                 item = getActiveItem();
                 
                 glCompanyFacade.save(item);
                 setSelectedItem(item);
                 
                List<GlAgency> listaAgenciasActual = glAgencyFacade.findByGlCompanyId(item);
                List<GlAgency> listaAgenciasFinal = getListaAgencias().getValue();
                
                System.out.println("listaAgenciasActual: "+listaAgenciasActual);
                System.out.println("listaAgenciasFinal: "+listaAgenciasFinal);
                
                List<GlAgency> listaAgenciasDeleted= getListaAgencias().getDeletedItems();
                
                System.out.println("listaAgenciasDeleted: "+listaAgenciasDeleted);
                
                
                if(  listaAgenciasFinal != null && !listaAgenciasFinal.isEmpty()){
                            for (GlAgency f : listaAgenciasFinal) {
                                
                                if( f.getId().intValue() == 0 ){
                                    f.setId(null);
                                }
                                glAgencyFacade.save(f,item);
                                System.out.println("id: "+f.getId()+" change: "+f.isChange());
                            }
                }
                
                
                if(  listaAgenciasDeleted != null && !listaAgenciasDeleted.isEmpty()){
                            for (GlAgency f : listaAgenciasDeleted) {
                                glAgencyFacade.delete(f);
                            }
                }
                 
                 JsfUtils.messageInfo(null, "Compañia guardada correctamente.", null);
                 
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
    
    
    private DataList<GlAgency> listaAgencias = new DataList<GlAgency>() {
        @Override
        protected void initialize() {
            
        }

        @Override
        public List<GlAgency> findAll() {
            return glAgencyFacade.findByGlCompanyId(getActiveItem());
        }

        @Override
        protected GlAgency rowAdd(GlAgency item) {
            item = new GlAgency();
            item.setId(BigInteger.ZERO);
            return item;
        }

        @Override
        protected void validateAddRow(GlAgency item) throws Exception{ 
            if(  item.getName() == null ){
                
                JsfUtils.messageError(null, "No puede estar vacio el nombre", null);
                
                throw  new Exception("Error rol nulo");
            }
            
            if(  item.getAdTypeOfficeId() == null ){
                
                JsfUtils.messageError(null, "No puede estar vacio el tipo oficina", null);
                
                throw  new Exception("Error rol nulo");
            }
            
        }
        
        
        
        
        
    };

    public DataList<GlAgency> getListaAgencias() {
        return listaAgencias;
    }

    public void setListaAgencias(DataList<GlAgency> listaAgencias) {
        this.listaAgencias = listaAgencias;
    }

    public List<AdTypeOffice> getListaTiposOficinas() {
        return listaTiposOficinas;
    }

    public void setListaTiposOficinas(List<AdTypeOffice> listaTiposOficinas) {
        this.listaTiposOficinas = listaTiposOficinas;
    }
    
    
    
    
    
    

}
