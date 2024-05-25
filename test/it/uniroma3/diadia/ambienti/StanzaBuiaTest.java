package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBuiaTest {
	static final String NOME_ATTREZZO_ILLUMINANTE = "torcia";
	static final String NOME_ATTREZZO_NON_ILLUMINANTE = "libro";
	static final int PESO_ATTREZZO = 1;
	static final String NOME_STANZA = "stanza buia";
	
	private Stanza stanzaBuia;
	private Attrezzo attrezzoIlluminante;
	private Attrezzo attrezzoNonIlluminante;
	
	
	
	@Before
	public void setUp()  {
		this.attrezzoIlluminante = new Attrezzo (NOME_ATTREZZO_ILLUMINANTE,PESO_ATTREZZO);
		this.attrezzoNonIlluminante = new Attrezzo (NOME_ATTREZZO_NON_ILLUMINANTE,PESO_ATTREZZO);
		this.stanzaBuia = new StanzaBuia(NOME_STANZA,NOME_ATTREZZO_ILLUMINANTE);
	}

	@Test
	public void testGetDescrizioneConAttrezzoIlluminante() {
		this.stanzaBuia.addAttrezzo(attrezzoIlluminante);
		assertTrue(this.stanzaBuia.hasAttrezzo(NOME_ATTREZZO_ILLUMINANTE));
		assertEquals("Ti trovi qui: "
				+ "stanza buia\n"
				+ "\n"
				+ "Uscite: \n"
				+ "Attrezzi nella stanza: torcia (1kg) "+"\n",stanzaBuia.getDescrizione());
	}
	@Test
	public void testGetDescrizioneSenzaAttrezzoIlluminante() {
		assertFalse(this.stanzaBuia.hasAttrezzo(NOME_ATTREZZO_ILLUMINANTE));
		assertEquals("qui c'� un buio pesto",stanzaBuia.getDescrizione());
	}
	@Test
	public void testGetDescrizioneConAttrezzoNonIlluminantePosato() {
		this.stanzaBuia.addAttrezzo(attrezzoNonIlluminante);
		assertFalse(this.stanzaBuia.hasAttrezzo(NOME_ATTREZZO_ILLUMINANTE));
		assertEquals("qui c'� un buio pesto",stanzaBuia.getDescrizione());
	}
	@Test
	public void testGetDescrizioneConPiuAttrezziFraCuiUnIlluminante() {
		this.stanzaBuia.addAttrezzo(attrezzoNonIlluminante);
		this.stanzaBuia.addAttrezzo(attrezzoIlluminante);
		assertTrue(this.stanzaBuia.hasAttrezzo(NOME_ATTREZZO_ILLUMINANTE));
		assertEquals("Ti trovi qui: "
				+ "stanza buia\n"
				+ "\n"
				+ "Uscite: \n"
				+ "Attrezzi nella stanza: libro (1kg) torcia (1kg) \n"
				+ "",stanzaBuia.getDescrizione());
	}
}
