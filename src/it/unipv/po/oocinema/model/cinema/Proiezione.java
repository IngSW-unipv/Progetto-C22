package it.unipv.po.oocinema.model.cinema;

import java.util.ArrayList;


/**
 * Classe che modella un oggetto di tipo Proiezione.
 * @author GoF
 */

public class Proiezione {
	
	/**
	 * Attributo che contiene l'id da assegnare alla prossima proiezione
	 */
	private static int progressivo = 0;
	
	/**
	 *Id della proiezione
	 */
	private int id;
	
	/**
	 * Film proiettato
	 */
	private Film film;
	
	/**
	 * Giorno della proiezione
	 */
	private String giorno;
	
	/**
	 * Sala della proiezione
	 */
	private Sala sala;
	
	/**
	 * Prezzo di un biglietto per la proiezione
	 */
	private double prezzo;
	
	/**
	 * Insieme dei posti disponibili per la proiezione
	 */
	private ArrayList<ArrayList<Posto>> posti;
	
	/**
	 * Orario della proiezione
	 */
	private String orario;  //problema con il confronto delle proiezioni

	/**
	 * Costruttore con tutte le variabili
	 * @param id
	 * @param film
	 * @param giorno
	 * @param sala
	 * @param prezzo
	 * @param orario
	 */
	public Proiezione(int id, Film film, String giorno, Sala sala, double prezzo, String orario) {
		this.id = id;
		this.film = film;
		this.giorno=giorno;
		this.sala=sala;
		this.prezzo=prezzo;
		this.orario = orario;
		this.posti = new ArrayList<ArrayList<Posto>>();
		this.setSala(sala);
	}

	/**
	 * Costruttore. Id generato tramite attributo progresssivo
	 * @param film
	 * @param giorno
	 * @param sala
	 * @param prezzo
	 * @param orario
	 */
	public Proiezione(Film film, String giorno, Sala sala, double prezzo, String orario) {
		this.id = progressivo;
		progressivo++;
		this.film = film;
		this.giorno=giorno;
		this.sala=sala;
		this.prezzo=prezzo;
		this.orario = orario;
		this.posti = new ArrayList<ArrayList<Posto>>();
		this.setSala(sala);
	}
	
	/**
	 * Costruttore vuoto
	 */
	public Proiezione() {
		
	}

	/**
	 * @param riga
	 * @param colonna
	 * @return true se il posto è libero, altrimenti false
	 */
	public boolean checkPostoDisponibile(int riga, int colonna) {
		if(posti.get(riga).get(colonna).getPrenotazione()!= null)
			return false;
		else
			return true;
	}
	
	/**
	 * @param riga
	 * @param colonna
	 * @return posto selezionato
	 */
	public Posto getPosto(int riga, int colonna){
		return this.getPosti().get(riga).get(colonna);
	}
	
	/**
	 * 
	 * @return lista dei posti della proiezione
	 */
	public ArrayList<ArrayList<Posto>> getPosti() {
		return posti;
	}

	/**
	 * Setter 
	 * @param posti
	 */
	public void setPosti(ArrayList<ArrayList<Posto>> posti) {
		this.posti = posti;
	}

	/**
	 * Getter
	 * @return film della proiezione
	 */
	public Film getFilm() {
		return film;
	}

	/**
	 * Setter
	 * @param film
	 */
	public void setFilm(Film film) {
		this.film = film;
	}

	/**
	 * Getter
	 * @return sala della proiezione
	 */
	public Sala getSala() {
		return sala;
	}

	/**
	 * Setter
	 * @param sala
	 */
	public void setSala(Sala sala) {
		this.sala = sala;
		
		for (int i = 0; i < sala.getRighe(); i++) {
			ArrayList<Posto> riga = new ArrayList<Posto>();
			for (int j = 0; j < sala.getColonne(); j++) {
				riga.add(new Posto(i,j, null));  //PRENOTAZIONE NULLA PER POSTI LIBERI
			}
			posti.add(riga);
		}
	}

	/**
	 * Getter
	 * @return orario della della proiezione
	 */
	public String getOrario() {
		return orario;
	}

	/**
	 * Setter
	 * @param orario
	 */
	public void setOrario(String orario) {
		this.orario = orario;
	}
	
	/**
	 * Getter
	 * @return prezzo del biglietto per la proiezione
	 */
	public double getPrezzo() {
		return prezzo;
	}

	/**
	 * Setter
	 * @param prezzo
	 */
	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}
	
	/**
	 * Getter
	 * @return giorno della proiezione
	 */
	public String getGiorno() {
		return giorno;
	}

	/**
	 * Setter
	 * @param giorno
	 */
	public void setGiorno(String giorno) {
		this.giorno = giorno;
	}
	
	/**
	 * Getter
	 * @return id della proiezione
	 */
	public int getId() {
		return id;
	}

	/**
	 * Setter
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

}
