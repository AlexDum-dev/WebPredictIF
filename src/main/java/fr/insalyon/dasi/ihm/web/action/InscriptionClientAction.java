/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.ihm.web.action;

import fr.insalyon.dasi.PredictIF.predictif.metier.Service;
import fr.insalyon.dasi.PredictIF.predictif.metier.modele.Client;
import fr.insalyon.dasi.PredictIF.predictif.metier.modele.Utilisateur;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author hugol
 */
public class InscriptionClientAction extends Action {
    
    @Override
    public void executer(HttpServletRequest request) {

        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String dateNaissanceString = request.getParameter("dateNaissance");
        Date dateNaissance = new Date();
        String numeroTel = request.getParameter("numeroTel");
        String mail = request.getParameter("mail");
        String password = request.getParameter("password");
        String adresse = request.getParameter("adresse");
        
        SimpleDateFormat DateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            dateNaissance = DateFormat.parse(dateNaissanceString);
        } catch (ParseException ex) {
            Logger.getLogger(InscriptionClientAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Client client = null;
        
        try {
            client = new Client(nom,prenom,numeroTel,password,mail,dateNaissance,adresse);
        } catch (ParseException ex) {
            Logger.getLogger(InscriptionClientAction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(InscriptionClientAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Service service = new Service();
        
        client = service.inscriptionClient(client);
        
        if (client != null)
        {
            request.setAttribute("status", true);
        }
        else
        {
            request.setAttribute("status", false);
        }
        
        request.setAttribute("user", client);
        request.getSession(true).setAttribute("user", client);
       
    }
}
