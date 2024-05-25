package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Giocatore;

public class ComandoVaiTest {

	static final String NOME_STANZA = "Stanza di test";
	static final String NOME_STANZA_ADIACENTE = "Stanza adiacente di test";
	static final String DIREZIONE_ADIACENZA = "nord";
	static final String OPPOSTO_DIREZIONE_ADIACENZA = "sud";

	private Stanza stanzaIniziale;
	private Stanza stanzaAdiacente;

	private IO io;
	private Giocatore giocatoreTest;
	private Partita partitaTest;
	private Labirinto labirintoTest;
	private AbstractComando comandoVai;

	@Before
	public void setUp()  {
		io = new IOConsole();
		stanzaIniziale = new Stanza (NOME_STANZA);
		stanzaAdiacente = new Stanza (NOME_STANZA_ADIACENTE);
		partitaTest = new Partita(io);
		labirintoTest = new Labirinto();
		giocatoreTest = new Giocatore(stanzaIniziale);
		labirintoTest.setStanzaIniziale(stanzaIniziale);
		giocatoreTest.setStanzaCorrente(labirintoTest.getStanzaIniziale());
		partitaTest.setGiocatore(giocatoreTest);
		partitaTest.setLabirinto(labirintoTest);
	}

	@Test
	public void testVaiInStanzaAdiacenteEsistente(){
		stanzaIniziale.impostaStanzaAdiacente(DIREZIONE_ADIACENZA, stanzaAdiacente);
		stanzaAdiacente.impostaStanzaAdiacente(OPPOSTO_DIREZIONE_ADIACENZA, stanzaIniziale);
		comandoVai = new ComandoVai(DIREZIONE_ADIACENZA);
		comandoVai.esegui(partitaTest);
		assertEquals(stanzaAdiacente,giocatoreTest.getStanzaCorrente());
	}
	
	@Test
	public void testVaiInStanzaAdiacenteERitornaIndietro() {
		stanzaIniziale.impostaStanzaAdiacente(DIREZIONE_ADIACENZA, stanzaAdiacente);
		stanzaAdiacente.impostaStanzaAdiacente(OPPOSTO_DIREZIONE_ADIACENZA, stanzaIniziale);
		comandoVai = new ComandoVai(DIREZIONE_ADIACENZA);
		comandoVai.esegui(partitaTest);
		comandoVai = new ComandoVai(OPPOSTO_DIREZIONE_ADIACENZA);
		comandoVai.esegui(partitaTest);
		assertEquals(stanzaIniziale,giocatoreTest.getStanzaCorrente());
	}
	
	@Test
	public void testVaiInStanzaNonCollegata() {
		comandoVai = new ComandoVai(DIREZIONE_ADIACENZA);
		comandoVai.esegui(partitaTest);
		assertEquals(stanzaIniziale,giocatoreTest.getStanzaCorrente());
	}
	
	@Test
	public void testVaiInStanzaAdiacenteMaDirezioneSbagliata() {
		stanzaIniziale.impostaStanzaAdiacente(DIREZIONE_ADIACENZA, stanzaAdiacente);
		stanzaAdiacente.impostaStanzaAdiacente(OPPOSTO_DIREZIONE_ADIACENZA, stanzaIniziale);
		comandoVai = new ComandoVai("Direzione a caso");
		comandoVai.esegui(partitaTest);
		assertEquals(stanzaIniziale,giocatoreTest.getStanzaCorrente());
	}
	
	@Test
	public void testVaiInStanzaAdiacenteAdIniziale() {
		labirintoTest = Labirinto.newBuilder()
				.addStanzaIniziale(NOME_STANZA)
				.addStanza(NOME_STANZA_ADIACENTE)
				.addAdiacenza(NOME_STANZA, NOME_STANZA_ADIACENTE, DIREZIONE_ADIACENZA)
				.getLabirinto();
		this.reinizializzaPartita();
		comandoVai = new ComandoVai(DIREZIONE_ADIACENZA);
		comandoVai.esegui(partitaTest);
		Stanza stanzaAdiacente = giocatoreTest.getStanzaCorrente();
		assertEquals(new Stanza(NOME_STANZA_ADIACENTE),stanzaAdiacente);
	}
	@Test
	public void testVaiInStanzaAdiacenteAdIniziale_EtornaIndietro() {
		labirintoTest = Labirinto.newBuilder()
				.addStanzaIniziale(NOME_STANZA)
				.addStanza(NOME_STANZA_ADIACENTE)
				.addAdiacenza(NOME_STANZA, NOME_STANZA_ADIACENTE, DIREZIONE_ADIACENZA)
				.addAdiacenza(NOME_STANZA_ADIACENTE, NOME_STANZA, OPPOSTO_DIREZIONE_ADIACENZA)
				.getLabirinto();
		this.reinizializzaPartita();
		comandoVai = new ComandoVai(DIREZIONE_ADIACENZA);
		comandoVai.esegui(partitaTest);
		comandoVai = new ComandoVai(OPPOSTO_DIREZIONE_ADIACENZA);
		comandoVai.esegui(partitaTest);
		Stanza stanzaIniziale = giocatoreTest.getStanzaCorrente();
		assertEquals(new Stanza(NOME_STANZA),stanzaIniziale);
	}
	@Test
	public void testVaiDaStanzaBloccataAdAdiacente_SenzaPassepartout() {
		labirintoTest = Labirinto.newBuilder()
				.addStanzaIniziale(NOME_STANZA_ADIACENTE)
				.addStanzaBloccata(NOME_STANZA, OPPOSTO_DIREZIONE_ADIACENZA, "chiave")
				.addAdiacenza(NOME_STANZA, NOME_STANZA_ADIACENTE, DIREZIONE_ADIACENZA)
				.addAdiacenza(NOME_STANZA_ADIACENTE, NOME_STANZA, OPPOSTO_DIREZIONE_ADIACENZA)
				.getLabirinto();
		this.reinizializzaPartita();
		Stanza stanzaCorrente = this.labirintoTest.getStanzaIniziale().getMapStanzeAdiacenti().get(Direzione.valueOf(OPPOSTO_DIREZIONE_ADIACENZA));
		System.out.println(this.labirintoTest.getStanzaIniziale());
		System.out.println(this.labirintoTest.getStanzaIniziale().getMapStanzeAdiacenti().keySet());
		System.out.println(stanzaCorrente);
		giocatoreTest.setStanzaCorrente(stanzaCorrente);
		this.comandoVai = new ComandoVai(OPPOSTO_DIREZIONE_ADIACENZA);
		this.comandoVai.esegui(partitaTest);
		Stanza stanzaCorrenteDopoSpostamento = giocatoreTest.getStanzaCorrente();
		assertEquals(stanzaCorrente,stanzaCorrenteDopoSpostamento);
	}
	
	@Test
	public void testVaiDaStanzaBloccataAdAdiacente_ConPassepartout() {
		labirintoTest = Labirinto.newBuilder()
				.addStanzaIniziale(NOME_STANZA_ADIACENTE)
				.addStanzaBloccata(NOME_STANZA, DIREZIONE_ADIACENZA, "chiave")
				.addAttrezzo("chiave", 1)
				.addAdiacenza(NOME_STANZA, NOME_STANZA_ADIACENTE, DIREZIONE_ADIACENZA)
				.addAdiacenza(NOME_STANZA_ADIACENTE, NOME_STANZA, OPPOSTO_DIREZIONE_ADIACENZA)
				.getLabirinto();
		this.reinizializzaPartita();
		Stanza stanzaCorrente = this.labirintoTest.getStanzaIniziale().getMapStanzeAdiacenti().get(Direzione.valueOf(OPPOSTO_DIREZIONE_ADIACENZA));
		
		System.out.println(this.labirintoTest.getStanzaIniziale().getMapStanzeAdiacenti().keySet());
		System.out.println(stanzaCorrente);
		giocatoreTest.setStanzaCorrente(stanzaCorrente);
		this.comandoVai = new ComandoVai(DIREZIONE_ADIACENZA);
		this.comandoVai.esegui(partitaTest);
		Stanza stanzaCorrenteDopoSpostamento = giocatoreTest.getStanzaCorrente();
		assertEquals(labirintoTest.getStanzaIniziale(),stanzaCorrenteDopoSpostamento);
	}
	
	// Metodo di utilità
	public void reinizializzaPartita() {
		this.partitaTest.setLabirinto(labirintoTest);
		this.giocatoreTest.setStanzaCorrente(labirintoTest.getStanzaIniziale());
	}
}
