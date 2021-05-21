/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.ihm.web;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import fr.insalyon.dasi.PredictIF.predictif.dao.JpaUtil;
import fr.insalyon.dasi.PredictIF.predictif.metier.Service;
import fr.insalyon.dasi.PredictIF.predictif.metier.modele.Utilisateur;
import fr.insalyon.dasi.ihm.web.action.Action;
import fr.insalyon.dasi.ihm.web.action.ConnecterUtilisateurAction;
import fr.insalyon.dasi.ihm.web.action.DemanderConsultationAction;
import fr.insalyon.dasi.ihm.web.action.FinirConsultationEmployeAction;
import fr.insalyon.dasi.ihm.web.action.GetPredictionAction;
import fr.insalyon.dasi.ihm.web.action.GetProfilClientAction;
import fr.insalyon.dasi.ihm.web.action.GetProfilEmpAction;
import fr.insalyon.dasi.ihm.web.action.GetTop5Action;
import fr.insalyon.dasi.ihm.web.action.InscriptionClientAction;
import fr.insalyon.dasi.ihm.web.action.StatEmployeAction;
import fr.insalyon.dasi.ihm.web.action.StatMediumAction;
import fr.insalyon.dasi.ihm.web.serialisation.ConnecterUtilisateurSerialisation;
import fr.insalyon.dasi.ihm.web.serialisation.DemanderConsultationSerialisation;
import fr.insalyon.dasi.ihm.web.serialisation.FinirConsultationEmployeSerialisation;
import fr.insalyon.dasi.ihm.web.serialisation.GetPredictionSerialisation;
import fr.insalyon.dasi.ihm.web.serialisation.GetProfilClientSerialisation;
import fr.insalyon.dasi.ihm.web.serialisation.GetProfilEmpSerialisation;
import fr.insalyon.dasi.ihm.web.serialisation.GetTop5Serialisation;
import fr.insalyon.dasi.ihm.web.serialisation.InscriptionClientSerialisation;
import fr.insalyon.dasi.ihm.web.serialisation.Serialisation;
import fr.insalyon.dasi.ihm.web.serialisation.StatEmployeSerialisation;
import fr.insalyon.dasi.ihm.web.serialisation.StatMediumSerialisation;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author alex
 */
@WebServlet(name = "ActionServlet", urlPatterns = {"/ActionServlet"})
public class ActionServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //response.setContentType("text/html;charset=UTF-8");

        //TODO : faire le switch + gérer la session
        request.getSession(true);
        request.setCharacterEncoding("UTF-8");
        String todo = request.getParameter("todo");
        System.out.println(todo);
        Action action = null;
        Serialisation serialisation = null;
        
        switch(todo){
            case "connecter":{
                System.out.println("Connecter");
                action = new ConnecterUtilisateurAction();
                serialisation = new ConnecterUtilisateurSerialisation();
            }
            break;
            
            case "inscription":{
                action = new InscriptionClientAction();
                serialisation = new InscriptionClientSerialisation();
            }
            break;
            
            case "demanderConsult":{
                
                action = new DemanderConsultationAction();
                serialisation = new DemanderConsultationSerialisation();
            }
            break;
            
            case "finirConsultation":{
                action = new FinirConsultationEmployeAction();
                serialisation = new FinirConsultationEmployeSerialisation();
            }
            break;
            
            case "getPrediction":{
                action = new GetPredictionAction();
                serialisation = new GetPredictionSerialisation();
            }
            break;
            
            case "getTop5":{
                action = new GetTop5Action();
                serialisation = new GetTop5Serialisation();
            }
            break;
            
            case "profilEmp":{
                action = new GetProfilEmpAction();
                serialisation = new GetProfilEmpSerialisation();
            }
            break;
            
            case "profilClient":{
                action = new GetProfilClientAction();
                serialisation = new GetProfilClientSerialisation();
            }
            break;
	
	    case "statsEmploye":{
                action = new StatEmployeAction();
                serialisation = new StatEmployeSerialisation();
            }
            break;
            
            case "statsMedium":{
                action = new StatMediumAction();
                serialisation = new StatMediumSerialisation();
            }
            break;
        }
        
        action.executer(request);
        serialisation.serialiser(request, response);
        
    }

    @Override
    public void init() throws ServletException {
        super.init();
        JpaUtil.init();
    }

    @Override
    public void destroy() {
        JpaUtil.destroy();
        super.destroy();
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}