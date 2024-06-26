package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoBorsa extends AbstractComando {

	public ComandoBorsa() {
		super("borsa");
	}

	@Override
	public void esegui(Partita partita) {
		partita.getIO().mostraMessaggio(partita.getGiocatore().getBorsa().toString());

	}
}
