package it.unipv.po.oocinema.model.cinema;

/**
 * Classe che modella un oggetto di tipo Sala del cinema
 * @author GoF
 */

public class Sala {
	
	/**
	 * Attributo che contiene l'id da assegnare alla prossima sala
	 */
	private static int progressivo = 0;
	
	/**
	 * Id della sala
	 */
	private int id;
	
	/**
	 * Numero di righe della sala
	 */
	private int righe;
	
	/**
	 * Numero di colonne della sala
	 */
	private int colonne;

	
	/**
	 * Costruttore con id generato da progressivo
	 * @param righe numero di righe della sala
	 * @param colonne numero di colonne della sala
	 */
	public Sala(int righe, int colonne) {
		this.id = progressivo;
		progressivo++;
		this.righe = righe;
		this.colonne = colonne;
	}
	
	/**
	 * Costruttore completo
	 * @param id id della sala
	 * @param righe numero di righe della sala
	 * @param colonne numero di colonne della sala
	 */
	public Sala(int id, int righe, int colonne) {
		this.id = id;
		this.righe = righe;
		this.colonne = colonne;
	}
	
	/**
	 * Costruttore vuoto
	 */
	public Sala() {
	}
	
	/**
	 * Getter dell'attributo id
	 * @return id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Setter dell'attributo id
	 * @param id
	 */
	public void setId(int ID_sala) {
		this.id = ID_sala;
	}

	/**
	 * Getter dell'attributo righe
	 * @return righe
	 */
	public int getRighe() {
		return righe;
	}

	/**
	 * Setter dell'attributo righe
	 * @param righe
	 */
	public void setRighe(int righe) {
		this.righe = righe;
	}

	/**
	 * Getter dell'attributo colonne
	 * @return colonne
	 */
	public int getColonne() {
		return colonne;
	}

	/**
	 * Setter dell'attributo colonne
	 * @param colonne
	 */
	public void setColonne(int colonne) {
		this.colonne = colonne;
	}
}
