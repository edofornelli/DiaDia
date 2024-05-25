package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public final class Mago extends AbstractPersonaggio {

	private static final String MESSAGGIO_DONO = "Sei un vero simpaticone, " +
			"con una mia magica azione, troverai un nuovo oggetto nella stanza " +
			"per il tuo borsone!";
	private static final String MESSAGGIO_SCUSE = "Mi spiace, ma non ho piu' nulla...";
	
	private Attrezzo attrezzo = null;
	
	public Mago() {
		super();
	}
	
	public Mago(String nome, String presentazione, Attrezzo attrezzo) {
		super(nome, presentazione);
		this.attrezzo = attrezzo;
	}
	
	public Attrezzo getAttrezzoDaRegalare() {
		return attrezzo;
	}
	
	@Override
	public String agisci(Partita partita) {
		String msg;
		if (this.attrezzo!=null) {
			partita.getGiocatore().getStanzaCorrente().addAttrezzo(this.getAttrezzoDaRegalare());
			this.attrezzo = null;
			msg = MESSAGGIO_DONO;
		}
		else {
			msg = MESSAGGIO_SCUSE;
		}
		return msg;
	}
	
	public void setAttrezzoDaRegalare(Attrezzo attrezzoDaRegalare) {
		this.attrezzo = attrezzoDaRegalare;
	}
	
	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		partita.getGiocatore().getStanzaCorrente().addAttrezzo(new Attrezzo(attrezzo.getNome(),(attrezzo.getPeso()/2)));
		return "Per la tua generosita', ho modificato l'attrezzo che mi hai donato rendendolo pi� leggero. Fanne buon uso!";
	}
	
	@Override
	public String getTipoPersonaggio() {
		return "un Mago";
	}
}