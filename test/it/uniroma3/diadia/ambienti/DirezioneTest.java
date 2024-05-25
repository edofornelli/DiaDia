package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import static it.uniroma3.diadia.ambienti.Direzione.*;


public class DirezioneTest {
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testDirezioneOpposta() {
		assertEquals(0,nord.ordinal());
		assertEquals(1,est.ordinal());
		assertEquals(2,ovest.ordinal());
		assertEquals(3,sud.ordinal());
		assertEquals(sud,nord.direzioneOpposta());
		assertEquals(nord,sud.direzioneOpposta());
		assertEquals(ovest,est.direzioneOpposta());
		assertEquals(est,ovest.direzioneOpposta());
		assertNotEquals(sud,est.direzioneOpposta());
		assertNotEquals(sud,ovest.direzioneOpposta());
	}

}
