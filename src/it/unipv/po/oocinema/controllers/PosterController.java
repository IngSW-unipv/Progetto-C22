package it.unipv.po.oocinema.controllers;

import it.unipv.po.oocinema.model.cinema.Film;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import it.unipv.po.oocinema.controllers.MyListener;
import javafx.embed.swing.SwingFXUtils;
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
        titolo = new Label(film.getTitolo());
        Image image = readImage(new File(film.getCoverPath()));
        locandina = new ImageView(image);
    }
    
    public Image readImage(File file) { 
    	try { 
    		BufferedImage bimg = ImageIO.read(file); 
    		return SwingFXUtils.toFXImage(bimg, null); 
    		} catch( IOException e ) {
    	}
    	return null;
    }

}