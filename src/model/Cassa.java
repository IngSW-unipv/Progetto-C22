package model;

public class Cassa {
	private int ID_cash;
	private String pw_cash;
	
	/** 
	* Costruttore con tutte le variabili
	*/
	public Cassa(int ID_cash, String pw_cash) {
		this.ID_cash = ID_cash;
		this.pw_cash = pw_cash;
	}
	
	/** 
	* Costruttore vuoto
	*/
	public Cassa() {
		
	}
	
	/**
	 * Per ottenere l'ID della cassa
	 * 
	 * @return ID_cash  l'ID della cassa
	 */
	public int getID_cash() {
		return ID_cash;
	}
	
	/**
	* Setta l'ID della cassa
	*
	* @param ID_cash  l'ID da impostare
	*/
	public void setID_cash(int ID_cash) {
		this.ID_cash = ID_cash;
	}
	
	/**
	 * Per ottenere la password della cassa
	 * 
	 * @return pw_cash  la password della cassa
	 */
	public String getPw_cash() {
		return pw_cash;
	}
	
	/**
	* Setta la password della cassa
	*
	* @param pw_cash  la password da impostare
	*/
	public void setPw_cash(String pw_cash) {
		this.pw_cash = pw_cash;
	}
	
	
}
