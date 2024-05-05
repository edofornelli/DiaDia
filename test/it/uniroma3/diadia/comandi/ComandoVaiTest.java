package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.DiaDia;
import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.ambienti.Stanza;

public class ComandoVaiTest {
	public DiaDia diadia;
	public IOSimulator ioSimulator;
	public List <String> lista;
	public Stanza stanzaPrevista;
	
	@BeforeEach
	public void setUp() {
		this.lista = new ArrayList<>();
		stanzaPrevista = new Stanza("Aula N11");
		lista.add("vai sud");
		lista.add("vai est");
		lista.add("fine");
		this.ioSimulator = new IOSimulator(lista);
		this.diadia = new DiaDia(ioSimulator);
		diadia.gioca();
	}
	
	
	@Test
	public void testMiTrovoNellaStanzaCorrettaDopoGliSpostamenti () {
		assertEquals(stanzaPrevista.getNome() , diadia.getPartita().getStanzaCorrente().getNome());
	}
	
}

