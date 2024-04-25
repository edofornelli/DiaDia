package it.uniroma3.diadia.ambienti;

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
		Stanza stanza = null;
		return stanza;
	}
	
}
