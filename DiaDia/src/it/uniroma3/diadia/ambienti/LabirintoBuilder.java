package it.uniroma3.diadia.ambienti;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class LabirintoBuilder {

	Labirinto labirinto;
	Map <String, Stanza> nomeToStanza;
	List <Stanza> listaStanze;
	Set <Attrezzo> attrezziCreati;

	public LabirintoBuilder(Labirinto labirinto) {
		this.labirinto = labirinto;
		nomeToStanza = new HashMap <String,Stanza>();
		listaStanze = new LinkedList<Stanza>();
		attrezziCreati = new TreeSet <Attrezzo> ();
	}

	public LabirintoBuilder addStanzaIniziale(String stanzaIniziale) {
		Stanza stanza = new Stanza (stanzaIniziale);

		if (this.nomeToStanza.containsKey(stanzaIniziale)){
			this.labirinto.setStanzaIniziale (nomeToStanza.get(stanzaIniziale));
		}

		if (!this.nomeToStanza.containsKey(stanzaIniziale)) {
			nomeToStanza.put(stanzaIniziale, stanza);
			this.labirinto.setStanzaIniziale(stanza);
		}

		return this;
	}

	public LabirintoBuilder addStanzaVincente(String stanzaVincente) {
		Stanza stanza = new Stanza (stanzaVincente);

		if (this.nomeToStanza.containsKey(stanzaVincente)){
			this.labirinto.setStanzaVincente(nomeToStanza.get(stanzaVincente));
		}

		if (!this.nomeToStanza.containsKey(stanzaVincente)) {
			nomeToStanza.put(stanzaVincente, stanza);
			this.labirinto.setStanzaVincente(stanza);
		}
		return this;
	}

	public LabirintoBuilder aggiungiECreaStanza(String nomeStanzaDaCreare) {
		Stanza stanza = new Stanza (nomeStanzaDaCreare);

		if (!this.nomeToStanza.containsKey(nomeStanzaDaCreare)) {
			nomeToStanza.put(nomeStanzaDaCreare, stanza);
		}

		return this;
	}


	public LabirintoBuilder aggiungiECreaStanzaBuia(String nomeStanzaDaCreare) {
		StanzaBuia stanza = new StanzaBuia (nomeStanzaDaCreare);

		if (!this.nomeToStanza.containsKey(nomeStanzaDaCreare)) {
			nomeToStanza.put(nomeStanzaDaCreare, stanza);
		}

		return this;
	}

	public LabirintoBuilder aggiungiECreaStanzaMagica(String nomeStanzaDaCreare) {
		StanzaMagica stanza = new StanzaMagica (nomeStanzaDaCreare);

		if (!this.nomeToStanza.containsKey(nomeStanzaDaCreare)) {
			nomeToStanza.put(nomeStanzaDaCreare, stanza);
		}

		return this;
	}
	
	public LabirintoBuilder aggiungiECreaStanzaBloccata(String nomeStanzaDaCreare, String attrezzoSbloccante, String direzioneBloccata) {
		StanzaBloccata stanza = new StanzaBloccata (nomeStanzaDaCreare,attrezzoSbloccante, direzioneBloccata);
		if (!this.nomeToStanza.containsKey(nomeStanzaDaCreare)) {
			nomeToStanza.put(nomeStanzaDaCreare, stanza);
		}
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

	public List<Stanza> getListaStanze (){
		listaStanze.clear();
		for (Stanza stanza : nomeToStanza.values()) {
			listaStanze.add(stanza);
		}
		return listaStanze;
	}


	public Stanza getStanza(String nomeStanza) {
		Stanza stanza = this.nomeToStanza.get(nomeStanza);
		return stanza;
	}

	public Labirinto getLabirinto() {
		return this.labirinto;
	}
}
