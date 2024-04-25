package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaMagicaTest {

	private StanzaMagica stanzaMagica;
	private Attrezzo attrezzo;
	
	@BeforeEach
	public void setUp() {
		this.stanzaMagica = new StanzaMagica("salaGiochi", 0);
		this.attrezzo = new Attrezzo ("mouse", 1);
		stanzaMagica.addAttrezzo(attrezzo);
	}
	
	
	@Test
	public void testAddAttrezzo () {
		assertTrue(stanzaMagica.addAttrezzo(attrezzo));
	}
	
	
	@Test
	public void testHasAttrezzo () {
		assertTrue(stanzaMagica.hasAttrezzo("esuom"));
	}
	
	@Test
	public void testHasAttrezzo2 () {
		assertFalse(stanzaMagica.hasAttrezzo("mouse"));
	}
	
}
