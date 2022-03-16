package it.unipv.po.oocinema.model;

import java.time.LocalDate;

/**
 * Classe che modella un oggetto di tipo Cassa
 */
public class Cassa implements IAcquirente{
	/** 
	* ID della cassa
	*/
	private String user;
	
	/** 
	* Password della cassa
	*/
	private String pw_cassa;
	
	/** 
	* Costruttore con tutte le variabili
	*/
	public Cassa(String user, String pw_cassa) {
		this.user = user;
		this.pw_cassa = pw_cassa;
	}

	
	/**
	* Setta l'ID della cassa
	*
	* @param ID_cassa  l'ID da impostare
	*/
	public void setUser(String user) {
		this.user = user;
	}
	
	/**
	 * Per ottenere la password della cassa
	 * 
	 * @return pw_cassa  la password della cassa
	 */
	public String getPw_cassa() {
		return pw_cassa;
	}
	
	/**
	* Setta la password della cassa
	*
	* @param pw_cash  la password da impostare
	*/
	public void setPw_cassa(String pw_cassa) {
		this.pw_cassa = pw_cassa;
	}

	@Override
	public LocalDate getCompleanno() {
		return LocalDate.of(2022, 12, 25);
		 
	}

	@Override
	public String getUser() {
		return user;
	}

	@Override
	public String getPassword() {
		return pw_cassa;
	}
	
	
}
