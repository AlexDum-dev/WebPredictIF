/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.ihm.web.serialisation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
        boolean status = (boolean) request.getAttribute("status");
        
        Client client = (Client) request.getAttribute("client");
        Employe employe = (Employe) request.getAttribute("employe");
        Medium medium = (Medium) request.getAttribute("medium");
        List<Consultation> historique = client.getHistorique();
        int tailleHist= historique.size();
        
        //container.addProperty("tailleHist", tailleHist);
        String hist= historique.get(0).toString();
        for(int i=1; i<tailleHist; i++){
            hist += historique.get(i).toString() ; 
            
        }
        container.addProperty("historique", hist);
        container.addProperty("status",status);
        container.addProperty("client", client.getPrenom()+' '+client.getNom());
        container.addProperty("employé",employe.getPrenom()+' '+employe.getNom() );
        container.addProperty("medium",medium.getDenomination() );
        container.addProperty("signeZodiaque", client.getProfilAstral().getSigneZodiaque());
        container.addProperty("signeAstroChinois", client.getProfilAstral().getSigneAstroChinois());
        container.addProperty("couleurPorteBonheur", client.getProfilAstral().getCouleurPorteBonheur());
        container.addProperty("animalTotem", client.getProfilAstral().getAnimalTotem());
        
        PrintWriter out = this.getWriter(response);
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();
        
    }
    
}
