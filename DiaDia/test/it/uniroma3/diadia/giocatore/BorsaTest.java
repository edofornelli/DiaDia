package it.uniroma3.diadia.giocatore;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class BorsaTest {
	
	public Attrezzo attrezzo1;
	public Attrezzo attrezzo2;	
	public Attrezzo attrezzo3;
	public Attrezzo attrezzo4;
	public Attrezzo attrezzo5;
	public Attrezzo attrezzo6;

	public Set<Attrezzo> attrezzi;
	public Borsa borsa;
	
	
	@BeforeEach
	public void setUp() {
		this.attrezzo1 = new Attrezzo("spada", 1);
		this.attrezzo2 = new Attrezzo("spada", 2);
		this.attrezzo3 = new Attrezzo("spada", 2);
		this.attrezzo4 = new Attrezzo("mazza", 4);
		this.attrezzo5 = new Attrezzo("penna", 3);
		this.attrezzo6 = new Attrezzo("fucile", 3);
		this.attrezzi = new HashSet <Attrezzo> ();
		this.borsa = new Borsa();
	}
	
	@Test
	public void TestOrdinamentoAttrezziInBorsaRitornaCorrettamenteUnaLista() {
		this.borsa.addAttrezzo(attrezzo1);
		this.borsa.addAttrezzo(attrezzo4);
		this.borsa.addAttrezzo(attrezzo5);
		List <Attrezzo> attrezzi = this.borsa.getContenutoOrdinatoPerPeso();
		assertEquals(attrezzi.size(), 3);
	}
	
	@Test
	public void TestOrdinamentoAttrezziInBorsaSonoRealmenteOrdinatiPerPeso() {
		this.borsa.addAttrezzo(attrezzo1);
		this.borsa.addAttrezzo(attrezzo4);
		this.borsa.addAttrezzo(attrezzo5);
		List <Attrezzo> attrezzi = this.borsa.getContenutoOrdinatoPerPeso();
		assertEquals(attrezzi.get(0), attrezzo1);
		assertEquals(attrezzi.get(1), attrezzo5);
		assertEquals(attrezzi.get(2), attrezzo4);
	}

	
	@Test
	public void TestOrdinamentoAttrezziInBorsaSonoRealmenteOrdinatiPerNome() {
		this.borsa.addAttrezzo(attrezzo1);
		this.borsa.addAttrezzo(attrezzo4);
		this.borsa.addAttrezzo(attrezzo5);
		TreeSet <Attrezzo> attrezzi = (TreeSet<Attrezzo>) this.borsa.getContenutoOrdinatoPerNome();
		assertEquals(attrezzi.first(), attrezzo4);
		assertEquals(attrezzi.last(), attrezzo1);
	}
	
	@Test
	public void TestAttrezziStessoPesoRimangonoSeparatiNelSet() {
		this.borsa.addAttrezzo(attrezzo4);
		this.borsa.addAttrezzo(attrezzo3);
		this.borsa.addAttrezzo(attrezzo6);
		this.borsa.addAttrezzo(attrezzo5);
		
		Set <Attrezzo> attrezziSet = this.borsa.getSortedSetOrdinatoPerPeso();
		List <Attrezzo> attrezziLista = new LinkedList<>();

		for (Attrezzo attrezzo : attrezziSet) {
				attrezziLista.add(attrezzo);
		}
		
		assertEquals (attrezzo3 , attrezziLista.get(0));
		assertEquals (attrezzo6 , attrezziLista.get(1));


	}
	
}
