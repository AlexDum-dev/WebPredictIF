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
import fr.insalyon.dasi.PredictIF.predictif.metier.modele.Employe;
import fr.insalyon.dasi.ihm.web.serialisation.Serialisation;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.String.valueOf;
import java.util.List;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author hugol
 */
public class StatEmployeSerialisation extends Serialisation {
    
    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {

        JsonArray jsob = new JsonArray();    
        
        List<Employe> employes = (List<Employe>) request.getAttribute("statEmploye");
        for (int i=0; i<employes.size(); i++)
        {
            
            JsonObject container = new JsonObject();
            container.addProperty("prenom", employes.get(i).getPrenom());
            container.addProperty("nom", employes.get(i).getNom());
            container.addProperty("nbConsultation", employes.get(i).getNbConsultation());
            jsob.add(container);
            //String s = "employe"+valueOf(i);
            //globalContainer.add(s, container);
        }
        
        PrintWriter out = this.getWriter(response);
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(jsob, out);
        out.close();
    }
    
}
