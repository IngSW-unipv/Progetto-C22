package it.unipv.po.oocinema.model.prenotazione.sconti;

import java.io.FileInputStream;
import java.lang.reflect.Constructor;
import java.util.Properties;

public class ScontoFactory {
	
	private static ScontoFactory instance = null;
	IScontoPrenotazioneStrategy sconto;
	private static final String PROPERTYNAME = "discount.strategy.class.name";
	
	
	public static ScontoFactory getInstance() {
		if(instance == null) {
			instance = new ScontoFactory();
		}
		return instance;
	}
	
	public IScontoPrenotazioneStrategy getScontoStartegy() {
		if(sconto == null) {
			Properties p = new Properties(System.getProperties());
			String nomeClasseSconto;
			try {
				p.load(new FileInputStream("properties/properties"));
				nomeClasseSconto = p.getProperty(PROPERTYNAME);
				
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
