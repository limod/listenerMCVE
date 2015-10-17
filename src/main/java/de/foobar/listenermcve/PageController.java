/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.foobar.listenermcve;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.FacesException;
import javax.faces.application.Application;
import javax.faces.application.Resource;
import javax.faces.component.UIComponent;
import javax.faces.component.UIPanel;
import javax.faces.component.UIViewRoot;
import javax.faces.component.visit.VisitCallback;
import javax.faces.component.visit.VisitContext;
import javax.faces.component.visit.VisitResult;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.faces.view.facelets.FaceletContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author q381723
 */
@Named
@RequestScoped
public class PageController {

    private KpiPast kpipast;

    private List<KpiPast> itemlist = new ArrayList<KpiPast>();
    private List<Row> rowlist = new ArrayList<Row>();

    @PostConstruct
    public void init() {
        System.out.println("init");
        
        this.kpipast = new KpiPast(1l, "f13");
        this.kpipast.setId(233l);

        Row r1 = new Row(1l);
        
        KpiPast kpi1 = new KpiPast(2l, "f01");
        KpiPast kpi2 = new KpiPast(3l, "f02");

        r1.addContent(kpi1);
        r1.addContent(kpi2);
        
        rowlist.add(r1);
        
        itemlist.add(kpi1);
        itemlist.add(kpi2);
//       
    }

    public PageController() {
    }

    public KpiPast getKpipast() {
        return kpipast;
    }

    public void setKpipast(KpiPast kpipast) {
        this.kpipast = kpipast;
    }

    public void build(ComponentSystemEvent event) {
        UIComponent parent = event.getComponent();
//        for (KpiPast item : itemlist) {
//            item.render(parent);
//        }
        for (Row r : rowlist) {
            r.render(parent);
        }
    }

}
