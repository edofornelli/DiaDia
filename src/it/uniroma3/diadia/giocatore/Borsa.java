package it.uniroma3.diadia.giocatore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

//import it.uniroma3.diadia.Proprieta;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.attrezzi.ComparatorePerNome;
//import it.uniroma3.diadia.attrezzi.ComparatorePerPeso;
import it.uniroma3.diadia.attrezzi.ComparatorePerPeso;


/**
 * Classe Borsa che modella l'inventario del giocatore. La borsa è in grado di immagazzinare
 * e lasciare oggetti.
 * @author 561105
 *
 */

public class Borsa {

	/**
	 * Variabile che imposta il peso massimo che una borsa piena di oggetti può avere
	 */
	
	//@SuppressWarnings("unused")
	//static final private Proprieta prop = new Proprieta();
	
	//public final static int DEFAULT_PESO_MAX_BORSA = Integer.parseInt(Proprieta.getProp("peso_borsa"));
	public final static int DEFAULT_PESO_MAX_BORSA = 20;
	private List<Attrezzo> attrezzi;
	private int pesoMax;

	/**
	 * Builder sovraccarico che utilizza la variabile di peso di default per inizializzare una borsa
	 * quando non viene specificato altrimenti
	 * @see DEFAULT_PESO_MAX_BORSA
	 */

	public Borsa() {
		this(DEFAULT_PESO_MAX_BORSA);
	}

	/**
	 * Builder utilizzato per costruire una borsa quando un peso massimo viene specificato come parametro
	 * @param pesoMax
	 */

	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		this.attrezzi = new ArrayList<Attrezzo>();
	}

	/**
	 * Metodo che aggiunge un attrezzo in borsa.
	 * In caso il peso dell'attrezzo da inserire più il peso degli attrezzi
	 * già presenti in borsa superi il peso massimo consentito della borsa,
	 * ritorna falso, altrimenti vero.
	 * @param attrezzo
	 * @return
	 */

	
	//Ammette duplicati
	public boolean addAttrezzo(Attrezzo attrezzo) {
		int size = this.attrezzi.size();
		if(attrezzo != null && this.getPeso()+attrezzo.getPeso()<=this.getPesoMax())
			this.attrezzi.add(attrezzo);

		return (this.attrezzi.size()>size);
	}

	/**
	 * Metodo che ritorna il peso massimo consentito per una borsa
	 * @return
	 */

	public int getPesoMax() {
		return pesoMax;
	}

	/**
	 * Metodo che ritorna un attrezzo che viene cercato all'interno della borsa
	 * in base al nome passato come un parametro di tipo String.
	 * @param nomeAttrezzo
	 * @return
	 */

	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		int index = this.attrezzi.indexOf(new Attrezzo(nomeAttrezzo,0));
		if(index != -1)
			return this.attrezzi.get(index);
		return null;
	}

	/**
	 * Metodo che ritorna il peso totale di tutti gli oggetti contenuti all'interno di una borsa
	 * @return
	 */

	public int getPeso() {
		int peso = 0;
		Iterator<Attrezzo> itAttrezzo = attrezzi.iterator();
		Attrezzo a = null;
		while(itAttrezzo.hasNext()){
			a = itAttrezzo.next();
			peso = peso + a.getPeso();
		}
		return peso;
	}

	/**
	 * Metodo che controlla se una borsa è vuota verificando che la variabile numeroAttrezzi sia 0
	 * (Variabile numeroAttrezzi va aggiornata di conseguenza se i vari oggetti vengono aggiunti o rimossi)
	 * @see numeroAttrezzi
	 * @return
	 */

	// public boolean isEmpty() {
	// 	return this.attrezzi.isEmpty();
	// }

	/**
	 * Metodo che controlla se è presente un attrezzo cercato all'interno della borsa e
	 * ritorna un booleano. True se è presente, false altrimenti.
	 * L'attrezzo viene ricercato passando un paramentro di tipo String
	 * @param nomeAttrezzo
	 * @return
	 */

	public boolean hasAttrezzo(String nomeAttrezzo) {
		return (this.getAttrezzo(nomeAttrezzo)!=null);
	}

	/**
	 * Metodo che rimuove un attrezzo dalla borsa. L'attrezzo viene cercato all'interno della borsa
	 * tramite l'uso di un parametro di tipo String.
	 * @param nomeAttrezzo tipo String. Viene utilizzato per cercare l'attrezzo.
	 * @return Ritorna il riferimento all'oggetto da rimuovere se trovato nella borsa, altrimenti ritorna null
	 */
	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
		Attrezzo attrezzoDaCercare = new Attrezzo(nomeAttrezzo,0);
		Attrezzo a=null;
		int index = attrezzi.indexOf(attrezzoDaCercare);
		if(index != -1) {
			a = attrezzi.get(index);
			attrezzi.remove(index);
		}
		return a;
	}
	/**
	 * Metodo che restutuisce la quantità di attrezzi inseriti
	 */
	public int getNumeroAttrezzi() {
		return attrezzi.size();
	}
	
	public List<Attrezzo> getContenutoOrdinatoPerPeso(){
		List<Attrezzo> lista = new ArrayList<Attrezzo>(this.attrezzi);
		Collections.sort(lista);
		return lista;
	}
	// O ANCHE
//	public List<Attrezzo> getContenutoOrdinatoPerPeso(){
//		Comparator<Attrezzo> cmp = new ComparatorePerPeso();
//		List<Attrezzo> lista = new ArrayList<Attrezzo>(this.attrezzi);
//		Collections.sort(lista,cmp);
//		return lista;
//	}
	public SortedSet<Attrezzo> getContenutoOrdinatoPerNome(){
		Comparator<Attrezzo> cmp = new ComparatorePerNome();
		SortedSet<Attrezzo> setAttrezziOrdinatiPerNome = new TreeSet<Attrezzo>(cmp);
		setAttrezziOrdinatiPerNome.addAll(this.attrezzi);
		return setAttrezziOrdinatiPerNome;
	}
	
	public Map<Integer,Set<Attrezzo>> getContenutoRaggruppatoPerPeso(){
		
		Map<Integer,Set<Attrezzo>> peso2Attrezzi;
		peso2Attrezzi = new HashMap<Integer,Set<Attrezzo>>();
		
		for(Attrezzo attrezzo : this.attrezzi) {
			int peso = attrezzo.getPeso();
			if(peso2Attrezzi.containsKey(Integer.valueOf(peso))) {
				// Ho già visto questo peso
				Set<Attrezzo> attrezziDelloStessoPeso = peso2Attrezzi.get(peso);
				attrezziDelloStessoPeso.add(attrezzo);
				
			}else{
				// Mai visto un attrezzo con questo peso prima
				Set<Attrezzo> nuovoSetAttrezzi = new HashSet<>();
				nuovoSetAttrezzi.add(attrezzo);
				peso2Attrezzi.put(Integer.valueOf(peso), nuovoSetAttrezzi);
			}
		}
		return peso2Attrezzi;
	}
	
	public SortedSet<Attrezzo> getSortedSetOrdinatoPerPeso(){
		ComparatorePerPeso cmp = new ComparatorePerPeso();
		SortedSet<Attrezzo> setAttrezziOrdPerPeso = new TreeSet<>(cmp);
		setAttrezziOrdPerPeso.addAll(attrezzi);
		return setAttrezziOrdPerPeso;
	}
	
	// ANCHE LA MAPPA E' ORDINATA, NON RICHIESTO.
//	public Map<Integer,Set<Attrezzo>> getContenutoRaggruppatoPerPeso(){
//		Comparator<Integer> cmp = new ComparatorePerPesoPerSortedMap();
//		Map<Integer,Set<Attrezzo>> mapAttrezziOrdinatiPerPeso = new TreeMap<Integer,Set<Attrezzo>>(cmp);
//		for(Attrezzo item:this.attrezzi) {
//			if(item!=null) {
//				if(mapAttrezziOrdinatiPerPeso.containsKey(item.getPeso())) {
//					mapAttrezziOrdinatiPerPeso.get(item.getPeso()).add(item);
//				}else{
//					Set<Attrezzo> setAttrezzi = new TreeSet<Attrezzo>(new ComparatorePerPeso());
//					setAttrezzi.add(item);
//					mapAttrezziOrdinatiPerPeso.put(item.getPeso(), setAttrezzi);
//				}
//			}
//		}
//		return mapAttrezziOrdinatiPerPeso;
//	}
	
	/**
	 * Metodo che costruisce una stringa contenente la lista degli strumenti contenuti nella borsa.
	 */
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		if (!attrezzi.isEmpty()) {
			s.append("Contenuto borsa ("+this.getPeso()+"kg/"+this.getPesoMax()+"kg): ");
			for(Attrezzo item:attrezzi){
				s.append(item.toString()+" ");
			}
		}
		else
			s.append("Borsa vuota");
		return s.toString();
	}
}
