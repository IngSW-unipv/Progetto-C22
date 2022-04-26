package it.unipv.po.oocinema.controllers.admin;

import it.unipv.po.oocinema.controllers.WindowsHandler;
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
	    WindowsHandler.openWindow(getClass(), "../../view/scenes/cassa.fxml");
	    WindowsHandler.closeWindow(getWindow());
	 }

	 @FXML
	 void esci(MouseEvent event) {
	    WindowsHandler.openWindow(getClass(), "../../view/scenes/login.fxml");
	    WindowsHandler.closeWindow(getWindow());
	 }

	 @FXML
	 void film(MouseEvent event) {
	    WindowsHandler.openWindow(getClass(), "../../view/scenes/film.fxml");
	    WindowsHandler.closeWindow(getWindow());
	 }

	 @FXML
	 void home(MouseEvent event) {
	    WindowsHandler.openWindow(getClass(), "../../view/scenes/homeADM.fxml");
	    WindowsHandler.closeWindow(getWindow());
	 }

	 @FXML
	 void proiezione(MouseEvent event) {
	    WindowsHandler.openWindow(getClass(), "../../view/scenes/proiezione.fxml");
	    WindowsHandler.closeWindow(getWindow());
	 }
	 public Window getWindow() {
	    	return home.getScene().getWindow();
	    }
}
