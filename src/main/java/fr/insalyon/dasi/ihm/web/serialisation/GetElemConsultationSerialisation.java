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
import fr.insalyon.dasi.PredictIF.predictif.metier.modele.Client;
import fr.insalyon.dasi.PredictIF.predictif.metier.modele.Consultation;
import fr.insalyon.dasi.PredictIF.predictif.metier.modele.Employe;
import fr.insalyon.dasi.PredictIF.predictif.metier.modele.Medium;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author basma
 */
public class GetElemConsultationSerialisation extends Serialisation{

    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JsonObject container = new JsonObject();
        Consultation consultation = (Consultation) request.getAttribute("consultation");
        Client client = consultation.getClient();
        Employe emp = (Employe) request.getSession(true).getAttribute("user");
        List<Consultation> historique = client.getHistorique();
        
        container.addProperty("client", client.getPrenom()+' '+client.getNom());
        container.addProperty("employe",emp.getPrenom()+' '+emp.getNom());
        container.addProperty("medium", consultation.getMedium().getDenomination());
        container.addProperty("signeZodiaque", client.getProfilAstral().getSigneZodiaque());
        container.addProperty("signeAstroChinois", client.getProfilAstral().getSigneAstroChinois());
        container.addProperty("couleurPorteBonheur", client.getProfilAstral().getCouleurPorteBonheur());
        container.addProperty("animalTotem", client.getProfilAstral().getAnimalTotem());
        
        JsonArray jobj = new JsonArray();
        
        for(Consultation consult : historique)
        {
            if(consult != consultation)
            {
                JsonObject container2 = new JsonObject();
                container2.addProperty("employe", consult.getEmploye().getNom());
                container2.addProperty("medium", consult.getMedium().getDenomination());
                container2.addProperty("date", consult.getDateDebut().toString());
                container2.addProperty("commentaire", consult.getCommentaire());
                jobj.add(container2);
            }   
        }
        
        container.add("historique",jobj);
       
        PrintWriter out = this.getWriter(response);
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();
        
    }
    
}
