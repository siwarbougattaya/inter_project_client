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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import tn.esprit.siwar.persistence.Utilisateur;
import tn.esprit.siwar.services.MessageServiceRemote;
import tn.esprit.siwar.services.UtilisateurServiceRemote;

/**
 * FXML Controller class
 *
 * @author hechem
 */
public class AdminlistController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TableView<Utilisateur> tableId;
    @FXML
    private TableColumn<Utilisateur, Integer> id1;
    @FXML
    private TableColumn<Utilisateur, String> nom1;
    @FXML
    private TableColumn<Utilisateur, String> prenom1;
    @FXML
    private TableColumn<Utilisateur, String> cin1;
    @FXML
    private TableColumn<Utilisateur, String> username1;
    @FXML
    private TableColumn<Utilisateur, String> password1;
    @FXML
    private TableColumn<Utilisateur, String> role1;
    @FXML
    private TableColumn<Utilisateur, Integer> status1;
    InitialContext ctx;
    UtilisateurServiceRemote utlisateurservice = null;

    @FXML
    private Button activer;
    @FXML
    private Button desactiver;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            ctx = new InitialContext();
            utlisateurservice = (UtilisateurServiceRemote) ctx.lookup("/siwar-ear/siwar-ejb/UtilisateurService!tn.esprit.siwar.services.UtilisateurServiceRemote");
            ObservableList<Utilisateur> data = FXCollections.observableArrayList(utlisateurservice.findAll());
            id1.setCellValueFactory(new PropertyValueFactory<Utilisateur, Integer>("id"));
            nom1.setCellValueFactory(new PropertyValueFactory<Utilisateur, String>("nom"));
             prenom1.setCellValueFactory(new PropertyValueFactory<Utilisateur, String>("prenom"));
            cin1.setCellValueFactory(new PropertyValueFactory<Utilisateur, String>("cin"));
            username1.setCellValueFactory(new PropertyValueFactory<Utilisateur, String>("username"));
            password1.setCellValueFactory(new PropertyValueFactory<Utilisateur, String>("password"));
            role1.setCellValueFactory(new PropertyValueFactory<Utilisateur, String>("role"));
            status1.setCellValueFactory(new PropertyValueFactory<Utilisateur, Integer>("status"));
            tableId.setItems(data);

        } catch (NamingException ex) {
            Logger.getLogger(MessageController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    public void activer() {
          Utilisateur selecteActivity = tableId.getSelectionModel().getSelectedItems().get(0);
          selecteActivity.setStatus(1);
          utlisateurservice.modifierUtilisateur(selecteActivity);
          tableId.setItems(FXCollections.observableArrayList(utlisateurservice.findAll()));

    }

    @FXML
    public void desactiver() {
        
            Utilisateur selecteActivity = tableId.getSelectionModel().getSelectedItems().get(0);
          selecteActivity.setStatus(0);
          utlisateurservice.modifierUtilisateur(selecteActivity);
          tableId.setItems(FXCollections.observableArrayList(utlisateurservice.findAll()));

    }

}
