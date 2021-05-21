/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.ihm.web.action;

import fr.insalyon.dasi.PredictIF.predictif.metier.Service;
import fr.insalyon.dasi.PredictIF.predictif.metier.modele.Medium;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author alex
 */
public class GetTop5Action extends Action{

    @Override
    public void executer(HttpServletRequest request) {
        Service service = new Service();
        
        List<Medium> top = service.top5();
        
        request.setAttribute("listeTop5", top);
        
    }
    
}
