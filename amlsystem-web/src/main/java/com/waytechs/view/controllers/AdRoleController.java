/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.waytechs.view.controllers;

import com.waytechs.model.ejb.facades.AdActionFacade;
import com.waytechs.model.ejb.facades.AdMenuFacade;
import com.waytechs.model.ejb.facades.AdMenuRoleFacade;
import com.waytechs.model.ejb.facades.AdModuleFacade;
import com.waytechs.model.ejb.facades.AdPermissionFacade;
import com.waytechs.view.beans.GlobalBean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import com.waytechs.model.ejb.facades.AdRoleFacade;
import com.waytechs.model.entities.AdAction;
import com.waytechs.model.entities.AdMenu;
import com.waytechs.model.entities.AdMenuRole;
import com.waytechs.model.entities.AdModule;
import com.waytechs.model.entities.AdPermission;
import com.waytechs.model.entities.AdRole;
import com.waytechs.view.components.DataView;
import com.waytechs.view.components.DataViewType;
import com.waytechs.view.utils.JsfUtils;
import javax.faces.event.ActionEvent;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.primefaces.event.TransferEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.DualListModel;
import org.primefaces.model.TreeNode;

/**
 *
 * @author mllerenap
 */
@ManagedBean(name = "adRole")
@ViewScoped
public class AdRoleController implements Serializable {

    @Inject
    private GlobalBean appGlobal;

    @Inject
    private AdRoleFacade adRoleFacade;

    private AdRole activeItem;

    //private MenuModel model;
    private TreeNode rootMenu;

    @Inject
    private AdMenuFacade adMenuFacade;

    @Inject
    private AdMenuRoleFacade adMenuRoleFacade;

    @Inject
    private AdModuleFacade adModuleFacade;
    private List<AdModule> listaModulos;

    @Inject
    private AdPermissionFacade adPermissionFacade;

    private DualListModel<AdPermission> listaPermisos;

    @Inject
    private AdActionFacade adActionFacade;

    @PostConstruct
    public void initialize() {
        getListaRoles().load();

        this.rootMenu = new DefaultTreeNode(new AdMenu(), null);

        listaModulos = adModuleFacade.findAll();
        
        
        listaPermisos =  new DualListModel<AdPermission>(new ArrayList<AdPermission>(), new ArrayList<AdPermission>());

    }

    public void childMenuHasChildren(AdMenu menu, TreeNode parentTree) {

        List<AdMenuRole> listaMenuRoles = adMenuRoleFacade.findByAdMenuId(menu);
        menu.setAdMenuRoleList(listaMenuRoles);

        if (listaMenuRoles != null & !listaMenuRoles.isEmpty()) {
            for (AdMenuRole mr : listaMenuRoles) {
                if (getListaRoles().getSelectedItem().getId().intValue() == mr.getAdRoleId().getId().intValue()) {
                    mr.setActive(true);
                    menu.setAdMenuRole(mr);
                    break;
                }
            }
        }

        TreeNode sub = new DefaultTreeNode(menu, parentTree);
        List<AdMenu> hijos = adMenuFacade.findByAdMenuParentId(menu);
        if (hijos != null & !hijos.isEmpty()) {
            for (AdMenu c : hijos) {
                childMenuHasChildren(c, sub);
            }
        }
    }

    public void collapsingORexpanding(TreeNode n, boolean option) {
        if (n.getChildren().size() == 0) {
            n.setSelected(false);
        } else {
            for (TreeNode s : n.getChildren()) {
                collapsingORexpanding(s, option);
            }
            n.setExpanded(option);
            n.setSelected(false);
        }
    }
    
    public void onTransfer(TransferEvent event) {
        /*
        StringBuilder builder = new StringBuilder();
        for(Object item : event.getItems()) {
            builder.append(((Theme) item).getName()).append("<br />");
        }
         
        FacesMessage msg = new FacesMessage();
        msg.setSeverity(FacesMessage.SEVERITY_INFO);
        msg.setSummary("Items Transferred");
        msg.setDetail(builder.toString());
         
        FacesContext.getCurrentInstance().addMessage(null, msg);
        */
    } 

    public void actionActualizar(ActionEvent action) {
        try {
            System.out.println("actionActualizar ...");
            appGlobal.loadFilterChainResolver();
            System.out.println("actionActualizar ... fin");
        } catch (Exception ex) {
            Logger.getLogger(AdRoleController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private DataView<AdRole> listaRoles = new DataView<AdRole>() {

        @Override
        protected void initialize() {
            setId("dvRoles");

            System.out.println("initialize DataView1 :  " + getId());

            Subject s =  SecurityUtils.getSubject();
            
            setHasPermmissionCreate(s.isPermitted("1:7"));
            setHasPermmissionEdit(s.isPermitted("1:8"));
            setHasPermmissionDelete(s.isPermitted("1:9"));
            setHasPermmissionSave(s.isPermitted("1:10"));
        }

        @Override
        public List<DataViewType> viewTypes() {
            List<DataViewType> list = new ArrayList<>();
            list.add(DataViewType.TABLE);
            list.add(DataViewType.ROW);
            return list;
        }

        @Override
        protected void rowSelected(AdRole item) {
            setActiveItem(item);

            AdMenu rtMenu = adMenuFacade.find(1L);
            rootMenu = new DefaultTreeNode(rtMenu, null);

            List<AdMenu> hijos = adMenuFacade.findByAdMenuParentId(rtMenu);
            if (hijos != null & !hijos.isEmpty()) {
                for (AdMenu c : hijos) {
                    childMenuHasChildren(c, rootMenu);
                }
            }

            collapsingORexpanding(rootMenu, true);

            /*
            private List<AdPermission> listaPermisosSource;
            private List<AdPermission> listaPermisosTarget;
             */
            List<AdPermission> listaPermisosTarget = adPermissionFacade.findByAdRoleId(item);

            List<AdPermission> listaPermisosSource = new ArrayList<AdPermission>();

            List<AdAction> listaAcciones = adActionFacade.findAll();
            //List<AdAction> listaAcciones = adActionFacade.findAll();

            if (listaAcciones != null && !listaAcciones.isEmpty()) {
                for (AdAction a : listaAcciones) {
                    
                    boolean find = false;
                    
                    if (listaPermisosTarget != null && !listaPermisosTarget.isEmpty()) {
                        for (AdPermission p : listaPermisosTarget) {
                            if( p.getAdActionId().getId().intValue() == a.getId().intValue() ){
                                find = true;
                            }
                        }
                    }

                    if (!find) {
                            AdPermission ps = new AdPermission();
                            ps.setAdRoleId(item);
                            ps.setAdActionId(a);
                            ps.setNuevo(true);
                            listaPermisosSource.add(ps);
                    }

                }
            }

           listaPermisos =  new DualListModel<AdPermission>(listaPermisosSource, listaPermisosTarget);

        }

        @Override
        public List<AdRole> findAll() {
            return adRoleFacade.findAll();
        }

        @Override
        protected AdRole create() {
            System.out.println("create role");
            setActiveItem(new AdRole());
            return getActiveItem();
        }

        @Override
        protected AdRole save(AdRole item) {
            try {
                //adRoleFacade.save(item);
                
                System.out.println("getRootMenu().getData(): "+getRootMenu());
                
                setSelectedItem(item);
            } catch (Exception e) {
                JsfUtils.messageError(null, e.getMessage(), null);
                return null;
            }

            JsfUtils.messageInfo(null, "Rol guardado correctamente.", null);

            return item;
        }

        @Override
        protected void cancel() {
            //getListaUsuarioRoles().load();
        }

        @Override
        protected void delete(List<AdRole> items) {
            try {
                //adUserFacade.delete(items.get(0));
            } catch (Exception e) {
                JsfUtils.messageError(null, e.getMessage(), null);
            }

            JsfUtils.messageInfo(null, "Usuario eliminado correctamente.", null);
        }

    };

    public DataView<AdRole> getListaRoles() {
        return listaRoles;
    }

    public void setListaRoles(DataView<AdRole> listaRoles) {
        this.listaRoles = listaRoles;
    }

    public AdRole getActiveItem() {
        return activeItem;
    }

    public void setActiveItem(AdRole activeItem) {
        this.activeItem = activeItem;
    }

    public TreeNode getRootMenu() {
        return rootMenu; 
    }

    

    public List<AdModule> getListaModulos() {
        return listaModulos;
    }

    public void setListaModulos(List<AdModule> listaModulos) {
        this.listaModulos = listaModulos;
    }

    public DualListModel<AdPermission> getListaPermisos() {
        return listaPermisos;
    }

    public void setListaPermisos(DualListModel<AdPermission> listaPermisos) {
        this.listaPermisos = listaPermisos;
    }

}
