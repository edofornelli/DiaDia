package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

public class ComandoInteragisci extends AbstractComando{
	private static final String MESSAGGIO_CON_CHI = "Ma con chi voglio interagire... Non c'e' nessuno nella stanza";
	
	private AbstractPersonaggio personaggioInteragibile = null;
	
	public ComandoInteragisci() {
		super("interagisci");
	}
	
	@Override
	public void esegui(Partita partita) {
		IO io = partita.getIO();
		this.personaggioInteragibile = partita.getGiocatore().getStanzaCorrente().getPersonaggio();
		if(this.personaggioInteragibile != null) {
			io.mostraMessaggio(personaggioInteragibile.agisci(partita));
		}else {
			io.mostraMessaggio(MESSAGGIO_CON_CHI);
		}
	}
}
