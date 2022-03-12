package it.unipv.po.oocinema.model;

/**
 * Classe che modella un oggetto di tipo FasciaOraria
 */
public class FasciaOraria {
	private String ora;
	
	public FasciaOraria(String ora) {
		this.ora = ora;
	}

	/**
	 * Per ottenere la fascia oraria della mia proiezione
	 * 
	 * @return possibilita ovvero la fascia oraria
	 */
	public String getOra() {
		return ora;
	}

	public void setOra(String ora) {
		this.ora = ora;
	}
}
