/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.ihm.web.serialisation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import fr.insalyon.dasi.PredictIF.predictif.metier.modele.Medium;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author hugol
 */
public class StatMediumSerialisation extends Serialisation {

    public StatMediumSerialisation() {
    }

    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        JsonArray jobj = new JsonArray();
        //JsonObject globalContainer = new JsonObject();
        
        List<Medium> mediums = (List<Medium>) request.getAttribute("statMedium");
        
        
        for (int i=0; i<mediums.size(); i++)
        {
            JsonObject container = new JsonObject();
            container.addProperty("denomination", mediums.get(i).getDenomination());
            container.addProperty("presentation", mediums.get(i).getPresentation());
            container.addProperty("nbConsultation", mediums.get(i).getNombreConsultation());
            jobj.add(container);
            //String s = "medium"+valueOf(i);
            //globalContainer.add(s, container);
        }
       
        PrintWriter out = this.getWriter(response);
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(jobj, out);
        out.close();
    }
    
}
