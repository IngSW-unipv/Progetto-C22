package it.unipv.po.oocinema.controllers.admin;

import java.net.URL;
import java.sql.Date;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Window;

public class ProiezioneController extends MenuController implements Initializable{

	private final String NOMEFILE = "proiezione.fxml";

	private DBFacade facade = new DBFacade();
	
    @FXML
    private Button aggiungi;

    @FXML
    private TextField idLabel;


    @FXML
    private Button rimuovi;

    @FXML
    private TableView<Proiezione> tabella;
    
    @FXML
    private TableColumn<Proiezione, Date> colonnaGiorno;

    @FXML
    private TableColumn<Proiezione, Integer> colonnaId;

    @FXML
    private TableColumn<Proiezione, String> colonnaOra;

    @FXML
    private TableColumn<Proiezione, String> colonnaSala;

    @FXML
    private TableColumn<Proiezione, String> colonnaTitolo;

    
    ObservableList<Proiezione> datiTabella = FXCollections.observableArrayList();
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	
    	colonnaGiorno.setCellValueFactory(new PropertyValueFactory<>("giorno"));
		colonnaTitolo.setCellValueFactory(new PropertyValueFactory<>("titolo"));
		colonnaSala.setCellValueFactory(new PropertyValueFactory<>("sala"));
		colonnaOra.setCellValueFactory(new PropertyValueFactory<>("ora"));
		colonnaId.setCellValueFactory(new PropertyValueFactory<>("id"));

		aggiorna();
		
	}

    @FXML
    void aggiungiProiezione(MouseEvent event) {

    }

    @FXML
    void rimuoviProiezione(MouseEvent event) {

    }

    public void aggiorna() {
		costruisciElementiTabella(datiTabella);
		tabella.setItems(datiTabella);
	}
	
	public  void costruisciElementiTabella(ObservableList<Proiezione> datiTabella) {
		
		datiTabella.removeAll(datiTabella);
		ArrayList<Proiezione> elencoProiezioni = new ArrayList<Proiezione>();
		try {
			elencoProiezioni = facade.getTutteProiezioniFuture();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		

		for(int i = 0; i < elencoProiezioni.size(); i++) {
			Proiezione p = new Proiezione(elencoProiezioni.get(i).getId(),elencoProiezioni.get(i).getFilm(),elencoProiezioni.get(i).getGiorno(),
					elencoProiezioni.get(i).getSala(),elencoProiezioni.get(i).getPrezzo(),elencoProiezioni.get(i).getOrario());
			datiTabella.add(p);
		}
	}
	
	@Override
	public Window getWindow() {
		return rimuovi.getScene().getWindow();
	}


	@Override
	public String getNomeFile() {
		return NOMEFILE;
	}
	

}
