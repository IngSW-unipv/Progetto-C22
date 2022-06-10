package it.unipv.po.oocinema.controllers.admin;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import it.unipv.po.oocinema.controllers.WindowsHandler;
import it.unipv.po.oocinema.model.cinema.Proiezione;
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

/**
 * Classe controller che permette di gestire le proiezioni e aggiungerne di nuove
 * @author GoF
 *
 */
public class ProiezioneController extends AdminMenuController implements Initializable{

	/**
	 * Istanza della classe che comunica con il DB
	 */
	private DBFacade facade = DBFacade.getInstance();
	
	/**
	 * Bottone per passare alla pagina di aggiunta di una nuova proiezione
	 */
    @FXML
    private Button aggiungi;

    /**
     * Label id proiezione da rimuovere
     */
    @FXML
    private TextField idLabel;

    /**
     * Bottone per rimuovere la proiezione selezionata dalla tabella
     */
    @FXML
    private Button rimuovi;

    /**
     * Tabella delle proiezioni
     */
    @FXML
    private TableView<InnerProiezione> tabella;
    
    /**
     * Colonna della tabella - giorno
     */
    @FXML
    private TableColumn<InnerProiezione, String> colonnaGiorno;

    /**
     * Colonna della tabella - giorno
     */
    @FXML
    private TableColumn<InnerProiezione, Integer> colonnaId;

    /**
     * Colonna della tabella - giorno della proiezione
     */
    @FXML
    private TableColumn<InnerProiezione, String> colonnaOra;

    /**
     * Colonna della tabella - sala
     */
    @FXML
    private TableColumn<InnerProiezione, String> colonnaSala;

    /**
     * Colonna della tabella - titolo del film
     */
    @FXML
    private TableColumn<InnerProiezione, String> colonnaTitolo;
    
    /**
     * Dati da visualizzare nella tabella
     */
    private ObservableList<InnerProiezione> datiTabella = FXCollections.observableArrayList();
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {

    	
    	colonnaGiorno.setCellValueFactory(new PropertyValueFactory<>("giorno"));
		colonnaTitolo.setCellValueFactory(new PropertyValueFactory<>("titolo"));
		colonnaSala.setCellValueFactory(new PropertyValueFactory<>("sala"));
		colonnaOra.setCellValueFactory(new PropertyValueFactory<>("ora"));
		colonnaId.setCellValueFactory(new PropertyValueFactory<>("id"));

		aggiorna();
		
	}

    /**
     * Passa alla pagina di aggiunta di una nuova proiezione
     */
    @FXML
    void aggiungiProiezione(MouseEvent event) {
    	WindowsHandler.openWindow(getClass(), "../../view/scenes/aggiungiProiezione.fxml");
	    WindowsHandler.closeWindow(getWindow());
    }

    /**
     * Rimuove la proiezione selezionata
     */
    @FXML
    void rimuoviProiezione(MouseEvent event) {
    	Alert alert = new Alert(AlertType.CONFIRMATION, "Verranno rimossi tutti i dati associati alla proiezione");
    	alert.showAndWait(); 
    	
    	if(alert.getResult().equals(ButtonType.OK)) {
    	Proiezione p = new Proiezione();
    	try {
    	p.setId(Integer.parseInt(idLabel.getText()));
    	facade.rimuoviProiezione(p);	
    	}catch(Exception e) {
    		Alert errore = new Alert(AlertType.WARNING, "Controlla i dati inseriti");
			errore.showAndWait();
    	}
    	
    	
    	aggiorna();
    	}
    }

    /**
     * Aggiorna l'interfaccia grafica
     */
    public void aggiorna() {
		costruisciElementiTabella(datiTabella);
		tabella.setItems(datiTabella);
	}
	
    /**
     * Aggiunge i dati alla tabella 
     * @param datiTabella
     */
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

	/**
	 * Classe nested di Proiezione che serve per far combaciare i dati della tabella con un oggetto 
	 * @author GoF
	 *
	 */
	public static class InnerProiezione{
		
		/**
		 * Id della proiezione
		 */
		private int id;
		
		/**
		 * Titolo del film
		 */
		private String titolo;
		
		/**
		 * Sala di proiezione
		 */
		private String sala;
		
		/**
		 * Giorno di proiezione
		 */
		private String giorno;
		
		/**
		 * Ora di proiezione
		 */
		private String ora;
		
		/**
		 * Costruttore completo.
		 * @param id
		 * @param titolo
		 * @param sala
		 * @param giorno
		 * @param ora
		 */
		public InnerProiezione(int id, String titolo, String sala, String giorno, String ora) {
			super();
			this.id = id;
			this.titolo = titolo;
			this.sala = sala;
			this.giorno = giorno;
			this.ora = ora;
		}

		/**
		 * Getter 
		 * @return id della proiezione
		 */
		public int getId() {
			return id;
		}

		/**
		 * Setter
		 * @param id
		 */
		public void setId(int id) {
			this.id = id;
		}

		/**
		 * Getter 
		 * @return id della proiezione
		 */
		public String getTitolo() {
			return titolo;
		}

		/**
		 * Setter
		 * @param titolo
		 */
		public void setTitolo(String titolo) {
			this.titolo = titolo;
		}

		/**
		 * Getter 
		 * @return id della sala
		 */
		public String getSala() {
			return sala;
		}

		/**
		 * Setter
		 * @param sala
		 */
		public void setSala(String sala) {
			this.sala = sala;
		}

		/**
		 * Getter 
		 * @return giorno della proiezione
		 */
		public String getGiorno() {
			return giorno;
		}

		/**
		 * Setter
		 * @param giorno
		 */
		public void setGiorno(String giorno) {
			this.giorno = giorno;
		}

		/**
		 * Getter 
		 * @return ora della proiezione
		 */
		public String getOra() {
			return ora;
		}

		/**
		 * Setter
		 * @param ora
		 */
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
