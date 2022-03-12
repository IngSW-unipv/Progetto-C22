package it.unipv.po.oocinema.model.sconti;

import it.unipv.po.oocinema.model.Prenotazione;

public class ScontoPercentuale implements IScontoPrenotazioneStrategy{
	
	private static final double PERCENTUALE = 0.75;
	@Override
	public double getTotale(Prenotazione p) {
		return p.getPrezzoTot()*PERCENTUALE;
	}

}
