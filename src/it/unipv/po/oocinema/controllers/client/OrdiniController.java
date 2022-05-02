
package it.unipv.po.oocinema.controllers.client;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import it.unipv.po.oocinema.controllers.LoginController;
import it.unipv.po.oocinema.model.prenotazione.Prenotazione;
import it.unipv.po.oocinema.persistenza.DBFacade;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class OrdiniController extends ClientMenuController implements Initializable{

    @FXML
    private TableColumn<InnerPrenotazione, String> colonnaDataAcquisto;

    @FXML
    private TableColumn<InnerPrenotazione, String> colonnaFilm;

    @FXML
    private TableColumn<InnerPrenotazione, String> colonnaGiorno;

    @FXML
    private TableColumn<InnerPrenotazione, Integer> colonnaId;

    @FXML
    private TableColumn<InnerPrenotazione, Integer> colonnaNumTicket;

    @FXML
    private TableColumn<InnerPrenotazione, Double> colonnaPrezzo;

    @FXML
    private TableView<InnerPrenotazione> tabella;

    private DBFacade facade = DBFacade.getInstance();

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
	
	public void aggiorna() {
		costruisciElementiTabella(datiTabella);
		tabella.setItems(datiTabella);
	}
	
	public void costruisciElementiTabella(ObservableList<InnerPrenotazione> datiTabella) {
		
		datiTabella.removeAll(datiTabella);
		ArrayList<Prenotazione> elencoPrenotazioni = new ArrayList<Prenotazione>();
		try {
			elencoPrenotazioni = facade.getPrenotazioniFutureByCliente(LoginController.getCliente());
			int numPosti;
		
			for(int i = 0; i < elencoPrenotazioni.size(); i++) {
				InnerPrenotazione ip = new InnerPrenotazione();
				ip.setId(elencoPrenotazioni.get(i).getId());
				ip.setFilm(elencoPrenotazioni.get(i).getProiezione().getFilm().getTitolo());
				ip.setGiorno(elencoPrenotazioni.get(i).getProiezione().getGiorno());
				ip.setDataAcquisto(elencoPrenotazioni.get(i).getDataAcquisto());
				numPosti = facade.getNumPostiByPrenotazione(elencoPrenotazioni.get(i));
				ip.setNumTicket(numPosti);
				elencoPrenotazioni.get(i).setNumPosti(numPosti);
				ip.setPrezzo(elencoPrenotazioni.get(i).getTotale()); // problema ogni volta che cambio sconto cambia anche prezzo
				datiTabella.add(ip);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public class InnerPrenotazione{
		int id;
		String film;
		String giorno;
		String dataAcquisto;
		int numTicket;
		double prezzo;
		
		public InnerPrenotazione() {
			super();
		}

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

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getFilm() {
			return film;
		}

		public void setFilm(String film) {
			this.film = film;
		}

		public String getGiorno() {
			return giorno;
		}

		public void setGiorno(String giorno) {
			this.giorno = giorno;
		}

		public String getDataAcquisto() {
			return dataAcquisto;
		}

		public void setDataAcquisto(String dataAcquisto) {
			this.dataAcquisto = dataAcquisto;
		}

		public int getNumTicket() {
			return numTicket;
		}

		public void setNumTicket(int numTicket) {
			this.numTicket = numTicket;
		}

		public double getPrezzo() {
			return prezzo;
		}

		public void setPrezzo(double prezzo) {
			this.prezzo = prezzo;
		}
		
	}
}
