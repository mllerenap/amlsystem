/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.waytechs.view.components;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.faces.FacesException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import org.primefaces.component.api.AjaxSource;
import org.primefaces.component.api.UIOutcomeTarget;
import org.primefaces.component.menu.AbstractMenu;
import org.primefaces.component.menu.BaseMenuRenderer;
import org.primefaces.model.menu.MenuElement;
import org.primefaces.model.menu.MenuItem;
import org.primefaces.model.menu.Separator;
import org.primefaces.model.menu.Submenu;
import org.primefaces.util.ComponentUtils;
import org.primefaces.util.WidgetBuilder;

/**
 *
 * @author mllerena
 */
public class OmegaMenuRenderer extends BaseMenuRenderer {

    protected void encodeMarkup(FacesContext context, AbstractMenu abstractMenu)
            throws IOException {
        OmegaMenu menu = (OmegaMenu) abstractMenu;
        ResponseWriter writer = context.getResponseWriter();
        String style = menu.getStyle();
        String styleClass = menu.getStyleClass() != null ? menu.getStyleClass() : "";
        styleClass = "menu-container " + styleClass;
        String clientId = menu.getClientId(context);

        writer.startElement("ul", menu);
        writer.writeAttribute("id", clientId, "id");
        writer.writeAttribute("class", styleClass, "styleClass");
        if (style != null) {
            writer.writeAttribute("style", style, "style");
        }
        if (menu.getElementsCount() > 0) {
            encodeElements(context, menu, menu.getElements(), 0);
        }
        writer.endElement("ul");
    }

    protected void encodeElements(FacesContext context, AbstractMenu menu, List<MenuElement> elements, int marginLevel)
            throws IOException {
        int size = elements.size();
        for (int i = 0; i < size; i++) {
            encodeElement(context, menu, (MenuElement) elements.get(i), ++marginLevel);
        }
    }

    protected void encodeElement(FacesContext context, AbstractMenu menu, MenuElement element, int marginLevel)
            throws IOException {
        ResponseWriter writer = context.getResponseWriter();
        if (element.isRendered()) {
            if ((element instanceof MenuItem)) {
                MenuItem menuItem = (MenuItem) element;
                String menuItemClientId = menu.getClientId(context) + "_" + menuItem.getClientId();
                String containerStyle = menuItem.getContainerStyle();
                String containerStyleClass = menuItem.getContainerStyleClass();

                writer.startElement("li", null);
                writer.writeAttribute("id", menuItemClientId, null);
                writer.writeAttribute("role", "menuitem", null);
                if (containerStyle != null) {
                    writer.writeAttribute("style", containerStyle, null);
                }
                if (containerStyleClass != null) {
                    writer.writeAttribute("class", containerStyleClass, null);
                }
                encodeMenuItem(context, menu, menuItem);

                writer.endElement("li");
            } else if ((element instanceof Submenu)) {
                Submenu submenu = (Submenu) element;
                String submenuClientId = menu.getClientId(context) + "_" + submenu.getId();
                String style = submenu.getStyle();
                String styleClass = submenu.getStyleClass();

                writer.startElement("li", null);
                writer.writeAttribute("id", submenuClientId, null);
                writer.writeAttribute("role", "menuitem", null);
                if (style != null) {
                    writer.writeAttribute("style", style, null);
                }
                if (styleClass != null) {
                    writer.writeAttribute("class", styleClass, null);
                }
                encodeSubmenu(context, menu, submenu, marginLevel);

                writer.endElement("li");
            } else if ((element instanceof Separator)) {
                encodeSeparator(context, (Separator) element);
            }
        }
    }

    protected void encodeSubmenu(FacesContext context, AbstractMenu menu, Submenu submenu, int marginLevel)
            throws IOException {
        ResponseWriter writer = context.getResponseWriter();
        String icon = submenu.getIcon();
        String label = submenu.getLabel();

        writer.startElement("a", null);
        writer.writeAttribute("href", "#", null);
        //writer.writeAttribute("class", "menulink ripplelink", null);

        /*
        writer.startElement("i", null);
        writer.writeAttribute("class", "icon-null", null);
        writer.endElement("i");
        */
        
        if (icon != null) {
            writer.startElement("i", null);
            writer.writeAttribute("class", "fa-fw "+icon, null);
            writer.endElement("i");
        }
        if (label != null) {
            
            writer.startElement("span", null);
            writer.writeText(" " + label, null);
            writer.endElement("span");
        }
        if (marginLevel > 0) {
            writer.startElement("i", null);
            writer.writeAttribute("class", "fa fa-fw fa-angle-down", null);
            writer.endElement("i");
        }
        writer.endElement("a");
        if (submenu.getElementsCount() > 0) {
            writer.startElement("ul", null);
            /*
            if (marginLevel > 0) {
                writer.writeAttribute("class", "menu-level-" + marginLevel, label);
            }
            */
            writer.writeAttribute("role", "menu", null);
            encodeElements(context, menu, submenu.getElements(), ++marginLevel);
            writer.endElement("ul");
        }
    }

    protected void encodeSeparator(FacesContext context, Separator separator)
            throws IOException {
        ResponseWriter writer = context.getResponseWriter();
        String style = separator.getStyle();
        String styleClass = separator.getStyleClass();
        styleClass = "Separator " + styleClass;

        writer.startElement("li", null);
        writer.writeAttribute("class", styleClass, null);
        if (style != null) {
            writer.writeAttribute("style", style, null);
        }
        writer.endElement("li");
    }

    protected void encodeMenuItem(FacesContext context, AbstractMenu menu, MenuItem menuitem)
            throws IOException {
        ResponseWriter writer = context.getResponseWriter();
        String title = menuitem.getTitle();
        boolean disabled = menuitem.isDisabled();
        String style = menuitem.getStyle();
        //String defaultStyleClass = "menulink ripplelink";
        String styleClass = menuitem.getStyleClass();
        //styleClass = defaultStyleClass + " " + styleClass;

        writer.startElement("a", null);
        if (title != null) {
            writer.writeAttribute("title", title, null);
        }
        if (style != null) {
            writer.writeAttribute("style", style, null);
        }
        writer.writeAttribute("class", styleClass, null);
        if (disabled) {
            writer.writeAttribute("href", "#", null);
            writer.writeAttribute("onclick", "return false;", null);
        } else {
            String onclick = menuitem.getOnclick();
            if ((menuitem.getUrl() != null) || (menuitem.getOutcome() != null)) {
                String targetURL = getTargetURL(context, (UIOutcomeTarget) menuitem);
                writer.writeAttribute("href", targetURL, null);
                if (menuitem.getTarget() != null) {
                    writer.writeAttribute("target", menuitem.getTarget(), null);
                }
            } else {
                writer.writeAttribute("href", "#", null);

                UIComponent form = ComponentUtils.findParentForm(context, menu);
                if (form == null) {
                    throw new FacesException("MenuItem must be inside a form element");
                }
                String command;
                if (menuitem.isDynamic()) {
                    String menuClientId = menu.getClientId(context);
                    Map<String, List<String>> params = menuitem.getParams();
                    if (params == null) {
                        params = new LinkedHashMap<>();
                    }
                    List<String> idParams = new ArrayList<>();
                    idParams.add(menuitem.getId());
                    params.put(menuClientId + "_menuid", idParams);

                    command = menuitem.isAjax() ? buildAjaxRequest(context, menu, (AjaxSource) menuitem, form, params) : buildNonAjaxRequest(context, menu, form, menuClientId, params, true);
                } else {
                    command = menuitem.isAjax() ? buildAjaxRequest(context, (AjaxSource) menuitem, form) : buildNonAjaxRequest(context, (UIComponent) menuitem, form, ((UIComponent) menuitem).getClientId(context), true);
                }
                onclick = onclick + ";" + command;
            }
            if (onclick != null) {
                writer.writeAttribute("onclick", onclick, null);
            }
        }
        encodeMenuItemContent(context, menu, menuitem);

        writer.endElement("a");
    }

    protected void encodeMenuItemContent(FacesContext context, AbstractMenu menu, MenuItem menuitem)
            throws IOException {
        ResponseWriter writer = context.getResponseWriter();
        String icon = menuitem.getIcon();
        Object value = menuitem.getValue();
        
        /*
        writer.startElement("i", null);
        writer.writeAttribute("class", "icon-null", null);
        writer.endElement("i");
        */
        if (icon != null) {
            writer.startElement("i", null);
            writer.writeAttribute("class", "fa-fw "+icon, null);
            writer.endElement("i");
        }
        if (value != null) {    
            writer.startElement("span", null);
            writer.writeText(" " + value, null);
            writer.endElement("span");
        }
        /*
        if (value != null) {
            writer.writeText(value, "value");
        }*/
    }

    protected void encodeScript(FacesContext context, AbstractMenu abstractMenu)
            throws IOException {
        
        OmegaMenu menu = (OmegaMenu) abstractMenu;
        
        String clientId = menu.getClientId();
        String widgetVar = menu.resolveWidgetVar();
        WidgetBuilder wb = getWidgetBuilder(context);
        wb.init("Omega", widgetVar, clientId);
        wb.finish();
        
        /*
        
        ResponseWriter writer = context.getResponseWriter();
        writer.startElement("script", null);
        writer.writeAttribute("type", "text/javascript", null);
        writer.write("Omega.restoreMenuState();");
        writer.endElement("script");
        */
        
        
        
    }
}
