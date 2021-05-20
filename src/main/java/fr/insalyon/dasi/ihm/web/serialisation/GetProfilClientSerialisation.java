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
import fr.insalyon.dasi.PredictIF.predictif.metier.modele.Utilisateur;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author basma
 */
public class GetProfilClientSerialisation extends Serialisation{

    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JsonObject container = new JsonObject();
         
        Client client = (Client) request.getSession(true).getAttribute("user");
       
        container.addProperty("prenom", client.getPrenom());
        container.addProperty("nom", client.getNom());
        container.addProperty("signeZodiaque", client.getProfilAstral().getSigneZodiaque());
        container.addProperty("signeAstroChinois", client.getProfilAstral().getSigneAstroChinois());
        container.addProperty("couleurPorteBonheur", client.getProfilAstral().getCouleurPorteBonheur());
        container.addProperty("animalTotem", client.getProfilAstral().getAnimalTotem());
        
        //System.out.println(clien);
                
        //Formatage de la structure de données en JSON : 
        PrintWriter out = this.getWriter(response);
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();   
    }
    
}
