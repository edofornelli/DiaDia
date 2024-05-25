package it.uniroma3.diadia.giocatore;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class BorsaTest {

	private Borsa borsa;
	
	@Before
	public void setUp() throws Exception {
		this.borsa = new Borsa(10);
	}

	@Test
    public void testGetPesoVuota(){
        assertEquals(0, this.borsa.getPeso());
    }

    @Test
    public void testGetPesoConAttrezzo(){
        Attrezzo attrezzo = new Attrezzo("Spada",10);
        this.borsa.addAttrezzo(attrezzo);
        assertEquals(10,this.borsa.getPeso());
    }

    @Test
    public void testHasAttrezzoNonInBorsa(){
        assertFalse(this.borsa.hasAttrezzo("osso"));
    }

    @Test
    public void testHasAttrezzoInBorsa(){
        Attrezzo attrezzo = new Attrezzo("Spada",10);
        this.borsa.addAttrezzo(attrezzo);
        assertTrue(this.borsa.hasAttrezzo(attrezzo.getNome()));
    }

    public void testHasAttrezzoNull(){
        assertFalse(this.borsa.hasAttrezzo(null));
    }

    @Test
    public void testRemoveAttrezzoNonInBorsa(){
        assertNull(this.borsa.removeAttrezzo("osso"));
    }

    @Test
    public void testRemoveAttrezzoInBorsa(){
        Attrezzo attrezzo = new Attrezzo("Spada",10);
        this.borsa.addAttrezzo(attrezzo);
        assertEquals(attrezzo,this.borsa.removeAttrezzo(attrezzo.getNome()));
    }

    public void testRemoveAttrezzoNull(){
        assertNull(this.borsa.removeAttrezzo(null));
    }
    
    @Test
    public void testAddAttrezzoCapienzaDisponibile(){
        Attrezzo attrezzo = new Attrezzo("Spada",10);
        assertTrue(this.borsa.addAttrezzo(attrezzo));
    }

    @Test
    public void testAddAttrezzoBorsaPiena(){
        Attrezzo attrezzo = new Attrezzo("Spada",10);
        Attrezzo nonInserire = new Attrezzo("Spadina",1);
        this.borsa.addAttrezzo(attrezzo);
        assertFalse(this.borsa.addAttrezzo(nonInserire));
    }
 
    @Test
    public void testAddAttrezzoNull(){
        assertFalse(this.borsa.addAttrezzo(null));
    }
    
    @Test
    public void testGetContenutoOrdinatoPerPesoBorsaVuota() {
    	List<Attrezzo> lista = this.borsa.getContenutoOrdinatoPerPeso();
    	assertTrue(lista.size()==0);
    }
    
    @Test
    public void testGetContenutoOrdinatoPerPesoBorsaNonVuota() {
    	Attrezzo a1 = new Attrezzo("lanterna",2);
    	Attrezzo a2 = new Attrezzo ("spada",1);
    	Attrezzo a3 = new Attrezzo ("spadone",3);
    	Attrezzo a4 = new Attrezzo ("martello",3);
    	Attrezzo a5 = new Attrezzo ("libro",1);
    	this.borsa.addAttrezzo(a1);
    	this.borsa.addAttrezzo(a2);
    	this.borsa.addAttrezzo(a3);
    	this.borsa.addAttrezzo(a4);
    	this.borsa.addAttrezzo(a5);
    	List<Attrezzo> listaAttrezziOrdinataPerPeso = this.borsa.getContenutoOrdinatoPerPeso();
    	assertTrue(listaAttrezziOrdinataPerPeso.get(0).getPeso()==1);
    	assertTrue(listaAttrezziOrdinataPerPeso.get(1).getPeso()==1);
    	assertTrue(listaAttrezziOrdinataPerPeso.get(2).getPeso()==2);
    	assertTrue(listaAttrezziOrdinataPerPeso.get(3).getPeso()==3);
    	assertTrue(listaAttrezziOrdinataPerPeso.get(4).getPeso()==3);
    }
    
    @Test
    public void testGetContenutoOrdinatoPerPesoBorsaConSingoloAttrezzo() {
    	Attrezzo a1 = new Attrezzo ("spada",1);
    	this.borsa.addAttrezzo(a1);
    	List<Attrezzo> listaAttrezziOrdinataPerPeso = this.borsa.getContenutoOrdinatoPerPeso();
    	assertTrue(listaAttrezziOrdinataPerPeso.get(0).getPeso()==1);
    }
    
    @Test
    public void testGetContenutoPerPesoBorsaConAttrezzoDuplicato() {
    	Attrezzo a1 = new Attrezzo ("spada",1);
    	Attrezzo a2 = new Attrezzo ("spada",1);
    	Attrezzo a3 = a1;
    	this.borsa.addAttrezzo(a1);
    	this.borsa.addAttrezzo(a2);
    	this.borsa.addAttrezzo(a3);
    	assertTrue(this.borsa.getNumeroAttrezzi()==3);
    	assertTrue(this.borsa.getContenutoOrdinatoPerPeso().size()==3);
    }
    
    @Test
    public void testGetContenutoOrdinatoPerNomeBorsaConSingoloAttrezzo(){
        Attrezzo a1 = new Attrezzo("sedia",1);
        this.borsa.addAttrezzo(a1);
        SortedSet<Attrezzo> listaAttrezziOrdinataPerNome = this.borsa.getContenutoOrdinatoPerNome();
        assertTrue(listaAttrezziOrdinataPerNome.contains(a1));
        assertTrue(listaAttrezziOrdinataPerNome.first().equals(a1));   	
    }
    
    @Test
    public void testGetContenutoOrdinatoPerNomeBorsaConMoltepliciAttrezzi(){
        Attrezzo a1 = new Attrezzo("sedia",1);
        Attrezzo a2 = new Attrezzo("spada",3);
        Attrezzo a3 = new Attrezzo ("bottiglia",1);
        Attrezzo a4 = new Attrezzo ("t-shirt",2);
        this.borsa.addAttrezzo(a1);
        this.borsa.addAttrezzo(a2);
        this.borsa.addAttrezzo(a3);
        this.borsa.addAttrezzo(a4);
        SortedSet<Attrezzo> listaAttrezziOrdinataPerNome = this.borsa.getContenutoOrdinatoPerNome();
        assertTrue(listaAttrezziOrdinataPerNome.first().equals(a3));
        assertTrue(listaAttrezziOrdinataPerNome.last().equals(a4));    	
    }
    
	@Test
	public void testGetContenutoOrdinatoPerPesoRaggruppatoConSingoloAttrezzo_BorsaVuota(){
        assertTrue(this.borsa.getContenutoRaggruppatoPerPeso().isEmpty());
        
        //assertEquals(Collections.emptyMap(),this.borsa.getContenutoRaggruppatoPerPeso());
    }
	
	@Test
	public void testGetContenutoOrdinatoPerPesoRaggruppatoConSingoloAttrezzo_Singleton(){
        Attrezzo attrezzo = new Attrezzo("attrezzo",1);
        this.borsa.addAttrezzo(attrezzo);
        Map<Integer, Set<Attrezzo>> singletonMap = Collections.singletonMap(1, Collections.singleton(attrezzo));
		assertEquals(singletonMap,this.borsa.getContenutoRaggruppatoPerPeso());
    }
	
	@Test
	public void testGetContenutoOrdinatoPerPesoRaggruppatoConSingoloAttrezzo_Singleton_DueAttrezzi(){
        int stessoPeso = 1;
		Attrezzo attrezzo = new Attrezzo("attrezzo",stessoPeso);
        Attrezzo attrezzoStessoPeso = new Attrezzo ("attrezzo stesso peso",stessoPeso);
        this.borsa.addAttrezzo(attrezzo);
        this.borsa.addAttrezzo(attrezzoStessoPeso);
//        Set<Attrezzo> insiemeAttrezziStessoPeso = Set.of(attrezzo,attrezzoStessoPeso); // Da java 9+
        Set<Attrezzo> insiemeAttrezziStessoPeso = new HashSet<>(Arrays.asList(attrezzo,attrezzoStessoPeso));
		Map<Integer, Set<Attrezzo>> singletonMap = Collections.singletonMap(stessoPeso, insiemeAttrezziStessoPeso);
		assertEquals(singletonMap,this.borsa.getContenutoRaggruppatoPerPeso());
    }
	
	@Test
	public void testGetContenutoOrdinatoPerPesoRaggruppatoConSingoloAttrezzo_Doubleton_DueAttrezzi(){
        Attrezzo attrezzo = new Attrezzo("attrezzo",1);
        Attrezzo attrezzoAltroPeso = new Attrezzo ("attrezzo altro peso",2);
        this.borsa.addAttrezzo(attrezzo);
        this.borsa.addAttrezzo(attrezzoAltroPeso);
     
		Map<Integer, Set<Attrezzo>> doubletonMap = new HashMap<>();
		
		doubletonMap.put(1, Collections.singleton(attrezzo));
		doubletonMap.put(2, Collections.singleton(attrezzoAltroPeso));
		assertEquals(doubletonMap,this.borsa.getContenutoRaggruppatoPerPeso());
    }
	
	@Test
	public void testGetContenutoOrdinatoPerPesoRaggruppatoConSingoloAttrezzo(){
        Attrezzo a1 =new Attrezzo("sedia",2);
        this.borsa.addAttrezzo(a1);
        Map<Integer,Set<Attrezzo>> listaAttrezziOrdinataPerPeso = this.borsa.getContenutoRaggruppatoPerPeso();
        assertTrue(listaAttrezziOrdinataPerPeso.get(2).contains(a1));
    }
	
	@Test
	public void testGetContenutoOrdinatoPerPesoRaggruppatoMoltepliciAttrezzi() {
		Attrezzo a1 = new Attrezzo("lanterna",2);
    	Attrezzo a2 = new Attrezzo ("spada",1);
    	Attrezzo a3 = new Attrezzo ("spadone",3);
    	Attrezzo a4 = new Attrezzo ("martello",3);
    	Attrezzo a5 = new Attrezzo ("libro",1);
    	this.borsa.addAttrezzo(a1);
    	this.borsa.addAttrezzo(a2);
    	this.borsa.addAttrezzo(a3);
    	this.borsa.addAttrezzo(a4);
    	this.borsa.addAttrezzo(a5);
    	Map<Integer,Set<Attrezzo>> listaAttrezziOrdinataPerPeso = this.borsa.getContenutoRaggruppatoPerPeso();
    	assertTrue(listaAttrezziOrdinataPerPeso.get(1).contains(a2));
    	assertTrue(listaAttrezziOrdinataPerPeso.get(1).contains(a5));
    	assertTrue(listaAttrezziOrdinataPerPeso.get(3).contains(a3));
    	assertTrue(listaAttrezziOrdinataPerPeso.get(3).contains(a4));
    	assertTrue(listaAttrezziOrdinataPerPeso.get(2).contains(a1));
	}
	
	@Test
	public void testAttrezziConStessoPesoNomeDiverso() {
		Attrezzo attrezzo = new Attrezzo ("libro",1);
		Attrezzo attrezzoStessoPesoFuoriOrdine = new Attrezzo ("spada",1);
		this.borsa.addAttrezzo(attrezzoStessoPesoFuoriOrdine);
		this.borsa.addAttrezzo(attrezzo);
		assertEquals(Arrays.asList(attrezzo,attrezzoStessoPesoFuoriOrdine),this.borsa.getContenutoOrdinatoPerPeso());
	}

}
