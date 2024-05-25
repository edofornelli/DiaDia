package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaTest {

	private static final int MAX_ATTREZZI = 10;
	private static final String ATTREZZO = "AttrezzoDiTest";
	private static final String STANZA = "StanzaTest";
	private static final String STANZA_ADIACENTE = "StanzaAdiacente";
	private static final String nord = "nord";

	protected Stanza stanza;

	@Before
	public void setUp (){
		this.stanza= new Stanza(STANZA);
	}

	@Test
	public void testImpostaStanzaAdiacenteSingola() {
		Stanza adiacente = creaStanzaEImpostaAdiacente(this.stanza, STANZA_ADIACENTE, nord);
		assertEquals(adiacente, this.stanza.getStanzaAdiacente(nord));
	}

	@Test
	public void testCambiaStanzaAdiacente() {
		@SuppressWarnings("unused")
		Stanza adiacente = creaStanzaEImpostaAdiacente(this.stanza, STANZA_ADIACENTE, nord);
		Stanza nuovaAdiacente = creaStanzaEImpostaAdiacente(this.stanza, "Nuova Adiacente", nord);
		assertEquals(nuovaAdiacente, this.stanza.getStanzaAdiacente(nord));
	}
/*
 * C'è ancora il senso nel testare questa dinamica con classi enumerative?
 */
//	@Test
//	public void testImpostaMax4StanzeAdiacenti() {
//		Stanza adiacente = new Stanza(STANZA_ADIACENTE);
//		String[] direzioni = {"nord","sud","est","ovest"};
//		for(String direzione : direzioni)
//			this.stanza.impostaStanzaAdiacente(direzione, adiacente);
//		String direzioneNuova="nord-est";
//		Stanza daNonInserire = creaStanzaEImpostaAdiacente(this.stanza,"da non inserire",direzioneNuova);
//		// Come testo questa dinamica? Ha più senso testare ciò con classi enumerative?
//		assertFalse(this.stanza.getDirezioni().contains(Direzione.valueOf(direzioneNuova)));
//		assertFalse(this.stanza.getMapStanzeAdiacenti().containsValue(daNonInserire));
//	}

	@Test
	public void testGetStanzaAdiacenteEsistente() {
		Stanza adiacente = new Stanza(STANZA_ADIACENTE);
		stanza.impostaStanzaAdiacente(nord,adiacente);
		assertEquals(adiacente,stanza.getStanzaAdiacente(nord));
	}

	@Test
	public void testGetStanzaAdiacenteInesistente() {
		assertNull(stanza.getStanzaAdiacente(nord));
	}
	
	@Test
	public void testGetStanzaAdiacenteDirezioneNonValida() {
		String direzioneNonValida= "direzioneACaso";
		Stanza adiacente = new Stanza(STANZA_ADIACENTE);
		stanza.impostaStanzaAdiacente(nord, adiacente);
		assertNull(stanza.getStanzaAdiacente(direzioneNonValida));
	}

	@Test
	public void testGetAttrezzoEsistente() {
		Attrezzo attrezzo = new Attrezzo(ATTREZZO,1);
		stanza.addAttrezzo(attrezzo);
		assertEquals(attrezzo,stanza.getAttrezzo(ATTREZZO));
	}

	@Test
	public void testGetAttrezzoNonEsistente() {
		assertNull(stanza.getAttrezzo(ATTREZZO));
	}
	
	@Test
	public void testGetAttrezzoNomeVuoto() {
		Attrezzo attrezzo = new Attrezzo (ATTREZZO,1);
		stanza.addAttrezzo(attrezzo);
		assertNull(stanza.getAttrezzo(""));
	}
	
	
	@Test
	public void testGetAttrezzoNomeNonValido() {
		Attrezzo attrezzo = new Attrezzo (ATTREZZO,1);
		String altroAttrezzoNonInStanza = "Altro Attrezzo";
		stanza.addAttrezzo(attrezzo);
		assertNull(stanza.getAttrezzo(altroAttrezzoNonInStanza));
	}

	// controlla se il metodo aggiunge correttamente l'attrezzo
	@Test
	public void testAddAttrezzo() {
		Attrezzo attrezzo = new Attrezzo(ATTREZZO,10);
		assertEquals(true, stanza.addAttrezzo(attrezzo));
	}
	//controlla che non vengano ammessi duplicati
	@Test
	public void testAddAttrezzoGiaPresente() {
		Attrezzo attrezzo = new Attrezzo(ATTREZZO,10);
		stanza.addAttrezzo(attrezzo);
		assertFalse(stanza.addAttrezzo(attrezzo));
	}
	// controlla se il metodo non aggiunge altri attrezzi se la stanza è piena
	@Test
	public void testAddAttrezzoArrayPieno() {
		Attrezzo attrezzo = new Attrezzo("osso",10);
		for(int i = 0;i < MAX_ATTREZZI;i++)
			stanza.addAttrezzo(attrezzo);
		assertFalse(stanza.addAttrezzo(attrezzo));
	}
	@Test
	public void testAddAttrezzoNull() {
		Attrezzo attrezzo=null;
		assertTrue(stanza.addAttrezzo(attrezzo));
	}
	
	// controlla se hasAttrezzo trova l'attrezzo cercato
	@Test
	public void testHasAttrezzo() {
		Attrezzo attrezzo = new Attrezzo("osso",10);
		stanza.addAttrezzo(attrezzo);
		assertTrue(stanza.hasAttrezzo(attrezzo.getNome()));
	}

	// controlla se hasAttrezzo ritorna false se l'oggetto non esiste
	@Test
	public void testHasAttrezzoAttrezzoNonEsistente() {
		assertFalse(stanza.hasAttrezzo("osso"));
	}

	// controlla se removeAttrezzo rimuove correttamente
	@Test
	public void testRemoveAttrezzo() {

		Attrezzo attrezzo = new Attrezzo(ATTREZZO,10);
		stanza.addAttrezzo(attrezzo);
		assertTrue(stanza.removeAttrezzo(ATTREZZO));
	}

	// controlla se removeAttrezzo non rimuove se l'oggetto non è presente nella stanza
	@Test
	public void testRemoveAttrezzoNonEsistente() {
		@SuppressWarnings("unused")
		Attrezzo daNonInserire = new Attrezzo(ATTREZZO,10);
		assertFalse(stanza.removeAttrezzo(ATTREZZO));
	}

	// controlla se removeAttrezzo ritorni false nel caso in cui il parametro passato come attrezzo è null
	@Test
	public void testRemoveAttrezzoNull() {
		assertFalse(stanza.removeAttrezzo(null));
	}

	//Metodo di utilità
	private Stanza creaStanzaEImpostaAdiacente (Stanza stanzaDiPartenza, String nomeStanzaAdiacente, String direzione) {
		Stanza stanzaAdiacente = new Stanza(nomeStanzaAdiacente);
		stanzaDiPartenza.impostaStanzaAdiacente(direzione,stanzaAdiacente);
		return stanzaAdiacente;
	}

}
