package it.unipv.po.oocinema.test;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import it.unipv.po.oocinema.model.acquirenti.Acquirente;
import it.unipv.po.oocinema.persistenza.DBFacade;

public class TestAcquirente {
	
	private DBFacade facade; 

	private Acquirente a1, a2;
	
	private boolean result;
	
	@Before
	public void intTest() throws Exception {
		a1=new Acquirente("A5", "password", "nome", "cognome", "compleanno");
		a2=new Acquirente("A6", "password", "nome", "cognome", "compleanno");
		facade = DBFacade.getInstance();
	}
	
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
