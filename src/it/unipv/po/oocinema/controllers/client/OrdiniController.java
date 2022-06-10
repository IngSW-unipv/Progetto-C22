
package it.unipv.po.oocinema.controllers.client;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import it.unipv.po.oocinema.controllers.LoginController;
import it.unipv.po.oocinema.model.cinema.Posto;
import it.unipv.po.oocinema.model.prenotazione.Prenotazione;
import it.unipv.po.oocinema.persistenza.DBFacade;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Classe controller per la pagina degli ordini
 * @author GoF
 *
 */
public class OrdiniController extends ClientMenuController implements Initializable{

	/**
	 * Colonna della tabella - data acqusito
	 */
    @FXML
    private TableColumn<InnerPrenotazione, String> colonnaDataAcquisto;

    /**
	 * Colonna della tabella - film
	 */
    @FXML
    private TableColumn<InnerPrenotazione, String> colonnaFilm;

    /**
	 * Colonna della tabella - giorno
	 */
    @FXML
    private TableColumn<InnerPrenotazione, String> colonnaGiorno;

    /**
	 * Colonna della tabella - id
	 */
    @FXML
    private TableColumn<InnerPrenotazione, Integer> colonnaId;

    /**
	 * Colonna della tabella - numero di biglietti
	 */
    @FXML
    private TableColumn<InnerPrenotazione, Integer> colonnaNumTicket;

    /**
	 * Colonna della tabella - totale pagato
	 */
    @FXML
    private TableColumn<InnerPrenotazione, Double> colonnaPrezzo;

    /**
	 * Tabella degli ordini
	 */
    @FXML
    private TableView<InnerPrenotazione> tabella;

    /**
	 * Istanza della classe che comunica con il DB
	 */
    private DBFacade facade = DBFacade.getInstance();

    /**
	 * Dati contenuti nella tabella
	 */
    ObservableList<InnerPrenotazione> datiTabella = FXCollections.observableArrayList();
   
   
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		colonnaId.setCellValueFactory(new PropertyValueFactory<>("id"));
		colonnaFilm.setCellValueFactory(new PropertyValueFactory<>("film"));
		colonnaGiorno.setCellValueFactory(new PropertyValueFactory<>("giorno"));
		colonnaDataAcquisto.setCellValueFactory(new PropertyValueFactory<>("dataAcquisto"));
		colonnaNumTicket.setCellValueFactory(new PropertyValueFactory<>("numTicket"));
		colonnaPrezzo.setCellValueFactory(new PropertyValueFactory<>("prezzo"));
		
		aggiorna();
		
	}
	
	/**
	 * Aggiorna i dati nella tabella
	 */
	public void aggiorna() {
		costruisciElementiTabella(datiTabella);
		tabella.setItems(datiTabella);
	}
	
	/**
	 * Inserisce i dati nella tabella
	 * @param datiTabella
	 */
	public void costruisciElementiTabella(ObservableList<InnerPrenotazione> datiTabella) {
		
		datiTabella.removeAll(datiTabella);
		ArrayList<Prenotazione> elencoPrenotazioni = new ArrayList<Prenotazione>();
		try {
			elencoPrenotazioni = facade.getPrenotazioniFutureByCliente(LoginController.getCliente());
			ArrayList<Posto> numPosti = new ArrayList<Posto>();
		
			for(int i = 0; i < elencoPrenotazioni.size(); i++) {
				InnerPrenotazione ip = new InnerPrenotazione();
				ip.setId(elencoPrenotazioni.get(i).getId());
				ip.setFilm(elencoPrenotazioni.get(i).getProiezione().getFilm().getTitolo());
				ip.setGiorno(elencoPrenotazioni.get(i).getProiezione().getGiorno());
				ip.setDataAcquisto(elencoPrenotazioni.get(i).getDataAcquisto());
				numPosti = facade.getPostiByPrenotazione(elencoPrenotazioni.get(i));
				ip.setNumTicket(numPosti.size());
				elencoPrenotazioni.get(i).setPosti(numPosti);
				ip.setPrezzo(elencoPrenotazioni.get(i).getTotale()); // problema ogni volta che cambio sconto cambia anche prezzo
				datiTabella.add(ip);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Classe nested di prenotazione che serve per far combaciare i dati della tabella con un oggetto
	 * @author GoF
	 *
	 */
	public static class InnerPrenotazione{
		/**
		 * Id prenotazione
		 */
		int id;
		
		/**
		 * Titolo film
		 */
		String film;
		
		/**
		 * Giorno della proiezione
		 */
		String giorno;
		
		/**
		 * Data acquisto biglietti
		 */
		String dataAcquisto;
		
		/**
		 * Numero di biglietti acquistati
		 */
		int numTicket;
		
		/**
		 * Totale pagato
		 */
		double prezzo;
		
		/**
		 * Costruttore vuoto
		 */
		public InnerPrenotazione() {
			super();
		}

		/**
		 * Costruttore completto
		 * @param id
		 * @param film
		 * @param giorno
		 * @param dataAcquisto
		 * @param numTicket
		 * @param prezzo
		 */
		public InnerPrenotazione(int id, String film, String giorno, String dataAcquisto, int numTicket,
				double prezzo) {
			super();
			this.id = id;
			this.film = film;
			this.giorno = giorno;
			this.dataAcquisto = dataAcquisto;
			this.numTicket = numTicket;
			this.prezzo = prezzo;
		}
		/**
		 * Getter
		 * @return id prenotazione
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
		 * @return titolo del film
		 */
		public String getFilm() {
			return film;
		}
		
		/**
		 * Setter
		 * @param film
		 */
		public void setFilm(String film) {
			this.film = film;
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
		 * @return data d'acquisto dei biglietti
		 */
		public String getDataAcquisto() {
			return dataAcquisto;
		}

		/**
		 * Setter
		 * @param dataAcquisto
		 */
		public void setDataAcquisto(String dataAcquisto) {
			this.dataAcquisto = dataAcquisto;
		}

		/**
		 * Getter
		 * @return numero di biglietti acquistati
		 */
		public int getNumTicket() {
			return numTicket;
		}

		/**
		 * Setter
		 * @param numTicket
		 */
		public void setNumTicket(int numTicket) {
			this.numTicket = numTicket;
		}

		/**
		 * Getter
		 * @return prezzo pagato
		 */
		public double getPrezzo() {
			return prezzo;
		}

		/**
		 * Setter
		 * @param prezzo
		 */
		public void setPrezzo(double prezzo) {
			this.prezzo = prezzo;
		}
		
	}
}
