package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Giocatore;

public class ComandoPosaTest {
	static final String NOME_ATTREZZO="attrezzo di test";
	static final int PESO_ATTREZZO = 1;
	static final String NOME_STANZA = "Stanza di test";
	private IO io;
	private Giocatore giocatoreTest;
	private Partita partitaTest;
	private Labirinto labirintoTest;
	private Attrezzo attrezzoTest;
	private Stanza stanzaDiTest;
	private AbstractComando comandoPosa;
	
	@Before
	public void setUp()  {
		io = new IOConsole();
		partitaTest = new Partita(io);
		labirintoTest = new Labirinto();
		attrezzoTest = new Attrezzo (NOME_ATTREZZO, PESO_ATTREZZO);
		stanzaDiTest = new Stanza (NOME_STANZA);
		giocatoreTest = new Giocatore(stanzaDiTest);
		labirintoTest.setStanzaIniziale(stanzaDiTest);
		partitaTest.setLabirinto(labirintoTest);
		partitaTest.setGiocatore(giocatoreTest);
	}

	@Test
	public void testPosaAttrezzoInBorsa() {
		giocatoreTest.getBorsa().addAttrezzo(attrezzoTest);
		comandoPosa = new ComandoPosa(NOME_ATTREZZO);
		comandoPosa.esegui(partitaTest);
		assertTrue(stanzaDiTest.hasAttrezzo(NOME_ATTREZZO));
		assertFalse(giocatoreTest.getBorsa().hasAttrezzo(NOME_ATTREZZO));
	}
	
	@Test
	public void testPosaAttrezzoNonInBorsa() {
		comandoPosa = new ComandoPosa(NOME_ATTREZZO);
		comandoPosa.esegui(partitaTest);
		assertFalse(stanzaDiTest.hasAttrezzo(NOME_ATTREZZO));
		assertFalse(giocatoreTest.getBorsa().hasAttrezzo(NOME_ATTREZZO));
	}
	
	@Test
	public void testPosaAttrezzoNomeSbagliato() {
		giocatoreTest.getBorsa().addAttrezzo(attrezzoTest);
		comandoPosa = new ComandoPosa("nomeACaso");
		comandoPosa.esegui(partitaTest);
		assertFalse(stanzaDiTest.hasAttrezzo(NOME_ATTREZZO));
		assertTrue(giocatoreTest.getBorsa().hasAttrezzo(NOME_ATTREZZO));
	}
}
