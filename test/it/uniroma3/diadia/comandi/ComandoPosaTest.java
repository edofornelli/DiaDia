package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.DiaDia;
import it.uniroma3.diadia.IOSimulator;

public class ComandoPosaTest {
	public DiaDia diadia;
	public IOSimulator ioSimulator;
	public List <String> lista;
	
	
	@BeforeEach
	public void setUp() {
		this.lista = new ArrayList<>();
		lista.add("prendi osso");
		lista.add("posa osso");
		lista.add("fine");
		this.ioSimulator = new IOSimulator(lista);
		this.diadia = new DiaDia(ioSimulator);
		diadia.gioca();
	}
	
	@Test
	public void testVerificoDiAverPresoEPosatoOssoEDiNonAverloInBorsa () {
		assertEquals(false , diadia.getPartita().getGiocatore().getBorsa().hasAttrezzo("osso"));
	}
}
