package it.unipv.po.oocinema.model.cinema;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;


/**
 * Classe che modella un oggetto di tipo Proiezione
 */

public class Proiezione {
	private Film film;
	private LocalDate giorno;
	private Sala sala;
	private double prezzo;
	private ArrayList<ArrayList<Posto>> posti;
	private LocalTime orario;

	/**
	 * Costruttore con tutte le variabili
	 */

	public Proiezione(Film film, LocalDate giorno, Sala sala, LocalTime orario) {
		this.film = film;
		this.giorno=giorno;
		this.orario = orario;
		this.posti = new ArrayList<ArrayList<Posto>>();
		this.setSala(sala);
	}

	public boolean checkPostoDisponibile(int row, int col) {
		return posti.get(row).get(col).isAvailable();
	}
	
	public int getPostiOccupati() {
		int count = 0;
		for(int i = 0; i < getSala().getRighe(); i++) {
			for (int j = 0; j < getSala().getColonne(); j++) {
				if(!posti.get(i).get(j).isAvailable()) {
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
				riga.add(new Posto(i,j,true));
			}
			posti.add(riga);
		}
	}

	public LocalTime getOrario() {
		return orario;
	}

	public void setOrario(LocalTime orario) {
		this.orario = orario;
	}
	
	public double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}
	
	public LocalDate getGiorno() {
		return giorno;
	}

	public void setGiorno(LocalDate giorno) {
		this.giorno = giorno;
	}

}
