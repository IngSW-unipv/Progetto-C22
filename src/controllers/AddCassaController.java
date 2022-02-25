package controllers;

import database.MySQLConnection;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.Cassa;

/**
* Controller che gestisce la finestra per l'aggiunta di una cassa
*/
public class AddCassaController {
	@FXML
	private TextField txtID;
	@FXML
	private PasswordField txtPw;
	
	/**
	* Prende i valori dalla UI ed aggiunge una cassa al database
	* @return true se la query ha avuto successo, false altrimenti
	* @see MySQLController#insertCassa(Cassa)
	*/
	@FXML
	public boolean addCassa() {
		Cassa cassa = new Cassa(Integer.valueOf(txtID.getText()), 
				txtPw.getText());
		
		Alert alert;
		
		try {
			if (MySQLConnection.insertCassa(cassa)) {
				alert = new Alert(AlertType.INFORMATION, "Cassa aggiunta con successo");
			} else {
				alert = new Alert(AlertType.ERROR, "C'è stato un problema nell'aggiunta della cassa, controlla i dati");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			alert = new Alert(AlertType.ERROR, "C'è stato un problema nell'aggiunta della cassa, controlla i dati");
			return false;
		}
		
		alert.showAndWait();
		Stage stage = (Stage) txtID.getScene().getWindow();
	    stage.close();
	    return true;
	}
}
