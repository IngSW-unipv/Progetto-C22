package it.unipv.po.oocinema.model.prenotazione.sconti;

import it.unipv.po.oocinema.model.prenotazione.Prenotazione;

public interface IScontoPrenotazioneStrategy {

	public double getTotale(Prenotazione p);
}
