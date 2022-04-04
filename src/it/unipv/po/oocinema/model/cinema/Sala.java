package it.unipv.po.oocinema.model.cinema;

/**
 * Classe che modella un oggetto di tipo Sala del cinema
 */

public class Sala {
	private static int progressivo = 0;
	private int id;
	private int righe;
	private int colonne;


	/**
	 * Costruttore con tutte le variabili
	 */
	public Sala(int righe, int colonne) {
		this.id = progressivo;
		progressivo++;
		this.righe = righe;
		this.colonne = colonne;
	}
	
	public Sala(int id, int righe, int colonne) {
		this.id = id;
		this.righe = righe;
		this.colonne = colonne;
	}
	
	public Sala() {
	}
	
	/**
	 * Getter and Setter
	 */
	public int getId() {
		return id;
	}

	public void setId(int ID_sala) {
		this.id = ID_sala;
	}


	public int getRighe() {
		return righe;
	}

	public void setRighe(int righe) {
		this.righe = righe;
	}

	public int getColonne() {
		return colonne;
	}

	public void setColonne(int colonne) {
		this.colonne = colonne;
	}
}
