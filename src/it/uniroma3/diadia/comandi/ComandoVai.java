package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

public class ComandoVai extends AbstractComando {
	
//	private String direzione;
	
	public ComandoVai() {
		super("vai",null);
	}
	
	public ComandoVai(String direzione) {
		super("vai",direzione);
	}
	
	@Override
	public void esegui(Partita partita) {
		IO io = partita.getIO();
		Stanza corrente = partita.getGiocatore().getStanzaCorrente();
		if(this.getParametro()==null) {
			io.mostraMessaggio("Specificare una direzione.");
		}else {
			Stanza prossimaStanza = null;
			prossimaStanza = corrente.getStanzaAdiacente(this.getParametro());
			if (prossimaStanza == null) {
				io.mostraMessaggio("Direzione inesistente.");
			}else {
				partita.getGiocatore().setStanzaCorrente(prossimaStanza);
				int cfu = partita.getGiocatore().getCfu();
				partita.getGiocatore().setCfu(--cfu);
				//io.mostraMessaggio(cfu); Debugging purposes
				io.mostraMessaggio(partita.getGiocatore().getStanzaCorrente().getDescrizione());
			}
		}
	}
}

