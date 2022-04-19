package it.unipv.po.oocinema.controllers;

import java.sql.SQLException;

import it.unipv.po.oocinema.controllers.WindowsHandler;
import it.unipv.po.oocinema.controllers.client.MenuController;
import it.unipv.po.oocinema.model.acquirenti.Acquirente;
import it.unipv.po.oocinema.persistenza.DBFacade;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Window;

public class LoginController {
	
	DBFacade facade = new DBFacade();

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

    @FXML
    void login(MouseEvent event) {
    	try {
    		Acquirente log = new Acquirente(user.getText(),password.getText());
			
    		if(!facade.login(log)) {
    			Alert alert = new Alert(AlertType.WARNING, "Utente o password errati");
    	    	alert.showAndWait(); 
    		}else {
				int tipo = facade.getTipoByUser(log);
				if(tipo == 0)
					WindowsHandler.openWindow(getClass(), "homeADM.fxml");
				else WindowsHandler.openWindow(getClass(), "homeCLI.fxml");
				MenuController.setCliente(log);
			    WindowsHandler.closeWindow(getWindow());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void passwordDimenticata(MouseEvent event) {
    	//Email handler magari
    }

    @FXML
    void registrati(MouseEvent event) {
    	WindowsHandler.openWindow(getClass(), "registrazione.fxml");
	    WindowsHandler.closeWindow(getWindow());
    }
    
    public Window getWindow() {
		return user.getScene().getWindow();
	}

}
