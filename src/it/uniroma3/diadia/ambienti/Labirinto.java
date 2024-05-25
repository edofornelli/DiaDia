package it.uniroma3.diadia.ambienti;

import java.util.HashMap;
import java.util.Map;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.*;

/**
 * Classe Labirinto - Classe che rappresenta un labirinto fisico, luogo di gioco.
 * Contiene all'interno di se una serie di stanze. Gestisce la topologia delle varie stanze.
 * Un labirinto ha 2 variabili di tipo Stanza che corrispondono alla stanza iniziale e a quella
 * vincente del labirinto.
 * @author giovi
 *
 */

public class Labirinto {
	
	Stanza stanzaVincente;
	Stanza stanzaIniziale;
	
	public Labirinto() {
		creaStanze();
	}
	
	public static LabirintoBuilder newBuilder() {
		return new LabirintoBuilder();
	}
	
	public static class LabirintoBuilder{
		private Labirinto maze = null;
		private Stanza stanzaIniziale = null;
		private Stanza stanzaVincente = null;
		private Stanza ultimaStanzaAggiunta = null;
		Map<String,Stanza> listaStanze = null;
		
		public LabirintoBuilder() {
			maze = new Labirinto();
			listaStanze = new HashMap<String,Stanza>();
		}
		
		public LabirintoBuilder addStanzaIniziale(String nomeStanza) {
			stanzaIniziale = new Stanza(nomeStanza);
			ultimaStanzaAggiunta = stanzaIniziale;
			listaStanze.put(nomeStanza, stanzaIniziale);
			return this;
		}
		
		public LabirintoBuilder setStanzaIniziale(Stanza stanzaIniziale) {
			this.stanzaIniziale = stanzaIniziale;
			ultimaStanzaAggiunta = this.stanzaIniziale;
			listaStanze.put(this.stanzaIniziale.getNome(), this.stanzaIniziale);
			return this;
		}
		
		public LabirintoBuilder addStanzaVincente(String nomeStanza) {
			stanzaVincente = new Stanza(nomeStanza);
			ultimaStanzaAggiunta = stanzaVincente;
			listaStanze.put(nomeStanza, stanzaVincente);
			return this;
		}
		
		public LabirintoBuilder setStanzaVincente(Stanza stanzaVincente) {
			this.stanzaVincente = stanzaVincente;
			ultimaStanzaAggiunta = this.stanzaVincente;
			listaStanze.put(this.stanzaVincente.getNome(), this.stanzaVincente);
			return this;
		}
		
		public LabirintoBuilder addStanza(String nomeStanza) {
			Stanza nuovaStanza = new Stanza(nomeStanza);
			listaStanze.put(nomeStanza, nuovaStanza);
			ultimaStanzaAggiunta = nuovaStanza;
			return this;
		}
		
		public LabirintoBuilder addStanzaMagica(String nomeStanza,int sogliaMagica) {
			Stanza nuovaStanzaMagica = new StanzaMagica(nomeStanza,sogliaMagica);
			listaStanze.put(nomeStanza, nuovaStanzaMagica);
			ultimaStanzaAggiunta = nuovaStanzaMagica;
			return this;
		}
		
		public LabirintoBuilder addStanzaBloccata(String nomeStanza, String direzioneBloccata, String nomeAttrezzoSbloccante) {
			Stanza nuovaStanzaBloccata = new StanzaBloccata(nomeStanza, direzioneBloccata, nomeAttrezzoSbloccante);
			listaStanze.put(nomeStanza, nuovaStanzaBloccata);
			ultimaStanzaAggiunta = nuovaStanzaBloccata;
			return this;
		}
		
		public LabirintoBuilder addStanzaBuia(String nomeStanza,String nomeAttrezzoIlluminante) {
			Stanza nuovaStanzaBuia = new StanzaBuia(nomeStanza,nomeAttrezzoIlluminante);
			listaStanze.put(nomeStanza,nuovaStanzaBuia);
			ultimaStanzaAggiunta = nuovaStanzaBuia;
			return this;
		}
		
		/*
		 * Metodo che imposta l'adiacenza tra una stanza di partenza (origine) e una stanza di adiacenza (stanza adiacente).
		 * Il metodo deve essere invocato dopo che entrambe le stanze sono state create e sono presenti
		 * nella mappa "listaStanze" del labirintoBuilder.
		 * @param nome della stanza di partenza
		 * @param nome della stanza adiacente a quella di partenza
		 * @param direzione verso la stanza adiacente
		 */
		
		public LabirintoBuilder addAdiacenza(String nomeStanzaOrigine, String nomeStanzaAdiacente, String direzioneVersoAdiacente) {
			Stanza origine = listaStanze.get(nomeStanzaOrigine);
			Stanza adiacente = listaStanze.get(nomeStanzaAdiacente);
			if(origine!=null && adiacente!=null) {
				origine.impostaStanzaAdiacente(direzioneVersoAdiacente, adiacente);
				return this;
			}else
				return null;
		}
		
		public LabirintoBuilder addAttrezzo(String nomeAttrezzo,int peso) {
			Attrezzo attrezzo = new Attrezzo(nomeAttrezzo,peso);
			ultimaStanzaAggiunta.addAttrezzo(attrezzo);
			return this;
		}
		
		public LabirintoBuilder addPersonaggio(AbstractPersonaggio personaggio) {
			ultimaStanzaAggiunta.setPersonaggio(personaggio);
			return this;
		}
		
		public LabirintoBuilder addMago(String nomeMago,String presentazione,String nomeAttrezzo,int pesoAttrezzo) {
			AbstractPersonaggio nuovoMago = new Mago(nomeMago,presentazione,new Attrezzo(nomeAttrezzo,pesoAttrezzo));
			ultimaStanzaAggiunta.setPersonaggio(nuovoMago);
			return this;
		}
		
		public LabirintoBuilder addStrega(String nomeStrega, String presentazione) {
			AbstractPersonaggio nuovaStrega = new Strega(nomeStrega,presentazione);
			ultimaStanzaAggiunta.setPersonaggio(nuovaStrega);
			return this;
		}
		
		public LabirintoBuilder addCane(String nomeCane, String presentazione,String nomeAttr,int pesoAttr,String nomeCiboAppetitoso) {
			AbstractPersonaggio nuovoCane = new Cane(nomeCane,presentazione,new Attrezzo(nomeAttr,pesoAttr),nomeCiboAppetitoso,false);
			ultimaStanzaAggiunta.setPersonaggio(nuovoCane);
			return this;
		}
		
		public Map<String,Stanza> getListaStanze() {
			return listaStanze;
		}
		
		public Labirinto getLabirinto() {
			if(ultimaStanzaAggiunta == null)
				return null;
			this.maze.setStanzaIniziale(stanzaIniziale);
			this.maze.setStanzaVincente(stanzaVincente);
			return maze;
		}
	}
	
	// Per motivi di testing
	public Labirinto (Stanza stanzaIniziale, Stanza stanzaVincente) {
		this.stanzaVincente=stanzaVincente;
		this.stanzaIniziale=stanzaIniziale;
	}
	
    public void setStanzaVincente(Stanza stanzaVincente) {
    	this.stanzaVincente=stanzaVincente;
    }
    
	public Stanza getStanzaVincente() {
		return stanzaVincente;
	}

	public void setStanzaIniziale(Stanza stanzaIniziale) {
		this.stanzaIniziale = stanzaIniziale;
	}
	
	/* Funzione che ritorna la stanza dove il giocatore si trova quando
	 * comincia la partita
	 */
	public Stanza getStanzaIniziale() {
		return this.stanzaIniziale;
	}
	
	private void creaStanze() {

		/* crea gli attrezzi */
    	Attrezzo lanterna = new Attrezzo("lanterna",2);
		Attrezzo osso = new Attrezzo("osso",1);
		//Attrezzo chiave = new Attrezzo("chiave",1);
		/* crea stanze del labirinto */
		Stanza atrio = new Stanza("Atrio");
		Stanza aulaN11 = new Stanza("Aula N11");
		Stanza aulaN10 = new StanzaBuia("Aula N10","lanterna");
		
		Stanza aulaCampus1= new StanzaMagica("Aula Campus 1");
		
		Stanza laboratorio = new Stanza("Laboratorio Campus");
		Stanza biblioteca = new Stanza("Biblioteca");
		
		Stanza DS1Bloccata = new StanzaBloccata("Aula DS1","nord","chiave");
		Stanza bagnoDS1 = new Stanza("Bagno DS1");
		/* collega le stanze */
		atrio.impostaStanzaAdiacente("nord", biblioteca);
		atrio.impostaStanzaAdiacente("est", aulaN11);
		atrio.impostaStanzaAdiacente("sud", aulaN10);
		atrio.impostaStanzaAdiacente("ovest", laboratorio);
		
		aulaN11.impostaStanzaAdiacente("est", laboratorio);
		aulaN11.impostaStanzaAdiacente("ovest", atrio);
		aulaN11.impostaStanzaAdiacente("nord", DS1Bloccata);
		
		DS1Bloccata.impostaStanzaAdiacente("sud", aulaN11);
		DS1Bloccata.impostaStanzaAdiacente("nord", bagnoDS1);
		
		bagnoDS1.impostaStanzaAdiacente("sud", DS1Bloccata);
		
		aulaN10.impostaStanzaAdiacente("nord", atrio);
		aulaN10.impostaStanzaAdiacente("est", aulaN11);
		aulaN10.impostaStanzaAdiacente("ovest", laboratorio);
		aulaN10.impostaStanzaAdiacente("sud", aulaCampus1);
		
		aulaCampus1.impostaStanzaAdiacente("nord", aulaN10);
		
		laboratorio.impostaStanzaAdiacente("est", atrio);
		laboratorio.impostaStanzaAdiacente("ovest", aulaN11);
		biblioteca.impostaStanzaAdiacente("sud", atrio);

        /* pone gli attrezzi nelle stanze */
		aulaN10.addAttrezzo(lanterna);
		atrio.addAttrezzo(osso);
		//atrio.addAttrezzo(chiave);
		// il gioco comincia nell'atrio
        stanzaIniziale = atrio;  
		stanzaVincente = biblioteca;
    }
}
