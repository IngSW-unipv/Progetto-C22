package it.unipv.po.oocinema.controllers.client;

import it.unipv.po.oocinema.controllers.client.MyListener;
import it.unipv.po.oocinema.model.cinema.Film;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;


public class PosterController {

    @FXML
    private ImageView locandina = new ImageView();

    @FXML
    private Label titolo = new Label();
    
   private MyListener myListener;

   Film f = new Film("Prova");
  
    public void setData(Film film, MyListener myListener) {
    	f.setId(2);
    	f.setCoverPath("../resources/locandine/download.png");
        this.myListener = myListener;
        titolo.setText(f.getTitolo());
        //Image image = readImage(new File(f.getCoverPath()));
        locandina.setImage(new Image(getClass().getResourceAsStream("../resources/locandine/download.png")));
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