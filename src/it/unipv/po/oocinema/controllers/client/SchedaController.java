package it.unipv.po.oocinema.controllers.client;

import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import it.unipv.po.oocinema.controllers.WindowsHandler;
import it.unipv.po.oocinema.model.cinema.Film;
import it.unipv.po.oocinema.model.cinema.Proiezione;
import it.unipv.po.oocinema.persistenza.DBFacade;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.util.Duration;

/**
 * Classe controller per la scheda tecnica del film
 * @author GoF
 *
 */
public class SchedaController extends ClientMenuController implements Initializable{

	/**
	 * Label descrizione
	 */
    @FXML
    private Label descrizione;

    /**
	 * Elenco dei giorni disponibili
	 */
    @FXML
    private ComboBox<String> giornoCombo;

    /**
	 * Locandina del film
	 */
    @FXML
    private ImageView locandinaFilmSel;

    /**
	 * Elenco delle ore disponibili
	 */
    @FXML
    private ComboBox<String> oraCombo;
    
    /**
	 * Bottone per passare alla pagina di prenotazione
	 */
    @FXML
    private ToggleButton prenota;

    /**
	 * Label titolo film
	 */
    @FXML
    private Label titoloFilmSel;

    /**
	 * Video trailer del film
	 */
    @FXML
    private MediaView trailer;
    
    /**
	 * Proiezione scelta
	 */
    private static Proiezione proiezione;
    
    /**
	 * Istanza della classe che comunica con il DB
	 */
    private DBFacade facade = DBFacade.getInstance();
    
    /**
	 * File del trailer
	 */
    private File file;
    
    /**
	 * Media del trailer
	 */
    private Media media;
    
    /**
	 * MediaPlayer del trailer
	 */
    private MediaPlayer mediaPlayer;

 
    /**
     * Mette in pausa il video
     */
    @FXML
    void pause(MouseEvent event) {
    	mediaPlayer.pause();
    }

    /**
     * Riavvia il video
     */
    @FXML
    void play(MouseEvent event) {
    	mediaPlayer.play();
    }
    
    /**
     * Resetta il video
     */
    @FXML
    void reset(MouseEvent event) {
    	mediaPlayer.seek(Duration.seconds(0.0));
    }

    /**
     * Inizializza la pagina
     */
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
	
	/**
	 * Inizializza l'elenco dei giorni
	 */
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
	
	/**
	 * Inizializza l'elenco delle ore
	 */
	public void initializeOra() {
		ArrayList<String> ore = new ArrayList<String>();
		Proiezione p = new Proiezione();
		try {
			p.setFilm(facade.getFilmbyTitolo(new Film(CLIController.getTitoloFilmSel())) );
			p.setGiorno(giornoCombo.getValue());
		
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

	/**
	 * Passa alla pagina di prenotazione
	 */
	@FXML
    void prenota(MouseEvent event) {
		if(giornoCombo.getValue()!=null && oraCombo.getValue()!=null) {
			costruisciProiezione();
			WindowsHandler.openWindow(getClass(), "../../view/scenes/prenotazioneNOGRAFICA.fxml");
		    WindowsHandler.closeWindow(getWindow());
		} else {
			Alert alert = new Alert(AlertType.WARNING, "Compilare i campi GIORNO e ORA");
	    	alert.showAndWait(); 
		}
    }
	
	 /**
     * Costruisce la proiezione in base ai dati inseriti
     */
	public void costruisciProiezione() {
		Proiezione p = new Proiezione();
		try {
			p.setFilm(facade.getFilmbyTitolo(new Film(CLIController.getTitoloFilmSel())) );
			p.setGiorno(giornoCombo.getValue());
			p.setOrario(oraCombo.getValue());
			
			proiezione = facade.getProiezioneByFilmGiornoOra(p);
			 
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
	}
	
	
	/**
	 * 
	 * @return la proiezione selezionata
	 */
	public static Proiezione getProiezione() {
		return proiezione;
	}

	/**
	 * Setter
	 * @param proiezione
	 */
	public static void setProiezione(Proiezione proiezione) {
		SchedaController.proiezione = proiezione;
	}

}
