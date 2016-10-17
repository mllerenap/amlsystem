/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.waytechs.view.controllers;

import com.waytechs.model.ejb.facades.AdMenuFacade;
import com.waytechs.model.ejb.facades.AdMenuRoleFacade;
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
import com.waytechs.model.entities.AdMenu;
import com.waytechs.model.entities.AdMenuRole;
import com.waytechs.model.entities.AdRole;
import com.waytechs.view.components.DataView;
import com.waytechs.view.components.DataViewType;
import com.waytechs.view.utils.JsfUtils;
import javax.faces.event.ActionEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

/**
 *
 * @author mllerenap
 */
@ManagedBean(name = "adRole")
@ViewScoped
public class AdRoleController implements Serializable {

    @Inject private GlobalBean appGlobal;
    
    
    @Inject
    private AdRoleFacade adRoleFacade;
    
    
    private AdRole activeItem;
     
    //private MenuModel model;
    private TreeNode root;
    
     
    @Inject
    private AdMenuFacade adMenuFacade;
    
    @Inject
    private AdMenuRoleFacade adMenuRoleFacade;

    @PostConstruct
    public void initialize() {
        getListaRoles().load();
        
        root = new DefaultTreeNode(new AdMenu(), null);
        
    }
    
    public void childMenuHasChildren(AdMenu menu, TreeNode parentTree){
        
        List<AdMenuRole> listaMenuRoles = adMenuRoleFacade.findByAdMenuId(menu);
        menu.setAdMenuRoleList(listaMenuRoles);
        
        if( listaMenuRoles != null & !listaMenuRoles.isEmpty() ){
            for (AdMenuRole mr : listaMenuRoles) {
                 if( getListaRoles().getSelectedItem().getId().intValue() == mr.getAdRoleId().getId().intValue() ){
                            mr.setActive(true);
                            menu.setAdMenuRole(mr);
                            break;
                 }
            }
        }
        
        TreeNode sub = new DefaultTreeNode(menu, parentTree);
        List<AdMenu> hijos = adMenuFacade.findByAdMenuParentId(menu);    
        if( hijos != null & !hijos.isEmpty() ){
            for (AdMenu c : hijos) {
                childMenuHasChildren(c,sub);
            }
        }
    }
   
    public void actionActualizar(ActionEvent action){
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
            
            System.out.println("initialize DataView: "+getId());
            
            /*
            Subject s =  SecurityUtils.getSubject();
            
            setHasPermmissionCreate(s.isPermitted("1:1"));
            setHasPermmissionEdit(s.isPermitted("1:2"));
            setHasPermmissionDelete(s.isPermitted("1:3"));
            setHasPermmissionSave(s.isPermitted("1:4"));
            */
            
        }
        
        
         @Override
        public List<DataViewType> viewTypes() { 
            List<DataViewType> list =  new ArrayList<>();
            list.add(DataViewType.TABLE);
            list.add(DataViewType.ROW);
            return list;
        }

        @Override
        protected void rowSelected(AdRole item) {
            setActiveItem(item);
            
            AdMenu rootMenu = adMenuFacade.find(1L);
            root = new DefaultTreeNode(rootMenu, null);
            
            List<AdMenu> hijos = adMenuFacade.findByAdMenuParentId(rootMenu);    
            if( hijos != null & !hijos.isEmpty() ){
                for (AdMenu c : hijos) {
                    childMenuHasChildren(c,root);
                }
            }
            
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
                setSelectedItem(item);
            } catch (Exception e) {
                JsfUtils.messageError(null, e.getMessage(), null);
                return null;
            }

            JsfUtils.messageInfo(null, "Usuario guardado correctamente.", null);

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

   public TreeNode getRoot() {
        return root;
    }
    
    
    
    

}
