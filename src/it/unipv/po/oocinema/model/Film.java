package it.unipv.po.oocinema.model;

/**
 * Classe che modella un oggetto di tipo Film
 */
public class Film {
	/**
	 * Id del film.
	 */
	private int ID_film;
	
	/**
	 * Titolo del film.
	 */
	private String titolo;
	
	/**
	 * Descrizione del film.
	 */
	private String descrizione;
	private String genere;
	private int durata;
	private String regista;
	private String cast;
	private String coverPath;
	private String trailerPath;


	/**
	 * Costruttore con tutte le variabili
	 */
	public Film(int ID_film, String titolo, String descrizione, String genere, int durata, String regista,
			String cast, String coverPath, String trailerPath) {
		this.ID_film = ID_film;
		this.titolo = titolo;
		this.descrizione = descrizione;
		this.genere = genere;
		this.durata = durata;
		this.regista = regista;
		this.cast = cast;
		this.coverPath = coverPath;
		this.trailerPath = trailerPath;
	}

	/**
	 * Getter and Setter
	 */

	public void setID_film(int iD_film) {
		ID_film = iD_film;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public void setGenere(String genere) {
		this.genere = genere;
	}

	public void setDurata(int durata) {
		this.durata = durata;
	}

	public void setRegista(String regista) {
		this.regista = regista;
	}

	public void setCast(String cast) {
		this.cast = cast;
	}

	public int getID_film() {
		return ID_film;
	}

	public String getTitolo() {
		return titolo;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public String getGenere() {
		return genere;
	}

	public int getDurata() {
		return durata;
	}

	public String getRegista() {
		return regista;
	}

	public String getCast() {
		return cast;
	}

	public String getTrailerPath() {
		return trailerPath;
	}

	public String getCoverPath() {
		return coverPath;
	}

	public void setTrailerPath(String trailerPath) {
		this.trailerPath = trailerPath;
	}

	public void setCoverPath(String coverPath) {
		this.coverPath = coverPath;
	}

}
