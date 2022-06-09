package it.unipv.po.oocinema.controllers.admin;

import java.net.URL;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import it.unipv.po.oocinema.model.cinema.Film;
import it.unipv.po.oocinema.model.cinema.Proiezione;
import it.unipv.po.oocinema.model.cinema.Sala;
import it.unipv.po.oocinema.persistenza.DBFacade;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;

/**
 * Classe controller per l'aggiunta di una nuova proiezione
 * @author GoF
 *
 */
public class AggiungiProiezioneController extends AdminMenuController implements Initializable{
	
	/**
	 * Istanza della classe che comunica con il DB
	 */
	private DBFacade facade = DBFacade.getInstance();
	
	/**
	 * Bottone per la conferma dell'aggiunta di una nuova prenotazione
	 */
	@FXML
    private ToggleButton aggiungiProiezione;

	/**
	 * Elenco dei film
	 */
    @FXML
    private ComboBox<String> filmCombo;

    /**
	 * Calendario per la scelta del giorno
	 */
    @FXML
    private DatePicker giorno;

    /**
	 * Elenco delle ore
	 */
    @FXML
    private ComboBox<String> oraCombo;

    /**
	 * Input - prezzo
	 */
    @FXML
    private TextField prezzo;

    /**
     * Elenco delle sale
     */
    @FXML
    private ComboBox<String> salaCombo;

    /**
     * Aggiunge una proiezione al DB e alla tabella
     */
	@FXML
    void aggiungiProiezione(MouseEvent event) {
	
		try {
			Film f = facade.getFilmbyTitolo(new Film(filmCombo.getValue()));
			Sala s = facade.getSalaById(new Sala (Integer.parseInt(salaCombo.getValue().substring(5)),0,0));
	    	Proiezione p = new Proiezione(f,giorno.getValue().toString(),s,Double.parseDouble(prezzo.getText()),oraCombo.getValue());
	    	
	    	boolean check = facade.aggiungiProiezione(p);
	    	if (!check) {
		    	Alert a = new Alert(AlertType.CONFIRMATION, "Proiezione aggiunta con successo");
				a.showAndWait();
	    	}
		} catch (Exception e) {
			Alert errore = new Alert(AlertType.WARNING, "Controlla i dati inseriti");
			errore.showAndWait();
		}
    	
    }
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initializeFilm();
		initializeSala();
		initializeOra();
	}
	

	/**
	 * Iniizializzazione della lista dei film
	 */
	public void initializeFilm() {
		ArrayList<Film> films = new ArrayList<Film>();
		ArrayList<String> titoli = new ArrayList<String>();
		try {
			films = facade.getTuttiFilm();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(Film f : films) {
			titoli.add(f.getTitolo());
		}
       
        ObservableList<String> obList = FXCollections.observableList(titoli);
        filmCombo.getItems().clear();
        filmCombo.setItems(obList);
		
	}
	
	/**
	 * Inizializzazione della lista delle sale
	 */
	public void initializeSala() {
		ArrayList<Sala> sale = new ArrayList<Sala>();
		ArrayList<String> id = new ArrayList<String>();
		try {
			sale = facade.getTutteSale();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(Sala s : sale) {
			id.add("Sala "+s.getId());
		}
       
        ObservableList<String> obList = FXCollections.observableList(id);
        salaCombo.getItems().clear();
        salaCombo.setItems(obList);
		
	}
	
	/**
	 * Inizializzazione della lista delle ore
	 */
	public void initializeOra() {
		ArrayList<String> ore = new ArrayList<String>();
		try {
			ore = facade.getTutteOre();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ObservableList<String> obList = FXCollections.observableList(ore);
        oraCombo.getItems().clear();
        oraCombo.setItems(obList);
		
	}
	
}
