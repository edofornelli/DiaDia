package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBloccataTest {
	
	Stanza stanzaBloccata;
	Stanza stanzaAdiacenteBloccata;
	Stanza stanzaAdiacenteNonBloccata;
	static final String NOME_STANZA_BLOCCATA_DEFAULT = "Stanza bloccata";
	static final String NOME_STANZA_DEFAULT = "Stanza generica";
	static final String DIREZIONE_BLOCCATA = "nord";
	static final String DIREZIONE_NON_BLOCCATA = "est";
	static final String OPPOSTO_DIREZIONE_BLOCCATA = "sud";
	static final String OPPOSTO_DIREZIONE_NON_BLOCCATA = "ovest";
	static final String NOME_PASSEPARTOUT_DEFAULT = "chiave";
	Attrezzo passepartout;
	
	@Before
	public void setUp()  {
		stanzaBloccata = new StanzaBloccata(NOME_STANZA_BLOCCATA_DEFAULT,DIREZIONE_BLOCCATA,NOME_PASSEPARTOUT_DEFAULT);
	}

	@Test
	public void testGetStanzaAdiacenteConPassepartout() {
		passepartout = new Attrezzo(NOME_PASSEPARTOUT_DEFAULT,1);
		stanzaBloccata.addAttrezzo(passepartout);
		stanzaAdiacenteBloccata = new Stanza(NOME_STANZA_DEFAULT);
		stanzaBloccata.impostaStanzaAdiacente(DIREZIONE_BLOCCATA, stanzaAdiacenteBloccata);
		stanzaAdiacenteBloccata.impostaStanzaAdiacente(OPPOSTO_DIREZIONE_BLOCCATA, stanzaBloccata);
		assertEquals(stanzaAdiacenteBloccata,stanzaBloccata.getStanzaAdiacente(DIREZIONE_BLOCCATA));
	}
	
	@Test //In assenza di passepartout deve ritornare se stessa
	public void testGetStanzaAdiacenteSenzaPassepartout() {
		stanzaAdiacenteBloccata = new Stanza(NOME_STANZA_DEFAULT);
		stanzaBloccata.impostaStanzaAdiacente(DIREZIONE_BLOCCATA, stanzaAdiacenteBloccata);
		stanzaAdiacenteBloccata.impostaStanzaAdiacente(OPPOSTO_DIREZIONE_BLOCCATA, stanzaBloccata);
		assertEquals(stanzaBloccata,stanzaBloccata.getStanzaAdiacente(DIREZIONE_BLOCCATA));
	}
	@Test
	public void testGetStanzaAdiacenteNonBloccataConPassepartout() {
		passepartout = new Attrezzo(NOME_PASSEPARTOUT_DEFAULT,1);
		stanzaBloccata.addAttrezzo(passepartout);
		stanzaAdiacenteNonBloccata = new Stanza(NOME_STANZA_DEFAULT);
		stanzaBloccata.impostaStanzaAdiacente(DIREZIONE_NON_BLOCCATA, stanzaAdiacenteNonBloccata);
		stanzaAdiacenteNonBloccata.impostaStanzaAdiacente(OPPOSTO_DIREZIONE_NON_BLOCCATA, stanzaBloccata);
		assertEquals(stanzaAdiacenteNonBloccata,stanzaBloccata.getStanzaAdiacente(DIREZIONE_NON_BLOCCATA));
	}
	
	@Test
	public void testGetStanzaAdiacenteNonBloccataSenzaPassepartout() {
		stanzaAdiacenteNonBloccata = new Stanza(NOME_STANZA_DEFAULT);
		stanzaBloccata.impostaStanzaAdiacente(DIREZIONE_NON_BLOCCATA, stanzaAdiacenteNonBloccata);
		stanzaAdiacenteNonBloccata.impostaStanzaAdiacente(OPPOSTO_DIREZIONE_NON_BLOCCATA, stanzaBloccata);
		assertEquals(stanzaAdiacenteNonBloccata,stanzaBloccata.getStanzaAdiacente(DIREZIONE_NON_BLOCCATA));
	}
}
