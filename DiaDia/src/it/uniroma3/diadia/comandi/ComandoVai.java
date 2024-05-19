package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

public class ComandoVai implements Comando{
	private String direzione;
	
	@Override
	public void esegui (Partita partita) {
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		Stanza prossimaStanza = null;
		if (this.direzione == null) {
			partita.getIoConsole().mostraMessaggio("Dove vuoi andare? Devi specificare una direzione.");
			return;
		}
		prossimaStanza = stanzaCorrente.getStanzaAdiacente(this.direzione);
		if (prossimaStanza == null) {
			partita.getIoConsole().mostraMessaggio("Direzione inesistente");
			return;
		}
		
		partita.setStanzaCorrente(prossimaStanza);
		partita.getIoConsole().mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
		partita.getGiocatore().setCfu(-1);
	}
	
	@Override
	public void setParametro (String parametro) {
		this.direzione = parametro;
	}

	@Override
	public String getNome() {
		return "comandoVai";
	}

	@Override
	public String getParametro() {
		return this.direzione;
	}
	
}
