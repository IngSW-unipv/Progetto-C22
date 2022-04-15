package it.unipv.po.oocinema.controllers.admin;

import javafx.stage.Window;

import java.sql.SQLException;
import java.text.SimpleDateFormat;

import it.unipv.po.oocinema.model.acquirenti.Cassa;
import it.unipv.po.oocinema.model.cinema.Film;
import it.unipv.po.oocinema.model.cinema.Proiezione;
import it.unipv.po.oocinema.model.cinema.Sala;
import it.unipv.po.oocinema.persistenza.DBFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class AggiungiCassaController extends MenuController{

	private final String NOMEFILE = "aggiungiCassa.fxml";
	private DBFacade facade = new DBFacade();
	
    @FXML
    private TextField password;

	@FXML
	private TextField username;

	@FXML
	public void aggiungiCassa(MouseEvent event) {
    	Cassa c = new Cassa(username.getText(),password.getText());
    	try {
    		facade.aggiungiCassa(c);
    	}catch(SQLException e) {
    		
    	}
	}

	@Override
	public Window getWindow() {
		return username.getScene().getWindow();
	}

	@Override
	public String getNomeFile() {
		return NOMEFILE;
	}

}
