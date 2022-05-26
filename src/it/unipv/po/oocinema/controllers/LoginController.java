package it.unipv.po.oocinema.controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import it.unipv.po.oocinema.model.acquirenti.Acquirente;
import it.unipv.po.oocinema.persistenza.DBFacade;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Window;

/**
 * Classe controller della pagina di login
 * @author GoF
 *
 */
public class LoginController implements Initializable{
	
	/**
	 * Istanza della classe che comunica con il DB
	 */
	private DBFacade facade = DBFacade.getInstance();

	/**
	 * Label password dimenticata
	 */
    @FXML
    private Label dimenticata;

    /**
	 * Label email del cinema
	 */
    @FXML
    private Text emailLabel;

    /**
	 * Label indirizzo cinema
	 */
    @FXML
    private Text indirizzoLabel;

    /**
	 * Bottone di conferma login
	 */
    @FXML
    private Button login;

    /**
	 * Input password
	 */
    @FXML
    private PasswordField password;

    /**
	 * Bottone per passare alla pagine di registrazione
	 */
    @FXML
    private Button registrati;

    /**
	 * Label telefono cinema
	 */
    @FXML
    private Text telLabel;

    /**
	 * Input username
	 */
    @FXML
    private TextField user;
    
    /**
	 * Utente che ha effettuato il login
	 */
    private static Acquirente utente;
   
    /**
	 * Effettua il login e passa alla pagina successiva
	 */
    @FXML
    void login(MouseEvent event) {
    	try {
    		Acquirente log = new Acquirente(user.getText());
			log.setPassword(password.getText());
    		if(facade.loginAmministratore(log)) {
    			utente = facade.getAmministratore();
    			WindowsHandler.openWindow(getClass(), "../view/scenes/homeADM.fxml");
    			WindowsHandler.closeWindow(getWindow());
    			
    			
    		} else {
	    		if(!facade.login(log)) {
	    			Alert alert = new Alert(AlertType.WARNING, "Utente o password errati");
	    	    	alert.showAndWait(); 
	    		}else {
	    			 utente = facade.getUtentebyUser(log);
					WindowsHandler.openWindow(getClass(), "../view/scenes/homeCLI.fxml");
				    WindowsHandler.closeWindow(getWindow());
				   
				}
	    		
    		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

   
    @FXML
    void passwordDimenticata(MouseEvent event) {
    	
    }

    /**
	 * Passa alla pagina di registrazione
	 */
    @FXML
    void registrati(MouseEvent event) {
    	WindowsHandler.openWindow(getClass(), "../view/scenes/registrazione.fxml");
	    WindowsHandler.closeWindow(getWindow());
    }
    
    /**
	 * @return la finestra corrente
	 */
    public Window getWindow() {
		return user.getScene().getWindow();
	}
    
    /**
	 * @return il cliente che ha effettuato il login
	 */
    public static Acquirente getCliente() {
    	return utente;
    }
    
    /**
     * Setter
     * @param c cliente
     */
    public static void setCliente(Acquirente c) {
    	utente = c;
    }

    /**
     * Inizializza la pagina di login
     */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			telLabel.setText(facade.getTelefono());
			emailLabel.setText(facade.getEmail());
			indirizzoLabel.setText(facade.getIndirizzo());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
