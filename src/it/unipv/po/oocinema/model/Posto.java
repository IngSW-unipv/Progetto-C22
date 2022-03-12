package it.unipv.po.oocinema.model;

public class Posto { 
	
	private int riga;
	private int colonna;
	
	private boolean available;
	
	
	public Posto(int riga, int colonna, boolean available) {
		this.riga = riga;
		this.colonna = colonna;
		this.available = available;
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

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

}
