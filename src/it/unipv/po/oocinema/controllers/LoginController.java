package it.unipv.po.oocinema.controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import it.unipv.po.oocinema.model.acquirenti.Acquirente;
import it.unipv.po.oocinema.persistenza.DBFacade;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Window;

public class LoginController implements Initializable{
	
	DBFacade facade = DBFacade.getInstance();

    @FXML
    private Label dimenticata;

    @FXML
    private Text emailLabel;

    @FXML
    private Text indirizzoLabel;

    @FXML
    private Button login;

    @FXML
    private PasswordField password;

    @FXML
    private Button registrati;

    @FXML
    private Text telLabel;

    @FXML
    private TextField user;
    
    private static Acquirente utente;
   

    @FXML
    void login(MouseEvent event) {
    	try {
    		Acquirente log = new Acquirente(user.getText());
			log.setPassword(password.getText());
    		if(facade.loginAmministratore(log)) {
    			utente = facade.getAmministratore();
    			WindowsHandler.openWindow(getClass(), "../view/scenes/homeADM.fxml");
    			WindowsHandler.closeWindow(getWindow());
    			
    			
    		} else {
	    		if(!facade.login(log)) {
	    			Alert alert = new Alert(AlertType.WARNING, "Utente o password errati");
	    	    	alert.showAndWait(); 
	    		}else {
	    			 utente = facade.getUtentebyUser(log);
					WindowsHandler.openWindow(getClass(), "../view/scenes/homeCLI.fxml");
				    WindowsHandler.closeWindow(getWindow());
				   
				}
	    		
    		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void passwordDimenticata(MouseEvent event) {
    	
    }

    @FXML
    void registrati(MouseEvent event) {
    	WindowsHandler.openWindow(getClass(), "../view/scenes/registrazione.fxml");
	    WindowsHandler.closeWindow(getWindow());
    }
    
    public Window getWindow() {
		return user.getScene().getWindow();
	}
    
    public static Acquirente getCliente() {
    	return utente;
    }
    
    public static void setCliente(Acquirente c) {
    	utente = c;
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			telLabel.setText(facade.getTelefono());
			emailLabel.setText(facade.getEmail());
			indirizzoLabel.setText(facade.getIndirizzo());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
