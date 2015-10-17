/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.foobar.listenermcve;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Named;

/**
 *
 * @author q381723
 */
@Named
@RequestScoped
public class StatisticController implements Serializable{

    public StatisticController() {
    }

    public void reportClickedAction(ActionEvent actionEvent) {
        System.out.println("ActionListener");
    }
    
    public void submitAction() {
        System.out.println("submitAction");
    }
    
}
