package it.uniroma3.diadia.ambienti;

import java.util.HashMap;
import java.util.Map;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class LabirintoBuilder {

	Labirinto labirinto;
	Map <String, Stanza> nomeToStanza;

	public LabirintoBuilder(Labirinto labirinto) {
		this.labirinto = labirinto;
		nomeToStanza = new HashMap <String,Stanza>();
	}

	public LabirintoBuilder addStanzaIniziale(String stanzaIniziale) {
		Stanza stanza = new Stanza (stanzaIniziale);
		nomeToStanza.put(stanzaIniziale, stanza);
		this.labirinto.setStanzaIniziale(stanza);
		return this;
	}

	public LabirintoBuilder addStanzaVincente(String stanzaVincente) {
		Stanza stanza = new Stanza (stanzaVincente);
		nomeToStanza.put(stanzaVincente, stanza);
		this.labirinto.setStanzaVincente(stanza);
		return this;
	}

	public LabirintoBuilder aggiungiECreaStanza(String nomeStanzaDaCreare) {
		Stanza stanza = new Stanza (nomeStanzaDaCreare);
		nomeToStanza.put(nomeStanzaDaCreare, stanza);
		return this;
	}

	public LabirintoBuilder addAdiacenza(String stanza1, String stanza2, String direzione) {
		Stanza stanzaPrima = this.getStanza(stanza1);
		Stanza stanzaSeconda = this.getStanza(stanza2);

		stanzaPrima.impostaStanzaAdiacente(direzione, stanzaSeconda);
		return this;
	}

	public LabirintoBuilder addECreaAttrezzo(String nomeAttrezzo, int peso, String stanza) {
		Attrezzo attrezzo = new Attrezzo (nomeAttrezzo, peso);
		Stanza stanzaInCuiAggiungere = this.getStanza(stanza);
		stanzaInCuiAggiungere.addAttrezzo(attrezzo);
		return this;
	}
	
	public Stanza getStanza(String nomeStanza) {
		Stanza stanza = this.nomeToStanza.get(nomeStanza);
		return stanza;
	}

	public Labirinto getLabirinto() {
		return this.labirinto;
	}
}
