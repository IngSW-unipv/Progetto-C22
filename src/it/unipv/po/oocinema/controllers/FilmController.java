package it.unipv.po.oocinema.controllers;

import java.net.URL;
import javafx.beans.value.ObservableValue;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import it.unipv.po.oocinema.model.cinema.Film;
import it.unipv.po.oocinema.persistenza.DBFacade;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Window;
import javafx.util.Callback;

public class FilmController extends MenuController{

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

    @FXML
    void aggiungiFilm(MouseEvent event) {
    	WindowsHandler.openWindow(getClass(), "aggiungiFilm.fxml");
	    WindowsHandler.closeWindow(getWindow());
	    SceneCreator.launchScene();
	    initialize();
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


	public void initialize() {
		/*
		colonnaId = new TableColumn<>("ID");
		colonnaId.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<InnerFilm, Number>, ObservableValue<Number>>() {

            @Override
            public ObservableValue<Number> call(TableColumn.CellDataFeatures<InnerFilm, Number> param) {
                return param.getValue().getId();
            }
        });
		
		colonnaTitolo = new TableColumn<>("Titolo");
		colonnaId.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<InnerFilm, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<InnerFilm, String> param) {
                return param.getValue().getTitolo();
            }
        });
		
		colonnaNumero = new TableColumn<>("Numero di Proiezioni");
		colonnaNumero.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<InnerFilm, Number>, ObservableValue<Number>>() {

            @Override
            public ObservableValue<Number> call(TableColumn.CellDataFeatures<InnerFilm, Number> param) {
                return param.getValue().getNp();
            }
        });*/
		ArrayList<Film> elencoFilm = new ArrayList<Film>();
		try {
			elencoFilm = facade.getTuttiFilm();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ObservableList<InnerFilm> righeTabella = costruisciElementiTabella(elencoFilm);

		tabella.setItems(righeTabella);
		
		
	}
	
	public ObservableList<InnerFilm> costruisciElementiTabella(ArrayList<Film> elencoFilm) {
		ObservableList<InnerFilm> righeTabella = FXCollections.observableArrayList();
		for(int i = 0; i < elencoFilm.size(); i++) {
			try {
				righeTabella.add(new InnerFilm(elencoFilm.get(i).getId(),elencoFilm.get(i).getTitolo(),facade.getNumProiezioniByFilm(elencoFilm.get(i))));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return (ObservableList<InnerFilm>) righeTabella;
	}
    
	private class InnerFilm{
		private SimpleIntegerProperty id;
		private SimpleStringProperty titolo;
		private SimpleIntegerProperty np;
		
		public InnerFilm(int id, String titolo, int np) {
			this.id = new SimpleIntegerProperty(id);
			this.titolo = new SimpleStringProperty(titolo);
			this.np = new SimpleIntegerProperty(np);
		}
		
		

		public SimpleIntegerProperty getId() {
			return id;
		}

		public void setId(SimpleIntegerProperty id) {
			this.id = id;
		}

		public SimpleStringProperty getTitolo() {
			return titolo;
		}

		public void setTitolo(SimpleStringProperty titolo) {
			this.titolo = titolo;
		}

		public SimpleIntegerProperty getNp() {
			return np;
		}

		public void setNp(SimpleIntegerProperty np) {
			this.np = np;
		}

		@Override
		public String toString() {
			return "InnerFilm [id=" + id + ", titolo=" + titolo + ", np=" + np + "]";
		}
		
		
	}

}