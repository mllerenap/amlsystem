/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.waytechs.view.components;

import javax.faces.application.ResourceDependencies;
import javax.faces.component.UINamingContainer;
import javax.faces.context.FacesContext;
import org.primefaces.component.api.Widget;
import org.primefaces.component.menu.AbstractMenu;
import org.primefaces.model.menu.MenuModel;

/**
 *
 * @author mllerena
 */
@ResourceDependencies({@javax.faces.application.ResourceDependency(library="js", name="layout.js"), @javax.faces.application.ResourceDependency(library="primefaces", name="jquery/jquery.js"), @javax.faces.application.ResourceDependency(library="primefaces", name="jquery/jquery-plugins.js")})
public class OmegaMenu  extends AbstractMenu
  implements Widget
{
  public static final String COMPONENT_TYPE = "org.primefaces.component.OmegaMenu";
  public static final String COMPONENT_FAMILY = "org.primefaces.component";
  private static final String DEFAULT_RENDERER = "org.primefaces.component.OmegaMenuRenderer";
  
  protected static enum PropertyKeys
  {
    widgetVar,  model,  style,  styleClass;
    
    String toString;
    
    private PropertyKeys(String toString)
    {
      this.toString = toString;
    }
    
    private PropertyKeys() {}
    
    public String toString()
    {
      return this.toString != null ? this.toString : super.toString();
    }
  }
  
  public OmegaMenu()
  {
    setRendererType("org.primefaces.component.OmegaMenuRenderer");
  }
  
  public String getFamily()
  {
    return "org.primefaces.component";
  }
  
  public String getWidgetVar()
  {
    return (String)getStateHelper().eval(PropertyKeys.widgetVar, null);
  }
  
  public void setWidgetVar(String _widgetVar)
  {
    getStateHelper().put(PropertyKeys.widgetVar, _widgetVar);
  }
  
  public MenuModel getModel()
  {
    return (MenuModel)getStateHelper().eval(PropertyKeys.model, null);
  }
  
  public void setModel(MenuModel _model)
  {
    getStateHelper().put(PropertyKeys.model, _model);
  }
  
  public String getStyle()
  {
    return (String)getStateHelper().eval(PropertyKeys.style, null);
  }
  
  public void setStyle(String _style)
  {
    getStateHelper().put(PropertyKeys.style, _style);
  }
  
  public String getStyleClass()
  {
    return (String)getStateHelper().eval(PropertyKeys.styleClass, null);
  }
  
  public void setStyleClass(String _styleClass)
  {
    getStateHelper().put(PropertyKeys.styleClass, _styleClass);
  }
  
  public String resolveWidgetVar()
  {
      FacesContext context = getFacesContext();
    String userWidgetVar = (String)getAttributes().get("widgetVar");
    if (userWidgetVar != null) {
      return userWidgetVar;
    }
    return "widget_" + getClientId(context).replaceAll(new StringBuilder().append("-|").append(UINamingContainer.getSeparatorChar(context)).toString(), "_");
  }
}

