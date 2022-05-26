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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;

public class AggiungiProiezioneController extends AdminMenuController implements Initializable{
	
	private DBFacade facade = DBFacade.getInstance();
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
    private ComboBox<String> oraCombo;

    @FXML
    private TextField prezzo;

    @FXML
    private Label proiezioni;

    @FXML
    private ComboBox<String> salaCombo;

	@FXML
    void aggiungiProiezione(MouseEvent event) {
	
		try {
			Film f = facade.getFilmbyTitolo(new Film(filmCombo.getValue()));
			Sala s = facade.getSalaById(new Sala (Integer.parseInt(salaCombo.getValue().substring(5)),0,0));
	    	Proiezione p = new Proiezione(f,giorno.getValue().toString(),s,Double.parseDouble(prezzo.getText()),oraCombo.getValue());
	    	
	    	boolean check = facade.aggiungiProiezione(p);
	    	if (check) {
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
