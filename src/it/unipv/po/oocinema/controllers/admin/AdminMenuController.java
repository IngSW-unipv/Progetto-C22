package it.unipv.po.oocinema.controllers.admin;


import java.net.URL;
import java.util.ResourceBundle;

import it.unipv.po.oocinema.controllers.LoginController;
import it.unipv.po.oocinema.controllers.WindowsHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Window;

/**
 * Classe per la gestione del menu laterale dell'interfaccia dell'amministratore
 * @author GoF
 *
 */
public class AdminMenuController implements Initializable{
	
	/**
	 * Label proiezione
	 */
	@FXML
	private Label proiezione;
	
	/**
	 * Label messaggio home
	 */
	@FXML
	private Label messaggio = new Label();
	
	/**
	 * Label esci
	 */
    @FXML
    private Label esci;
    
    /**
     * Label film
     */
    @FXML
    private Label film;
    
    /**
     * Label cassa
     */
    @FXML
    private Label cassa;
    
    /**
     * Label home
     */
    @FXML
    private Label home;


    /**
     * Passa alla pagina di gestione della cassa
     */
	 @FXML
	 void cassa(MouseEvent event) {
	    WindowsHandler.openWindow(getClass(), "../../view/scenes/cassa.fxml");
	    WindowsHandler.closeWindow(getWindow());
	 }

	 /**
     * Passa alla pagina di login
     */
	 @FXML
	 void esci(MouseEvent event) {
	    WindowsHandler.openWindow(getClass(), "../../view/scenes/login.fxml");
	    WindowsHandler.closeWindow(getWindow());
	 }

	 /**
	  * Passa alla pagina di gestione dei film
	  */
	 @FXML
	 void film(MouseEvent event) {
	    WindowsHandler.openWindow(getClass(), "../../view/scenes/film.fxml");
	    WindowsHandler.closeWindow(getWindow());
	 }

	 /**
	  * Passa alla pagina home
	  */
	 @FXML
	 void home(MouseEvent event) {
	    WindowsHandler.openWindow(getClass(), "../../view/scenes/homeADM.fxml");
	    WindowsHandler.closeWindow(getWindow());
	 }

	 /**
	  * Passa alla pagina di gestione delle proiezioni
	  */
	 @FXML
	 void proiezione(MouseEvent event) {
	    WindowsHandler.openWindow(getClass(), "../../view/scenes/proiezione.fxml");
	    WindowsHandler.closeWindow(getWindow());
	 }
	 
	 /**
	  * @return la finestra corrente
	  */
	 public Window getWindow() {
	    	return home.getScene().getWindow();
	    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		messaggio.setText("Ciao " + LoginController.getCliente().getUser()+", \na lato puoi trovare il menù che puoi utilizzare \n"
				+ "per gestire il tuo cinema");
	}

}
