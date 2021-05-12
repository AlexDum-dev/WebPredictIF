/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.ihm.web.serialisation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import fr.insalyon.dasi.PredictIF.predictif.metier.Service;
import fr.insalyon.dasi.PredictIF.predictif.metier.modele.Client;
import fr.insalyon.dasi.PredictIF.predictif.metier.modele.Employe;
import fr.insalyon.dasi.PredictIF.predictif.metier.modele.Utilisateur;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author alex
 */
public class ConnecterUtilisateurSerialisation extends Serialisation {

    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Utilisateur result = (Utilisateur) request.getAttribute("user");

        JsonObject globalContainer = new JsonObject();
        JsonObject container = new JsonObject();
        if (result != null) {
            globalContainer.addProperty("connexion", true);
            if (result instanceof Client) {
                globalContainer.addProperty("type", "client");
            }

            if (result instanceof Employe) {
                globalContainer.addProperty("type", "employe");
            }

            container.addProperty("id", result.getId());
            container.addProperty("nom", result.getNom());
            container.addProperty("prenom", result.getPrenom());
            container.addProperty("mail", result.getAdresseElectronique());

            globalContainer.add("client", container);

        } else {
            globalContainer.addProperty("status", false);
        }

        PrintWriter out = response.getWriter();
        response.setContentType("application/json;charset=UTF-8");
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(globalContainer, out);
        out.close();

    }

}
