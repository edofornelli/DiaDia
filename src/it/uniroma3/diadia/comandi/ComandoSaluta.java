package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

public class ComandoSaluta extends AbstractComando {

	public ComandoSaluta() {
		super("saluta");
	}

	@Override
	public void esegui(Partita partita) {
		IO io = partita.getIO();
		AbstractPersonaggio personaggio = partita.getGiocatore().getStanzaCorrente().getPersonaggio();
		if(personaggio != null) {
			io.mostraMessaggio(personaggio.saluta());
		}else {
			io.mostraMessaggio("Non c'� nessuno da salutare in questa stanza...");
		}
	}

}
