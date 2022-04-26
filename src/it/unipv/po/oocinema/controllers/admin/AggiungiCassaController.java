package it.unipv.po.oocinema.controllers.admin;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import it.unipv.po.oocinema.model.acquirenti.Cassa;
import it.unipv.po.oocinema.persistenza.DBFacade;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class AggiungiCassaController extends AdminMenuController implements Initializable{

	private DBFacade facade = new DBFacade();
	
    @FXML
    private TextField password;

    @FXML
    private ImageView logo;

	@FXML
	private TextField username;
	
	@FXML
    private ToggleButton aggiungi;


	@FXML
	public void aggiungiCassa(MouseEvent event) {
    	Cassa c = new Cassa(username.getText(),password.getText());
    	try {
    		facade.aggiungiCassa(c);
    		System.out.println("va");
    	}catch(SQLException e) {
    		
    	}
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Image image = new Image(getClass().getResourceAsStream("../../resources/logo.png"));
        logo.setImage(image);
		
	}

}
