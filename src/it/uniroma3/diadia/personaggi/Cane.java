package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Cane extends AbstractPersonaggio {

	private static final String MESSAGGIO_CANE = "WOOOOOF WOOOOOF TI FACCIO IL CULOOOOOOO E TI TOLGO QUEI DUE CFU CHE C'HAI";
	private Attrezzo attrezzo;

	
	public Cane(String nome, String presentaz, Attrezzo attrezzo) {
		super(nome, presentaz);
		this.attrezzo = attrezzo;
	}

	@Override
	public String agisci(Partita partita) {
		partita.getGiocatore().setCfu(-1);
		return MESSAGGIO_CANE;
	}
	
	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		if (attrezzo.getNome().equals("osso")) {
			partita.getStanzaCorrente().addAttrezzo(this.attrezzo);
			
		}
		
		else {
			partita.getStanzaCorrente().addAttrezzo(attrezzo);
			partita.getGiocatore().setCfu(-1);
			return MESSAGGIO_CANE;	
		}
		return null;
	}

	public void setAttrezzoDaRegalare(Attrezzo attrezzo2) {
		this.attrezzo = attrezzo2;
	}

}
