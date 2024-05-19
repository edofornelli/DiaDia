package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBloccataTest {
	private StanzaBloccata stanzaBloccata;
	private Stanza stanzaAdiacente;
	private Attrezzo attrezzoSbloccante;

	@BeforeEach
	public void setUp() {
		this.attrezzoSbloccante = new Attrezzo ("osso", 1);
		this.stanzaBloccata = new StanzaBloccata ("salaGiochi" , "osso", "sud");
		this.stanzaAdiacente = new Stanza ("toletta");		
		stanzaBloccata.impostaStanzaAdiacente("sud", stanzaAdiacente);		
	}

	@Test
	public void testStanzaBloccataSenzaAttrezzoSbloccante() {
		assertEquals(stanzaBloccata, stanzaBloccata.getStanzaAdiacente("sud"));
	}
	
	@Test
	public void testStanzaBloccataConAttrezzoSbloccante() {
		this.stanzaBloccata.addAttrezzo(attrezzoSbloccante);
		assertEquals(stanzaAdiacente, stanzaBloccata.getStanzaAdiacente("sud"));
	}

}
