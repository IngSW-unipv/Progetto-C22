package it.unipv.po.oocinema.controllers.client;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import it.unipv.po.oocinema.model.cinema.Film;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.awt.image.BufferedImage;

public class PosterController {

    @FXML
    private ImageView locandina;

    @FXML
    private Label titolo;
    
    public PosterController() {
    	titolo = new Label();
    	locandina = new ImageView();
    }
    
    public void setData(Film film) {
    
        titolo.setText(film.getTitolo());
        Image image = readImage(new File(film.getCoverPath()));
        locandina.setImage(image);
    }
    
    public Image readImage(File file) { 
    	try { 
    		BufferedImage bimg = ImageIO.read(file); 
    		return SwingFXUtils.toFXImage(bimg, null); 
    		} catch( IOException e ) {
    			return null;
    	}
    	
    }

}

 

