package it.unipv.po.oocinema.model.sconti;

import it.unipv.po.oocinema.model.Prenotazione;

public class ScontoNatale implements IScontoPrenotazioneStrategy {

	private static final double PERCENTUALE = 0.5;
	@Override
	public double getTotale(Prenotazione p) {
		return p.getPrezzoTot() * PERCENTUALE;
	}

}
