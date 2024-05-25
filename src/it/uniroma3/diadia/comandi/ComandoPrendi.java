package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Borsa;

public class ComandoPrendi extends AbstractComando {
//	private String nomeAttrezzo;

	public ComandoPrendi() {
		super("prendi",null);
	}
	
	public ComandoPrendi(String nomeAttrezzo) {
		super("prendi",nomeAttrezzo);
	}

	public void esegui(Partita partita) {
		IO io = partita.getIO();

		if(this.getParametro()==null) {
			io.mostraMessaggio("Digitare il nome dell'attrezzo che vuoi prendere");
			return;
		}else{
			Stanza stanzaCorrente=partita.getGiocatore().getStanzaCorrente();
			Borsa borsaGiocatore=partita.getGiocatore().getBorsa();
			if(stanzaCorrente.hasAttrezzo(this.getParametro())) {
				if(borsaGiocatore.addAttrezzo(stanzaCorrente.getAttrezzo(this.getParametro()))){
					io.mostraMessaggio("Hai preso "+this.getParametro());
					stanzaCorrente.removeAttrezzo(this.getParametro());
				}else{
					io.mostraMessaggio("L'operazione non ha avuto successo. La borsa è piena");
				}
			}else{
				io.mostraMessaggio("L'attrezzo non è presente nella stanza corrente");
			}
		}
	}
}
