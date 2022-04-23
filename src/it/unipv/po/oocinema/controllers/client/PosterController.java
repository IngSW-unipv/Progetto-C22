package it.unipv.po.oocinema.controllers.client;

import it.unipv.po.oocinema.model.cinema.Film;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class PosterController {

    @FXML
    private ImageView locandina;

    @FXML
    private Label titolo;
    
    private Film film;
    
    private MyListener myListener;


	 @FXML
	 void click(MouseEvent event) {
		 myListener.onClickListener(film);
	 }
	 
	 public void setData(Film film, MyListener myListener) {
		 this.film = film;
	     this.myListener = myListener;
	     titolo.setText(film.getTitolo());
	     Image image = new Image(getClass().getResourceAsStream(film.getCoverPath()));
	     locandina.setImage(image);
	}
	
}

 

