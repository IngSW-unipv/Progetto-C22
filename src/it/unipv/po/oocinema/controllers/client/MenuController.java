package it.unipv.po.oocinema.controllers.client;

import it.unipv.po.oocinema.controllers.admin.WindowsHandler;
import it.unipv.po.oocinema.model.acquirenti.Acquirente;
import it.unipv.po.oocinema.model.acquirenti.Cliente;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Window;

public class MenuController {

    @FXML
    private Label esci;

    @FXML
    private Label film;

    @FXML
    private Label info;

    @FXML
    private Label ordini;
    
    private static Acquirente user;

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
    
    public static Acquirente getCliente() {
    	return user;
    }
    
    public static void setCliente(Acquirente c) {
    	user = c;
    }
}
