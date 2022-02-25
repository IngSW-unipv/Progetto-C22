package model;

public class Proiezione {
	private String ID_film;
	private String giorno_to;
	private String giorno_from;
	private String ID_sala;
	private String orario;
	
	public Proiezione() {
		
	}
	

	public Proiezione(String ID_film, String giorno_to, String giorno_from, String ID_sala, String orario) {
		this.ID_film = ID_film;
		this.giorno_to = giorno_to;
		this.giorno_from = giorno_from;
		this.ID_sala = ID_sala;
		this.orario = orario;
	}



	public String getID_film() {
		return ID_film;
	}
	public void setID_film(String iD_film) {
		ID_film = iD_film;
	}
	public String getGiorno_to() {
		return giorno_to;
	}
	public void setGiorno_to(String giorno_to) {
		this.giorno_to = giorno_to;
	}
	public String getGiorno_from() {
		return giorno_from;
	}
	public void setGiorno_from(String giorno_from) {
		this.giorno_from = giorno_from;
	}
	public String getID_sala() {
		return ID_sala;
	}
	public void setID_sala(String ID_sala) {
		this.ID_sala = ID_sala;
	}
	public String getOrario() {
		return orario;
	}
	public void setOrario(String orario) {
		this.orario = orario;
	}
}
