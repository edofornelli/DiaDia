package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBloccata extends Stanza{
	
	 
	String attrezzoSbloccante;
	String direzioneBloccata;

	public StanzaBloccata(String nome, String attrezzoSbloccante, String direzioneBloccata) {
		super(nome);
		this.direzioneBloccata = direzioneBloccata;
		this.attrezzoSbloccante = attrezzoSbloccante;	
	}

	
	@Override
	public Stanza getStanzaAdiacente(String direzione) {
				if (this.hasAttrezzo(attrezzoSbloccante)) {
					return this.stanzeAdiacenti.get(direzione);
				}
				else {
					return this;
				}
	}
	
	
	@Override
	public String getDescrizione() {
		StringBuilder risultato = new StringBuilder();
		risultato.append(this.nome);
		risultato.append("\nUscite: ");
		for (String direzione : this.stanzeAdiacenti.keySet()) {
			//if (direzione!=null)
			risultato.append(" " + direzione);
		}
		risultato.append("\nAttrezzi nella stanza: ");
				
		for (Attrezzo attrezzo : this.attrezzi) {
			//if (attrezzo !=null) 
			risultato.append(attrezzo.toString()+" ");
			}
		
		risultato.append("\nSembra che una di queste uscite sia bloccata... servira' un attrezzo per aprirla");
		return risultato.toString();
	}

}
