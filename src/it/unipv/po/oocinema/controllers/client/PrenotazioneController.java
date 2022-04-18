package it.unipv.po.oocinema.controllers.client;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import it.unipv.po.oocinema.controllers.admin.WindowsHandler;
import it.unipv.po.oocinema.model.cinema.Film;
import it.unipv.po.oocinema.model.cinema.Posto;
import it.unipv.po.oocinema.model.cinema.Proiezione;
import it.unipv.po.oocinema.model.cinema.Sala;
import it.unipv.po.oocinema.persistenza.DBFacade;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Window;

public class PrenotazioneController implements Initializable{

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
    private Button prenota;

    @FXML
    private ScrollPane scroll;

    @FXML
    private Label titoloFilmSel;
    
    private Proiezione proiezione;
    private Sala sala;
    private Film filmSel;
    ArrayList<Posto> posti = new ArrayList<Posto>();
    
    private DBFacade facade = new DBFacade();

    @FXML
    void esci(MouseEvent event) {
    	WindowsHandler.openWindow(getClass(), "login.fxml");
	    WindowsHandler.closeWindow(getWindow());
    }

    @FXML
    void film(MouseEvent event) {
    	WindowsHandler.openWindow(getClass(), "homeCLI.fxml");
	    WindowsHandler.closeWindow(getWindow());
    }

    @FXML
    void info(MouseEvent event) {
    	WindowsHandler.openWindow(getClass(), "info.fxml");
	    WindowsHandler.closeWindow(getWindow());
    }

    @FXML
    void ordini(MouseEvent event) {
    	WindowsHandler.openWindow(getClass(), "ordini.fxml");
	    WindowsHandler.closeWindow(getWindow());
    }

    @FXML
    void prenota(MouseEvent event) {
    	
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
        Proiezione temp = new Proiezione();
		/*
		 * try { temp.setFilm(facade.getFilmbyTitolo(new
		 * Film(CLIController.getTitoloFilmSel()))); } catch (SQLException e1) { // TODO
		 * Auto-generated catch block e1.printStackTrace(); }
		 * temp.setGiorno(SchedaController.getGiorno());
		 * temp.setOrario(SchedaController.getOra()); Proiezione proiezione =
		 * facade.getProiezioneByFilmGiornoOra(temp);
		 */
        //Sala sala = facade.getSalaByProiezione(proiezione);
        this.sala = new Sala(1,5,2);
        
		setGUIPosti();
	}
	
	public void setGUIPosti() {
		int column = 0;
        int row = 1;
		try {
            for (int i = 0; i < sala.getColonne()*sala.getRighe(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("../../view/scenes/posto.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                
                

                if (column == sala.getColonne()) {
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
	public Window getWindow() {
    	return film.getScene().getWindow();
    }
	
	public ArrayList<Posto> getPostiScelti(){
		
		for(int i = 0; i < sala.getColonne()*sala.getRighe(); i++) {
			
		}
		return posti;
	}
	
}
