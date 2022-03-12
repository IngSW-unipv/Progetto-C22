package it.unipv.po.oocinema.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;


/**
 * Classe che modella un oggetto di tipo Proiezione
 */

public class Proiezione {
	private Film film;
	private LocalDate giorno_to;
	private LocalDate giorno_from;
	private Sala sala;
	private double prezzo;
	

	private ArrayList<ArrayList<Posto>> posti;
	
	private FasciaOraria orario;

	/**
	 * Costruttore con tutte le variabili
	 */

	public Proiezione(Film film, LocalDate giorno_to, LocalDate giorno_from, Sala sala, FasciaOraria orario) {
		this.film = film;
		this.giorno_to = giorno_to;
		this.giorno_from = giorno_from;
		this.setOrario(orario);
		this.posti = new ArrayList<ArrayList<Posto>>();
		this.setSala(sala);
	}

	public boolean checkPostoDisponibile(int row, int col) {
		return posti.get(row).get(col).isAvailable();
	}
	
	public int getPostiOccupati() {
		int count = 0;
		for(int i = 0; i < getSala().getRighe(); i++) {
			for (int j = 0; j < getSala().getRighe(); j++) {
				if(posti.get(i).get(j).isAvailable()) {
					count++;
				}
			}
		}
		return count;
	}
	
	public boolean occupaPosto(int riga, int colonna) {
		if (posti.get(riga).get(colonna).isAvailable()) {
			posti.get(riga).get(colonna).setAvailable(false);
			return true;
		}
		return false;
	}
	
	public boolean liberaPosto(int riga, int colonna) {
		if (!posti.get(riga).get(colonna).isAvailable()) {
			posti.get(riga).get(colonna).setAvailable(true);
			return true;
		}
		return false;
		
	}
	
	public Posto getPosto(int row, int col){
		return this.getPosti().get(row).get(col);
	}
	
	public ArrayList<ArrayList<Posto>> getPosti() {
		return posti;
	}

	public void setPosti(ArrayList<ArrayList<Posto>> posti) {
		this.posti = posti;
	}

	public LocalDate getGiorno_to() {
		return giorno_to;
	}

	public void setGiorno_to(LocalDate giorno_to) {
		this.giorno_to = giorno_to;
	}

	public LocalDate getGiorno_from() {
		return giorno_from;
	}

	public void setGiorno_from(LocalDate giorno_from) {
		this.giorno_from = giorno_from;
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
		if (posti.size() != 0) {
			posti.removeAll(posti);
		}
		for (int i = 0; i < sala.getRighe(); i++) {
			ArrayList<Posto> riga = new ArrayList<Posto>();
			for (int j = 0; j < sala.getColonne(); j++) {
				riga.add(new Posto(i,j,true));
			}
			posti.add(riga);
		}
	}

	public FasciaOraria getOrario() {
		return orario;
	}

	public void setOrario(FasciaOraria orario) {
		this.orario = orario;
	}
	
	public double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}
}
