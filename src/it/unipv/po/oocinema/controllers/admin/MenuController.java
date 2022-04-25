package it.unipv.po.oocinema.controllers.admin;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Window;

public class MenuController {
	
	@FXML
	private Label proiezione;
    @FXML
    private Label esci;
    @FXML
    private Label film;
    @FXML
    private Label cassa;
    @FXML
    private Label home;


	 @FXML
	 void cassa(MouseEvent event) {
	    WindowsHandler.openWindow(getClass(), "cassa.fxml");
	    WindowsHandler.closeWindow(getWindow());
	 }

	 @FXML
	 void esci(MouseEvent event) {
	    WindowsHandler.openWindow(getClass(), "login.fxml");
	    WindowsHandler.closeWindow(getWindow());
	 }

	 @FXML
	 void film(MouseEvent event) {
	    WindowsHandler.openWindow(getClass(), "film.fxml");
	    WindowsHandler.closeWindow(getWindow());
	 }

	 @FXML
	 void home(MouseEvent event) {
	    WindowsHandler.openWindow(getClass(), "homeADM.fxml");
	    WindowsHandler.closeWindow(getWindow());
	 }

	 @FXML
	 void proiezione(MouseEvent event) {
	    WindowsHandler.openWindow(getClass(), "proiezione.fxml");
	    WindowsHandler.closeWindow(getWindow());
	 }
	 public Window getWindow() {
	    	return home.getScene().getWindow();
	    }
	 
	 public String getNomeFile() {
			return null;
		}
}
