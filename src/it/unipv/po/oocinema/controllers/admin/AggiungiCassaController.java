package it.unipv.po.oocinema.controllers.admin;

import java.sql.SQLException;
import it.unipv.po.oocinema.model.acquirenti.Cassa;
import it.unipv.po.oocinema.persistenza.DBFacade;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;

/**
* Classe che implementa il Controller per l'aggiunta di una nuova cassiera da parte dell'amministratore
* 
* @author GoF
*/
public class AggiungiCassaController extends AdminMenuController {

	/**
	 * Istanza del gestore del DataBase
	 */
	private DBFacade facade = DBFacade.getInstance();
	
	/**
	 * Password dell'account della cassa
	 */
    @FXML
    private TextField password;

    /**
	 * Username dell'account della cassa
	 */
	@FXML
	private TextField username;
	
	/**
	 * Bottone per confermare l'aggiunta della cassa 
	 */
	@FXML
    private ToggleButton aggiungi;

	
	/**
	 * Aggiunge la cassa al DataBase
	 */
	@FXML
	public void aggiungiCassa(MouseEvent event) {
		Cassa c = null;
		try {
    	c = new Cassa(username.getText(),password.getText());
		}catch(Exception e) {
			Alert al = new Alert(AlertType.WARNING, "Controlla i dati inseriti");
			al.showAndWait();
		}
    	try {
    		facade.aggiungiCassa(c);
    		System.out.println("va");
    	}catch(SQLException e) {
    		
    	}
	}

}
