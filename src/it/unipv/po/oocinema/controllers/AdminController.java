package it.unipv.po.oocinema.controllers;

import java.io.File;

import javax.swing.JFileChooser;

import it.unipv.po.oocinema.model.cinema.Film;
import it.unipv.po.oocinema.persistenza.DBFacade;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;

public class AdminController {
	
	private DBFacade facade = new DBFacade();

    @FXML
    private ToggleButton aggiungi;

    @FXML
    private Label cassa;

    @FXML
    private TextArea descrizione;

    @FXML
    private TextField durata;

    @FXML
    private Label esci;

    @FXML
    private Label film;

    @FXML
    private TextField genere;

    @FXML
    private Label home;

    @FXML
    private Button locandina;

    @FXML
    private Label proiezione;

    @FXML
    private TextField regista;
    @FXML
    private TextField cast;

    @FXML
    private TextField titolo;

    @FXML
    private Button trailer;

    @FXML
    public void aggiungiFilm(MouseEvent event) {
    	
    	Film f = new Film(Film.getProgressivo(),titolo.getText(),descrizione.getText(), genere.getText(),
    			Integer.parseInt(durata.getText()),regista.getText(),cast.getText(), caricaLocandina(event),caricaTrailer(event));
    	Film.incrementaProgressivo();
    	facade.aggiungiFilm(f);
    }

    @FXML
    public String caricaLocandina(MouseEvent event) {
    	JFileChooser fc = new JFileChooser();
    	File file = fc.getSelectedFile();
    	return file.getPath();
    }

    @FXML
    public String caricaTrailer(MouseEvent event) {

    }

    @FXML
    void cassa(MouseEvent event) {

    }

    @FXML
    void esci(MouseEvent event) {

    }

    @FXML
    void film(MouseEvent event) {

    }

    @FXML
    void home(MouseEvent event) {

    }

    @FXML
    void proiezione(MouseEvent event) {

    }

}