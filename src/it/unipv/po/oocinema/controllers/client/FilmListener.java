package it.unipv.po.oocinema.controllers.client;

import it.unipv.po.oocinema.model.cinema.Film;

/**
 * Interfaccia per la creazione del listener per i poster dei film
 * @author GoF
 *
 */
public interface FilmListener {
	/**
	 * 
	 * @param film
	 */
	public void onClickListener(Film film);
}
