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
import com.waytechs.model.ejb.facades.AdEconomicActivityHomoFacade;
import com.waytechs.model.ejb.facades.AdEmploymentSituationFacade;
import com.waytechs.model.ejb.facades.AdGenderFacade;
import com.waytechs.model.ejb.facades.AdPositionFacade;
import com.waytechs.model.ejb.facades.AdProvinceFacade;
import com.waytechs.model.ejb.facades.AdSectorAddressFacade;
import com.waytechs.model.ejb.facades.AdTypeBuildingFacade;
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
import com.waytechs.model.ejb.facades.GlAddressFacade;
import com.waytechs.model.ejb.facades.GlPeopleFacade;
import com.waytechs.model.entities.AdCanton;
import com.waytechs.model.entities.AdCivilStatus;  
import com.waytechs.model.entities.AdCountry;
import com.waytechs.model.entities.AdCreditActivitySubject;
import com.waytechs.model.entities.AdEconomicActivityHomo;
import com.waytechs.model.entities.AdEmploymentSituation;
import com.waytechs.model.entities.AdGender;
import com.waytechs.model.entities.AdPosition;
import com.waytechs.model.entities.AdProvince;
import com.waytechs.model.entities.AdSectorAddress;
import com.waytechs.model.entities.AdTypeBuilding;
import com.waytechs.model.entities.AdTypeCompany;
import com.waytechs.model.entities.AdTypeEconomicActivity;
import com.waytechs.model.entities.AdTypeIde;
import com.waytechs.model.entities.AdTypeOffice;
import com.waytechs.model.entities.AdTypePeople;
import com.waytechs.model.entities.AdTypePep;
import com.waytechs.model.entities.GlAddress;
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
    
    
    
    private List<AdCountry> listaNacionalidades;
    
    private List<AdCountry> listaPaisesNacimiento;
    
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
    
    private List<AdEconomicActivityHomo> listaSubActividad;
    
    @Inject 
    private AdEconomicActivityHomoFacade adEconomicActivityHomoFacade;
    
    
    @Inject 
    private AdEmploymentSituationFacade adEmploymentSituationFacade;
    
    private List<AdEmploymentSituation> listaSituacionLaboral;
    
    
    @Inject 
    private AdPositionFacade adPositionFacade;
    
    private List<AdPosition> listaCargos;
    
    
    
    private List<AdCreditActivitySubject> listaActividadEconomicaEmpresa;
    
    private List<AdEconomicActivityHomo> listaSubActividadEmpresa;
    
    
    @Inject 
    private GlAddressFacade glAddressFacade;
    
    private GlAddress glAddressDomicilio;
    
    
    @Inject 
    private AdSectorAddressFacade adSectorAddressFacade;
    
    private List<AdSectorAddress> listaSector;
    
    
    @Inject 
    private AdTypeBuildingFacade adTypeBuildingFacade;
    
    private List<AdTypeBuilding> listaTipoVivienda;
    
    private GlAddress glAddressEmpresa;
    
    private AdCountry adCountryJobSelected;
    private AdProvince adProvinceJobSelected;
    
    private List<AdCountry> listaPaisesEmpresa;
    private List<AdProvince> listaProvinciasEmpresa;
    private List<AdCanton> listaCantonesEmpresa;
    
    private List<AdSectorAddress> listaSectorEmpresa;
    
    
    
    private GlAddress glAddressDomicilioCompania;
    
    private AdCountry adCountryCompaniaSelected;
    private AdProvince adProvinceCompaniaSelected;
    
    private List<AdCountry> listaPaisesCompania;
    private List<AdProvince> listaProvinciasCompania;
    private List<AdCanton> listaCantonesCompania;
    
    private List<AdSectorAddress> listaSectorCompania;
    
    
    
    private GlPeople conyuge;
    
    private List<AdTypeIde> listaTiposIdentificacionConyuge;
    
    
    
    private GlPeople repLegal;
    private List<AdTypeIde> listaTiposIdentificacionRepLegal;
    private List<AdGender> listaGenerosRepLegal;
    private List<AdCountry> listaNacionalidadesRepLegal;
    private List<AdCountry> listaPaisesNacimientoRepLegal;
    private List<AdCivilStatus> listaEstadoCivilRepLegal;
    
    
    
    private GlAddress glAddressDomicilioRepLegal;
    private AdCountry adCountryRepLegal;
    private AdProvince adProvinceRepLegal;
    private List<AdCountry> listaPaisesRepLegal;
    private List<AdProvince> listaProvinciasRepLegal;
    private List<AdCanton> listaCantonesRepLegal;
    private List<AdSectorAddress> listaSectorRepLegal;
    private List<AdTypeBuilding> listaTipoViviendaRepLegal;
    
    
    
    private GlPeople conyugeRepLegal;
    private List<AdTypeIde> listaTiposIdentificacionConyugeRepLegal;
    

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
        listaTiposIdentificacionConyuge = adTypeIdeFacade.findAll();
        listaTiposIdentificacionRepLegal = adTypeIdeFacade.findAll();
        
        listaGeneros = adGenderFacade.findAll();
        
        listaGenerosRepLegal = adGenderFacade.findAll();
        
        listaPaises = adCountryFacade.findAll();
        
        listaEstadoCivil = adCivilStatusFacade.findAll();
        listaEstadoCivilRepLegal = adCivilStatusFacade.findAll();
        
        listaTiposPep = adTypePepFacade.findAll();
        
        
        listaNacionalidades = adCountryFacade.findAll();
        listaPaisesNacimiento = adCountryFacade.findAll();
        
        
        
        
        
        
        
        listaSituacionLaboral = adEmploymentSituationFacade.findAll();
        
        listaCargos = adPositionFacade.findAll();
        
        AdTypeEconomicActivity adTypeEconomicActivity1 = adTypeEconomicActivityFacade.find(new BigInteger("1"));
        listaActividadEconomicaEmpresa  = adCreditActivitySubjectFacade.findByAdTypeEconomicActivityId(adTypeEconomicActivity1);
        
        
        listaSector = adSectorAddressFacade.findAll();
        listaTipoVivienda = adTypeBuildingFacade.findAll();
        
        
        listaPaisesEmpresa = adCountryFacade.findAll();
        listaSectorEmpresa = adSectorAddressFacade.findAll();
        
        listaPaisesCompania = adCountryFacade.findAll();
        listaSectorCompania = adSectorAddressFacade.findAll();
        
        
        listaNacionalidadesRepLegal = adCountryFacade.findAll();
        listaPaisesNacimientoRepLegal = adCountryFacade.findAll();
        listaPaisesRepLegal = adCountryFacade.findAll();
        listaSectorRepLegal = adSectorAddressFacade.findAll();
        listaTipoViviendaRepLegal = adTypeBuildingFacade.findAll();
        
        
        listaTiposIdentificacionConyugeRepLegal = adTypeIdeFacade.findAll();
        
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
            
            AdTypeEconomicActivity adTypeEconomicActivity2 = adTypeEconomicActivityFacade.find(new BigInteger("2"));
            listaActividadEconomica  = adCreditActivitySubjectFacade.findByAdTypeEconomicActivityId(adTypeEconomicActivity2);
            
            //listaActividadEconomicaEmpresa = adCreditActivitySubjectFacade.findByAdTypeEconomicActivityId(adTypeEconomicActivity2);
            
            glAddressDomicilio = new GlAddress();
            
            glAddressEmpresa = new GlAddress();
            
            glAddressDomicilioCompania = new GlAddress();
            
            conyuge = new GlPeople();
            
            repLegal =new GlPeople();
            
            glAddressDomicilioRepLegal = new GlAddress();
            
            conyugeRepLegal = new GlPeople();
            
            return getActiveItem();
        }

        @Override
        protected GlPeople save(GlPeople item) {
            
             try {
                 
                 getActiveItem().setAdTypePeopleId(getAdTypePeople());
                 
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

    public List<AdEconomicActivityHomo> getListaSubActividad() {
        return listaSubActividad;
    }

    public void setListaSubActividad(List<AdEconomicActivityHomo> listaSubActividad) {
        this.listaSubActividad = listaSubActividad;
    }

    public List<AdCountry> getListaNacionalidades() {
        return listaNacionalidades;
    }

    public void setListaNacionalidades(List<AdCountry> listaNacionalidades) {
        this.listaNacionalidades = listaNacionalidades;
    }

    public List<AdCountry> getListaPaisesNacimiento() {
        return listaPaisesNacimiento;
    }

    public void setListaPaisesNacimiento(List<AdCountry> listaPaisesNacimiento) {
        this.listaPaisesNacimiento = listaPaisesNacimiento;
    }

    public List<AdEmploymentSituation> getListaSituacionLaboral() {
        return listaSituacionLaboral;
    }

    public void setListaSituacionLaboral(List<AdEmploymentSituation> listaSituacionLaboral) {
        this.listaSituacionLaboral = listaSituacionLaboral;
    }

    public List<AdPosition> getListaCargos() {
        return listaCargos;
    }

    public void setListaCargos(List<AdPosition> listaCargos) {
        this.listaCargos = listaCargos;
    }

    public List<AdCreditActivitySubject> getListaActividadEconomicaEmpresa() {
        return listaActividadEconomicaEmpresa;
    }

    public void setListaActividadEconomicaEmpresa(List<AdCreditActivitySubject> listaActividadEconomicaEmpresa) {
        this.listaActividadEconomicaEmpresa = listaActividadEconomicaEmpresa;
    }

    public List<AdEconomicActivityHomo> getListaSubActividadEmpresa() {
        return listaSubActividadEmpresa;
    }

    public void setListaSubActividadEmpresa(List<AdEconomicActivityHomo> listaSubActividadEmpresa) {
        this.listaSubActividadEmpresa = listaSubActividadEmpresa;
    }

    public GlAddress getGlAddressDomicilio() {
        return glAddressDomicilio;
    }

    public void setGlAddressDomicilio(GlAddress glAddressDomicilio) {
        this.glAddressDomicilio = glAddressDomicilio; 
    }

    public List<AdSectorAddress> getListaSector() {
        return listaSector;
    }

    public void setListaSector(List<AdSectorAddress> listaSector) {
        this.listaSector = listaSector;
    }

    public List<AdTypeBuilding> getListaTipoVivienda() {
        return listaTipoVivienda;
    }

    public void setListaTipoVivienda(List<AdTypeBuilding> listaTipoVivienda) {
        this.listaTipoVivienda = listaTipoVivienda;
    }

    public GlAddress getGlAddressEmpresa() {
        return glAddressEmpresa;
    }

    public void setGlAddressEmpresa(GlAddress glAddressEmpresa) {
        this.glAddressEmpresa = glAddressEmpresa;
    }

    public AdCountry getAdCountryJobSelected() {
        return adCountryJobSelected;
    }

    public void setAdCountryJobSelected(AdCountry adCountryJobSelected) {
        this.adCountryJobSelected = adCountryJobSelected;
    }

    public AdProvince getAdProvinceJobSelected() {
        return adProvinceJobSelected;
    }

    public void setAdProvinceJobSelected(AdProvince adProvinceJobSelected) {
        this.adProvinceJobSelected = adProvinceJobSelected;
    }

    public List<AdCountry> getListaPaisesEmpresa() {
        return listaPaisesEmpresa;
    }

    public void setListaPaisesEmpresa(List<AdCountry> listaPaisesEmpresa) {
        this.listaPaisesEmpresa = listaPaisesEmpresa;
    }

    public List<AdProvince> getListaProvinciasEmpresa() {
        return listaProvinciasEmpresa;
    }

    public void setListaProvinciasEmpresa(List<AdProvince> listaProvinciasEmpresa) {
        this.listaProvinciasEmpresa = listaProvinciasEmpresa;
    }

    public List<AdCanton> getListaCantonesEmpresa() {
        return listaCantonesEmpresa;
    }

    public void setListaCantonesEmpresa(List<AdCanton> listaCantonesEmpresa) {
        this.listaCantonesEmpresa = listaCantonesEmpresa;
    }

    public List<AdSectorAddress> getListaSectorEmpresa() {
        return listaSectorEmpresa;
    }

    public void setListaSectorEmpresa(List<AdSectorAddress> listaSectorEmpresa) {
        this.listaSectorEmpresa = listaSectorEmpresa;
    }

    public AdCountry getAdCountryCompaniaSelected() {
        return adCountryCompaniaSelected;
    }

    public void setAdCountryCompaniaSelected(AdCountry adCountryCompaniaSelected) {
        this.adCountryCompaniaSelected = adCountryCompaniaSelected;
    }

    public AdProvince getAdProvinceCompaniaSelected() {
        return adProvinceCompaniaSelected;
    }

    public void setAdProvinceCompaniaSelected(AdProvince adProvinceCompaniaSelected) {
        this.adProvinceCompaniaSelected = adProvinceCompaniaSelected;
    }

    public List<AdCountry> getListaPaisesCompania() {
        return listaPaisesCompania;
    }

    public void setListaPaisesCompania(List<AdCountry> listaPaisesCompania) {
        this.listaPaisesCompania = listaPaisesCompania;
    }

    public List<AdProvince> getListaProvinciasCompania() {
        return listaProvinciasCompania;
    }

    public void setListaProvinciasCompania(List<AdProvince> listaProvinciasCompania) {
        this.listaProvinciasCompania = listaProvinciasCompania;
    }

    public List<AdCanton> getListaCantonesCompania() {
        return listaCantonesCompania;
    }

    public void setListaCantonesCompania(List<AdCanton> listaCantonesCompania) {
        this.listaCantonesCompania = listaCantonesCompania;
    }

    public List<AdSectorAddress> getListaSectorCompania() {
        return listaSectorCompania;
    }

    public void setListaSectorCompania(List<AdSectorAddress> listaSectorCompania) {
        this.listaSectorCompania = listaSectorCompania;
    }

    public GlAddress getGlAddressDomicilioCompania() {
        return glAddressDomicilioCompania;
    }

    public void setGlAddressDomicilioCompania(GlAddress glAddressDomicilioCompania) {
        this.glAddressDomicilioCompania = glAddressDomicilioCompania;
    }

    public GlPeople getConyuge() {
        return conyuge;
    }

    public void setConyuge(GlPeople conyuge) {
        this.conyuge = conyuge;
    }

    public List<AdTypeIde> getListaTiposIdentificacionConyuge() {
        return listaTiposIdentificacionConyuge;
    }

    public void setListaTiposIdentificacionConyuge(List<AdTypeIde> listaTiposIdentificacionConyuge) {
        this.listaTiposIdentificacionConyuge = listaTiposIdentificacionConyuge;
    }

    public GlPeople getRepLegal() {
        return repLegal;
    }

    public void setRepLegal(GlPeople repLegal) {
        this.repLegal = repLegal;
    }

    public List<AdTypeIde> getListaTiposIdentificacionRepLegal() {
        return listaTiposIdentificacionRepLegal;
    }

    public void setListaTiposIdentificacionRepLegal(List<AdTypeIde> listaTiposIdentificacionRepLegal) {
        this.listaTiposIdentificacionRepLegal = listaTiposIdentificacionRepLegal;
    }

    public List<AdGender> getListaGenerosRepLegal() {
        return listaGenerosRepLegal;
    }

    public void setListaGenerosRepLegal(List<AdGender> listaGenerosRepLegal) {
        this.listaGenerosRepLegal = listaGenerosRepLegal;
    }

    public List<AdCountry> getListaNacionalidadesRepLegal() {
        return listaNacionalidadesRepLegal;
    }

    public void setListaNacionalidadesRepLegal(List<AdCountry> listaNacionalidadesRepLegal) {
        this.listaNacionalidadesRepLegal = listaNacionalidadesRepLegal;
    }

    public List<AdCountry> getListaPaisesNacimientoRepLegal() {
        return listaPaisesNacimientoRepLegal;
    }

    public void setListaPaisesNacimientoRepLegal(List<AdCountry> listaPaisesNacimientoRepLegal) {
        this.listaPaisesNacimientoRepLegal = listaPaisesNacimientoRepLegal;
    }

    public List<AdCivilStatus> getListaEstadoCivilRepLegal() {
        return listaEstadoCivilRepLegal;
    }

    public void setListaEstadoCivilRepLegal(List<AdCivilStatus> listaEstadoCivilRepLegal) {
        this.listaEstadoCivilRepLegal = listaEstadoCivilRepLegal;
    }

    public GlAddress getGlAddressDomicilioRepLegal() {
        return glAddressDomicilioRepLegal;
    }

    public void setGlAddressDomicilioRepLegal(GlAddress glAddressDomicilioRepLegal) {
        this.glAddressDomicilioRepLegal = glAddressDomicilioRepLegal;
    }

    public AdCountry getAdCountryRepLegal() {
        return adCountryRepLegal;
    }

    public void setAdCountryRepLegal(AdCountry adCountryRepLegal) {
        this.adCountryRepLegal = adCountryRepLegal;
    }

    public AdProvince getAdProvinceRepLegal() {
        return adProvinceRepLegal;
    }

    public void setAdProvinceRepLegal(AdProvince adProvinceRepLegal) {
        this.adProvinceRepLegal = adProvinceRepLegal;
    }

    public List<AdCountry> getListaPaisesRepLegal() {
        return listaPaisesRepLegal;
    }

    public void setListaPaisesRepLegal(List<AdCountry> listaPaisesRepLegal) {
        this.listaPaisesRepLegal = listaPaisesRepLegal;
    }

    public List<AdProvince> getListaProvinciasRepLegal() {
        return listaProvinciasRepLegal;
    }

    public void setListaProvinciasRepLegal(List<AdProvince> listaProvinciasRepLegal) {
        this.listaProvinciasRepLegal = listaProvinciasRepLegal;
    }

    public List<AdCanton> getListaCantonesRepLegal() {
        return listaCantonesRepLegal;
    }

    public void setListaCantonesRepLegal(List<AdCanton> listaCantonesRepLegal) {
        this.listaCantonesRepLegal = listaCantonesRepLegal;
    }

    public List<AdSectorAddress> getListaSectorRepLegal() {
        return listaSectorRepLegal;
    }

    public void setListaSectorRepLegal(List<AdSectorAddress> listaSectorRepLegal) {
        this.listaSectorRepLegal = listaSectorRepLegal;
    }

    public List<AdTypeBuilding> getListaTipoViviendaRepLegal() {
        return listaTipoViviendaRepLegal;
    }

    public void setListaTipoViviendaRepLegal(List<AdTypeBuilding> listaTipoViviendaRepLegal) {
        this.listaTipoViviendaRepLegal = listaTipoViviendaRepLegal;
    }

    public GlPeople getConyugeRepLegal() {
        return conyugeRepLegal;
    }

    public void setConyugeRepLegal(GlPeople conyugeRepLegal) {
        this.conyugeRepLegal = conyugeRepLegal;
    }

    public List<AdTypeIde> getListaTiposIdentificacionConyugeRepLegal() {
        return listaTiposIdentificacionConyugeRepLegal;
    }

    public void setListaTiposIdentificacionConyugeRepLegal(List<AdTypeIde> listaTiposIdentificacionConyugeRepLegal) {
        this.listaTiposIdentificacionConyugeRepLegal = listaTiposIdentificacionConyugeRepLegal;
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
    
    public void onChangeAdCreditActivitySubject(ValueChangeEvent event){
        
        AdCreditActivitySubject actividad =   (AdCreditActivitySubject) event.getNewValue(); 
        
        System.out.println("onChangeAdCreditActivitySubject: "+actividad);
        
        //listaCantones = adCantonFacade.findByAdProvinceId(province);
        
        listaSubActividad = adEconomicActivityHomoFacade.findByCodeAct(actividad.getCodcreditactivitysubject());
        
    }
    
    
    public void onChangeAdCreditActivitySubjectJob(ValueChangeEvent event){
        AdCreditActivitySubject actividad =   (AdCreditActivitySubject) event.getNewValue(); 
        System.out.println("onChangeAdCreditActivitySubjectJob: "+actividad);
        
        listaSubActividadEmpresa = adEconomicActivityHomoFacade.findByCodeAct(actividad.getCodcreditactivitysubject());
        
    }
    
    
    
    public void onChangeAdCountryJob(ValueChangeEvent event){
        
        AdCountry country =   (AdCountry) event.getNewValue();
        
        System.out.println("onChangeAdCountryJob: "+event+" getAdCountrySelected: "+country);
        
        listaProvinciasEmpresa = adProvinceFacade.findByAdCountryId(country);
        
    }
    
    public void onChangeAdPronvinceJob(ValueChangeEvent event){
        
        AdProvince province =   (AdProvince) event.getNewValue();
        
        System.out.println("onChangeAdPronvinceJob: "+event+" getAdProvinceSelected: "+province);
        
        listaCantonesEmpresa = adCantonFacade.findByAdProvinceId(province);
        
    }
    
    public void onChangeAdCountryCompania(ValueChangeEvent event){
        
        AdCountry country =   (AdCountry) event.getNewValue();
        
        System.out.println("onChangeAdCountryCompania: "+event+" getAdCountrySelected: "+country);
        
        listaProvinciasCompania = adProvinceFacade.findByAdCountryId(country);
        
    }
    
    public void onChangeAdPronvinceCompania(ValueChangeEvent event){
        
        AdProvince province =   (AdProvince) event.getNewValue();
        
        System.out.println("onChangeAdPronvinceCompania: "+event+" getAdProvinceSelected: "+province);
        
        listaCantonesCompania = adCantonFacade.findByAdProvinceId(province);
        
    }
    
    
    
    public void onChangeAdCountryRepLegal(ValueChangeEvent event){
        
        AdCountry country =   (AdCountry) event.getNewValue();
        
        System.out.println("onChangeAdCountryRepLegal: "+event+" getAdCountrySelected: "+country);
        
        listaProvinciasRepLegal = adProvinceFacade.findByAdCountryId(country);
        
    }
    
    public void onChangeAdPronvinceRepLegal(ValueChangeEvent event){
        
        AdProvince province =   (AdProvince) event.getNewValue();
        
        System.out.println("onChangeAdPronvinceRepLegal: "+event+" getAdProvinceSelected: "+province);
        
        listaCantonesRepLegal = adCantonFacade.findByAdProvinceId(province);
        
    }
    
    
    

}
