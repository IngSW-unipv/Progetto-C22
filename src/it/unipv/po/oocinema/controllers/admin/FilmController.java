package it.unipv.po.oocinema.controllers.admin;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import it.unipv.po.oocinema.controllers.WindowsHandler;
import it.unipv.po.oocinema.model.cinema.Film;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;

/**
 * Classe controller per la gestione dei film
 * @author GoF
 *
 */
public class FilmController extends AdminMenuController implements Initializable{
	
	/**
	 * Istanza della classe che comunica con il DB
	 */
	private DBFacade facade = DBFacade.getInstance();
	
	/**
	 * Bottone per passare alal finestra di aggiunta di un nuovo film
	 */
    @FXML
    private Button aggiungi;
   
    /**
	 * Bottone per la rimozione del film selezionato
	 */
    @FXML
    private Button rimuovi;
    
    /**
	 * Id del film da rimuovere
	 */
    @FXML
    private TextField idFilm;

    /**
	 * Tabella dei film
	 */
    @FXML
    private TableView<InnerFilm> tabella;
    
    /**
	 * Colonna tabella - id del film
	 */
    @FXML
    private TableColumn<InnerFilm,Integer> colonnaId;

    /**
	 * Colonna tabella - numero di proiezioni associate con quel film
	 */
    @FXML
    private TableColumn<InnerFilm,Integer> colonnaNumero;

    /**
	 * Colonna tabella - titolo del film
	 */
    @FXML
    private TableColumn<InnerFilm,String> colonnaTitolo;
    
    /**
	 * Dati da inserire nella tabella
	 */
    private ObservableList<InnerFilm> datiTabella = FXCollections.observableArrayList();

    /**
     * Passa alla pagina di aggiunta di un nuovo film
     */
    @FXML
    void aggiungiFilm(MouseEvent event) {
    	WindowsHandler.openWindow(getClass(), "../../view/scenes/aggiungiFilm.fxml");
	    WindowsHandler.closeWindow(getWindow());
    }

    /**
     * Rimuove il film selezionato
     */
    @FXML
    void rimuoviFilm(MouseEvent event) {
    	Alert alert = new Alert(AlertType.CONFIRMATION, "Verranno rimossi tutti i dati associati al film");
    	alert.showAndWait(); 
    	
    	if(alert.getResult().equals(ButtonType.OK)) {
    	Film f = new Film();
    	try {
    		f.setId(Integer.parseInt(idFilm.getText()));
    	}catch(Exception e) {
    		Alert al = new Alert(AlertType.WARNING, "Controlla il dato inserito");
    		al.showAndWait(); }
    	try {
			facade.rimuoviFilm(f);	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	aggiorna();
    	}
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		colonnaId.setCellValueFactory(new PropertyValueFactory<>("id"));
		colonnaTitolo.setCellValueFactory(new PropertyValueFactory<>("titolo"));
		colonnaNumero.setCellValueFactory(new PropertyValueFactory<>("np"));
		
		aggiorna();
		
	}
	
	/**
	 * Inserisce i daiti nella tabella
	 * @param datiTabella
	 */
	public  void costruisciElementiTabella(ObservableList<InnerFilm> datiTabella) {
		
		datiTabella.removeAll(datiTabella);
		ArrayList<Film> elencoFilm = new ArrayList<Film>();
		try {
			elencoFilm = facade.getTuttiFilm();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		for(int i = 0; i < elencoFilm.size(); i++) {
			try {
				datiTabella.add(new InnerFilm(elencoFilm.get(i).getId(),elencoFilm.get(i).getTitolo(),facade.getNumProiezioniByFilm(elencoFilm.get(i))));
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Aggiorna il contenuto della tabella
	 */
	public void aggiorna() {
		costruisciElementiTabella(datiTabella);
		tabella.setItems(datiTabella);
	}
    
	/**
	 * Classe nested di film che serve per far combaciare i dati della tabella con un oggetto
	 * @author GoF
	 *
	 */
	 public static class InnerFilm{
		 
		 /**
		  * Id del film
		  */
		 private int id;
		 
		 /**
		  * Titolo del film
		  */
		 private String titolo;
		 
		 /**
		  * Numero di proiezioni
		  */
		 private int np;

		 /**
		  * Costruttore completo.
		  * @param id
		  * @param titolo
		  * @param np
		  */
		public InnerFilm(int id, String titolo, int np) {
			super();
			this.id = id;
			this.titolo = titolo;
			this.np = np;
		}

		/**
		 * Getter
		 * @return id del titolo
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
		 * @return numero di prenotazioni
		 */
		public int getNp() {
			return np;
		}

		/**
		 * Setter
		 * @param np
		 */
		public void setNp(int np) {
			this.np = np;
		}

		@Override
		public String toString() {
			return "InnerFilm [id=" + id + ", titolo=" + titolo + ", np=" + np + "]";
		}
		
		
	}



}