/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.ihm.web.serialisation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import fr.insalyon.dasi.PredictIF.predictif.metier.modele.Medium;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author alex
 */
public class GetTop5Serialisation extends Serialisation {

    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JsonObject container = new JsonObject();
        
        Medium medium1 = (Medium) request.getAttribute("numero1");
        Medium medium2 = (Medium) request.getAttribute("numero2");
        Medium medium3 = (Medium) request.getAttribute("numero3");
        Medium medium4 = (Medium) request.getAttribute("numero4");
        Medium medium5 = (Medium) request.getAttribute("numero5");
        
        container.addProperty("numero1", medium1.getDenomination());
        container.addProperty("numero2", medium2.getDenomination());
        container.addProperty("numero3", medium3.getDenomination());
        container.addProperty("numero4", medium4.getDenomination());
        container.addProperty("numero5", medium5.getDenomination());
        
        PrintWriter out = this.getWriter(response);
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container,out);
        out.close();
    }
    
}
