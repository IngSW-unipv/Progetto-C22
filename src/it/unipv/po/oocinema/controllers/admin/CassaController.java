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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;


public class CassaController extends AdminMenuController implements Initializable {

	private DBFacade facade = new DBFacade();
	
    @FXML
    private TextField userCassa;

    @FXML
    private Label proiezioni;

    @FXML
    private Button rimuovi;
    
    @FXML
    private Button aggiungi;

    @FXML
    private TableView<Cassa> tabella;

    @FXML
    private TableColumn<Cassa, String> colonnaUser;

    @FXML
    private TableColumn<Cassa, String> colonnaPassword;
    
    ObservableList<Cassa> datiTabella = FXCollections.observableArrayList();

    @FXML
    void aggiungiCassa(MouseEvent event) {
    	WindowsHandler.openWindow(getClass(), "../../view/scenes/aggiungiCassa.fxml");
	    WindowsHandler.closeWindow(getWindow());
    }

    @FXML
    void rimuoviCassa(MouseEvent event) {
    	Alert alert = new Alert(AlertType.CONFIRMATION, "Verranno rimossi tutti i dati associati alla cassa");
    	alert.showAndWait(); 
    	
    	if(alert.getResult().equals(ButtonType.OK)) {
	    	Cassa c = new Cassa(userCassa.getText());
	    	try {
				facade.rimuoviCassa(c);	
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		aggiorna();
    	}
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		colonnaUser.setCellValueFactory(new PropertyValueFactory<>("user"));
		colonnaPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
		
		aggiorna();
		
	}
	
	public void aggiorna() {
		costruisciElementiTabella(datiTabella);
		tabella.setItems(datiTabella);
	}
	
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
