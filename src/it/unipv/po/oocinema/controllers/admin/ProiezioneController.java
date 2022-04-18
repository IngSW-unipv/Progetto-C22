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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
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
    private TableView<InnerProiezione> tabella;
    
    @FXML
    private TableColumn<InnerProiezione, String> colonnaGiorno;

    @FXML
    private TableColumn<InnerProiezione, Integer> colonnaId;

    @FXML
    private TableColumn<InnerProiezione, String> colonnaOra;

    @FXML
    private TableColumn<InnerProiezione, String> colonnaSala;

    @FXML
    private TableColumn<InnerProiezione, String> colonnaTitolo;
    

    
    ObservableList<InnerProiezione> datiTabella = FXCollections.observableArrayList();
    
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
    	WindowsHandler.openWindow(getClass(), "aggiungiProiezione.fxml");
	    WindowsHandler.closeWindow(getWindow());
    }

    @FXML
    void rimuoviProiezione(MouseEvent event) {
    	Alert alert = new Alert(AlertType.CONFIRMATION, "Verranno rimossi tutti i dati associati alla proiezione");
    	alert.showAndWait(); 
    	
    	if(alert.getResult().equals(ButtonType.OK)) {
    	Film f = new Film();
    	f.setId(Integer.parseInt(idLabel.getText()));
    	try {
			facade.rimuoviFilm(f);	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	aggiorna();
    	}
    }

    public void aggiorna() {
		costruisciElementiTabella(datiTabella);
		tabella.setItems(datiTabella);
	}
	
	public  void costruisciElementiTabella(ObservableList<InnerProiezione> datiTabella) {
		
		datiTabella.removeAll(datiTabella);
		ArrayList<Proiezione> elencoProiezioni = new ArrayList<Proiezione>();
		try {
			elencoProiezioni = facade.getTutteProiezioniFuture();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		

		for(int i = 0; i < elencoProiezioni.size(); i++) {
			InnerProiezione p = new InnerProiezione(elencoProiezioni.get(i).getId(),
					elencoProiezioni.get(i).getFilm().getTitolo(),
					"Sala "+elencoProiezioni.get(i).getSala().getId(),
					elencoProiezioni.get(i).getGiorno(),elencoProiezioni.get(i).getOrario());
				
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
	
	public class InnerProiezione{
		int id;
		String titolo;
		String sala;
		String giorno;
		String ora;
		
		public InnerProiezione(int id, String titolo, String sala, String giorno, String ora) {
			super();
			this.id = id;
			this.titolo = titolo;
			this.sala = sala;
			this.giorno = giorno;
			this.ora = ora;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getTitolo() {
			return titolo;
		}

		public void setTitolo(String titolo) {
			this.titolo = titolo;
		}

		public String getSala() {
			return sala;
		}

		public void setSala(String sala) {
			this.sala = sala;
		}

		public String getGiorno() {
			return giorno;
		}

		public void setGiorno(String giorno) {
			this.giorno = giorno;
		}

		public String getOra() {
			return ora;
		}

		public void setOra(String ora) {
			this.ora = ora;
		}

		@Override
		public String toString() {
			return "InnerProiezione [id=" + id + ", titolo=" + titolo + ", sala=" + sala + ", giorno=" + giorno
					+ ", ora=" + ora + "]";
		}
		
		
	}

}
