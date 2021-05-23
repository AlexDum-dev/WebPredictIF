/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.ihm.web.action;

import fr.insalyon.dasi.PredictIF.predictif.metier.Service;
import fr.insalyon.dasi.PredictIF.predictif.metier.modele.Consultation;
import fr.insalyon.dasi.PredictIF.predictif.metier.modele.Employe;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author alex
 */
public class GetPredictionAction extends Action{

    @Override
    public void executer(HttpServletRequest request) {
        Service service = new Service();
        
        Consultation consultation = (Consultation) request.getSession(true).getAttribute("consultation");
        String couleur = consultation.getClient().getProfilAstral().getCouleurPorteBonheur();
        String animal = consultation.getClient().getProfilAstral().getAnimalTotem();
        
        int noteAmour = Integer.parseInt(request.getParameter("noteAmour"));
        int noteTravail = Integer.parseInt(request.getParameter("noteTravail"));
        int noteSante = Integer.parseInt(request.getParameter("noteSante"));
        System.out.println(couleur+animal+noteAmour+noteTravail+noteSante);
        List <String> predictions;
        try {
            System.out.println(couleur+animal+noteAmour+noteTravail+noteSante);
            predictions = service.getPredictions(couleur, animal, noteAmour, noteTravail, noteSante);
            request.setAttribute("Amour", predictions.get(0));
            request.setAttribute("Travail", predictions.get(1));
            request.setAttribute("Sante", predictions.get(2));
        } catch (IOException ex) {
            Logger.getLogger(GetPredictionAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
        
    }
    
}
