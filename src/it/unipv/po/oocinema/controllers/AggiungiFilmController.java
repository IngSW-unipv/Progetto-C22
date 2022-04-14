package it.unipv.po.oocinema.controllers;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.swing.JFileChooser;


import it.unipv.po.oocinema.model.cinema.Film;
import it.unipv.po.oocinema.model.cinema.Proiezione;
import it.unipv.po.oocinema.model.cinema.Sala;
import it.unipv.po.oocinema.persistenza.DBFacade;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.Window;

public class AggiungiFilmController extends MenuController {
	
	private DBFacade facade = new DBFacade();

	private final String NOMEFILE = "aggiungiFilm.fxml";
    @FXML
    private ToggleButton aggiungiFilm;

    @FXML
    private TextArea descrizione;

    @FXML
    private TextField durata;

    @FXML
    private TextField genere;

    @FXML
    private Button locandina;

    @FXML
    private TextField regista;
    
    @FXML
    private TextField cast;

    @FXML
    private TextField titolo;

    @FXML
    private Button trailer;
    
    
    private File l;
    private File t;
    @FXML
    public void aggiungiFilm(MouseEvent event) {
	
		 Film f = new Film(titolo.getText(),descrizione.getText(), genere.getText(),
		 Integer.parseInt(durata.getText()),regista.getText(),cast.getText(),
		 "/resources/locandine/"+l.getName(),"/resources/trailer/"+t.getName());
		
		 try {
			facade.aggiungiFilm(f);
		} catch (SQLException e) {
			e.printStackTrace();
			Alert errore = new Alert(AlertType.ERROR, "ERRORE");
			errore.showAndWait();
		}
		 
		Alert successo = new Alert(AlertType.INFORMATION, "SUCCESSO");
	    successo.showAndWait();
		 
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
    
    @Override
	public String getNomeFile() {
		return NOMEFILE;
	}
    
    @Override
    public Window getWindow() {
    	return titolo.getScene().getWindow();
    }
    
}