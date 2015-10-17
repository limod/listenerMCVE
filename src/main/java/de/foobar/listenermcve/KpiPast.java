/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.foobar.listenermcve;

import java.util.List;
import java.util.Map;
import javax.el.MethodExpression;
import javax.faces.application.Application;
import javax.faces.application.ResourceHandler;
import javax.faces.component.UICommand;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.MethodExpressionActionListener;
import javax.faces.view.facelets.FaceletContext;
import org.omnifaces.util.Components;

/**
 *
 * @author q381723
 */
public class KpiPast {

    public static final String RESOURCENAME = "kpi-past.xhtml";

    private long id;
    private String title;
    
    

    public KpiPast() {
    }

    public KpiPast(long id, String title) {
        this.id = id;
        this.title =title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

   
    public void reportClickedAction(ActionEvent actionEvent) {
        System.out.println("ActionListener kpipast");
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    

    public void render(UIComponent parent) {
        UIComponent composite = Components.includeCompositeComponent(parent, "qcockpit", KpiPast.RESOURCENAME, "past-" + this.getId());

        Map<String, Object> attributes = composite.getAttributes();
        MethodExpression listener = Components.createMethodExpression("#{statisticController.reportClickedAction}", Void.class, javax.faces.event.ActionEvent.class);
        attributes.put("listener", listener);
        attributes.put("bean", this);

    }

}
