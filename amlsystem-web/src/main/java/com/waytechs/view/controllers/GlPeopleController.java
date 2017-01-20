/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.waytechs.view.controllers;

import com.waytechs.model.ejb.facades.AdCantonFacade;
import com.waytechs.model.ejb.facades.AdCivilStatusFacade;
import com.waytechs.model.ejb.facades.AdCountryFacade;
import com.waytechs.model.ejb.facades.AdCreditActivitySubjectFacade;
import com.waytechs.model.ejb.facades.AdGenderFacade;
import com.waytechs.model.ejb.facades.AdProvinceFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import com.waytechs.model.ejb.facades.AdTypeCompanyFacade;
import com.waytechs.model.ejb.facades.AdTypeEconomicActivityFacade;
import com.waytechs.model.ejb.facades.AdTypeIdeFacade;
import com.waytechs.model.ejb.facades.AdTypeOfficeFacade;
import com.waytechs.model.ejb.facades.AdTypePeopleFacade;
import com.waytechs.model.ejb.facades.AdTypePepFacade;
import com.waytechs.model.ejb.facades.GlPeopleFacade;
import com.waytechs.model.entities.AdCanton;
import com.waytechs.model.entities.AdCivilStatus;
import com.waytechs.model.entities.AdCountry;
import com.waytechs.model.entities.AdCreditActivitySubject;
import com.waytechs.model.entities.AdGender;
import com.waytechs.model.entities.AdProvince;
import com.waytechs.model.entities.AdTypeCompany;
import com.waytechs.model.entities.AdTypeEconomicActivity;
import com.waytechs.model.entities.AdTypeIde;
import com.waytechs.model.entities.AdTypeOffice;
import com.waytechs.model.entities.AdTypePeople;
import com.waytechs.model.entities.AdTypePep;
import com.waytechs.model.entities.GlPeople;
import com.waytechs.view.components.DataView;
import com.waytechs.view.components.DataViewType;
import com.waytechs.view.utils.JsfUtils;
import java.math.BigInteger;
import javax.faces.event.ValueChangeEvent;
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
    
    private List<AdGender> listaGeneros;
    
    private AdTypePeople adTypePeople;
    
    @Inject 
    private AdTypePeopleFacade adTypePeopleFacade;
    
    
    private boolean enabledCedula;
    private boolean enabledRuc;
    
    private AdCountry adCountrySelected;
    private AdProvince adProvinceSelected;
    
    private List<AdCountry> listaPaises;
    private List<AdProvince> listaProvincias;
    private List<AdCanton> listaCantones;
    
    @Inject 
    private AdCountryFacade adCountryFacade;
    
    @Inject 
    private AdProvinceFacade adProvinceFacade;
    
    @Inject 
    private AdCantonFacade adCantonFacade;
    
    private List<AdCivilStatus> listaEstadoCivil;
    @Inject 
    private AdCivilStatusFacade adCivilStatusFacade;
    
    
    private List<AdTypePep> listaTiposPep;
    
    @Inject 
    private AdTypePepFacade adTypePepFacade;
    
    
    private List<AdCreditActivitySubject> listaActividadEconomica;
    
    @Inject 
    private AdCreditActivitySubjectFacade adCreditActivitySubjectFacade;
    
    @Inject 
    private AdTypeEconomicActivityFacade adTypeEconomicActivityFacade;
    
    

    @PostConstruct
    public void initialize() {
        
        listaSocios.load();
        
        template = (TemplateController) JsfUtils.getManagedBean("template");
        
        System.out.println(getClass().getSimpleName()+" - "+template.getCurrentMenu()); 
        
        
        switch (template.getCurrentMenu().getId().intValue()) {
            case 10:
                adTypePeople = adTypePeopleFacade.find(new BigInteger("1"));
                break;
            case 11:
                adTypePeople = adTypePeopleFacade.find(new BigInteger("2"));
                break;
            case 12:
                adTypePeople = adTypePeopleFacade.find(new BigInteger("3"));
                break;
            case 13:
                adTypePeople = adTypePeopleFacade.find(new BigInteger("4"));
                break;
            default:
                break;
        }
        
        System.out.println("initialize adTypePeople: "+adTypePeople);
        
        
        listaTiposCompania = adTypeCompanyFacade.findAll();
        
        listaTiposOficinas = adTypeOfficeFacade.findAll();
        
        
        listaTiposIdentificacion = adTypeIdeFacade.findAll();
        
        listaGeneros = adGenderFacade.findAll();
        
        listaPaises = adCountryFacade.findAll();
        
        listaEstadoCivil = adCivilStatusFacade.findAll();
        
        listaTiposPep = adTypePepFacade.findAll();
        
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

    public boolean isEnabledCedula() {
        return enabledCedula;
    }

    public void setEnabledCedula(boolean enabledCedula) {
        this.enabledCedula = enabledCedula;
    }

    public boolean isEnabledRuc() {
        return enabledRuc;
    }

    public void setEnabledRuc(boolean enabledRuc) {
        this.enabledRuc = enabledRuc;
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
            System.out.println("create GlPeople");
            setActiveItem(new GlPeople());
            
            AdTypeIde adTypeIdeId = adTypeIdeFacade.find(new BigInteger("1"));
            getActiveItem().setAdTypeIdeId(adTypeIdeId);
            
            setEnabledCedula(true);
            setEnabledRuc(false);
            
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
    
    
    public void onChangeTypeId(ValueChangeEvent event){
        System.out.println("onChangeTypeId: "+event);
        
        AdTypeIde type =  (AdTypeIde) event.getNewValue();
        
        switch (type.getId().intValue()) {
            case 1:
                setEnabledCedula(true);
                setEnabledRuc(false);
                
                
                AdTypeEconomicActivity adTypeEconomicActivity2 = adTypeEconomicActivityFacade.find(new BigInteger("2"));
                listaActividadEconomica  = adCreditActivitySubjectFacade.findByAdTypeEconomicActivityId(adTypeEconomicActivity2);
                
                break;
            case 2:
                setEnabledCedula(false);
                setEnabledRuc(true);
                
                AdTypeEconomicActivity adTypeEconomicActivity1 = adTypeEconomicActivityFacade.find(new BigInteger("1"));
                listaActividadEconomica  = adCreditActivitySubjectFacade.findByAdTypeEconomicActivityId(adTypeEconomicActivity1);
                
                break;
            default:
                setEnabledCedula(true);
                setEnabledRuc(false);
                break;
        }
        
            
        
    }

    public List<AdGender> getListaGeneros() {
        return listaGeneros;
    }

    public void setListaGeneros(List<AdGender> listaGeneros) {
        this.listaGeneros = listaGeneros;
    }

    public AdCountry getAdCountrySelected() {
        return adCountrySelected;
    }

    public void setAdCountrySelected(AdCountry adCountrySelected) {
        this.adCountrySelected = adCountrySelected;
    }

    public AdProvince getAdProvinceSelected() {
        return adProvinceSelected;
    }

    public void setAdProvinceSelected(AdProvince adProvinceSelected) {
        this.adProvinceSelected = adProvinceSelected;
    }

    public List<AdCountry> getListaPaises() {
        return listaPaises;
    }

    public void setListaPaises(List<AdCountry> listaPaises) {
        this.listaPaises = listaPaises;
    }
    
    public List<AdProvince> getListaProvincias() {
        return listaProvincias;
    }

    public void setListaProvincias(List<AdProvince> listaProvincias) {
        this.listaProvincias = listaProvincias;
    }

    public List<AdCanton> getListaCantones() {
        return listaCantones;
    }

    public void setListaCantones(List<AdCanton> listaCantones) {
        this.listaCantones = listaCantones;
    }

    public List<AdCivilStatus> getListaEstadoCivil() {
        return listaEstadoCivil;
    }

    public void setListaEstadoCivil(List<AdCivilStatus> listaEstadoCivil) {
        this.listaEstadoCivil = listaEstadoCivil;
    }

    public List<AdTypePep> getListaTiposPep() {
        return listaTiposPep;
    }

    public void setListaTiposPep(List<AdTypePep> listaTiposPep) {
        this.listaTiposPep = listaTiposPep;
    }

    public List<AdCreditActivitySubject> getListaActividadEconomica() {
        return listaActividadEconomica;
    }

    public void setListaActividadEconomica(List<AdCreditActivitySubject> listaActividadEconomica) {
        this.listaActividadEconomica = listaActividadEconomica;
    }
    
    
    
    
    public void onChangeAdCountry(ValueChangeEvent event){
        
        AdCountry country =   (AdCountry) event.getNewValue();
        
        System.out.println("onChangeAdCountrySelected: "+event+" getAdCountrySelected: "+country);
        
        listaProvincias = adProvinceFacade.findByAdCountryId(country);
        
    }
    
    public void onChangeAdPronvince(ValueChangeEvent event){
        
        AdProvince province =   (AdProvince) event.getNewValue();
        
        System.out.println("onChangeAdCountrySelected: "+event+" getAdProvinceSelected: "+province);
        
        listaCantones = adCantonFacade.findByAdProvinceId(province);
        
    }
    
    
    

}
