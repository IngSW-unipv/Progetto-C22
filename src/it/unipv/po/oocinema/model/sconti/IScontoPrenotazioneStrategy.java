package it.unipv.po.oocinema.model.sconti;

import it.unipv.po.oocinema.model.Prenotazione;

public interface IScontoPrenotazioneStrategy {

	public double getTotale(Prenotazione p);
}
