package it.unipv.po.oocinema.controllers.client;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import it.unipv.po.oocinema.controllers.EmailController;
import it.unipv.po.oocinema.controllers.LoginController;
import it.unipv.po.oocinema.controllers.WindowsHandler;
import it.unipv.po.oocinema.model.cinema.Film;
import it.unipv.po.oocinema.model.cinema.Posto;
import it.unipv.po.oocinema.model.prenotazione.Prenotazione;
import it.unipv.po.oocinema.persistenza.DBFacade;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * Classe controller per la prenotazione dei posti
 * @author GoF
 *
 */
public class PrenotazioneController extends ClientMenuController implements Initializable{

	/**
	 * Bottone per aggiungere il posto selezionato
	 */
	 @FXML
	 private Button aggiungi;
	 
	 /**
	  * Elenco delle file
	  */
	 @FXML
	 private ComboBox<Character> filaCombo;

	 /**
	  * Lista dei posti scelti
	  */
	 @FXML
	 private Label lista = new Label();

	 /**
	  * Locandina del film selezionato
	  */
	 @FXML
	 private ImageView locandinaFilmSel;

	 /**
	  * Elenco della colonna dei posti
	  */
	 @FXML
	 private ComboBox<Integer> postoCombo;

	 /**
	  * Bottone per confermare la prenotazione
	  */
	 @FXML
	 private Button prenota;

	 /**
	  * Label titolo film selezionato
	  */
	 @FXML
	 private Label titoloFilmSel;
    
	 /**
	  * Istanza della classe che comunica con il DB
	  */
	 private DBFacade facade = DBFacade.getInstance();
	 
	 /**
	  * Prenotaziond creata
	  */
	 private Prenotazione prenotazione;

	 /**
	  * Prenota i posti selezionati
	  */
    @FXML
    void prenota(MouseEvent event) {
    	
    	prenotazione.setAcquirente(LoginController.getCliente());
    	prenotazione.setDataAcquisto(LocalDate.now().toString());
    	if (prenotazione.pagamento()) {
	    	try {
				facade.aggiungiPrenotazione(prenotazione);
				try {
					String messaggio = "grazie per aver scelto OOCINEMA. \nIn allegato troverai i biglietti "
							+ "che ti permetteranno di accedere alle proiezioni.\n A presto! \nLo staff di OOCINEMA";
					
					if(facade.getTipoByAcquirente(prenotazione.getAcquirente()) == '1') {
						EmailController e = new EmailController(messaggio);
						e.sendEmail(prenotazione).run();
					}else {
						
						//Invio biglietti alla stampante
					}
					WindowsHandler.openWindow(getClass(), "../../view/scenes/homeCLI.fxml");
	    			WindowsHandler.closeWindow(getWindow());
				} catch (Exception e1) {
					Alert errore = new Alert(AlertType.ERROR, "MAIL NON INVIATA");
					errore.showAndWait();
					e1.printStackTrace();
				}
	    	} catch (SQLException e) {
	    		Alert errore = new Alert(AlertType.ERROR, "QUALCOSA E' ANDATO STORTO");
				errore.showAndWait();
				e.printStackTrace();
			}
	    	Alert al = new Alert(AlertType.CONFIRMATION, "Complimenti! Prenotazione effettuata con successo. \nTotale pagato: "+prenotazione.getTotale()+"€");
			al.showAndWait();
    	}
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	
        prenotazione = new Prenotazione();
		prenotazione.setProiezione(SchedaController.getProiezione());
	
		Film f;
		try {
			f = facade.getFilmbyTitolo(new Film(CLIController.getTitoloFilmSel()));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			f = null;
			e.printStackTrace();
		}
		
		titoloFilmSel.setText(f.getTitolo());
		Image image = new Image(getClass().getResourceAsStream(f.getCoverPath()));
	    locandinaFilmSel.setImage(image);
		aggiorna();
	}
	
	/**
	 * Aggiorna interfaccia grafica
	 */
	public void aggiorna() {
		setLabelText();
		initializeRighe();	
	}
	
	/**
	 * Inizializza elenco righe
	 */
	public void initializeRighe() {
	
		ArrayList<Character> righe = new ArrayList<Character>();
	
		for(int i = 0; i <SchedaController.getProiezione().getSala().getRighe(); i++) {
			
			righe.add(i, (char) (i + 'A'));
		}
		ObservableList<Character> obListFila = FXCollections.observableList(righe);
        
		filaCombo.getItems().clear();
        filaCombo.setItems(obListFila);
       
	}
	
	
	@FXML
    void scegliPosto(MouseEvent event) {
		if(filaCombo.getValue()!=null) {
			
			initializePosto();
			
		}
    }
	
	/**
	 * Inizializza l'elenco delle colonne
	 */
    public void initializePosto() {
		
		ArrayList<Integer> colonne = new ArrayList<Integer>();
		ArrayList<Integer> colonneOccupate = new ArrayList<Integer>();
		Posto p = new Posto(filaCombo.getValue()-'A');
		
		try {
			colonneOccupate = facade.getPostiLiberiByRiga(SchedaController.getProiezione(), p);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i = 0; i<SchedaController.getProiezione().getSala().getColonne();i++) {
			colonne.add(i+1);
		}
		
		for(int i = 0; i< colonneOccupate.size();i++) {
			colonne.remove(colonneOccupate.get(i));
		}
		
		ObservableList<Integer> obListColonna = FXCollections.observableList(colonne);
        
		postoCombo.getItems().clear();
        postoCombo.setItems(obListColonna);
    }
	
    /**
     * Aggiunge il posto alla lista 
     */
	 @FXML
	 void aggiungi(MouseEvent event) {
		 if(filaCombo.getValue() != null && postoCombo.getValue() != null) {
			 
			 prenotazione.aggiungiPosto((int)(filaCombo.getValue()-'A'),postoCombo.getValue());
			 aggiorna();
		 } else {
			 
			 // FARE ALERT
		 }
	 }
	 
	 /**
	  * Rimuove un posto dalla lista
	  */
	 @FXML
	 void rimuovi(MouseEvent event) {
		 if(filaCombo.getValue() != null && postoCombo.getValue() != null) {
			 for(int i = 0 ; i <prenotazione.getNumPosti(); i++) {
				 if((prenotazione.getPosti().get(i).getColonna() == postoCombo.getValue()) && (prenotazione.getPosti().get(i).getRiga()+'A' == filaCombo.getValue())) {
					 prenotazione.rimuoviPosto(i);
				 	 aggiorna();
				 }
			 }
		 } else {
			 
			 // FARE ALERT
		 }
	 }
	
	 /**
	  * Scrive la lista dei posti nella label
	  */
	 public void setLabelText() {
		 lista.setText("");
		 for(int i = 0; i < prenotazione.getNumPosti(); i++) {
			 lista.setText(lista.getText()+"Fila: "+ (char)(prenotazione.getPosti().get(i).getRiga()+'A')+ " - Posto: "+ prenotazione.getPosti().get(i).getColonna()+"\n");
		 }
		 
		 
	 }
	
}
