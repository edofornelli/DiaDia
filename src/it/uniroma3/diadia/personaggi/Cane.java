package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Giocatore;

public final class Cane extends AbstractPersonaggio {

	private Attrezzo attrezzoDaRegalare = null;
	private String nomeCiboAppetitoso;
	private boolean haMangiato = false;

	public Cane() {
		super();
	}
	
	public Cane(String nome, String presentazione,Attrezzo attrezzoDaRegalare ,String nomeCiboAppetitoso,boolean haMangiato) {
		super(nome, presentazione);
		this.nomeCiboAppetitoso = nomeCiboAppetitoso;
		this.attrezzoDaRegalare = attrezzoDaRegalare;
		this.haMangiato = haMangiato;
	}
	
	public String getNomeCiboAppetitoso() {
		return nomeCiboAppetitoso;
	}
	
	public Attrezzo getAttrezzoDaRegalare() {
		return attrezzoDaRegalare;
	}
	
	@Override
	public String saluta() {
		StringBuilder risposta = new StringBuilder("Sembra esserci un cane... Provo ad avvicinarmi.\nSul collare leggo un nome: ");
		risposta.append(this.getNome()+".\n");
		if(!this.haSalutato()) {
			this.rispostaCane(risposta);
		}else {
			this.rispostaCane(risposta);
			risposta.append("L'ho gia incontrato prima.\n");
		}
		this.setSalutato(true);
		return risposta.toString();
	}

	private void rispostaCane(StringBuilder risposta) {
		if(!this.haMangiato)
			risposta.append("Cane: Woof woof... ggrrrr...\n");
		else
			risposta.append("Il cane se ne sta buono a cuccia con la pancia piena.\n");
	}

	@Override
	public String getTipoPersonaggio() {
		return "un Cane";
	}

	@Override
	public String agisci(Partita partita) {
		StringBuilder msg = new StringBuilder();
		msg.append("Provo ad accarezzare il cane...\n");
		this.morso(partita.getGiocatore(),msg);
		return msg.toString();
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		StringBuilder messaggio = new StringBuilder();
		Giocatore giocatore = partita.getGiocatore();
		if(attrezzo.getNome().equals(nomeCiboAppetitoso)) {
			this.haMangiato=true;
			messaggio.append("Il Cane sembra apprezzare il regalo"
					+ " e mi porta verso un punto della stanza\n"
					+ "dove tiene un attrezzo nascosto.\n"
					+ "Sembra me lo voglia dare come ricompensa...\n");
			if(this.attrezzoDaRegalare != null) {
				if(giocatore.getBorsa().addAttrezzo(this.attrezzoDaRegalare))
					messaggio.append("Prendo "+this.attrezzoDaRegalare.toString()+" e lo metto in borsa\n");
				else {
					messaggio.append("La borsa � piena, dovrei lasciare qualcosa.\n");
					partita.getGiocatore().getStanzaCorrente().addAttrezzo(this.attrezzoDaRegalare);
					messaggio.append("Posso passare a prendere l'attrezzo pi� tardi.\n"
							+ "Il cane non dovrebbe pi� dare fastidio.\n");
				}
				this.attrezzoDaRegalare = null;
			}else {
				messaggio.append("Il cane si � mangiato cio che gli ho dato ma si � rimesso a cuccia.\n");
			}
		}else {
			//restituisce indietro l'attrezzo che il cane ha rifiutato
			partita.getGiocatore().getBorsa().addAttrezzo(attrezzo);
			this.morso(giocatore,messaggio);
		}
		return messaggio.toString();
	}
	
	public void setAttrezzoDaRegalare(Attrezzo attrezzoDaRegalare) {
		this.attrezzoDaRegalare = attrezzoDaRegalare;
	}
	
	public void setNomeCiboAppetitoso(String nomeCiboAppetitoso) {
		this.nomeCiboAppetitoso = nomeCiboAppetitoso;
	}
	
	//Rimuove 1 cfu al giocatore
	private void morso(Giocatore giocatore,StringBuilder msg) {
		giocatore.setCfu(giocatore.getCfu()-1);
		msg.append("Ahia!! Il cane mi ha morso. Evidentemente non ha gradito.");
	}
}
