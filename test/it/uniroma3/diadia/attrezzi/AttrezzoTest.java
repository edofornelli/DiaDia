package it.uniroma3.diadia.attrezzi;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class AttrezzoTest {
	
	private Attrezzo attrezzoTest1;
	private Attrezzo attrezzoTest2;
	
	@Before
	public void setUp()  {
		attrezzoTest1 = new Attrezzo ("spada",1);
		attrezzoTest2 = new Attrezzo ("spada",1);
	}

	@Test
	public void testEqualsObject() {
		// uguaglianza basata sul nome, non sul riferimento o sul peso.
		assertEquals(attrezzoTest1,attrezzoTest2);
	}
	@Test
	public void testArrayListContenenteAttrezziUguali() {
		/*
		 * la lista dichiara di contenere un certo attrezzo se gli 
		 * viene passata una istanza di attrezzo diversa ma con nome uguale
		 */
		ArrayList<Attrezzo> lista = new ArrayList<Attrezzo>();
		lista.add(attrezzoTest1);
		assertTrue(lista.contains(attrezzoTest2));
	}
}
