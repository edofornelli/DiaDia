package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoGuarda extends AbstractComando{
	
	public ComandoGuarda() {
		super("guarda");
	}
	
	@Override
	public void esegui(Partita partita) {
		partita.getIO().mostraMessaggio(partita.getGiocatore().getStanzaCorrente().getDescrizione());
		//partita.getIO().mostraMessaggio(partita.getGiocatore().toString());
	}
}
