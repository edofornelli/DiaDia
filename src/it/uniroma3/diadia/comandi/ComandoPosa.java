package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Borsa;

public class ComandoPosa extends AbstractComando{
//	String nomeAttrezzo;
	
	public ComandoPosa() {
		super("posa",null);
	}
	
	public ComandoPosa(String parametro) {
		super("posa",parametro);
	}
	
	@Override
	public void esegui(Partita partita) {
		if(this.getParametro()==null) {
			partita.getIO().mostraMessaggio("Inserisci il nome dell'attrezzo da lasciare.");
		}else{
			Stanza stanzaCorrente=partita.getGiocatore().getStanzaCorrente();
			Borsa borsaGiocatore=partita.getGiocatore().getBorsa();
			if(borsaGiocatore.hasAttrezzo(this.getParametro())) {
				if(stanzaCorrente.addAttrezzo(borsaGiocatore.getAttrezzo(this.getParametro()))){
					borsaGiocatore.removeAttrezzo(this.getParametro());
					partita.getIO().mostraMessaggio("L'attrezzo è stato posato nella stanza");
				}else{
					partita.getIO().mostraMessaggio("L'attrezzo non può essere posato in questa stanza perché è già piena di altri attrezzi.");
					partita.getIO().mostraMessaggio("Posa l'attrezzo in un'altra stanza!");
				}
			}else{
				partita.getIO().mostraMessaggio("Questo oggetto non è presente nella borsa");
			}
		}
		
	}
}
