package it.unipv.po.oocinema.controllers.client;


import java.net.URL;
import java.util.ResourceBundle;

import it.unipv.po.oocinema.controllers.WindowsHandler;
import it.unipv.po.oocinema.model.acquirenti.Acquirente;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Window;

public class ClientMenuController implements Initializable{

    @FXML
    private Label esci;

    @FXML
    private Label film;

    @FXML
    private Label info;
    @FXML
    private ImageView logo;

    @FXML
    private Label ordini;

    @FXML
    void esci(MouseEvent event) {
    	WindowsHandler.openWindow(getClass(), "../../view/scenes/login.fxml");
	    WindowsHandler.closeWindow(getWindow());
    }

    @FXML
    void film(MouseEvent event) {
    	WindowsHandler.openWindow(getClass(), "../../view/scenes/homeCLI.fxml");
	    WindowsHandler.closeWindow(getWindow());
    }

    @FXML
    void info(MouseEvent event) {
    	WindowsHandler.openWindow(getClass(), "../../view/scenes/info.fxml");
	    WindowsHandler.closeWindow(getWindow());
    }

    @FXML
    void ordini(MouseEvent event) {
    	WindowsHandler.openWindow(getClass(), "../../view/scenes/ordini.fxml");
	    WindowsHandler.closeWindow(getWindow());
    }
    
    public Window getWindow() {
    	return film.getScene().getWindow();
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Image image = new Image(getClass().getResourceAsStream("../../resources/logo.png"));
        logo.setImage(image);
		
	}
}
