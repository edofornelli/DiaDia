package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Giocatore;

public class PartitaTest {
	
	private Partita partita;
	private Giocatore giocatore;
	private Stanza stanzaCorrente;
	public IOConsole ioConsole;
	Labirinto labirinto = new Labirinto ("completo");


	
	@BeforeEach
	public void setUp () {
		IO io = new IOConsole();
		
		labirinto = new LabirintoBuilder(labirinto)
				.addStanzaIniziale("Atrio")
				.aggiungiECreaStanza("AulaN11")
				.aggiungiECreaStanza("AulaN10")
				.aggiungiECreaStanza("Laboratorio")
				.addStanzaVincente("Biblioteca")
				.addAdiacenza("Atrio","Biblioteca","nord")
				.addAdiacenza("Atrio","Laboratorio","ovest")
				.addAdiacenza("Atrio","AulaN11","est")
				.addAdiacenza("Atrio","AulaN10","sud")
				.addAdiacenza("Biblioteca","Atrio","sud")
				.addAdiacenza("Laboratorio","Atrio","est")
				.addAdiacenza("Laboratorio","AulaN11","ovest")
				.addAdiacenza("AulaN11","Atrio","ovest")
				.addAdiacenza("AulaN11","Laboratorio","est")
				.addAdiacenza("AulaN10","Atrio","nord")
				.addAdiacenza("AulaN10","AulaN11","est")
				.addAdiacenza("AulaN10","Laboratorio","ovest")
				.addECreaAttrezzo("lanterna",3,"AulaN10")
				.addECreaAttrezzo("osso",1,"Atrio")
				.addECreaAttrezzo("spada",5,"AulaN11")
				.addECreaAttrezzo("chiave",1,"Atrio")
				.getLabirinto();
		
		this.partita = new Partita((IOConsole) io , labirinto);
		this.giocatore = new Giocatore ();
		this.stanzaCorrente = new Stanza("sala giochi");
		
		giocatore.setCfu(-20);
		partita.setGiocatore(giocatore);
		partita.setStanzaCorrente(stanzaCorrente);
	}
	
	
	@Test
	public void testIsFinita() {
		assertTrue(this.partita.isFinita());
	}
	
	@Test
	public void testSetStanzaCorrente() {
		assertEquals(stanzaCorrente, partita.getStanzaCorrente());
	}

	@Test
	public void testSetGiocatore() {
		assertEquals(giocatore, partita.getGiocatore());
	}
}
