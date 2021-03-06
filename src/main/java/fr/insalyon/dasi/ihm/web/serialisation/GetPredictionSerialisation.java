/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.ihm.web.serialisation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author alex
 */
public class GetPredictionSerialisation extends Serialisation{

    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JsonObject container = new JsonObject();
        
        //Lecture des paramètres : 
        String predictAmour = (String) request.getAttribute("Amour");
        String predictTravail = (String) request.getAttribute("Travail");
        String predictSante = (String) request.getAttribute("Sante");
        
        container.addProperty("Amour",predictAmour);
        container.addProperty("Travail",predictTravail);
        container.addProperty("Sante",predictSante);
        
        //Formatage de la structure de données en JSON : 
        PrintWriter out = this.getWriter(response);
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();   
    }
    
}
