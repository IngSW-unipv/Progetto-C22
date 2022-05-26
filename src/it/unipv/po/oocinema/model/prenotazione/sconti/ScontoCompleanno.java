package it.unipv.po.oocinema.model.prenotazione.sconti;

import it.unipv.po.oocinema.model.prenotazione.Prenotazione;

/**
 * Applica uno sconto
 * @author Gof
 *
 */
public class ScontoCompleanno implements IScontoPrenotazioneStrategy{
	
	/**
	 * Percentuale che l'utente dovrà pagare
	 */
	private static final double PERCENTUALE = 0.75;
	
	
	@Override
	public double getTotale(Prenotazione p) {
		if(p.getProiezione().getGiorno().equals(p.getAcquirente().getCompleanno())) {
			return p.getPrezzoTot()*PERCENTUALE;
		} else {
			return p.getPrezzoTot();
		}
		
		
	}

}