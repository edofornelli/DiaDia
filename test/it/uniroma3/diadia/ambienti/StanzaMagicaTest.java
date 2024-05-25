package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaMagicaTest {
	static final int SOGLIA_MAGICA=1;
	static final String NOME_ATTREZZO1 = "abc";
	static final String NOME_ATTREZZO_INVERTITO1 = "cba";
	static final String NOME_ATTREZZO2 = "def";
	static final String NOME_ATTREZZO_INVERTITO2 = "fed";
	static final int PESO_ATTREZZO = 1;
	static final int PESO_ATTREZZO_X2=2;
	static final String NOME_STANZA = "Stanza Magica di Test";
	
	private Stanza stanzaMagicaTest;
	private Attrezzo attrezzo1;
	private Attrezzo attrezzo2;
	private Attrezzo attrezzoMagico;
	
	@Before
	public void setUp()  {
		stanzaMagicaTest = new StanzaMagica(NOME_STANZA,SOGLIA_MAGICA);
		attrezzo1 = new Attrezzo(NOME_ATTREZZO1,PESO_ATTREZZO);
		attrezzo2 = new Attrezzo(NOME_ATTREZZO2,PESO_ATTREZZO);
	}

	@Test
	public void testAddAttrezzoSopraSogliaMagica() {
		stanzaMagicaTest.addAttrezzo(attrezzo1);
		stanzaMagicaTest.addAttrezzo(attrezzo2);
		attrezzoMagico = stanzaMagicaTest.getAttrezzi().get(SOGLIA_MAGICA);
		assertEquals(NOME_ATTREZZO_INVERTITO2,attrezzoMagico.getNome());
		assertEquals(PESO_ATTREZZO_X2,attrezzoMagico.getPeso());
	}
	@Test
	public void testAddAttrezzoSottoSogliaMagica() {
		stanzaMagicaTest = new StanzaMagica(NOME_STANZA,2);
		stanzaMagicaTest.addAttrezzo(attrezzo1);
		stanzaMagicaTest.addAttrezzo(attrezzo2);
		int index = stanzaMagicaTest.getAttrezzi().indexOf(attrezzoMagico);
		assertTrue(index == -1);
		assertEquals(NOME_ATTREZZO1,stanzaMagicaTest.getAttrezzi().get(0).getNome());
		assertEquals(PESO_ATTREZZO,stanzaMagicaTest.getAttrezzi().get(0).getPeso());
		assertEquals(NOME_ATTREZZO2,stanzaMagicaTest.getAttrezzi().get(1).getNome());
		assertEquals(PESO_ATTREZZO,stanzaMagicaTest.getAttrezzi().get(1).getPeso());
	}
	
	@Test
	public void testAddMoltepliciAttrezziSopraSogliaMagica() {
		stanzaMagicaTest = new StanzaMagica(NOME_STANZA,0);
		stanzaMagicaTest.addAttrezzo(attrezzo1);
		stanzaMagicaTest.addAttrezzo(attrezzo2);
		Attrezzo attrezzoMagico1 = stanzaMagicaTest.getAttrezzi().get(0);
		Attrezzo attrezzoMagico2 = stanzaMagicaTest.getAttrezzi().get(1);
		assertEquals(NOME_ATTREZZO_INVERTITO1,attrezzoMagico1.getNome());
		assertEquals(PESO_ATTREZZO_X2,attrezzoMagico1.getPeso());
		assertEquals(NOME_ATTREZZO_INVERTITO2,attrezzoMagico2.getNome());
		assertEquals(PESO_ATTREZZO_X2,attrezzoMagico2.getPeso());
	}
}
