package it.unipv.po.oocinema.controllers;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import it.unipv.po.oocinema.model.acquirenti.Acquirente;
import it.unipv.po.oocinema.persistenza.DBFacade;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Window;
import javafx.fxml.Initializable;

public class RegistrazioneController implements Initializable{

	DBFacade facade = DBFacade.getInstance();
    @FXML
    private DatePicker compleanno;

    @FXML
    private PasswordField conferma;

    @FXML
    private TextField email;

    @FXML
    private Text emailLabel;

    @FXML
    private Text indirizzoLabel;

    @FXML
    private Button login;

    @FXML
    private PasswordField password;

    @FXML
    private Button registrati;

    @FXML
    private Text telLabel;
    
    @FXML
    private TextField cognome;
    
    @FXML
    private TextField nome;

    @FXML
    void login(MouseEvent event) {
    	WindowsHandler.openWindow(getClass(), "../view/scenes/login.fxml");
	    WindowsHandler.closeWindow(getWindow());
    }

   
    @FXML
    void registrazione(MouseEvent event) {
    	if(!password.getText().equals(conferma.getText())) {
    		Alert errore = new Alert(AlertType.ERROR, "LE PASSWORD NON COINCIDONO");
			errore.showAndWait();
			return;
    	}
    	
    	
    	
    	if (controllaInput()) {
    		Acquirente a = null;
    		try {
    			a = new Acquirente(email.getText(), password.getText(),nome.getText(),cognome.getText(),compleanno.getValue().toString());
    		}catch(Exception e) {
    			Alert al = new Alert(AlertType.WARNING, "Controlla i dati inseriti");
    			al.showAndWait();
    		}
    		try {
	    		if(facade.controllaUser(a)) 
	    			facade.registrazione(a);
	    		else {
	    			Alert error = new Alert(AlertType.ERROR, "USER GIA' REGISTRATO");
	    			error.showAndWait();
	    		}
			} catch (SQLException e) {
				Alert errore = new Alert(AlertType.ERROR, "ERRORE");
				errore.showAndWait();
				e.printStackTrace();
			}
    		
    		login(event);
    		
		}else {
			Alert errore = new Alert(AlertType.ERROR, "Controlla i dati inseriti");
			errore.showAndWait();
    	
		}
    }
    
    public Window getWindow() {
		return password.getScene().getWindow();
	}

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
	
	private boolean controllaInput() {
		Pattern p = Pattern.compile("^(.+)@(.+)$");
	    Matcher m = p.matcher(email.getText());
	    boolean b = false;
	    try {
	    if(compleanno.getPromptText()!=null) {
	    	if(compleanno.getValue().isBefore(LocalDate.now())) {
	    		b = true;
	    	} else b = false;
	    } else b = false;
	    }catch(Exception e) {Alert errore = new Alert(AlertType.ERROR, "Controlla i dati inseriti");
		errore.showAndWait();}
		return (email.getText()!= null && password.getText()!=null && b
				&& nome.getText()!=null && cognome.getText()!=null && m.find());
		
	}

}
