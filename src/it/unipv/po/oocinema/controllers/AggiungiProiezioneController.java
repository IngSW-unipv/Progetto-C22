package it.unipv.po.oocinema.controllers;



import java.time.LocalDate;

import it.unipv.po.oocinema.model.cinema.Film;
import it.unipv.po.oocinema.model.cinema.Proiezione;
import it.unipv.po.oocinema.model.cinema.Sala;
import it.unipv.po.oocinema.persistenza.DBFacade;
import it.unipv.po.oocinema.view.scenes.MenuController;
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
    void aggiungiProiezione(MouseEvent event) {
    	Film f = facade.getFilmbyTitolo(new Film(filmCombo.getPromptText()));
    	Sala s = facade.getSala(new Sala (salaCombo.getPromptText()));
    	Proiezione p = new Proiezione(f, (LocalDate)giorno.getValue(),s,Double.parseDouble(prezzo.getText()),oraCombo.getPromptText());
    	facade.aggiungiProiezione(p);
    }
	
	@Override
	public String getNomeFile() {
		return NOMEFILE;
	}

	public Window getWindow() {
	    return prezzo.getScene().getWindow();
	}
}
