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

public class AggiungiCassaController extends AdminMenuController {

	private DBFacade facade = DBFacade.getInstance();
	
    @FXML
    private TextField password;

	@FXML
	private TextField username;
	
	@FXML
    private ToggleButton aggiungi;


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
