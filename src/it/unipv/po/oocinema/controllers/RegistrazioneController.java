package it.unipv.po.oocinema.controllers;

import java.sql.SQLException;

import it.unipv.po.oocinema.model.acquirenti.Cliente;
import it.unipv.po.oocinema.persistenza.DBFacade;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Window;

public class RegistrazioneController {

	DBFacade facade = new DBFacade();
    @FXML
    private DatePicker compleanno;

    @FXML
    private PasswordField conferma;

    @FXML
    private TextField email;

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
    private TextField cognome;
    
    @FXML
    private TextField nome;

    @FXML
    void login(MouseEvent event) {
    	WindowsHandler.openWindow(getClass(), "registrazione.fxml");
	    WindowsHandler.closeWindow(getWindow());
    }

    @FXML
    void registrazione(MouseEvent event) {
    	while(!password.getText().equals(conferma.getText())) {
    		Alert errore = new Alert(AlertType.ERROR, "LE PASSWORD NON COINCIDONO");
			errore.showAndWait();
    	}
    	try {
			facade.registrazione(new Cliente(email.getText(), password.getText(),nome.getText(),cognome.getText(),compleanno.getValue().toString()));
		} catch (SQLException e) {
			Alert errore = new Alert(AlertType.ERROR, "ERRORE");
			errore.showAndWait();
			e.printStackTrace();
		}
    }

    
    public Window getWindow() {
		return password.getScene().getWindow();
	}

}
