package it.unipv.po.oocinema.model.cinema;

/**
 * Classe che modella un oggetto di tipo Sala del cinema
 */

public class Sala {
	private String id;
	private int righe;
	private int colonne;


	/**
	 * Costruttore con tutte le variabili
	 */
	public Sala(String id, int righe, int colonne) {
		this.id = id;
		this.righe = righe;
		this.colonne = colonne;
	}
	
	public Sala(String id) {
		this.id = id;
	}
	/**
	 * Getter and Setter
	 */
	public String getId() {
		return id;
	}

	public void setId(String ID_sala) {
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
