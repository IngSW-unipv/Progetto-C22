package it.unipv.po.oocinema.controllers;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.swing.JFileChooser;


import it.unipv.po.oocinema.model.cinema.Film;
import it.unipv.po.oocinema.model.cinema.Proiezione;
import it.unipv.po.oocinema.model.cinema.Sala;
import it.unipv.po.oocinema.persistenza.DBFacade;
import it.unipv.po.oocinema.view.WindowsHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class AdminController {
	
	//private DBFacade facade = new DBFacade();

    @FXML
    private ToggleButton aggiungiFilm;

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
    private ToggleButton aggiungiProiezione;


    @FXML
    private ComboBox<?> filmCombo;

    @FXML
    private DatePicker giorno;

    @FXML
    private ComboBox<?> oraCombo;

    @FXML
    private TextField prezzo;

    @FXML
    private ComboBox<?> salaCombo;

    
    private File l;
    private File t;
    @FXML
    public void aggiungiFilm(MouseEvent event) {
    	
    	Film f = new Film(titolo.getText(),descrizione.getText(), genere.getText(),
    			Integer.parseInt(durata.getText()),regista.getText(),cast.getText(), "resources/locandine/"+l.getName(),"resources/trailer/"+t.getName());
    	//facade.aggiungiFilm(f);
    }
    
    @FXML
    void aggiungiProiezione(MouseEvent event) {
    	//Film f = facade.getFilmbyTitolo(new Film(filmCombo.getPromptText()));
    	//Sala s = facade.getSala(new Sala (salaCombo.getPromptText()));
    	//Proiezione p = new Proiezione(f, (LocalDate)giorno.getValue(),s,Double.parseDouble(prezzo.getText()),oraCombo.getPromptText());
    	//facade.aggiungiProiezione(p);
    }

    @FXML
    public void caricaLocandina(MouseEvent event) throws IOException {
    	
    	JFileChooser fc = new JFileChooser();
    	fc.setCurrentDirectory(new File("./resources/locandine").getCanonicalFile());
        int checkInput = fc.showOpenDialog(null);
      
        if (checkInput == JFileChooser.APPROVE_OPTION) {
            l = fc.getSelectedFile();
        }
    	
    }

    @FXML
    public void caricaTrailer(MouseEvent event) throws IOException {
    	JFileChooser fc = new JFileChooser();
    	fc.setCurrentDirectory(new File("./resources/trailer").getCanonicalFile());
        int checkInput = fc.showOpenDialog(null);
      
        if (checkInput == JFileChooser.APPROVE_OPTION) {
            t = fc.getSelectedFile();
        }
    }

    @FXML
    void cassa(MouseEvent event) {
    	WindowsHandler.openWindow(getClass(), "cassa.fxml");
    	Stage thisStage = (Stage) cassa.getScene().getWindow();
    	thisStage.hide();
    }

    @FXML
    void esci(MouseEvent event) {
    	WindowsHandler.openWindow(getClass(), "login.fxml");
    	Stage thisStage = (Stage) esci.getScene().getWindow();
    	thisStage.hide();
    }

    @FXML
    void film(MouseEvent event) {
    	WindowsHandler.openWindow(getClass(), "film.fxml");
    	Stage thisStage = (Stage) film.getScene().getWindow();
    	thisStage.hide();
    }

    @FXML
    void home(MouseEvent event) {
    	WindowsHandler.openWindow(getClass(), "homeADM.fxml");
    	Stage thisStage = (Stage) home.getScene().getWindow();
    	thisStage.hide();
    }

    @FXML
    void proiezione(MouseEvent event) {
    	WindowsHandler.openWindow(getClass(), "proiezione.fxml");
    	Stage thisStage = (Stage) proiezione.getScene().getWindow();
    	thisStage.hide();
    }
    
}