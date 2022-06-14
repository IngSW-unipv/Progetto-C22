package it.unipv.po.oocinema.controllers.client;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import it.unipv.po.oocinema.controllers.WindowsHandler;
import it.unipv.po.oocinema.model.cinema.Film;
import it.unipv.po.oocinema.persistenza.DBFacade;
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

/**
 * Classe controller per la pagina home del cliente
 * @author GoF
 *
 */
public class CLIController extends ClientMenuController implements Initializable{

	/**
	 * Layout della pagina che contiene i poster
	 */
    @FXML
    private GridPane grid;
    
    /**
	 * Locandina del film selezionato
	 */
    @FXML
    private ImageView locandinaFilmSel;

    /**
	 * Bottone che porta alla scheda del film selezionato
	 */
    @FXML
    private Button schedaFilm;

    /**
	 * Pannello con scroll laterale
	 */
    @FXML
    private ScrollPane scroll;

    /**
	 * Label titolo film slezionato
	 */
    @FXML
    private Label titoloFilmSel;
    
    /**
	 * Titolo del film selezionato
	 */
    private static String titolo;

    /**
	 * Istanza della classe che comunica con il DB
	 */
    private DBFacade facade= DBFacade.getInstance();
    
    /**
	 * Listener del poster del film
	 */
    private FilmListener myListener;
    
    /**
	 * Istanza della classe che comunica con il DB
	 */
    ArrayList<Film> films = new ArrayList<Film>();
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
  
		try {
			films = facade.getTuttiFilm();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (films.size() > 0) 
           setFilmSel(films.get(0));
           
		myListener = new FilmListener() {
        	
			@Override
			public void onClickListener(Film film) {
				setFilmSel(film);
				
			}
        };
       
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < films.size(); i++) {
            	
            	FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("../../view/scenes/film_poster.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                PosterController posterController = fxmlLoader.getController();
                posterController.setData(films.get(i), myListener);
                
                if (column == 3) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row); 
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

	/**
	 * Imposta il film selezoinato nel menù a sinistra	
	 * @param film
	 */
    private void setFilmSel(Film film) {
    	titolo = film.getTitolo();        
    	titoloFilmSel.setText(film.getTitolo());
        Image image = new Image(getClass().getResourceAsStream(film.getCoverPath()));
        locandinaFilmSel.setImage(image);
    }
    
    /**
     * 
     * @return il titolo del film selezionato
     */
    public static String getTitoloFilmSel() {
    	return titolo;
    }

    /**
	 * Passa alla pagina della scheda tecnica del film selezionato
	 */
    @FXML
    void schedaFilm(MouseEvent event) {
    	WindowsHandler.openWindow(getClass(), "../../view/scenes/schedaFilm.fxml");
	    WindowsHandler.closeWindow(getWindow());
    }

}