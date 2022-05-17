package it.unipv.po.oocinema.controllers.admin;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JFileChooser;
import it.unipv.po.oocinema.model.cinema.Film;
import it.unipv.po.oocinema.persistenza.DBFacade;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;

/**
* Classe che implementa il Controller per l'aggiunta di un nuovo film da parte dell'amministratore
* 
* @author GoF
*/
public class AggiungiFilmController extends AdminMenuController{
	
	/**
	 * Istanza del gestore del DataBase
	 */
	private DBFacade facade = DBFacade.getInstance();

	/**
	 * Bottone per aggiungere un nuovo film
	 */
    @FXML
    private ToggleButton aggiungiFilm;

    /**
	 * Descrizione del film
	 */
    @FXML
    private TextArea descrizione;
    
    /**
	 * Durata del film
	 */
    @FXML
    private TextField durata;

    /**
	 * Genere del film
	 */
    @FXML
    private TextField genere;

    /**
	 * Bottone per l'aggiunta della locandina del film
	 */
    @FXML
    private Button locandina;

    /**
	 * Regista del film
	 */
    @FXML
    private TextField regista;
    
    /**
	 * Cast del film
	 */
    @FXML
    private TextField cast;

    /**
	 * Titolo del film
	 */
    @FXML
    private TextField titolo;

    /**
	 * Bottone per l'aggiunta del trailer del film
	 */
    @FXML
    private Button trailer;
    
    /**
	 * Locandina
	 */
    private File l;
    
    /**
	 * Trailer
	 */
    private File t;
    
    /**
	 * Aggiunge un film al DataBase
	 */
    @FXML
    public void aggiungiFilm(MouseEvent event) {
    	 Film f = null;
    	try {
		  f = new Film(titolo.getText(),descrizione.getText(), genere.getText(),
		 Integer.parseInt(durata.getText()),regista.getText(),cast.getText(),
		 "../../resources/locandine/"+l.getName(),"src/it/unipv/po/oocinema/resources/trailer/"+t.getName());
    	}catch(Exception e) {Alert errore = new Alert(AlertType.WARNING, "Controlla i dati inseriti");
		errore.showAndWait();
		return;}
		 try {
			facade.aggiungiFilm(f);
		} catch (SQLException e) {
			
			Alert errore = new Alert(AlertType.ERROR, "ERRORE");
			errore.showAndWait();
		}
		 
		Alert successo = new Alert(AlertType.INFORMATION, "SUCCESSO");
	    successo.showAndWait();
		 
    }
    
    /**
	 * Permette di caricare la locandina
	 * @throws IOException
	 */
    @FXML
    public void caricaLocandina(MouseEvent event) throws IOException {
    	
    	JFileChooser fc = new JFileChooser();
    	fc.setCurrentDirectory(new File("src/it/unipv/po/oocinema/resources/locandine").getCanonicalFile());
        int checkInput = fc.showOpenDialog(null);
      
        if (checkInput == JFileChooser.APPROVE_OPTION) {
            l = fc.getSelectedFile();
        }
    	
    }

    /**
	 * Permette di caricare il trailer
	 * @throws IOException
	 */
    @FXML
    public void caricaTrailer(MouseEvent event) throws IOException {
    	JFileChooser fc = new JFileChooser();
    	fc.setCurrentDirectory(new File("src/it/unipv/po/oocinema/resources/trailer").getCanonicalFile());
        int checkInput = fc.showOpenDialog(null);
      
        if (checkInput == JFileChooser.APPROVE_OPTION) {
            t = fc.getSelectedFile();
        }
    }
 
    
}