
package fr.insalyon.dasi.ihm.web.action;

import fr.insalyon.dasi.PredictIF.predictif.metier.Service;
import fr.insalyon.dasi.PredictIF.predictif.metier.modele.Consultation;
import fr.insalyon.dasi.PredictIF.predictif.metier.modele.Employe;
import fr.insalyon.dasi.PredictIF.predictif.metier.modele.Utilisateur;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author alex
 */
public class GetProfilEmpAction extends Action {

    @Override
    public void executer(HttpServletRequest request) {

        Service service = new Service();
        Utilisateur u = (Utilisateur) request.getSession(true).getAttribute("user");
        Employe e = (Employe) service.authentifierUtilisateur(u.getAdresseElectronique(), u.getMdp());

        Consultation consultation = null;
        consultation = service.findConsultation(e);

        if (consultation != null) {
            request.getSession(true).setAttribute("dispo", false);
            request.getSession(true).setAttribute("consultation", consultation);
        } else {

            request.getSession(true).setAttribute("dispo", true);
            request.getSession(true).setAttribute("consultation", consultation);
        }

    }

}