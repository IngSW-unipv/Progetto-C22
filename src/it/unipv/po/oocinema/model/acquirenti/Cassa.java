package it.unipv.po.oocinema.model.acquirenti;

/**
 * Classe che estende Acquirente e modella un oggetto di tipo Cassa, 
 * rappresentazione di una cassa fisica presente nel cinema.
 * 
 * @author Gruppo GoF
 */
public class Cassa extends Acquirente{

	/**
	 * Costruttore completo
	 * @param user username dell'utente
	 * @param pw_cassa password dell'utente
	 */ 
	public Cassa(String user, String pw_cassa) {
		super(user,pw_cassa,null,null,null);
	}
	
	/**
	 * Costruttore 
	 * @param user username dell'utente
	 */ 
	public Cassa(String user) {
		super(user);
	}
	
}
