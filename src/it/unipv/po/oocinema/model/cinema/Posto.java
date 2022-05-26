package it.unipv.po.oocinema.model.cinema;

import it.unipv.po.oocinema.model.prenotazione.Prenotazione;

/**
 * Classe che modella un posto eventualmente occupato da una prenotazione.
 * Se l'attributo prenotazione è nullo allora il posto non è ancora 
 * stato prenotato. 
 */

public class Posto { 
	
	/**
	 * Riga del posto occupato
	 */
	private int riga;
	
	/**
	 * Colonna del posto occupato
	 */
	private int colonna;
	
	/**
	 * Prenotazione che contiene il posto
	 */
	private Prenotazione prenotazione;
	
	/**
	 * Costruttore completo
	 * @param riga riga del posto occupato
	 * @param colonna colonna del posto occupato
	 * @param prenotazione prenotazione che contiene il posto
	 */
	public Posto(int riga, int colonna, Prenotazione prenotazione) {
		this.riga = riga;
		this.colonna = colonna;
		this.prenotazione = prenotazione;
	}
	
	/**
	 * Costruttore
	 * @param riga riga del posto occupato
	 * @param colonna colonna del posto occupato
	 */
	public Posto(int riga, int colonna) {
		super();
		this.riga = riga;
		this.colonna = colonna;
	}

	/**
	 * Costruttore
	 * @param riga riga del posto occupato
	 */
	public Posto(int riga) {
		this.riga = riga;
	}
	
	/**
	 * Getter dell'attributo riga
	 * @return riga 
	 */
	public int getRiga() {
		return riga;
	}
	
	/**
	 * Setter dell'attributo riga
	 * @param riga 
	 */
	public void setRiga(int riga) {
		this.riga = riga;
	}
	
	/**
	 * Getter dell'attributo colonna
	 * @return colonna 
	 */
	public int getColonna() {
		return colonna;
	}
	
	/**
	 * Setter dell'attributo colonna
	 * @param colonna 
	 */
	public void setColonna(int colonna) {
		this.colonna = colonna;
	}

	/**
	 * Getter dell'attributo prenotazione
	 * @return prenotazione 
	 */
	public Prenotazione getPrenotazione() {
		return prenotazione;
	}

	/**
	 * Setter dell'attributo prenotazione
	 * @param prenotazione 
	 */
	public void setPrenotazione(Prenotazione prenotazione) {
		this.prenotazione = prenotazione;
	}
	
	/**
	 * Restituisce la riga e la colonna del posto in una stringa
	 */
	@Override
	public String toString() {
		
		return"Fila: "+(char)(this.getRiga()+'A')+" Posto: "+this.getColonna()+"\n";
		
	}

}
