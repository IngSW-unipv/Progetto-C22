package it.unipv.po.oocinema.controllers.client;

import java.io.File;

import java.net.URL;

import java.util.ResourceBundle;

import it.unipv.po.oocinema.controllers.WindowsHandler;

import it.unipv.po.oocinema.persistenza.DBFacade;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import javafx.stage.Window;

public class SchedaController implements Initializable{


    @FXML
    private Label descrizione;
    @FXML
    private Label trailer;

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
    private ComboBox<String> oraCombo;

    @FXML
    private Label ordini;

    @FXML
    private ToggleButton prenota;

    @FXML
    private Label titoloFilmSel;

    
    
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
		 * Film film = null; try { film = facade.getFilmbyTitolo(new
		 * Film(CLIController.getTitoloFilmSel())); // film che ci sono nelle proiezioni
		 * } catch (SQLException e1) { // TODO Auto-generated catch block
		 * e1.printStackTrace(); }
		 */
		
		descrizione.setText("ciao");
		trailer = new Label();
		trailer.setText("https://youtu.be/i0in1cRXgE8");
		
		
	}
	
	@FXML
    void prenota(MouseEvent event) {
		WindowsHandler.openWindow(getClass(), "prenotazione.fxml");
	    WindowsHandler.closeWindow(getWindow());
    }

}
