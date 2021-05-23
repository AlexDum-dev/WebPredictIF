/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.ihm.web.action;

import fr.insalyon.dasi.PredictIF.predictif.metier.Service;
import fr.insalyon.dasi.PredictIF.predictif.metier.modele.Consultation;
import fr.insalyon.dasi.PredictIF.predictif.metier.modele.Employe;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author basma
 */
public class GetElemConsultationAction extends Action {

    @Override
    public void executer(HttpServletRequest request) {
        Service service = new Service();
        Employe employeActuel = (Employe) request.getSession(true).getAttribute("user");
        Consultation consultation = (Consultation) request.getSession(true).getAttribute("consultation");
        
        request.setAttribute("consultation", consultation);
    }
    
    
}
