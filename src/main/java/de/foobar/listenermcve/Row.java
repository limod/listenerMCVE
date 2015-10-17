/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.foobar.listenermcve;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.el.MethodExpression;
import javax.faces.FacesException;
import javax.faces.application.Application;
import javax.faces.application.Resource;
import javax.faces.application.ResourceHandler;
import javax.faces.component.UIComponent;
import javax.faces.component.UIPanel;
import javax.faces.context.FacesContext;
import javax.faces.view.facelets.FaceletContext;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.omnifaces.util.Components;

public class Row {

    public static final String RESOURCENAME = "row.xhtml";
    private long id;

    List<KpiPast> elements;

    public Row() {
    }

    public Row(long id) {
        this.setId(id);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void addContent(KpiPast item) {
        if (this.elements == null) {
            elements = new ArrayList<KpiPast>();
        }
        elements.add(item);

    }

    /*
    With this render method using ominfaces everything works!
    
    */
//    public void render(UIComponent parent) {
//        UIComponent composite = Components.includeCompositeComponent(parent, "qcockpit", Row.RESOURCENAME,"row-" +this.getId());
//         for (KpiPast item : this.elements) {
//            item.render(composite);
//        }
//    }
    
    /*
    With this render method the listener genereated in KpiPast renders method are not working.
    */
    public void render(UIComponent parent) {
//        UIComponent composite = Components.includeCompositeComponent(parent, "qcockpit", Row.RESOURCENAME,"row-" +this.getId());

        // Prepare.
        FacesContext context = FacesContext.getCurrentInstance();
        Application application = context.getApplication();
        FaceletContext faceletContext = (FaceletContext) context.getAttributes().get(FaceletContext.FACELET_CONTEXT_KEY);

        // This basically creates <ui:component> based on <composite:interface>.
        Resource resource = application.getResourceHandler().createResource( Row.RESOURCENAME,"qcockpit");
        UIComponent composite = application.createComponent(context, resource);

        // This basically creates <composite:implementation>.
        UIComponent implementation = application.createComponent(UIPanel.COMPONENT_TYPE);
        implementation.setRendererType("javax.faces.Group");
        composite.getFacets().put(UIComponent.COMPOSITE_FACET_NAME, implementation);

        // Now include the composite component file in the given parent.
        parent.getChildren().add(composite);
        parent.pushComponentToEL(context, composite); // This makes #{cc} available.
        try {
            faceletContext.includeFacelet(implementation, resource.getURL());
        } catch (IOException e) {
            throw new FacesException(e);
        } finally {
            parent.popComponentFromEL(context);
        }

        // Render children
        for (KpiPast item : this.elements) {
            item.render(composite);
        }
    }

}
