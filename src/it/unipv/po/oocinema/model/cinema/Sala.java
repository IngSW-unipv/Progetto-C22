package it.unipv.po.oocinema.model.cinema;

/**
 * Classe che modella un oggetto di tipo Sala del cinema
 */

public class Sala {
	private String ID_sala;
	private int righe;
	private int colonne;


	/**
	 * Costruttore con tutte le variabili
	 */
	public Sala(String ID_sala, int righe, int colonne) {
		this.ID_sala = ID_sala;
		this.righe = righe;
		this.colonne = colonne;
	}

	/**
	 * Getter and Setter
	 */
	public String getID_sala() {
		return ID_sala;
	}

	public void setID_sala(String ID_sala) {
		this.ID_sala = ID_sala;
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
