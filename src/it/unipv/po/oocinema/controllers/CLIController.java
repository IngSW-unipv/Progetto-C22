package it.unipv.po.oocinema.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

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


public class CLIController implements Initializable {

	DBFacade facade = new DBFacade();
    @FXML
    private Label esci;

    @FXML
    private Label film;

    @FXML
    private GridPane grid;

    @FXML
    private Label info;

    @FXML
    private ImageView locandinaFilmSel;

    @FXML
    private Label ordini;

    @FXML
    private Button schedaFilm;

    @FXML
    private ScrollPane scroll;

    @FXML
    private Label titoloFilmSel;
    
    private Image image;
    
    private MyListener myListener;
    
    ArrayList<Film> films = new ArrayList<Film>();
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			films = facade.getTuttiFilm();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        if (films.size() > 0) {
            setFilmSel(films.get(0));
            myListener = new MyListener() {
            	
				@Override
				public void onClickListener(Film film) {
					setFilmSel(film);
					
				}
            };
        }
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < films.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/views/film_poster.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                PosterController posterController = fxmlLoader.getController();
                posterController.setData(films.get(i),myListener);

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
        System.out.println(film.getCoverPath());
        image = new Image(getClass().getResourceAsStream(film.getCoverPath()));
        locandinaFilmSel.setImage(image);
    }

    @FXML
    void esci(MouseEvent event) {

    }

    @FXML
    void film(MouseEvent event) {

    }

    @FXML
    void info(MouseEvent event) {

    }

    @FXML
    void ordini(MouseEvent event) {

    }

    @FXML
    void schedaFilm(MouseEvent event) {

    }

    
	

}