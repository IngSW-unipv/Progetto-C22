package it.unipv.po.oocinema.controllers.client;

import it.unipv.po.oocinema.model.cinema.Film;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * Classe controller per la gestione dei poster del film
 * @author GoF
 *
 */
public class PosterController {

	/**
	 * Locandina del film
	 */
    @FXML
    private ImageView locandina;

    /**
     * Label Titolo del film
     */
    @FXML
    private Label titolo;
    
    /**
     * Film contenuto nel poster
     */
    private Film film;
    
    /**
     * Listener per click del poster
     */
    private FilmListener myListener;


	 @FXML
	 void click(MouseEvent event) {
		 myListener.onClickListener(film);
	 }
	 /**
	  * Impostazione iniziale dell'interfaccia 
	  * @param film
	  * @param myListener
	  */
	 public void setData(Film film, FilmListener myListener) {
		 this.film = film;
	     this.myListener = myListener;
	     titolo.setText(film.getTitolo());
	     Image image = new Image(getClass().getResourceAsStream(film.getCoverPath()));
	     locandina.setImage(image);
	}
	
}

 

