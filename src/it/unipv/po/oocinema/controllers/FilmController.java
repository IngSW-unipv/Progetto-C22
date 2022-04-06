package it.unipv.po.oocinema.controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import it.unipv.po.oocinema.model.cinema.Film;
import it.unipv.po.oocinema.persistenza.DBFacade;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
    private TableColumn<InnerFilm, Integer> colonnaId;

    @FXML
    private TableColumn<InnerFilm, Integer> colonnaNumero;

    @FXML
    private TableColumn<InnerFilm, String> colonnaTitolo;

    @FXML
    void aggiungiFilm(MouseEvent event) {
    	WindowsHandler.openWindow(getClass(), "aggiungiFilm.fxml");
	    WindowsHandler.closeWindow(getWindow());
    }


    @FXML
    void rimuoviFilm(MouseEvent event) {
    	Alert alert = new Alert(AlertType.CONFIRMATION, "Rimuovendo il film verranno eliminate tutte le proiezioni e prenotazioni ad esso associate");
    	alert.showAndWait();
    	
    	Film f = new Film();
    	f.setId(Integer.parseInt(idFilm.getText()));
    	try {
			facade.rimuoviFilm(f);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		
		ArrayList<Film> elencoFilm = new ArrayList<Film>();
		try {
			elencoFilm = facade.getTuttiFilm();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<InnerFilm> righeTabella = costruisciElementiTabella(elencoFilm);
		
		tabella.getColumns().add(colonnaId);
		tabella.getColumns().add(colonnaTitolo);
		tabella.getColumns().add(colonnaNumero);
		
		for(InnerFilm riga : righeTabella) {
			tabella.getItems().add(riga);
		}
		
		
	}
	
	public ArrayList<InnerFilm> costruisciElementiTabella(ArrayList<Film> elencoFilm) {
		ArrayList<InnerFilm> righeTabella = new ArrayList<InnerFilm>();
		for(int i = 0; i < elencoFilm.size(); i++) {
			try {
				righeTabella.add(new InnerFilm(elencoFilm.get(i).getId(),elencoFilm.get(i).getTitolo(),facade.getNumProiezioniByFilm(elencoFilm.get(i))));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return righeTabella;
	}
    
	private class InnerFilm{
		private int id;
		private String titolo;
		private int np;
		
		public InnerFilm(int id, String titolo, int np) {
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
		
		
	}

}