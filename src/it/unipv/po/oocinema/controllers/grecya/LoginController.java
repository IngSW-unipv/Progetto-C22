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
    private TextField userTxt;
    @FXML
    private PasswordField pswTxt;
    
    private DBFacade f;
    
    public LoginController() {
    	f = new DBFacade();
    }
    
    @FXML
    private void login() {
 
    	if (f.login(new Acquirente(userTxt.getText(),pswTxt.getText()))) {  
			System.out.print("- Login avvenuto con successo -");
			Alert alert = new Alert(AlertType.INFORMATION, "Login avvenuto con successo, benvenuto " + userTxt.getText());
			alert.showAndWait();
			/*
			 * WindowsHandler.openWindow(getClass(), "homeAdmin.fxml"); Stage stage =
			 * (Stage) userTxt.getScene().getWindow(); 
			 * stage.close();*/
		} else {
			System.out.print("- Login fallito, email o password errati -");
			Alert alert = new Alert(AlertType.ERROR, "Email o password errati, riprova");
			alert.showAndWait();
		}
    	
    	
    }
    
    @FXML
    private void nuovoUtente() {
    	WindowsHandler.openWindow(getClass(), "registrazione.fxml"); 
    	Stage stage = (Stage) userTxt.getScene().getWindow(); 
   		stage.close();
    }
    
    @FXML
    private void passwordDimenticata() {
    	WindowsHandler.openWindow(getClass(), "password_dimenticata.fxml"); 
    	Stage stage = (Stage) userTxt.getScene().getWindow(); 
   		stage.close();
    }

    	
 
}