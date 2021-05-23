/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.ihm.web.action;

import fr.insalyon.dasi.PredictIF.predictif.metier.Service;
import fr.insalyon.dasi.PredictIF.predictif.metier.modele.Client;
import fr.insalyon.dasi.PredictIF.predictif.metier.modele.Consultation;
import fr.insalyon.dasi.PredictIF.predictif.metier.modele.Medium;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author hugol
 */
public class DemanderConsultationAction extends Action{
    
    @Override
    public void executer(HttpServletRequest request) {

        Service service = new Service();
        String denomination = request.getParameter("denomination");
        Client client = (Client) request.getSession(true).getAttribute("user");
        Consultation consultation = new Consultation();
        
        consultation = service.demanderConsultation(client, service.getMedium(denomination));
        
        if (consultation != null)
        {
            request.setAttribute("status", true);
        }
        else
        {
            System.out.println("consultation null");
            request.setAttribute("status", false);
        }
        
        
        request.getSession(true).setAttribute("consultation", consultation);
        
    }
}
