package it.uniroma3.diadia.ambienti;


import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaTest {
	
	private Stanza stanza;
	private Stanza stanzaAdiacente;
	private Attrezzo attrezzo;
	
	@BeforeEach
	public void setUp() {
		this.stanza = new Stanza("salaGiochi");
		this.stanzaAdiacente = new Stanza ("salotto");
		stanza.impostaStanzaAdiacente("sud", stanzaAdiacente);
		this.attrezzo = new Attrezzo ("mouse", 1);
		stanza.addAttrezzo(attrezzo);
	}
	
	
	@Test
	public void testAddAttrezzo () {
		assertTrue(stanza.addAttrezzo(attrezzo));
	}
	
	
	@Test
	public void testHasAttrezzo () {
		assertTrue(stanza.hasAttrezzo("mouse"));
	}
	
	@Test
	public void testHasAttrezzo2 () {
		assertFalse(stanza.hasAttrezzo("formaggio"));
	}
	
	@Test
	public void testGetStanzaAdiacente() {
		assertEquals(stanzaAdiacente.getNome(),stanza.getStanzaAdiacente("sud").getNome());
	}


}
