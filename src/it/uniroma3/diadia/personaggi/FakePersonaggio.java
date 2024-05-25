package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
/*
 * Classe che esiste per soli motivi di testing della classe astratta AbstractPersonaggio
 */
public class FakePersonaggio extends AbstractPersonaggio {

	public FakePersonaggio(String nome, String presentazione) {
		super(nome, presentazione);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String agisci(Partita partita) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTipoPersonaggio() {
		// TODO Auto-generated method stub
		return null;
	}

}
