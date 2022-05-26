package it.unipv.po.oocinema.model.prenotazione.sconti;

import java.io.FileInputStream;
import java.lang.reflect.Constructor;
import java.util.Properties;

/**
 * Classe che preleva lo sconto attivo
 * @author GoF
 *
 */
public class ScontoFactory {
	
	/**
	 * Istanza della classe
	 */
	private static ScontoFactory instance = null;
	private IScontoPrenotazioneStrategy sconto;
	/**
	 * Nome della proprietà degli sconti
	 */
	private static final String PROPERTYNAME = "discount.strategy.class.name";
	
	/**
	 * 
	 * @return l'istanza della classe
	 */
	public static ScontoFactory getInstance() {
		if(instance == null) {
			instance = new ScontoFactory();
		}
		return instance;
	}
	
	/**
	 * 
	 * @return istanza della classe corrispondente allo sconto attivo
	 */
	public IScontoPrenotazioneStrategy getScontoStartegy() {
		if(sconto == null) {
			Properties p = new Properties(System.getProperties());
			String nomeClasseSconto;
			try {
				p.load(new FileInputStream("properties/properties"));
				nomeClasseSconto = p.getProperty(PROPERTYNAME);
				
				@SuppressWarnings("rawtypes")
				Constructor c = Class.forName(nomeClasseSconto).getConstructor();
				sconto = (IScontoPrenotazioneStrategy)c.newInstance();
				
			} catch (Exception e) {
				e.printStackTrace();
				sconto = null;
			}
		}
		
		return sconto;
	}
	
}
