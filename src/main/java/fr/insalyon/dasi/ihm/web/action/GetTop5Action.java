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
        
        request.setAttribute("numero1", top.get(0));
        request.setAttribute("numero2", top.get(1));
        request.setAttribute("numero3", top.get(2));
        request.setAttribute("numero4", top.get(3));
        request.setAttribute("numero5", top.get(4));
    }
    
}
