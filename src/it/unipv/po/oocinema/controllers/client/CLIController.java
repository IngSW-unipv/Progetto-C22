package it.unipv.po.oocinema.controllers.client;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import it.unipv.po.oocinema.controllers.admin.WindowsHandler;
import it.unipv.po.oocinema.model.cinema.Film;
import it.unipv.po.oocinema.persistenza.DBFacade;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Window;


public class CLIController extends MenuController implements Initializable {

	DBFacade facade = new DBFacade();

    @FXML
    private GridPane grid;


    @FXML
    private ImageView locandinaFilmSel;

    @FXML
    private Button schedaFilm;

    @FXML
    private ScrollPane scroll;

    @FXML
    private static Label titoloFilmSel = new Label();

    
    private Image image;
    
    
    ArrayList<Film> films = new ArrayList<Film>();
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			films = facade.getTuttiFilm();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
       
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < films.size(); i++) {
            	
            	
                AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("../../view/scenes/film_poster.fxml"));

                PosterController posterController = new PosterController();
                posterController.setData(films.get(i));

                if (column == 3) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

		
    private void setFilmSel(Film film) {
        titoloFilmSel.setText(film.getTitolo());
        image = readImage(new File(film.getCoverPath()));
        locandinaFilmSel.setImage(image);
    }
    
    public static String getTitoloFilmSel() {
    	
    	return titoloFilmSel.getText();
		
    }

    public Image readImage(File file) { 
    	try { 
    		BufferedImage bimg = ImageIO.read(file); 
    		return SwingFXUtils.toFXImage(bimg, null); 
    		} catch( IOException e ) {
    	}
    	return null;
    }

    @Override
    public Window getWindow() {
    	return scroll.getScene().getWindow();
    }
	

}