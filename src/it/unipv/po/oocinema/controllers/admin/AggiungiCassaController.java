package it.unipv.po.oocinema.controllers.admin;

import javafx.stage.Window;
import java.sql.SQLException;
import it.unipv.po.oocinema.model.acquirenti.Cassa;
import it.unipv.po.oocinema.persistenza.DBFacade;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;

public class AggiungiCassaController extends MenuController{

	private DBFacade facade = new DBFacade();
	
    @FXML
    private TextField password;

	@FXML
	private TextField username;
	
	@FXML
    private ToggleButton aggiungi;


	@FXML
	public void aggiungiCassa(MouseEvent event) {
    	Cassa c = new Cassa(username.getText(),password.getText());
    	try {
    		facade.aggiungiCassa(c);
    		System.out.println("va");
    	}catch(SQLException e) {
    		
    	}
	}

	@Override
	public Window getWindow() {
		return username.getScene().getWindow();
	}
}
