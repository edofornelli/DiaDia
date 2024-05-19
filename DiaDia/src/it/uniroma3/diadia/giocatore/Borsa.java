package it.uniroma3.diadia.giocatore;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

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
		for (Attrezzo attrezzo : attrezzi.values()) {
			peso += attrezzo.getPeso();
		}
		return peso;		
	}

	public List<Attrezzo> getContenutoOrdinatoPerPeso(){
		List <Attrezzo> listaAttrezzi = new LinkedList <Attrezzo>();
		for (Attrezzo attrezzo : attrezzi.values()) {
			listaAttrezzi.add(attrezzo);
		}
		listaAttrezzi.sort(Attrezzo.ComparatorePesiAttrezzo);
		return listaAttrezzi;
	}

	public SortedSet<Attrezzo> getContenutoOrdinatoPerNome(){
		SortedSet <Attrezzo> setAttrezzi = new TreeSet<Attrezzo>(Attrezzo.ComparatoreNomiAttrezzi);
		for (Attrezzo attrezzo : attrezzi.values()) {
			setAttrezzi.add(attrezzo);
		}
		return setAttrezzi;
	}
	
	public SortedSet<Attrezzo> getSortedSetOrdinatoPerPeso(){
		SortedSet <Attrezzo> setAttrezzi = new TreeSet<Attrezzo>(Attrezzo.ComparatoreNomiEPesoAttrezzi);
		
		for (Attrezzo attrezzo : this.attrezzi.values()) {
			setAttrezzi.add(attrezzo);
		}
		
		return setAttrezzi;
	}
	

	public Map <Integer, Set <Attrezzo>> getContenutoRaggruppatoPerPeso(){
		Map <Integer, Set <Attrezzo>> mappaContenuti = new HashMap <Integer, Set <Attrezzo>>();

		for (Attrezzo attrezzo : this.attrezzi.values()) {
			if (mappaContenuti.containsKey(attrezzo.getPeso())) {
				mappaContenuti.get(attrezzo.getPeso()).add(attrezzo);
			}
			else {
				SortedSet <Attrezzo> setAttrezzi = new TreeSet<Attrezzo>(Attrezzo.ComparatoreNomiAttrezzi);
				setAttrezzi.add(attrezzo);
				mappaContenuti.put(attrezzo.getPeso(), setAttrezzi);
			}	
		}
		return mappaContenuti;
	}


	public boolean isEmpty() {
		return this.attrezzi.size()==0;
	}

	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.containsKey(nomeAttrezzo);
	}



	public String toString() {
		StringBuilder s = new StringBuilder();

		Map <Integer, Set <Attrezzo>> mappaContenuti = new HashMap <Integer, Set <Attrezzo>>();
		mappaContenuti = this.getContenutoRaggruppatoPerPeso();


		if (!this.isEmpty()) {
			s.append("Contenuto borsa ("+this.getPeso()+"kg/"+this.getPesoMax()+"kg): ");

			Iterator <Integer> iter = mappaContenuti.keySet().iterator();

			while (iter.hasNext()) {
				Integer prossimo = iter.next();

				s.append("(" +prossimo.intValue() +", {");

				Iterator<Attrezzo> it = mappaContenuti.get(prossimo).iterator();

				while (it.hasNext()) {
					s.append(it.next().getNome());
					if (it.hasNext()) {
						s.append(" ,");
					}
				}
				s.append("}" + ")");

				if (iter.hasNext()) {
					s.append(" ;");
				}
			}

		}
		else
			s.append("Borsa vuota");
		return s.toString();
	}
}