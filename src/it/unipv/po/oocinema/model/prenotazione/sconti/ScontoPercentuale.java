package it.unipv.po.oocinema.model.prenotazione.sconti;

import it.unipv.po.oocinema.model.prenotazione.Prenotazione;

/**
 * Classe che applica uno sconto
 * @author teora
 *
 */
public class ScontoPercentuale implements IScontoPrenotazioneStrategy{
	
	/**
	 * Percentuale che l'utente dovrà pagare
	 */
	private static final double PERCENTUALE = 0.85;
	
	@Override
	public double getTotale(Prenotazione p) {
		return p.getPrezzoTot()*PERCENTUALE;
	}

}
