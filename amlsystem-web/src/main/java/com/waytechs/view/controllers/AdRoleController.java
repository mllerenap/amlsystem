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
import com.waytechs.model.enums.YesNo;
import com.waytechs.view.components.DataView;
import com.waytechs.view.components.DataViewType;
import com.waytechs.view.utils.JsfUtils;
import java.math.BigInteger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
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

    private DualListModel<AdAction> listaAcciones;

    @Inject
    private AdActionFacade adActionFacade;

    List<AdAction> listaAccionesConverter = new ArrayList<>();

    @PostConstruct
    public void initialize() {
        getListaRoles().load();

        this.rootMenu = new DefaultTreeNode(new AdMenu(), null);

        listaModulos = adModuleFacade.findAll();

        listaAcciones = new DualListModel<AdAction>(new ArrayList<AdAction>(), new ArrayList<AdAction>());

    }

    public void childMenuHasChildren(AdMenu menu, TreeNode parentTree) {

        List<AdMenuRole> listaMenuRoles = adMenuRoleFacade.findByAdMenuId(menu);
        menu.setAdMenuRoleList(listaMenuRoles);

        if (listaMenuRoles != null & !listaMenuRoles.isEmpty()) {
            for (AdMenuRole mr : listaMenuRoles) {
                if( activeItem != null && activeItem.getId() != null)
                if (activeItem.getId().intValue() == mr.getAdRoleId().getId().intValue()) {
                    mr.setActive(true);
                    menu.setAdMenuRole(mr);
                    break;
                }
            }
        }

        if (menu.getAdMenuRole() == null) {
            AdMenuRole mr = new AdMenuRole();
            mr.setActive(false);
            mr.setAdMenuId(menu);
            mr.setAdRoleId(activeItem != null ? activeItem : null);
            menu.setAdMenuRole(mr);
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

    public List<AdMenu> getDataMenuItem(TreeNode node) {
        List<AdMenu> result = new ArrayList<>();
        if (node.getChildCount() > 0) {
            for (TreeNode n : node.getChildren()) {
                result.addAll(getDataMenuItem(n));
            }
        }
        {
            result.add((AdMenu) node.getData());
        }
        return result;
    }

    public void cargarOpcionMenues() {
        AdMenu rtMenu = adMenuFacade.find(1L);
        rootMenu = new DefaultTreeNode(rtMenu, null);

        List<AdMenu> hijos = adMenuFacade.findByAdMenuParentId(rtMenu);
        if (hijos != null & !hijos.isEmpty()) {
            for (AdMenu c : hijos) {
                childMenuHasChildren(c, rootMenu);
            }
        }

        collapsingORexpanding(rootMenu, true);

    }

    public void cargarOpcionPermisos() {

        List<AdPermission> listaPermisosAsignados = adPermissionFacade.findByAdRoleId(activeItem);

        List<AdAction> listaAccionesTarget = new ArrayList<>();
        List<AdAction> listaAccionesSource = new ArrayList<>();

        List<AdAction> listaAccionesGlobal = adActionFacade.findAll();

        if (listaAccionesGlobal != null && !listaAccionesGlobal.isEmpty()) {
            for (AdAction a : listaAccionesGlobal) {

                boolean find = false;

                if (listaPermisosAsignados != null && !listaPermisosAsignados.isEmpty()) {
                    for (AdPermission p : listaPermisosAsignados) {
                        if (p.getAdActionId().getId().intValue() == a.getId().intValue()) {
                            find = true;
                            listaAccionesTarget.add(a);
                        }
                    }
                }

                if (!find) {
                    /*
                            AdPermission ps = new AdPermission();
                            ps.setAdRoleId(item);
                            ps.setAdActionId(a);
                            ps.setNuevo(true);
                     */
                    a.setNuevo(true);
                    listaAccionesSource.add(a);
                }

            }
        }

        listaAcciones = new DualListModel<>(listaAccionesSource, listaAccionesTarget);

        listaAccionesConverter.addAll(listaAccionesSource);
        listaAccionesConverter.addAll(listaAccionesTarget);

    }

    private DataView<AdRole> listaRoles = new DataView<AdRole>() {

        @Override
        protected void initialize() {
            setId("dvRoles");

            System.out.println("initialize DataView1 :  " + getId());

            Subject s = SecurityUtils.getSubject();

            setHasPermmissionCreate(s.isPermitted("1:7"));
            setHasPermmissionEdit(s.isPermitted("1:8"));
            setHasPermmissionDelete(s.isPermitted("1:9"));
            setHasPermmissionSave(s.isPermitted("1:10"));
            
            setFileNameTemplate("reporte_roles_template.xls");
            setFileNameResult("reporte_roles.xls");
            
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
            cargarOpcionMenues();
            cargarOpcionPermisos();
        }

        @Override
        public List<AdRole> findAll() {
            return adRoleFacade.findAll();
        }

        @Override
        protected AdRole create() {
            System.out.println("create role");
            setActiveItem(new AdRole());
            cargarOpcionMenues();
            cargarOpcionPermisos();
            return getActiveItem();
        }

        @Override
        protected AdRole save(AdRole item) {
            try {
                item = getActiveItem();
                //item.setIsactive(YesNo.SI);
                adRoleFacade.save(item);

                List<AdMenu> listaMenu = getDataMenuItem(getRootMenu());

                for (AdMenu adMenu : listaMenu) {
                    System.out.println("adMenu: " + adMenu.getName() + " - activar en rol " + item.getName() + " " + (adMenu.getAdMenuRole() != null ? adMenu.getAdMenuRole().isActive() : "n/a"));
                    if (adMenu.getAdMenuRole() != null) {

                        if (adMenu.getAdMenuRole().getId() == null) {
                            if (adMenu.getAdMenuRole().isActive()) {
                                adMenuRoleFacade.save(adMenu.getAdMenuRole());
                            }
                        } else if (!adMenu.getAdMenuRole().isActive()) {
                            adMenuRoleFacade.delete(adMenu.getAdMenuRole());
                        }

                    }

                }

                System.out.println("getListaAcciones(): " + getListaAcciones());

                List<AdAction> listaAccionesTarget = getListaAcciones().getTarget();
                List<AdAction> listaAccionesSource = getListaAcciones().getSource();

                System.out.println("listaAccionesTarget: " + listaAccionesTarget);
                System.out.println("listaAccionesSource: " + listaAccionesSource);

                for (AdAction adAction : listaAccionesTarget) {
                    System.out.println("listaAccionesTarget perm: " + adAction.getName() + " es nuevo? " + adAction.isNuevo());

                    if (adAction.isNuevo()) {

                        AdPermission p = new AdPermission();
                        p.setAdRoleId(item);
                        p.setAdActionId(adAction);
                        adPermissionFacade.save(p);
                    }

                }
                System.out.println("\n");
                for (AdAction adAction : listaAccionesSource) {
                    System.out.println("listaAccionesSource perm: " + adAction.getName() + " es nuevo? " + adAction.isNuevo());
                    if (!adAction.isNuevo()) {
                        AdPermission p = new AdPermission();
                        p.setAdRoleId(item);
                        p.setAdActionId(adAction);
                        p = adPermissionFacade.findByAdActionIdAndAdRoleId(p);
                        if (p != null) {
                            adPermissionFacade.delete(p);
                        }

                    }
                }

                appGlobal.loadFilterChainResolver();
                System.out.println("appGlobal.loadFilterChainResolver aplicado");

                rowSelected(item);
                setSelectedItem(item);
            } catch (Exception e) {
                e.printStackTrace();
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

    public DualListModel<AdAction> getListaAcciones() {
        return listaAcciones;
    }

    public void setListaAcciones(DualListModel<AdAction> listaAcciones) {
        this.listaAcciones = listaAcciones;
    }

    public List<AdAction> getListaAccionesConverter() {
        return listaAccionesConverter;
    }

    public void setListaAccionesConverter(List<AdAction> listaAccionesConverter) {
        this.listaAccionesConverter = listaAccionesConverter;
    }

    public Converter getAdActionConverter() {
        return new Converter() {
            @Override
            public Object getAsObject(FacesContext context, UIComponent component, String value) {
                AdAction objRes = null;
                if (!value.trim().equals("") && value != null) {
                    BigInteger id = new BigInteger(value);

                    if (getListaAccionesConverter() != null) {
                        for (AdAction adAction : listaAccionesConverter) {
                            if (adAction.getId().intValue() == id.intValue()) {
                                objRes = adAction;
                                break;
                            }
                        }
                    }

                }
                return objRes;
            }

            @Override
            public String getAsString(FacesContext context, UIComponent component, Object o) {
                return (o == null || o.equals("")) ? "" : ((AdAction) o).getId() + "";
            }
        };
    }

}
