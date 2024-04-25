package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IOConsole;



public class FabbricaDiComandiFisarmonicaTest {
	public FabbricaDiComandi factory;
	public String comandoVai;
	public String comandoFine;
	public String comandoGuarda;
	
	@BeforeEach
	public void setUp() {
		factory = new FabbricaDiComandiFisarmonica();
		comandoVai = "vai sud";
		comandoFine = "fine";
		comandoGuarda = "guarda";
	}
	
	
	@Test
	public void testComandoVai() {
		assertEquals( "comandoVai" , factory.costruisciComando(comandoVai).getNome());
		assertEquals( "sud" , factory.costruisciComando(comandoVai).getParametro());
	}
	
	@Test
	public void testComandoGuarda() {
		assertEquals( "comandoGuarda" , factory.costruisciComando(comandoGuarda).getNome());
		assertEquals( null , factory.costruisciComando(comandoGuarda).getParametro());
	}
	
	@Test
	public void testComandoFine() {
		assertEquals( "comandoFine" , factory.costruisciComando(comandoFine).getNome());
		assertEquals( null , factory.costruisciComando(comandoFine).getParametro());
	}

}
