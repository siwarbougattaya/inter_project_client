
package siwarClient;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Projet extends Application {
    
      public static Stage s=new Stage();
    @Override
    public void start(Stage stage) throws Exception {
            Parent root = FXMLLoader.load(getClass().getResource("../fxml/Adminlist.fxml")); 
            s=stage;
            Scene scene = new Scene(root);  
            s.setScene(scene);
            s.show();
       
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
