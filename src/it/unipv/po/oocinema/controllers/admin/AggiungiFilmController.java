package it.unipv.po.oocinema.controllers.admin;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.swing.JFileChooser;
import it.unipv.po.oocinema.model.cinema.Film;
import it.unipv.po.oocinema.persistenza.DBFacade;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class AggiungiFilmController extends AdminMenuController implements Initializable{
	
	private DBFacade facade = DBFacade.getInstance();

    @FXML
    private ToggleButton aggiungiFilm;

    @FXML
    private TextArea descrizione;
    
    @FXML
    private ImageView logo;

    @FXML
    private TextField durata;

    @FXML
    private TextField genere;

    @FXML
    private Button locandina;

    @FXML
    private TextField regista;
    
    @FXML
    private TextField cast;

    @FXML
    private TextField titolo;

    @FXML
    private Button trailer;
    
    
    private File l;
    private File t;
    @FXML
    public void aggiungiFilm(MouseEvent event) {
	
		 Film f = new Film(titolo.getText(),descrizione.getText(), genere.getText(),
		 Integer.parseInt(durata.getText()),regista.getText(),cast.getText(),
		 "../../resources/locandine/"+l.getName(),"src/it/unipv/po/oocinema/resources/trailer/"+t.getName());
		
		 try {
			facade.aggiungiFilm(f);
		} catch (SQLException e) {
			e.printStackTrace();
			Alert errore = new Alert(AlertType.ERROR, "ERRORE");
			errore.showAndWait();
		}
		 
		Alert successo = new Alert(AlertType.INFORMATION, "SUCCESSO");
	    successo.showAndWait();
		 
    }
    
    
    @FXML
    public void caricaLocandina(MouseEvent event) throws IOException {
    	
    	JFileChooser fc = new JFileChooser();
    	fc.setCurrentDirectory(new File("src/it/unipv/po/oocinema/resources/locandine").getCanonicalFile());
        int checkInput = fc.showOpenDialog(null);
      
        if (checkInput == JFileChooser.APPROVE_OPTION) {
            l = fc.getSelectedFile();
        }
    	
    }

    @FXML
    public void caricaTrailer(MouseEvent event) throws IOException {
    	JFileChooser fc = new JFileChooser();
    	fc.setCurrentDirectory(new File("src/it/unipv/po/oocinema/resources/trailer").getCanonicalFile());
        int checkInput = fc.showOpenDialog(null);
      
        if (checkInput == JFileChooser.APPROVE_OPTION) {
            t = fc.getSelectedFile();
        }
    }
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Image image = new Image(getClass().getResourceAsStream("../../resources/logo.png"));
        logo.setImage(image);
		
	}
    
}