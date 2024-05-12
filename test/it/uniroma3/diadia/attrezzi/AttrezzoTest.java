package it.uniroma3.diadia.attrezzi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.giocatore.Borsa;

public class AttrezzoTest {
	public Attrezzo attrezzo1;
	public Attrezzo attrezzo2;	
	public Attrezzo attrezzo3;
	public Attrezzo attrezzo4;
	public Attrezzo attrezzo5;
	public Set<Attrezzo> attrezzi;
	public Borsa borsa;
	


	@BeforeEach
	public void setUp() {
		this.attrezzo1 = new Attrezzo("spada", 1);
		this.attrezzo2 = new Attrezzo("spada", 2);
		this.attrezzo3 = new Attrezzo("spada", 2);
		this.attrezzo4 = new Attrezzo("mazza", 4);
		this.attrezzo5 = new Attrezzo("penna", 3);
		
		this.attrezzi = new HashSet <Attrezzo> ();
		this.borsa = new Borsa();
	}
		
	@Test
	public void TestAggiuntaConOggettiStessoNomeMaPesoDiverso() {
		assertTrue(this.attrezzi.add(attrezzo1));
		assertEquals(this.attrezzi.size(), 1);
		
		assertTrue(this.attrezzi.add(attrezzo2));
		assertEquals(this.attrezzi.size(), 2);
	}

	@Test
	public void TestAggiuntaConOggettiStessoNomeStessoPeso() {
		assertTrue(this.attrezzi.add(attrezzo2));
		assertEquals(this.attrezzi.size(), 1);
		
		assertFalse(this.attrezzi.add(attrezzo3));
		assertEquals(this.attrezzi.size(), 1);
	}
	


}
