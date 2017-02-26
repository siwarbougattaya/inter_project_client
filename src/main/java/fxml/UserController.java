/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import tn.esprit.siwar.persistence.Utilisateur;
import tn.esprit.siwar.services.UtilisateurServiceRemote;

public class UserController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField cin;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Button sauvegarder;
    @FXML
    private ChoiceBox<String> role;

    InitialContext ctx;
    UtilisateurServiceRemote utlisateurservice = null;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        role.getItems().add("client");
        role.getItems().add("vendeur"); 
        try {
            ctx = new InitialContext();
            utlisateurservice = (UtilisateurServiceRemote) ctx.lookup("/siwar-ear/siwar-ejb/UtilisateurService!tn.esprit.siwar.services.UtilisateurServiceRemote");
        } catch (NamingException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    public void inscription() {
        
        Utilisateur u =new Utilisateur();
        u.setCin(cin.getText());
        u.setNom(nom.getText());
        u.setPrenom(prenom.getText());
        u.setUsername(username.getText());
        u.setPassword(password.getText());
        if(role.getValue().equals("client"))
        {
            u.setStatus(1);
        }
        
        else
        {
            u.setStatus(0);
        }
        u.setRole(role.getValue());
        utlisateurservice.ajouterUtilisateur(u);
        
    }

}
