package it.unipv.po.oocinema.controllers.client;

import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import it.unipv.po.oocinema.controllers.admin.WindowsHandler;
import it.unipv.po.oocinema.model.cinema.Film;
import it.unipv.po.oocinema.model.cinema.Proiezione;
import it.unipv.po.oocinema.persistenza.DBFacade;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Window;
import javafx.util.Duration;

public class SchedaController extends MenuController implements Initializable{


    @FXML
    private Label descrizione;

    @FXML
    private ComboBox<String> giornoCombo;

    @FXML
    private ImageView locandinaFilmSel;

    @FXML
    private  ComboBox<String> oraCombo;


    @FXML
    private ToggleButton prenota;

    @FXML
    private Label titoloFilmSel;

    @FXML
    private MediaView trailer;
    

    private static Proiezione proiezione;
    
    private DBFacade facade = new DBFacade();
    
    private File file;
    private Media media;
    private MediaPlayer mediaPlayer;

    
    public Window getWindow() {
    	return prenota.getScene().getWindow();
    }

    @FXML
    void pause(MouseEvent event) {
    	mediaPlayer.pause();
    }

    @FXML
    void play(MouseEvent event) {
    	mediaPlayer.play();
    }
    
    @FXML
    void reset(MouseEvent event) {
    	mediaPlayer.seek(Duration.seconds(0.0));
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Film f ;
		try {
			f = facade.getFilmbyTitolo(new Film(CLIController.getTitoloFilmSel()));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			f = null;
			e.printStackTrace();
		}
		
		file = new File("src/it/unipv/po/oocinema/resources/trailer/trailer.mp4");
		media = new Media(file.toURI().toString());
		mediaPlayer = new MediaPlayer(media);
		trailer.setMediaPlayer(mediaPlayer);
		
		initializeGiorno();
		descrizione.setText(f.toString());
		titoloFilmSel.setText(f.getTitolo());
		Image image = new Image(getClass().getResourceAsStream(f.getCoverPath()));
	    locandinaFilmSel.setImage(image);
		

	}
	
	
	public void initializeGiorno() {
		ArrayList<String> giorni = new ArrayList<String>();
		try {
			giorni = facade.getGiorniByFilm(new Film(CLIController.getTitoloFilmSel()));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ObservableList<String> obList = FXCollections.observableList(giorni);
        giornoCombo.getItems().clear();
        giornoCombo.setItems(obList);
		
	}
	
	
	public void initializeOra() {
		ArrayList<String> ore = new ArrayList<String>();
		Proiezione p = new Proiezione();
		p.setFilm(new Film(CLIController.getTitoloFilmSel()));
		p.setGiorno(giornoCombo.getValue());
		try {
			ore = facade.getOreByProiezione(p);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ObservableList<String> obList = FXCollections.observableList(ore);
        oraCombo.getItems().clear();
        oraCombo.setItems(obList);
		
	}
	
	 @FXML
	 void scegliOra(MouseEvent event) {
		if(giornoCombo.getValue()!=null) {
			initializeOra();
		}
	 }

	
	@FXML
    void prenota(MouseEvent event) {
		if(giornoCombo.getValue()!=null && oraCombo.getValue()!=null) {
			setProiezione(costruisciProiezione());
			WindowsHandler.openWindow(getClass(), "prenotazione.fxml");
		    WindowsHandler.closeWindow(getWindow());
		} else {
			Alert alert = new Alert(AlertType.WARNING, "Compilare i campi GIORNO e ORA");
	    	alert.showAndWait(); 
		}
    }
	
	public Proiezione costruisciProiezione() {
		Proiezione p = new Proiezione();
		p.setFilm(new Film(CLIController.getTitoloFilmSel()));
		p.setGiorno(giornoCombo.getValue());
		p.setOrario(oraCombo.getValue());
		try {
			return facade.getProiezioneByFilmGiornoOra(p);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}


	public static Proiezione getProiezione() {
		return proiezione;
	}


	public static void setProiezione(Proiezione proiezione) {
		SchedaController.proiezione = proiezione;
	}

}
