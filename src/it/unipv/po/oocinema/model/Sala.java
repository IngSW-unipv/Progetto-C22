package it.unipv.po.oocinema.model;

/**
 * Classe che modella un oggetto di tipo Sala del cinema
 */

public class Sala {
	private String ID_sala;
	private String n_sala;
	private int capienza_max;

	public Sala() {

	}

	/**
	 * Costruttore con tutte le variabili
	 */
	public Sala(String ID_sala, String n_sala, int capienza_max) {
		this.ID_sala = ID_sala;
		this.n_sala = n_sala;
		this.capienza_max = capienza_max;
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

	public String getN_sala() {
		return n_sala;
	}

	public void setN_sala(String n_sala) {
		this.n_sala = n_sala;
	}

	public int getCapienza_max() {
		return capienza_max;
	}

	public void setCapienza_max(int capienza_max) {
		this.capienza_max = capienza_max;
	}
}
