/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.ihm.web.action;

import fr.insalyon.dasi.PredictIF.predictif.metier.Service;
import fr.insalyon.dasi.PredictIF.predictif.metier.modele.Utilisateur;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author alex
 */
public class ConnecterUtilisateurAction extends Action {

    @Override
    public void executer(HttpServletRequest request) {

        String login = request.getParameter("login");
        String password = request.getParameter("password");
        
        Service service = new Service();
        Utilisateur result = service.authentifierUtilisateur(login, password);
        
        //System.out.println(result.getNom());
        request.setAttribute("user", result);
        request.getSession(true).setAttribute("user", result);
      
       
    }
}
