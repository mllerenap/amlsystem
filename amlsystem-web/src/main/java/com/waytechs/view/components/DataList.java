package com.waytechs.view.components;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;
import com.waytechs.model.entities.AbstractEntityModel;
import com.waytechs.view.utils.JsfUtils;
import java.util.Collection;
import java.util.Collections;
import org.primefaces.context.RequestContext;

public abstract class DataList<E extends AbstractEntityModel> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private boolean enabledRefresh;

    private boolean enabledCreate;

    private boolean enabledEdit;

    private boolean enabledSave;

    private boolean enabledDelete;

    private boolean enabledCancel;

    private boolean enabledProcess;

    private boolean enabledGenerate;

    protected List<E> value;

    protected List<E> selectItems;

    protected E newItem;

    protected List<E> modifiedItems;
    
    protected List<E> deletedItems;

    private String updateComponents;
    
    
    

    public DataList() {
        init();
    }

    public void init() {
        System.out.println("initialize DataList.. ");
        initialize();
    }

    protected abstract void initialize();

    public boolean isEnabledRefresh() {
        return enabledRefresh;
    }

    public void setEnabledRefresh(boolean enabledRefresh) {
        this.enabledRefresh = enabledRefresh;
    }

    public boolean isEnabledCreate() {
        return enabledCreate;
    }

    public void setEnabledCreate(boolean enabledCreate) {
        this.enabledCreate = enabledCreate;
    }

    public boolean isEnabledEdit() {
        return enabledEdit;
    }

    public void setEnabledEdit(boolean enabledEdit) {
        this.enabledEdit = enabledEdit;
    }

    public boolean isEnabledSave() {
        return enabledSave;
    }

    public void setEnabledSave(boolean enabledSave) {
        this.enabledSave = enabledSave;
    }

    public boolean isEnabledDelete() {
        return enabledDelete;
    }

    public void setEnabledDelete(boolean enabledDelete) {
        this.enabledDelete = enabledDelete;
    }

    public boolean isEnabledCancel() {
        return enabledCancel;
    }

    public void setEnabledCancel(boolean enabledCancel) {
        this.enabledCancel = enabledCancel;
    }

    public boolean isEnabledProcess() {
        return enabledProcess;
    }

    public void setEnabledProcess(boolean enabledProcess) {
        this.enabledProcess = enabledProcess;
    }

    public boolean isEnabledGenerate() {
        return enabledGenerate;
    }

    public void setEnabledGenerate(boolean enabledGenerate) {
        this.enabledGenerate = enabledGenerate;
    }

    public List<E> getValue() {
        return value;
    }

    public void setValue(List<E> value) {
        this.value = value;
    }

    

    public List<E> getSelectItems() {
        return selectItems;
    }

    public void setSelectItems(List<E> selectItems) {
        this.selectItems = selectItems;
    }

    /*
    public E getActiveItem() {
        return activeItem;
    }

    public void setActiveItem(E activeItem) {
        this.activeItem = activeItem;
    }*/

    public List<E> getModifiedItems() {
        return modifiedItems;
    }

    public void setModifiedItems(List<E> modifiedItems) {
        this.modifiedItems = modifiedItems;
    }

    public List<E> getDeletedItems() {
        return deletedItems;
    }

    public void setDeletedItems(List<E> deletedItems) {
        this.deletedItems = deletedItems;
    }
    
    

    public void put(E dataReg, List<E> items) {
        // lista actual de registros
        List<E> col = items;
        // buscar el registro a insertar
        int posicion = col.indexOf(dataReg);
        // (ltw) si no se encuentra el elemento, se lo agrega
        if (posicion == -1) {
            col.add(dataReg);
        } else {
            col.set(posicion, dataReg);
        }
    }

    public abstract List<E> findAll();
    
    public void load() {
        setValue(findAll());
        setDeletedItems(new ArrayList<E>());
    }

   public int getRowCountTotal() {
        return getValue()!= null ? getValue().size() : 0;
    }

    public int getRowSelectCountTotal() {
        System.out.println("getRowSelectCountTotal: " + getSelectItems());
        return getSelectItems() != null ? getSelectItems().size() : 0;
    }

    public int getRowModifiedCountTotal() {
        return getModifiedItems() != null ? getModifiedItems().size() : 0;
    }
    
     

    public void onRowDelete(E row) {
        
        System.out.println("onRowDelete: "+row.getId());
        
        for (E bean:   getValue() ){
            if(bean.getId().intValue()== row.getId().intValue()){
                getValue().remove(bean);
                getDeletedItems().add(bean);
                break;
            }
        }
        
    }

    public void onRowEditInit(E item) {
        System.out.println("onRowEditInit execute- "+item);
        
    }

    public void onRowEdit(E item) {
       System.out.println("onRowEdit execute- "+item);
       item.setChange(true);
    }

    public void onRowEditCancel(int index) {
        
    }
    
    public void onRowCancel(ActionEvent event) {    
        
        System.out.println("onRowCancel: "+getNewItem().getId());
        
        for (E bean:   getValue() ){
            if(bean.getId().intValue()== getNewItem().getId().intValue()){
                getValue().remove(bean);
                break;
            }
        }
    }
    
    protected E rowAdd(E item) {
        return item;
    }
     
    public void onRowAdd(ActionEvent event) {    
        E row = rowAdd(newItem);
        setNewItem(row);
        getValue().add(row);
        
        System.out.println("onRowAdd: " + row.getId());
    }
    
    public void onValidateAddRow(String dataTableId, E item) {   
        
        try{
            System.out.println("onValidateAddRow: " + item.getId());
            validateAddRow(item);    
            JsfUtils.currentPFContext().execute("hideTableButtonsAddRow('"+dataTableId+"');showTableButtons();showAdd();");
            JsfUtils.currentPFContext().update(  JsfUtils.findComponentById(dataTableId).getClientId());
        }catch(Exception e){
            e.printStackTrace();
        }
        
        
    }
    
    protected void validateAddRow(E item) throws Exception{
        throw new UnsupportedOperationException();
    }
    
     protected E rowCancel(E item) {
        return item;
    }

    public E getNewItem() {
        return newItem;
    }

    public void setNewItem(E newItem) {
        this.newItem = newItem;
    }

        
    

    

}
