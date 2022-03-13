package it.unipv.po.oocinema.model;

import java.time.LocalDate;

/**
 * Classe che modella un oggetto di tipo Cassa
 */
public class Cassa implements IAcquirente{
	/** 
	* ID della cassa
	*/
	private int ID_cassa;
	
	/** 
	* Password della cassa
	*/
	private String pw_cassa;
	
	/** 
	* Costruttore con tutte le variabili
	*/
	public Cassa(int ID_cassa, String pw_cassa) {
		this.ID_cassa = ID_cassa;
		this.pw_cassa = pw_cassa;
	}
	
	/**
	 * Per ottenere l'ID della cassa
	 * 
	 * @return ID_cassa  l'ID della cassa
	 */
	public int getID_cassa() {
		return ID_cassa;
	}
	
	/**
	* Setta l'ID della cassa
	*
	* @param ID_cassa  l'ID da impostare
	*/
	public void setID_cash(int ID_cassa) {
		this.ID_cassa = ID_cassa;
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
	
	
}
