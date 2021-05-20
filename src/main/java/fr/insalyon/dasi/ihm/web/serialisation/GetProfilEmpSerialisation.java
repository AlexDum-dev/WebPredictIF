
package fr.insalyon.dasi.ihm.web.serialisation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import fr.insalyon.dasi.PredictIF.predictif.metier.modele.Consultation;
import fr.insalyon.dasi.PredictIF.predictif.metier.modele.Utilisateur;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author alex
 */
public class GetProfilEmpSerialisation extends Serialisation {

    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JsonObject container = new JsonObject();

        Utilisateur user = (Utilisateur) request.getSession(true).getAttribute("user");
        Consultation consultation = null;
        consultation = (Consultation) request.getSession(true).getAttribute("consultation");
        
        System.out.println("************"+user.getPrenom());
        container.addProperty("prenom", user.getPrenom());
        container.addProperty("nom", user.getNom());
        Boolean dispo = (Boolean) request.getSession(true).getAttribute("dispo");
        
        if (!dispo){
            container.addProperty("consultation", true);
            System.out.println("Il y a une consultation");
        } else {
            container.addProperty("consultation", false);
        }
            

        //Formatage de la structure de données en JSON : 
        PrintWriter out = this.getWriter(response);
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();
    }

}