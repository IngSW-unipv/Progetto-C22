package it.unipv.po.oocinema.model.cinema;

import it.unipv.po.oocinema.model.prenotazione.Prenotazione;

public class Posto { 
	
	private int riga;
	private int colonna;
	private Prenotazione prenotazione;
	
	
	public Posto(int riga, int colonna, Prenotazione prenotazione) {
		this.riga = riga;
		this.colonna = colonna;
		this.prenotazione = prenotazione;
	}
	
	
	public Posto(int riga, int colonna) {
		super();
		this.riga = riga;
		this.colonna = colonna;
	}


	public int getRiga() {
		return riga;
	}
	public void setRiga(int riga) {
		this.riga = riga;
	}
	public int getColonna() {
		return colonna;
	}
	public void setColonna(int colonna) {
		this.colonna = colonna;
	}

	public Prenotazione getPrenotazione() {
		return prenotazione;
	}

	public void setPrenotazione(Prenotazione prenotazione) {
		this.prenotazione = prenotazione;
	}
	
	@Override
	public String toString() {
		
		return"Fila: "+(char)(this.getRiga()+'A')+" Posto: "+this.getColonna()+"\n";
		
	}

}
