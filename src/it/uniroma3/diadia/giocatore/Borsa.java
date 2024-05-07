package it.uniroma3.diadia.giocatore;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Borsa {
	public final static int DEFAULT_PESO_MAX_BORSA = 10;
	private Map<String , Attrezzo> attrezzi;
	private int pesoMax;

	

	
	public Borsa() {
		this.pesoMax = DEFAULT_PESO_MAX_BORSA;
		this.attrezzi = new HashMap <String , Attrezzo> ();
	}
	
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (this.getPeso() + attrezzo.getPeso() > this.getPesoMax())
			return false;
		if (this.attrezzi.size() > 10)
			return false;
		else
		this.attrezzi.put(attrezzo.getNome(), attrezzo);
		return true;
	}
	
	
	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.remove(nomeAttrezzo);
		}
	
	public int getPesoMax() {
		return pesoMax;
	}
	
	
	public int getPeso() {
		int peso = 0;
		
		/* IMPLEMENTAZIONE ALTERNATIVA CON ITERATORE 
		Iterator <Attrezzo> iteratoreDio = this.attrezzi.values().iterator();
		
		while (iteratoreDio.hasNext()) {
			peso += iteratoreDio.next().getPeso();
		}
		*/
		
		for (Attrezzo attrezzo : attrezzi.values()) {
			peso += attrezzo.getPeso();
		}
		
		return peso;		
	}
	
	public boolean isEmpty() {
		return this.attrezzi.size()==0;
	}
	
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.containsKey(nomeAttrezzo);
	}
	

	
	public String toString() {
		StringBuilder s = new StringBuilder();
		if (!this.isEmpty()) {
			s.append("Contenuto borsa ("+this.getPeso()+"kg/"+this.getPesoMax()+"kg): ");
			for (Attrezzo attrezzo : attrezzi.values()) {
				s.append(attrezzo.toString()+" ");
			}
		}
		else
			s.append("Borsa vuota");
		return s.toString();
	}
}