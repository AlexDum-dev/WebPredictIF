/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.ihm.web.action;

import fr.insalyon.dasi.PredictIF.predictif.metier.Service;
import fr.insalyon.dasi.PredictIF.predictif.metier.modele.Consultation;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author alex
 */
public class FinirConsultationEmployeAction extends Action {

    @Override
    public void executer(HttpServletRequest request) {
        Service service = new Service();
        
        String commentaire = request.getParameter("commentaire");
        Consultation consultation = (Consultation) request.getSession(true).getAttribute("consultation");
        
        if(service.finirConsultation(consultation, commentaire) != null)
        {
            request.setAttribute("status", true);
        } else {
            request.setAttribute("status", false);
        }
        
        //On enlève l'attribut consultation de la session : 
        request.getSession(true).removeAttribute("consultation");
    }
    
    
    
}
