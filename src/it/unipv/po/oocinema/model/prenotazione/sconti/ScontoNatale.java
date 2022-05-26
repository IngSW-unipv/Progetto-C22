package it.unipv.po.oocinema.model.prenotazione.sconti;

import it.unipv.po.oocinema.model.prenotazione.Prenotazione;
/**
 * Applica uno sconto
 * @author GoF
 *
 */
public class ScontoNatale implements IScontoPrenotazioneStrategy {
	/**
	 * Percentuale che l'utente dovrà pagare
	 */
	private static final double PERCENTUALE = 0.5;
	
	@Override
	public double getTotale(Prenotazione p) {
		return p.getPrezzoTot() * PERCENTUALE;
	}

}
