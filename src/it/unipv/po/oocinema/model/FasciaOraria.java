package it.unipv.po.oocinema.model;

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

	/**
	 * Per ottenere la fascia oraria della mia proiezione
	 * 
	 * @return possibilita ovvero la fascia oraria
	 */
	public String getPossibilita() {
		return possibilita;
	}

	public void setPossibilita(String possibilita) {
		this.possibilita = possibilita;
	}
}
