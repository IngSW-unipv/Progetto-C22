package it.unipv.po.oocinema.model.cinema;

/**
 * classe che modella un film che sarà proiettato al cinema.
 * 
 * @author Gruppo GoF
 *
 */
public class Film {
	
	/**
	 * Attributo che contiene l'id da assegnare al prossimo film
	 */
	private static int progressivo = 0;
	
	/**
	 * 
	 * Id del film.
	 */
	private int id;
	
	/**
	 * Titolo del film.
	 */
	private String titolo;
	
	/**
	 * Descrizione del film.
	 */
	private String descrizione;
	/**
	 * Genere del film.
	 */
	private String genere;
	/**
	 * Durata del film.
	 */
	private int durata;
	/**
	 * Regista del film.
	 */
	private String regista;
	/**
	 * Cast del film.
	 */
	private String cast;
	/**
	 * Percorso della locandina del film all'interno della cartella del progetto.
	 */
	private String coverPath;
	
	/**
	 * Percorso del file trailer del film all'interno della cartella del progetto.
	 */
	private String trailerPath;

	
	/**
	 * Costruttore del film. Id generato incrementando un contatore
	 * 
	 * @param titolo       titolo del film.
	 * @param descrizione descrizione del film.
	 * @param genere      generi del film.
	 * @param regista   direttori del film.
	 * @param cast        cast del film.
	 * @param durata    durata del film in minuti.
	 * @param coverPath    URL della locandina del film (deve puntare a un'immagine
	 *                    all'interno della cartella
	 *                    src/it/unipv/po/oocinema/resorces/locandine).
	 * @param trailerPath  URL del trailer del film (deve puntare a un video
	 *                    all'interno della cartella
	 *                    src/it/unipv/po/oocinema/resorces/trailer).
	 */
	public Film(String titolo, String descrizione, String genere, int durata, String regista,
			String cast, String coverPath, String trailerPath) {
		this.id = progressivo;
		progressivo++;
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
	 * Costruttore del film.
	 * 
	 * @param id          id del film.
	 * @param titolo       titolo del film.
	 * @param descrizione descrizione del film.
	 * @param genere      generi del film.
	 * @param regista   direttori del film.
	 * @param cast        cast del film.
	 * @param durata    durata del film in minuti.
	 * @param coverPath    URL della locandina del film (deve puntare a un'immagine
	 *                    all'interno della cartella
	 *                    src/it/unipv/po/oocinema/resorces/locandine).
	 * @param trailerPath  URL del trailer del film (deve puntare a un video
	 *                    all'interno della cartella
	 *                    src/it/unipv/po/oocinema/resorces/trailer).
	 */
	public Film(int id, String titolo, String descrizione, String genere, int durata, String regista,
			String cast, String coverPath, String trailerPath) {
		this.id = id;
		this.titolo = titolo;
		this.descrizione = descrizione;
		this.genere = genere;
		this.durata = durata;
		this.regista = regista;
		this.cast = cast;
		this.coverPath = coverPath;
		this.trailerPath = trailerPath;
	}
	
	/** Costruttore vuoto                   
	 */
	public Film() {		
	}
	
	/** Costruttore con titolo
	 * @param titolo                   
	 */
	public Film(String titolo) {		
		this.titolo = titolo;
	}

	/**
	 * Setter del parametro id
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}	

	/**
	 * Setter del parametro titolo
	 * @param titolo
	 */
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	/**
	 * Setter del parametro descrizione
	 * @param descrizione
	 */
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	/**
	 * Setter del parametro genere
	 * @param genere
	 */
	public void setGenere(String genere) {
		this.genere = genere;
	}

	/**
	 * Setter del parametro durata
	 * @param durata
	 */
	public void setDurata(int durata) {
		this.durata = durata;
	}

	/**
	 * Setter del parametro regista
	 * @param regista
	 */
	public void setRegista(String regista) {
		this.regista = regista;
	}

	/**
	 * Setter del parametro cast
	 * @param cast
	 */
	public void setCast(String cast) {
		this.cast = cast;
	}

	/**
	 * Getter del parametro id
	 * @return id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Getter del parametro titolo
	 * @return titolo
	 */
	public String getTitolo() {
		return titolo;
	}

	/**
	 * Getter del parametro descrizione
	 * @return descrizione
	 */
	public String getDescrizione() {
		return descrizione;
	}

	/**
	 * Getter del parametro genere
	 * @return genere
	 */
	public String getGenere() {
		return genere;
	}

	/**
	 * Getter del parametro durata
	 * @return durata
	 */
	public int getDurata() {
		return durata;
	}

	/**
	 * Getter del parametro regista
	 * @return regista
	 */
	public String getRegista() {
		return regista;
	}

	/**
	 * Getter del parametro cast
	 * @return cast
	 */
	public String getCast() {
		return cast;
	}

	/**
	 * Getter del parametro trailerPath
	 * @return trailerPath
	 */
	public String getTrailerPath() {
		return trailerPath;
	}

	/**
	 * Getter del parametro coverPath
	 * @return coverPath
	 */
	public String getCoverPath() {
		return coverPath;
	}

	/**
	 * Setter del parametro trailerPath
	 * @param trailerPath
	 */
	public void setTrailerPath(String trailerPath) {
		this.trailerPath = trailerPath;
	}

	/**
	 * Setter del parametro coverPath
	 * @param coverPath
	 */
	public void setCoverPath(String coverPath) {
		this.coverPath = coverPath;
	}

	/**
	 * Restituisce una breve descrizione degli attributi del film
	 */
	@Override
	public String toString() {
		return "titolo=" + titolo +"\n"+ " descrizione=" + descrizione +"\n"+ " genere=" + genere+"\n"
				+ " durata=" + durata+"\n" + " regista=" + regista+"\n" + " cast=" + cast+"\n" ;
	}
	
	/**
	 * Confronto fra due film. 
	 * @return true se i due film sono uguali
	 * @return false se i due film sono diversi
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj != null && getClass().equals(obj.getClass())){
			Film film = (Film) obj;
			return (film.getId() == id) && (film.getTitolo().equals(titolo)) && (film.getDescrizione().equals(descrizione)) &&
					(film.getGenere().equals(genere)) && (film.getDurata()== durata) && (film.getRegista().equals(regista)) &&
					(film.getCast().equals(cast)) && (film.getCoverPath().equals(coverPath)) && (film.getTrailerPath().equals(trailerPath));
		}
		else return false;
	}

}
