package it.unipv.po.oocinema.model.prenotazione.sconti;

import it.unipv.po.oocinema.model.prenotazione.Prenotazione;

public class ScontoNatale implements IScontoPrenotazioneStrategy {

	private static final double PERCENTUALE = 0.5;
	@Override
	public double getTotale(Prenotazione p) {
		return p.getPrezzoTot() * PERCENTUALE;
	}

}
