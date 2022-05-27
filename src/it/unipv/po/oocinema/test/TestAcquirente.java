package it.unipv.po.oocinema.test;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import it.unipv.po.oocinema.model.acquirenti.Acquirente;
import it.unipv.po.oocinema.persistenza.DBFacade;

/**
 * Classe di test per la classe Acquirente
 * @author GoF
 *
 */
public class TestAcquirente {
	
	/**
	 * Istanza della classe che comunica con il DB
	 */
	private DBFacade facade; 

	/**
	 * Acquirenti di test
	 */
	private Acquirente a1, a2;
	
	/**
	 * Risultato del test
	 */
	private boolean result;
	
	/**
	 * Inizializzazione delle variabili, prima del test
	 * @throws Exception
	 */
	@Before
	public void intTest() throws Exception {
		a1=new Acquirente("A5", "password", "nome", "cognome", "compleanno");
		a2=new Acquirente("A6", "password", "nome", "cognome", "compleanno");
		facade = DBFacade.getInstance();
	}
	
	/**
	 * Test sul login 
	 */
	@Test
	public void testLogin(){
		try {
			facade.registrazione(a1);
			result=facade.login(a1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		assertTrue(result); 
	}
	
	/**
	 * Test sulla presenza dell'utente nel db
	 */
	@Test
	public void testControllaUser(){
		try {
			facade.registrazione(a2);
			result=facade.controllaUser(a2);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		assertFalse(result); 
	}
}
