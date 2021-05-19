/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.ihm.web.action;

import fr.insalyon.dasi.PredictIF.predictif.metier.Service;
import fr.insalyon.dasi.PredictIF.predictif.metier.modele.Medium;
import fr.insalyon.dasi.ihm.web.action.Action;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author hugol
 */
public class StatMediumAction extends Action {

    @Override
    public void executer(HttpServletRequest request) {

        Service service = new Service();

        List<Medium> Resultat = service.ListerMedium();

        if (Resultat != null) {
            request.setAttribute("status", true);
        } else {
            request.setAttribute("status", false);
        }

        request.setAttribute("statMedium", Resultat);

    }

}
