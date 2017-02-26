/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package siwarClient;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import tn.esprit.siwar.persistence.Message;
import tn.esprit.siwar.services.MessageServiceRemote;
import tn.esprit.siwar.services.UtilisateurServiceRemote;

/**
 * FXML Controller class
 *
 * @author hechem
 */
public class MessageController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    
    InitialContext ctx ;
    MessageServiceRemote messageservice=null;
    UtilisateurServiceRemote utlisateurservice = null;

    
    @FXML
    private TextArea description;
    @FXML
    private Button envoyer;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            ctx = new InitialContext();
            messageservice =(MessageServiceRemote)
            ctx.lookup("/siwar-ear/siwar-ejb/MessageService!tn.esprit.siwar.services.MessageServiceRemote");
            utlisateurservice = (UtilisateurServiceRemote) ctx.lookup("/siwar-ear/siwar-ejb/UtilisateurService!tn.esprit.siwar.services.UtilisateurServiceRemote");

        } catch (NamingException ex) {
            Logger.getLogger(MessageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    
    @FXML
    public void envoyer()
    {
        Message m =new Message();
        m.setDescription(description.getText());
        m.setSender(utlisateurservice.findById(5));
        m.setReceiver(utlisateurservice.getRandomBuyer());
        messageservice.ajouterMessage(m);
        
    }
    
}
