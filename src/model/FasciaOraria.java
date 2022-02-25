package model;

/**
 * Classe che modella un oggetto di tipo FasciaOraria
 */
public class FasciaOraria {
	private String possibilita;
	
	public FasciaOraria() {
		
	}
	
	public FasciaOraria(String possibilita) {
		this.possibilita = possibilita;
	}

	public String getPossibilita() {
		return possibilita;
	}

	public void setPossibilita(String possibilita) {
		this.possibilita = possibilita;
	}
}
