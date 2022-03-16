package it.unipv.po.oocinema.controllers;

import it.unipv.po.oocinema.database.MySQLConnectionFactory;
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
    
    /**
	* Effettua il login recuperando i dati dall'interfaccia grafica
	* Il login viene effettuato tramite una chiamata alla classe MySQLConnection
	* @see MySQLController#login(String, String)
	*/
    @FXML
    private void handleSignInButton() {
        System.out.println("-- Faccio login con " + txtUsername.getText() + " - " + txtPassword.getText() + " --");
        try {
			if (/**true ||*/ MySQLConnectionFactory.login(txtUsername.getText(), txtPassword.getText())) {  //avevo messo true || in modo tale da debaggure prima il programma
				System.out.print("- Login avvenuto con successo -");
				
				Alert alert = new Alert(AlertType.INFORMATION, "Login avvenuto con successo, benvenuto " + txtUsername.getText());
				alert.showAndWait();
				
				WindowsHandler.openWindow(getClass(), "homeAdmin.fxml");
				
				Stage stage = (Stage) txtUsername.getScene().getWindow();
			    stage.close();
			} else {
				System.out.print("- Login fallito, email o password errati -");
				//Alert alert = new Alert(AlertType.ERROR, "Email o password errati, riprova");
				//alert.showAndWait();
				lblErrors.setText("Login fallito");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			
			Alert alert = new Alert(AlertType.ERROR, "Connessione al database fallita");
			alert.showAndWait();
		}
    }
    

    	
 
}