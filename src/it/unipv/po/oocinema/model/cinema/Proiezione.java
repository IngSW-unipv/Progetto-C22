package it.unipv.po.oocinema.model.cinema;

import java.util.ArrayList;


/**
 * Classe che modella un oggetto di tipo Proiezione
 */

public class Proiezione {
	private static int progressivo = 0;
	private int id;
	private Film film;
	private String giorno;
	private Sala sala;
	private double prezzo;
	private ArrayList<ArrayList<Posto>> posti;
	private String orario;  //problema con il confronto delle proiezioni

	/**
	 * Costruttore con tutte le variabili
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
	
	public Proiezione() {
		
	}

	public boolean checkPostoDisponibile(int riga, int colonna) {
		if(posti.get(riga).get(colonna).getPrenotazione()!= null)
			return false;
		else
			return true;
	}
	
	/*
	 * public int getPostiOccupati() { int count = 0; for(int i = 0; i <
	 * getSala().getRighe(); i++) { for (int j = 0; j < getSala().getColonne(); j++)
	 * { if(!posti.get(i).get(j).isAvailable()) { count++; } } } return count; }
	 */
	
	
	public Posto getPosto(int riga, int colonna){
		return this.getPosti().get(riga).get(colonna);
	}
	
	public ArrayList<ArrayList<Posto>> getPosti() {
		return posti;
	}

	public void setPosti(ArrayList<ArrayList<Posto>> posti) {
		this.posti = posti;
	}

	public Film getFilm() {
		return film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}

	public Sala getSala() {
		return sala;
	}

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

	public String getOrario() {
		return orario;
	}

	public void setOrario(String orario) {
		this.orario = orario;
	}
	
	public double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}
	
	public String getGiorno() {
		return giorno;
	}

	public void setGiorno(String giorno) {
		this.giorno = giorno;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
