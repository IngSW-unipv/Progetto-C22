package it.unipv.po.oocinema.model.prenotazione.sconti;

import it.unipv.po.oocinema.model.prenotazione.Prenotazione;
/**
 * Interfaccia per l'impostazione dello sconto attivo
 * @author GoF
 *
 */
public interface IScontoPrenotazioneStrategy {

	/**
	 * 
	 * @param p
	 * @return preezzo totale dopo lo sconto
	 */
	public double getTotale(Prenotazione p);
}
