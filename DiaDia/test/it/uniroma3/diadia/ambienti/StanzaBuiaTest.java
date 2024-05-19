package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBuiaTest {
	private StanzaBuia stanzaBuia;
	private Attrezzo attrezzo;
	
	@BeforeEach
	public void setUp() {
		this.stanzaBuia = new StanzaBuia("salaGiochi");
		this.attrezzo = new Attrezzo ("lanterna", 1);
	}
	
	
	
	@Test
	public void testSenzaAttrezzoBuioPesto () {
		assertEquals("qui c'è buio pesto" , stanzaBuia.getDescrizione());
	}
	
	@Test
	public void testHasAttrezzo () {
		stanzaBuia.addAttrezzo(attrezzo);
		assertTrue(stanzaBuia.hasAttrezzo("lanterna"));
	}
	
}
