package it.unipv.po.oocinema.controllers.grecya;

import it.unipv.po.oocinema.model.acquirenti.Acquirente;
import it.unipv.po.oocinema.persistenza.DBFacade;
import it.unipv.po.oocinema.view.WindowsHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
* Contoller che gestisce la pagina di Login
*/
public class LoginController {

	
    @FXML 
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Label lblErrors;
    
   public DBFacade f = new DBFacade();
    
    @FXML
    private void handleSignInButton() {
 
    	if (f.login(new Acquirente(txtUsername.getText(),txtPassword.getText(),null))) {  
			System.out.print("- Login avvenuto con successo -");
			Alert alert = new Alert(AlertType.INFORMATION, "Login avvenuto con successo, benvenuto " + txtUsername.getText());
			alert.showAndWait();
			WindowsHandler.openWindow(getClass(), "homeAdmin.fxml");
			Stage stage = (Stage) txtUsername.getScene().getWindow();
			stage.close();
		} else {
			System.out.print("- Login fallito, email o password errati -");
			Alert alert = new Alert(AlertType.ERROR, "Email o password errati, riprova");
			alert.showAndWait();
			lblErrors.setText("Login fallito");
			}
    }
    

    	
 
}