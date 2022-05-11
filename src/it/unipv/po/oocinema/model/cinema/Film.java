package it.unipv.po.oocinema.model.cinema;

/**
 * classe che modella un film che sarà proiettato al cinema.
 * 
 * @author Gruppo GoF
 *
 */
public class Film {
	
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
	 * @param rating      valutazione del film (1-5 stelle).
	 * @param duration    durata del film in minuti.
	 * @param imageURL    URL della locandina del film (deve puntare a un'immagine
	 *                    all'interno della cartella
	 *                    src/main/resources/static/img/movie-posters).
	 * @param trailerURL  URL del trailer del film (YouTube).
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
	
	public Film() {		
	}
	
	public Film(String titolo) {		
		this.titolo = titolo;
	}

	/**
	 * Getter and Setter
	 */

	public void setId(int iD_film) {
		id = iD_film;
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

	public int getId() {
		return id;
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

	@Override
	public String toString() {
		return "titolo=" + titolo +"\n"+ " descrizione=" + descrizione +"\n"+ " genere=" + genere+"\n"
				+ " durata=" + durata+"\n" + " regista=" + regista+"\n" + " cast=" + cast+"\n" + " coverPath=" + coverPath+"\n"
				+ " trailerPath=" + trailerPath +"\n";
	}
	
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
