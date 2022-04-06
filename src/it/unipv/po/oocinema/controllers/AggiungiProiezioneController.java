package it.unipv.po.oocinema.controllers;



import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import it.unipv.po.oocinema.model.cinema.Film;
import it.unipv.po.oocinema.model.cinema.Proiezione;
import it.unipv.po.oocinema.model.cinema.Sala;
import it.unipv.po.oocinema.persistenza.DBFacade;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Window;

public class AggiungiProiezioneController extends MenuController {
	
	private final String NOMEFILE = "aggiungiProiezione.fxml";
	private DBFacade facade = new DBFacade();
	@FXML
    private ToggleButton aggiungiProiezione;

    @FXML
    private Label cassa;

    @FXML
    private Label esci;

    @FXML
    private Label film;

    @FXML
    private ComboBox<String> filmCombo;

    @FXML
    private DatePicker giorno;

    @FXML
    private Label home;

    @FXML
    private ComboBox<?> oraCombo;

    @FXML
    private TextField prezzo;

    @FXML
    private Label proiezioni;

    @FXML
    private ComboBox<String> salaCombo;

	@FXML
    void aggiungiProiezione(MouseEvent event) throws NumberFormatException, ParseException {
		Date d = (Date) new SimpleDateFormat("dd/MM/yyyy").parse(giorno.getPromptText());
		try {
			Film f = facade.getFilmbyTitolo(new Film(filmCombo.getPromptText()));
			Sala s = facade.getSalaById(new Sala (Integer.parseInt(salaCombo.getPromptText()),0,0));
	    	Proiezione p = new Proiezione(f,d,s,Double.parseDouble(prezzo.getText()),oraCombo.getPromptText());
	    	facade.aggiungiProiezione(p);
		} catch (SQLException e) {
			
		}
    	
    }
	
	@Override
	public String getNomeFile() {
		return NOMEFILE;
	}

	public Window getWindow() {
	    return prezzo.getScene().getWindow(); // ATTENZIONE non molto corretto ma funzionante
	}
}
