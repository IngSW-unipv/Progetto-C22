package it.unipv.po.oocinema.controllers.client;


import it.unipv.po.oocinema.controllers.WindowsHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Window;

/**
 * Classe che gestisce il menu delle interfacce del cliente
 * @author GoF
 *
 */
public class ClientMenuController {

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
	 * Label info
	 */
    @FXML
    private Label info;
   
    /**
	 * Label ordini
	 */
    @FXML
    private Label ordini;

    /**
	 * Passa alla pagina di login
	 */
    @FXML
    void esci(MouseEvent event) {
    	WindowsHandler.openWindow(getClass(), "../../view/scenes/login.fxml");
	    WindowsHandler.closeWindow(getWindow());
    }

    /**
	 * Passa alla pagina home del cliente
	 */
    @FXML
    void film(MouseEvent event) {
    	WindowsHandler.openWindow(getClass(), "../../view/scenes/homeCLI.fxml");
	    WindowsHandler.closeWindow(getWindow());
    }

    /**
	 * Passa alla pagina di info
	 */
    @FXML
    void info(MouseEvent event) {
    	WindowsHandler.openWindow(getClass(), "../../view/scenes/info.fxml");
	    WindowsHandler.closeWindow(getWindow());
    }

    /**
	 * Passa alla pagina degli ordini 
	 */
    @FXML
    void ordini(MouseEvent event) {
    	WindowsHandler.openWindow(getClass(), "../../view/scenes/ordini.fxml");
	    WindowsHandler.closeWindow(getWindow());
    }
    
    /**
	 * @return la finestra corrente
	 */
    public Window getWindow() {
    	return film.getScene().getWindow();
    }

}
