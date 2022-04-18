package it.unipv.po.oocinema.controllers.client;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import it.unipv.po.oocinema.controllers.admin.WindowsHandler;
import it.unipv.po.oocinema.model.cinema.Film;
import it.unipv.po.oocinema.model.cinema.Proiezione;
import it.unipv.po.oocinema.persistenza.DBFacade;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import javafx.stage.Window;

public class SchedaController implements Initializable{


    @FXML
    private Label descrizione;

    @FXML
    private Label esci;

    @FXML
    private Label film;

    @FXML
    private ComboBox<String> giornoCombo;

    @FXML
    private Label info;

    @FXML
    private ImageView locandinaFilmSel;

    @FXML
    private  ComboBox<String> oraCombo;

    @FXML
    private Label ordini;

    @FXML
    private ToggleButton prenota;

    @FXML
    private Label titoloFilmSel;

    @FXML
    private Label trailer;
    
    private static String giorno;
    private static String ora;


    
    DBFacade facade = new DBFacade();

    @FXML
    void esci(MouseEvent event) {
    	WindowsHandler.openWindow(getClass(), "login.fxml");
	    WindowsHandler.closeWindow(getWindow());
    }
    

    @FXML
    void film(MouseEvent event) {
    	WindowsHandler.openWindow(getClass(), "homeCLI.fxml");
	    WindowsHandler.closeWindow(getWindow());
    }
    

    @FXML
    void info(MouseEvent event) {
    	WindowsHandler.openWindow(getClass(), "info.fxml");
	    WindowsHandler.closeWindow(getWindow());
    }
    

    @FXML
    void ordini(MouseEvent event) {
    	WindowsHandler.openWindow(getClass(), "ordini.fxml");
	    WindowsHandler.closeWindow(getWindow());
    
    }
    
    public Window getWindow() {
    	return film.getScene().getWindow();
    }


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		/*
		 * Film film; try { film = facade.getFilmbyTitolo(new
		 * Film(CLIController.getTitoloFilmSel())); // film che ci sono nelle proiezioni
		 * } catch (SQLException e1) { // TODO Auto-generated catch block
		 * e1.printStackTrace(); film = null; }
		 */
		 
		initializeGiorno();
		descrizione.setText("ciao");
		trailer.setText("https://youtu.be/i0in1cRXgE8");
		oraCombo.setDisable(true);
		
		
	}
	
	
	public void initializeGiorno() {
		ArrayList<String> giorni = new ArrayList<String>();
		try {
			giorni = facade.getGiorniByFilm(new Film(CLIController.getTitoloFilmSel()));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ObservableList<String> obList = FXCollections.observableList(giorni);
        giornoCombo.getItems().clear();
        giornoCombo.setItems(obList);
		
	}
	
	
	public void initializeOra() {
		ArrayList<String> ore = new ArrayList<String>();
		Proiezione p = new Proiezione();
		p.setFilm(new Film(CLIController.getTitoloFilmSel()));
		p.setGiorno(giornoCombo.getValue());
		try {
			ore = facade.getOreByProiezione(p);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ObservableList<String> obList = FXCollections.observableList(ore);
        oraCombo.getItems().clear();
        oraCombo.setItems(obList);
		
	}
	
	 @FXML
	 void scegliOra(ActionEvent event) {
		if(giornoCombo.getValue()!=null) {
			initializeOra();
			oraCombo.setDisable(false);
		}
	 }

	
	@FXML
    void prenota(MouseEvent event) {
		if(giornoCombo.getValue()!=null && oraCombo.getValue()!=null) {
			WindowsHandler.openWindow(getClass(), "prenotazione.fxml");
		    WindowsHandler.closeWindow(getWindow());
		} else {
			Alert alert = new Alert(AlertType.WARNING, "Compilare i campi GIORNO e ORA");
	    	alert.showAndWait(); 
		}
    }

}
