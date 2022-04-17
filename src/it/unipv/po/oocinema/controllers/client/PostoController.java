package it.unipv.po.oocinema.controllers.client;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class PostoController {

    @FXML
    private AnchorPane anchor;

    @FXML
    private ImageView img;
    
    private Image spunta = new Image(getClass().getResourceAsStream("../../resources/spunta.png"));
    private Image sedia = new Image(getClass().getResourceAsStream("../../resources/icons8-poltrona-24.png"));

    @FXML
    void cambiaImmagine(MouseEvent event) {
    	if(img.getImage().equals(spunta)) {
    		img.setImage(sedia);
    	}else {
    		img.setImage(spunta);
    	}
    }
    
}
