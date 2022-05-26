package it.unipv.po.oocinema.model.acquirenti;

/**
 * Classe che modella un generico acquirente del cinema con i suoi 
 * attributi e metodi utili.
 * 
 * @author Gruppo GoF
 */

public class Acquirente {
	
	/**
	 * User dell'utente che utilizza il sistema
	 */ 
	private String user;
	
	/**
	 * Password dell'utente che utilizza il sistema
	 */ 
	private String password;
	
	/**
	 * Compleanno dell'utente che utilizza il sistema
	 */ 
	private String compleanno; //specificare il formato. 
	
	/**
	 * Nome dell'utente che utilizza il sistema
	 */ 
	private String nome;
	
	/**
	 * Cognome dell'utente che utilizza il sistema
	 */ 
	private String cognome;

	
	/**
	 * Costruttore completo
	 * @param user username dell'utente
	 * @param password password dell'utente
	 * @param nome nome dell'utente
	 * @param cognome cognome dell'utente
	 * @param compleanno compleanno dell'utente
	 */ 
	public Acquirente(String user, String password, String nome,String cognome, String compleanno) {
		this.user = user;
		this.password = password;
		this.compleanno = compleanno;
		this.nome = nome;
		this.cognome = cognome;
	}
	
	/**
	 * Costruttore con solo utente
	 * @param user username dell'utente
	 */ 
	public Acquirente(String user){
		this.user = user;
	}

	/**
	 * Getter dell'attributo user
	 * @return user
	 */ 
	public String getUser() {
		return user;
	}
	
	/**
	 * Setter dell'attributo user
	 * @param user
	 */ 
	public void setUser(String user) {
		this.user = user;
	}
	/**
	 * Getter dell'attributo password
	 * @return password
	 */ 
	public String getPassword() {
		return password;
	}
	
	/**
	 * Setter dell'attributo password
	 * @param password
	 */ 
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * Getter dell'attributo compleanno
	 * @return compleanno
	 */ 
	public String getCompleanno() {
		return compleanno;
	}

	/**
	 * Setter dell'attributo compleanno
	 * @param compleanno
	 */ 
	public void setCompleanno(String compleanno) {
		this.compleanno = compleanno;
	}
	
	/**
	 * Getter dell'attributo nome
	 * @return nome
	 */ 
	public String getNome() {
		return nome;
	}
	
	/**
	 * Setter dell'attributo nome
	 * @param nome
	 */ 
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Getter dell'attributo cognome
	 * @return cognome
	 */ 
	public String getCognome() {
		return cognome;
	}

	/**
	 * Setter dell'attributo cognome
	 * @param cognome
	 */ 
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	
}
