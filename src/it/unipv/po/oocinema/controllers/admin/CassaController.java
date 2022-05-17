package it.unipv.po.oocinema.controllers.admin;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import it.unipv.po.oocinema.controllers.WindowsHandler;
import it.unipv.po.oocinema.model.acquirenti.Cassa;
import it.unipv.po.oocinema.persistenza.DBFacade;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
* Classe che implementa il Controller per la gestione delle cassiere da parte dell'amministratore
* 
* @author GoF
*/
public class CassaController extends AdminMenuController implements Initializable {

	/**
	 * Istanza del gestore del DataBase
	 */
	private DBFacade facade = DBFacade.getInstance();
	
	/**
	 * Username della cassa da eliminare
	 */
    @FXML
    private TextField userCassa;
   

    /**
	 * Bottone per rimuovere la cassa selezionata
	 */
    @FXML
    private Button rimuovi;
    
    /**
	 * Bottone per andare alla pagina di aggiunta di una nuova cassa
	 */
    @FXML
    private Button aggiungi;

    /**
	 * Tabella con l'elenco di tutte le casse presenti
	 */
    @FXML
    private TableView<Cassa> tabella;

    /**
	 * Colonna dell'username
	 */
    @FXML
    private TableColumn<Cassa, String> colonnaUser;

    /**
	 * Colonna della password
	 */
    @FXML
    private TableColumn<Cassa, String> colonnaPassword;
    
    private ObservableList<Cassa> datiTabella = FXCollections.observableArrayList();

    /**
	 * Porta alla pagina di aggiunta di una nuova cassa
	 */
    @FXML
    void aggiungiCassa(MouseEvent event) {
    	WindowsHandler.openWindow(getClass(), "../../view/scenes/aggiungiCassa.fxml");
	    WindowsHandler.closeWindow(getWindow());
    }

    /**
	 * Rimuove la cassa selezionata
	 */
    @FXML
    void rimuoviCassa(MouseEvent event) {
    	Alert alert = new Alert(AlertType.CONFIRMATION, "Verranno rimossi tutti i dati associati alla cassa");
    	alert.showAndWait(); 
    	
    	if(alert.getResult().equals(ButtonType.OK)) {
    		
    		Cassa c = null;
    		try {
	    	 c = new Cassa(userCassa.getText());
	    	 }catch(Exception e){
	    		 Alert al = new Alert(AlertType.WARNING, "Controlla i dati inseriti");
	 			al.showAndWait();
	    	 }
	    	try {
				facade.rimuoviCassa(c);	
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		aggiorna();
    	}
    }

    /**
	 * Inizializza la tabella
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		colonnaUser.setCellValueFactory(new PropertyValueFactory<>("user"));
		colonnaPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
		
		aggiorna();
		
	}
	
	/**
	 * Aggiorna il contenuto della tabella
	 */
	public void aggiorna() {
		costruisciElementiTabella(datiTabella);
		tabella.setItems(datiTabella);
	}
	
	/**
	 * Costruisce una tabella a pertire da @param datiTabella
	 */
	public  void costruisciElementiTabella(ObservableList<Cassa> datiTabella) {
		
		datiTabella.removeAll(datiTabella);
		ArrayList<Cassa> elencoCasse = new ArrayList<Cassa>();
		try {
			elencoCasse = facade.getTutteCasse();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		for(int i = 0; i < elencoCasse.size(); i++) {
			datiTabella.add(new Cassa(elencoCasse.get(i).getUser(),elencoCasse.get(i).getPassword()));
		}
	}

}
