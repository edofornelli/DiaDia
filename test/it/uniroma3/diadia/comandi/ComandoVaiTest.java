package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.DiaDia;
import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.ambienti.Stanza;

public class ComandoVaiTest {
	public DiaDia diadia;
	public IOSimulator ioSimulator;
	public List <String> lista;
	public Stanza stanzaPrevista;
	Labirinto labirinto = new Labirinto ("completo");

	
	@BeforeEach
	public void setUp() {
		this.lista = new ArrayList<>();
		stanzaPrevista = new Stanza("AulaN11");
		lista.add("vai sud");
		lista.add("vai est");
		lista.add("fine");
		this.ioSimulator = new IOSimulator(lista);
		labirinto = new LabirintoBuilder(labirinto)
				.addStanzaIniziale("Atrio")
				.aggiungiECreaStanza("AulaN11")
				.aggiungiECreaStanza("AulaN10")
				.aggiungiECreaStanza("Laboratorio")
				.addStanzaVincente("Biblioteca")
				.addAdiacenza("Atrio","Biblioteca","nord")
				.addAdiacenza("Atrio","Laboratorio","ovest")
				.addAdiacenza("Atrio","AulaN11","est")
				.addAdiacenza("Atrio","AulaN10","sud")
				.addAdiacenza("Biblioteca","Atrio","sud")
				.addAdiacenza("Laboratorio","Atrio","est")
				.addAdiacenza("Laboratorio","AulaN11","ovest")
				.addAdiacenza("AulaN11","Atrio","ovest")
				.addAdiacenza("AulaN11","Laboratorio","est")
				.addAdiacenza("AulaN10","Atrio","nord")
				.addAdiacenza("AulaN10","AulaN11","est")
				.addAdiacenza("AulaN10","Laboratorio","ovest")
				.addECreaAttrezzo("lanterna",3,"AulaN10")
				.addECreaAttrezzo("osso",1,"Atrio")
				.addECreaAttrezzo("spada",5,"AulaN11")
				.addECreaAttrezzo("chiave",1,"Atrio")
				.getLabirinto();
		
		this.diadia = new DiaDia(ioSimulator , labirinto);
		diadia.gioca();
	}
	
	
	@Test
	public void testMiTrovoNellaStanzaCorrettaDopoGliSpostamenti () {
		assertEquals(stanzaPrevista.getNome() , diadia.getPartita().getStanzaCorrente().getNome());
	}
	
}

