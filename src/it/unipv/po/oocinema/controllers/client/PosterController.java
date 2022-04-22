package it.unipv.po.oocinema.controllers.client;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import it.unipv.po.oocinema.model.cinema.Film;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class PosterController implements Initializable {

    @FXML
    private ImageView locandina;

    @FXML
    private Label titolo;
    
    private static Film film;
    
    private static MyListener myListener;


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		titolo.setText(film.getTitolo());
		Image image = new Image(getClass().getResourceAsStream(film.getCoverPath()));
        locandina.setImage(image);
		
	}

	 @FXML
	 void click(MouseEvent event) {
		 myListener.onClickListener(film);
	 }

	
	public static void setFilm(Film f) {
		film = f;
	}

	public static void setListener(MyListener l) {
		myListener =  l;
	}
}

 

