package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

public class ComandoRegala extends AbstractComando {

	public ComandoRegala() {
		super("regala",null);
	}
	
	public ComandoRegala(String parametro) {
		super("regala",parametro);
	}

	@Override
	public void esegui(Partita partita) {
		Attrezzo attrezzoDaRegalare = null;
		IO io = partita.getIO();
		AbstractPersonaggio personaggio = partita.getGiocatore().getStanzaCorrente().getPersonaggio();
		if(personaggio != null) {
			if(this.getParametro() == null || this.getParametro().equals("")) {
				io.mostraMessaggio("Ma cosa voglio regalare?");
			}else {
				Borsa borsa = partita.getGiocatore().getBorsa();
				attrezzoDaRegalare = borsa.getAttrezzo(this.getParametro());
				if(attrezzoDaRegalare == null)
					io.mostraMessaggio("Non ho questo attrezzo in borsa...");
				else {
					borsa.removeAttrezzo(attrezzoDaRegalare.getNome());
					io.mostraMessaggio("*regalo "+attrezzoDaRegalare.toString()+" a "+personaggio.toString()+" *");
					io.mostraMessaggio(personaggio.riceviRegalo(attrezzoDaRegalare, partita));
				}
			}
		}else {
			io.mostraMessaggio("Non c'e' nessuno a cui possa regalare qualcosa in questa stanza...");
		}
	}

}
