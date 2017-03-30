/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.waytechs.view.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.Part;
import com.waytechs.model.ejb.facades.AdRoleFacade;
import com.waytechs.model.ejb.facades.AdUserFacade;
import com.waytechs.model.ejb.facades.AdUserRolesFacade;
import com.waytechs.model.ejb.facades.GlAgencyFacade;
import com.waytechs.model.ejb.facades.GlCompanyFacade;
import com.waytechs.model.entities.AdRole;
import com.waytechs.model.entities.AdUser;
import com.waytechs.model.entities.AdUserRoles;
import com.waytechs.model.entities.GlAgency;
import com.waytechs.model.entities.GlCompany;
import com.waytechs.view.components.DataList;
import com.waytechs.view.components.DataTable;
import com.waytechs.view.components.DataView;
import com.waytechs.view.components.DataViewType;
import com.waytechs.view.utils.JsfUtils;
import javax.faces.bean.ManagedProperty;
import javax.faces.event.ActionEvent;
import org.apache.commons.io.IOUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 *
 * @author Usuario
 */
@ManagedBean(name = "adUser")
@ViewScoped
public class AdUserController implements Serializable {

    @Inject
    private AdUserFacade adUserFacade;

    private AdUser activeItem;

    private String pass1;
    private String pass2;

    private Part image;
    private boolean imageModified;

    @Inject
    private AdRoleFacade adRoleFacade;

    @Inject
    private AdUserRolesFacade adUserRolesFacade;

    @Inject
    private GlCompanyFacade alCompanyFacade;

    @Inject
    private GlAgencyFacade glAgencyFacade;

    private List<AdRole> roles;

    private TemplateController template;

    private List<GlCompany> companies;

    private GlCompany companySelected;

    private List<GlAgency> agencies;

    @PostConstruct
    public void initialize() {
        listaUsuarios.load();
        listaUsuarios.setViewTypeActive(DataViewType.GRID);

        roles = adRoleFacade.findAll();

        template = (TemplateController) JsfUtils.getManagedBean("template");

        System.out.println(getClass().getSimpleName() + " - " + template.getCurrentMenu());

        companies = alCompanyFacade.findFull();
        agencies = new ArrayList<>();

    }

    public Part getImage() {
        return image;
    }

    public void setImage(Part image) {
        if (image != null) {
            try {
                InputStream input = image.getInputStream();
                activeItem.setImage(IOUtils.toByteArray(input));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else if (image == null && activeItem.getImage() != null && imageModified == true) {
            activeItem.setImage(null);
        }
        System.out.println("setImage: " + image);
    }

    public boolean isImageModified() {
        return imageModified;
    }

    public void setImageModified(boolean imageModified) {
        this.imageModified = imageModified;
    }

    public void validateImage(FacesContext ctx, UIComponent comp, Object value) {
        if (value != null) {
            Part file = (Part) value;

            if (!file.getContentType().startsWith("image")) {
                JsfUtils.messageWarning(null, "No hay imagen", null);
            }
            if (file.getSize() > 200024) {
                JsfUtils.messageWarning(null, "Imagen muy grande", null);
            }
        }
    }

    public AdUser getActiveItem() {
        return this.activeItem;
    }

    public void setActiveItem(AdUser activeItem) {
        this.activeItem = activeItem;
    }

    private DataView<AdUser> listaUsuarios = new DataView<AdUser>() {

        @Override
        protected void initialize() {
            setId("dvUsuarios");
            System.out.println("initialize DataView: " + getId());

            Subject s = SecurityUtils.getSubject();

            setHasPermmissionCreate(s.isPermitted("1:1"));
            setHasPermmissionEdit(s.isPermitted("1:2"));
            setHasPermmissionDelete(s.isPermitted("1:3"));
            setHasPermmissionSave(s.isPermitted("1:4"));

        }

        @Override
        protected List<AdUser> filterGrid(String searchKey, List<AdUser> filteredValues) {
            List<AdUser> results = new ArrayList<>();
            for (AdUser user : filteredValues) {
                if (user.getName().toUpperCase().contains(searchKey.toUpperCase())) {
                    results.add(user);
                }
            }
            System.out.println("filtrado: " + results);
            return results;
        }

        @Override
        public List<DataViewType> viewTypes() {
            List<DataViewType> list = new ArrayList<>();
            list.add(DataViewType.GRID);
            list.add(DataViewType.TABLE);
            list.add(DataViewType.ROW);
            return list;
        }

        @Override
        protected void rowSelected(AdUser item) {
            setPass1(item.getPassword());
            setActiveItem(item);
            getListaUsuarioRoles().load();
            setCompanySelected(item.getGlAgencyId() != null ? item.getGlAgencyId().getGlCompanyId() : null);
            agencies = glAgencyFacade.findByGlCompanyId(getCompanySelected());

        }

        @Override
        public List<AdUser> findAll() {
            return adUserFacade.findAll();
        }

        @Override
        protected AdUser create() {
            System.out.println("create aduser");
            setPass1(null);
            setPass2(null);
            setActiveItem(new AdUser());
            getListaUsuarioRoles().load();
            return getActiveItem();
        }

        @Override
        protected AdUser save(AdUser item) {

            item = getActiveItem();

            System.out.println("save aduser: " + item + " pass1: " + getPass1() + " pass2: " + getPass2() + " image: " + Arrays.toString(item.getImage()));
            try {

                adUserFacade.save(item);
                setSelectedItem(item);
                setPass1(item.getPassword());
                setPass2(null);

                List<AdUserRoles> listaRolesActual = adUserRolesFacade.findByAdUser(item);
                List<AdUserRoles> listaRolesFinal = getListaUsuarioRoles().getValue();

                if (listaRolesActual != null && !listaRolesActual.isEmpty()) {
                    for (AdUserRoles ur : listaRolesActual) {
                        adUserRolesFacade.delete(ur);
                    }
                }

                //buscar en lista final
                if (listaRolesFinal != null && !listaRolesFinal.isEmpty()) {
                    for (AdUserRoles f : listaRolesFinal) {
                        f.setId(null);
                        adUserRolesFacade.save(f, getActiveItem());
                    }
                }

                /*
                if(  listaRolesActual != null && !listaRolesActual.isEmpty()){
                    for (AdUserRoles ur : listaRolesActual) {
                        boolean find = false;
                        
                        //buscar en lista final
                        if(  listaRolesFinal != null && !listaRolesFinal.isEmpty()){
                            for (AdUserRoles f : listaRolesFinal) {
                                if(f.getId() != null && (ur.getId().intValue() == f.getId().intValue()) ){
                                    find = true;
                                    break;
                                }else{
                                    f.setId(null);
                                    adUserRolesFacade.save(f,getActiveItem());
                                }
                                        
                            }
                        }
                        //si no se encontro se elimina
                        if( !find ){
                            adUserRolesFacade.delete(ur);
                        }
                        
                    }
                }*/
            } catch (Exception e) {
                JsfUtils.messageError(null, e.getMessage(), null);
                return null;
            }

            JsfUtils.messageInfo(null, "Usuario guardado correctamente.", null);

            return item;
        }

        @Override
        protected void cancel() {
            getListaUsuarioRoles().load();
        }

        @Override
        protected void delete(List<AdUser> items) {
            try {
                adUserFacade.delete(items.get(0));

            } catch (Exception e) {
                JsfUtils.messageError(null, e.getMessage(), null);
            }

            JsfUtils.messageInfo(null, "Usuario eliminado correctamente.", null);
        }

    };

    public DataView<AdUser> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(DataView<AdUser> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    private DataList<AdUserRoles> listaUsuarioRoles = new DataList<AdUserRoles>() {
        @Override
        protected void initialize() {

        }

        @Override
        public List<AdUserRoles> findAll() {
            return adUserRolesFacade.findByAdUser(getActiveItem());
        }

        @Override
        protected AdUserRoles rowAdd(AdUserRoles item) {
            item = new AdUserRoles();
            item.setId(BigInteger.ZERO);
            return item;
        }

        @Override
        protected void validateAddRow(AdUserRoles item) throws Exception {
            if (item.getAdRoleId() == null) {

                JsfUtils.messageError(null, "No puede estar vacio el rol", null);

                throw new Exception("Error rol nulo");
            }
        }

    };

    public DataList<AdUserRoles> getListaUsuarioRoles() {
        return listaUsuarioRoles;
    }

    public void setListaUsuarioRoles(DataList<AdUserRoles> listaUsuarioRoles) {
        this.listaUsuarioRoles = listaUsuarioRoles;
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

    public List<AdRole> getRoles() {
        return roles;
    }

    public void setRoles(List<AdRole> roles) {
        this.roles = roles;
    }

    public List<GlCompany> getCompanies() {
        return companies;
    }

    public void setCompanies(List<GlCompany> companies) {
        this.companies = companies;
    }

    public List<GlAgency> getAgencies() {
        return agencies;
    }

    public void setAgencies(List<GlAgency> agencies) {
        this.agencies = agencies;
    }

    public GlCompany getCompanySelected() {
        return companySelected;
    }

    public void setCompanySelected(GlCompany companySelected) {
        this.companySelected = companySelected;
    }

    public void onCompany() {
        System.out.println("onCompany: " + getCompanySelected());
        agencies = glAgencyFacade.findByGlCompanyId(getCompanySelected());
    }

}
