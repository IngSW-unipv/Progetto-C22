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
import javafx.stage.Window;

public class FilmController extends MenuController implements Initializable{

	private final String NOMEFILE = "film.fxml";
	
	private DBFacade facade = new DBFacade();
	
    @FXML
    private Button aggiungi;
   
    @FXML
    private Button rimuovi;
    
    @FXML
    private TextField idFilm;

    @FXML
    private TableView<InnerFilm> tabella;
    
    @FXML
    private TableColumn<InnerFilm,Integer> colonnaId;

    @FXML
    private TableColumn<InnerFilm,Integer> colonnaNumero;

    @FXML
    private TableColumn<InnerFilm,String> colonnaTitolo;
    
    ObservableList<InnerFilm> datiTabella = FXCollections.observableArrayList();

    @FXML
    void aggiungiFilm(MouseEvent event) {
    	WindowsHandler.openWindow(getClass(), "aggiungiFilm.fxml");
	    WindowsHandler.closeWindow(getWindow());
    }


    @FXML
    void rimuoviFilm(MouseEvent event) {
    	Alert alert = new Alert(AlertType.CONFIRMATION, "Verranno rimossi tutti i dati associati al film");
    	alert.showAndWait(); 
    	
    	if(alert.getResult().equals(ButtonType.OK)) {
    	Film f = new Film();
    	f.setId(Integer.parseInt(idFilm.getText()));
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
	public Window getWindow() {
		return rimuovi.getScene().getWindow();
	}


	@Override
	public String getNomeFile() {
		return NOMEFILE;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		colonnaId.setCellValueFactory(new PropertyValueFactory<>("id"));
		colonnaTitolo.setCellValueFactory(new PropertyValueFactory<>("titolo"));
		colonnaNumero.setCellValueFactory(new PropertyValueFactory<>("np"));
		
		aggiorna();
		
	}
	
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
	
	public void aggiorna() {
		costruisciElementiTabella(datiTabella);
		tabella.setItems(datiTabella);
	}
    
	 public class InnerFilm{
		 int id;
		 String titolo;
		 int np;

		public InnerFilm(int id, String titolo, int np) {
			super();
			this.id = id;
			this.titolo = titolo;
			this.np = np;
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

		public int getNp() {
			return np;
		}

		public void setNp(int np) {
			this.np = np;
		}

		@Override
		public String toString() {
			return "InnerFilm [id=" + id + ", titolo=" + titolo + ", np=" + np + "]";
		}
		
		
	}



}