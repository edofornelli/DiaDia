package it.uniroma3.diadia;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.ambienti.StanzaBloccata;
import it.uniroma3.diadia.ambienti.StanzaBuia;
import it.uniroma3.diadia.ambienti.StanzaMagica;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Giocatore;

public class IOSimulatorTest {

	private IOSimulator simulatoreIO;
	private DiaDia diadia;
	private Giocatore giocatoreTest;
	private Attrezzo osso;
	private Attrezzo lanterna;
	private Attrezzo chiave;
	private Labirinto labirintoTest;
	private Stanza Iniziale;
	private Stanza Finale;
	private Stanza Bloccata;
	private Stanza AdiacenteBloccata;
	private Stanza Buia;
	private Stanza Magica;

	@Before
	public void setUp()  {
		osso = new Attrezzo ("osso",1);
		lanterna = new Attrezzo ("lanterna",3);
		chiave = new Attrezzo ("chiave",0);

		Iniziale = new Stanza ("Stanza Iniziale");
		Finale = new Stanza ("Stanza Finale");
		Bloccata = new StanzaBloccata("Stanza Bloccata","est","chiave");
		AdiacenteBloccata = new Stanza ("Stanza adiacente alla bloccata");
		Buia = new StanzaBuia ("Stanza Buia","lanterna");
		Magica = new StanzaMagica("Stanza Magica",1);

		Iniziale.impostaStanzaAdiacente("nord", Finale);
		Finale.impostaStanzaAdiacente("sud", Iniziale);
		Iniziale.impostaStanzaAdiacente("est", Bloccata);
		Bloccata.impostaStanzaAdiacente("ovest", Iniziale);
		Bloccata.impostaStanzaAdiacente("est", AdiacenteBloccata);
		AdiacenteBloccata.impostaStanzaAdiacente("ovest", Bloccata);
		Iniziale.impostaStanzaAdiacente("sud", Magica);
		Magica.impostaStanzaAdiacente("nord", Iniziale);
		Iniziale.impostaStanzaAdiacente("ovest", Buia);
		Buia.impostaStanzaAdiacente("est", Iniziale);

		Iniziale.addAttrezzo(osso);
		Iniziale.addAttrezzo(lanterna);
		Iniziale.addAttrezzo(chiave);

		simulatoreIO = new IOSimulator();
		diadia = new DiaDia(simulatoreIO);
		labirintoTest = new Labirinto();
		labirintoTest.setStanzaIniziale(Iniziale);
		labirintoTest.setStanzaVincente(Finale);
		giocatoreTest = new Giocatore(Iniziale);
		diadia = new DiaDia(simulatoreIO,labirintoTest);
		diadia.getPartita().setGiocatore(giocatoreTest);
	}
	
	@Test
	public void testComandoNullo() {
		List<String> comandoNullo = new ArrayList<>();
		comandoNullo.add("\n");
		simulatoreIO.setListaInput(comandoNullo);
		diadia.gioca();
		assertEquals("Inserire un comando",this.simulatoreIO.getOutputList().get(this.simulatoreIO.getOutputList().size()-2));
	}
	
	@Test
	public void testComandoSconosciuto() {
		List<String> comandoSconosciuto= new ArrayList<>();
		comandoSconosciuto.add("cOmAnDoACaSo");
		simulatoreIO.setListaInput(comandoSconosciuto);
		diadia.gioca();
		assertEquals("Comando non esistente",this.simulatoreIO.getOutputList().get(this.simulatoreIO.getOutputList().size()-2));
	}
	
	@Test
	public void testVittoria() {
		ArrayList<String> vaiNord = new ArrayList<String>();
		vaiNord.add("vai nord");
		simulatoreIO.setListaInput(vaiNord);
		diadia.gioca();
		assertTrue(diadia.getPartita().getGiocatore().getStanzaCorrente()==diadia.getPartita().getLabirinto().getStanzaVincente());
		assertEquals("Hai vinto!",this.simulatoreIO.getOutputList().get(this.simulatoreIO.getOutputList().size()-1));
	}

	@Test
	public void testMorte() {
		diadia.getPartita().getGiocatore().setCfu(1);
		List<String> avantiEIndietro = new ArrayList<String>();
		avantiEIndietro.add("vai sud");
		avantiEIndietro.add("vai nord");
		simulatoreIO.setListaInput(avantiEIndietro);
		diadia.gioca();
		assertFalse(diadia.getPartita().getGiocatore().isVivo());
		assertEquals("Sei morto",this.simulatoreIO.getOutputList().get(this.simulatoreIO.getOutputList().size()-1));
	}

	@Test
	public void testPrendiAttrezzo() {
		List<String> prendiAttrezzo = new ArrayList<String>();
		prendiAttrezzo.add("prendi osso");
		prendiAttrezzo.add("borsa");
		simulatoreIO.setListaInput(prendiAttrezzo);
		diadia.gioca();
		assertTrue(this.diadia.getPartita().getGiocatore().getBorsa().hasAttrezzo("osso"));
		assertEquals("Contenuto borsa (1kg/20kg): osso (1kg) ",this.simulatoreIO.getOutputList().get(this.simulatoreIO.getOutputList().size()-2));
	}
	@Test
	public void testPrendiAttrezzoEProvaARiprenderlo() {
		List<String> prendiAttrezzoEProvaARiprenderlo = new ArrayList<>();
		prendiAttrezzoEProvaARiprenderlo.add("prendi osso");
		prendiAttrezzoEProvaARiprenderlo.add("prendi osso");
		prendiAttrezzoEProvaARiprenderlo.add("borsa");
		simulatoreIO.setListaInput(prendiAttrezzoEProvaARiprenderlo);
		diadia.gioca();
		assertTrue(this.diadia.getPartita().getGiocatore().getBorsa().hasAttrezzo("osso"));
		assertEquals("L'attrezzo non è presente nella stanza corrente",this.simulatoreIO.getOutputList().get(this.simulatoreIO.getOutputList().size()-3));
		assertEquals("Contenuto borsa (1kg/20kg): osso (1kg) ",this.simulatoreIO.getOutputList().get(this.simulatoreIO.getOutputList().size()-2));
	}

	@Test
	public void testPrendiAttrezzoNonPresente() {
		List<String> prendiAttrezzoNonPresente = new ArrayList<String>();
		prendiAttrezzoNonPresente.add("prendi attrezzorandom");
		simulatoreIO.setListaInput(prendiAttrezzoNonPresente);
		diadia.gioca();
		assertNull(diadia.getPartita().getGiocatore().getStanzaCorrente().getAttrezzo(prendiAttrezzoNonPresente.get(0)));
		assertEquals("L'attrezzo non è presente nella stanza corrente",this.simulatoreIO.getOutputList().get(this.simulatoreIO.getOutputList().size()-2));
	}

	@Test
	public void testPosaAttrezzoEsistenteInBorsa() {
		List<String> posaAttrezzo = new ArrayList<String>();
		posaAttrezzo.add("prendi osso");
		posaAttrezzo.add("vai sud");
		posaAttrezzo.add("posa osso");
		simulatoreIO.setListaInput(posaAttrezzo);
		diadia.gioca();
		assertTrue(this.diadia.getPartita().getGiocatore().getStanzaCorrente().hasAttrezzo("osso"));
		assertEquals("L'attrezzo è stato posato nella stanza",this.simulatoreIO.getOutputList().get(this.simulatoreIO.getOutputList().size()-2));
	}

	@Test
	public void testPosaAttrezzoNonEsistenteInBorsa() {
		List<String> posaAttrezzo = new ArrayList<String>();
		posaAttrezzo.add("posa attrezzo");
		simulatoreIO.setListaInput(posaAttrezzo);
		diadia.gioca();
		assertEquals("Questo oggetto non è presente nella borsa",this.simulatoreIO.getOutputList().get(this.simulatoreIO.getOutputList().size()-2));
	}
	
	@Test
	public void testBorsaVuota() {
		List<String> istruzione = new ArrayList<String>();
		istruzione.add("borsa");
		simulatoreIO.setListaInput(istruzione);
		diadia.gioca();
		assertEquals("Borsa vuota",this.simulatoreIO.getOutputList().get(this.simulatoreIO.getOutputList().size()-2));
	}
	
	@Test
	public void testVaiInDirezioneEsistente() {
		List<String> direzioneEsistente = new ArrayList<String>();
		direzioneEsistente.add("vai est");
		simulatoreIO.setListaInput(direzioneEsistente);
		diadia.gioca();
		// controlla che il giocatore si sia spostato in modo adeguato controllando se effettivamente la stanza adiacente a quella inizialecorretta
		assertTrue(this.diadia.getPartita().getGiocatore().getStanzaCorrente()==this.diadia.getPartita().getLabirinto().getStanzaIniziale().getStanzaAdiacente("est"));
	}

	@Test
	public void testVaiInDirezioneNonEsistente() {
		List<String> direzioneInesistente = new ArrayList<String>();
		direzioneInesistente.add("vai direzioneACaso");
		simulatoreIO.setListaInput(direzioneInesistente);
		diadia.gioca();
		//non si è mosso
		assertTrue(this.diadia.getPartita().getLabirinto().getStanzaIniziale()==this.diadia.getPartita().getGiocatore().getStanzaCorrente());
		assertEquals("Direzione inesistente.",this.simulatoreIO.getOutputList().get(this.simulatoreIO.getOutputList().size()-2));
	}

	@Test
	public void testGuardaDescrizione() {
		List<String> descrizione = new ArrayList <String>();
		descrizione.add("guarda");
		simulatoreIO.setListaInput(descrizione);
		diadia.gioca();
		assertEquals("Ti trovi qui: "
				+ "Stanza Iniziale\n"
				+ "\n"
				+ "Uscite: nord est ovest sud \n"
				+ "Attrezzi nella stanza: osso (1kg) lanterna (3kg) chiave (0kg) "+"\n",this.simulatoreIO.getOutputList().get(this.simulatoreIO.getOutputList().size()-2));
	}

	 @Test
	 public void testVaiInStanzaBloccataSenzaPassepartout() {
		 List<String> vaiVersoStanzaBloccata = new ArrayList<String>();
		 vaiVersoStanzaBloccata.add("vai est");
		 vaiVersoStanzaBloccata.add("vai est");
		 vaiVersoStanzaBloccata.add("vai est");
		 simulatoreIO.setListaInput(vaiVersoStanzaBloccata);
		 diadia.gioca();
		 assertEquals(giocatoreTest.getStanzaCorrente().getStanzaAdiacente("est"),giocatoreTest.getStanzaCorrente());
		 assertEquals("Ti trovi qui: "
		 		+ "Stanza Bloccata\n"
		 		+ "\n"
		 		+ "Uscite: est ovest \n"
		 		+ "Attrezzi nella stanza: nessun attrezzo\n"
		 		+ "Questa stanza ha la direzione est bloccata.\n"
		 		+ "Per passare devi posare l'oggetto chiave chiamato: chiave."+"\n",this.simulatoreIO.getOutputList().get(this.simulatoreIO.getOutputList().size()-2));
	 }

	 @Test
	 public void testVaiInStanzaBloccataConPassepartout() {
		 List<String> vaiVersoStanzaBloccata = new ArrayList<String>();
		 vaiVersoStanzaBloccata.add("prendi chiave");
		 vaiVersoStanzaBloccata.add("vai est");
		 vaiVersoStanzaBloccata.add("posa chiave");
		 vaiVersoStanzaBloccata.add("vai est");
		 simulatoreIO.setListaInput(vaiVersoStanzaBloccata);
		 diadia.gioca();
		 assertEquals("Ti trovi qui: "
		 		+ "Stanza adiacente alla bloccata\n"
		 		+ "\n"
		 		+ "Uscite: ovest \n"
		 		+ "Attrezzi nella stanza: nessun attrezzo\n"
		 		+ "",this.simulatoreIO.getOutputList().get(this.simulatoreIO.getOutputList().size()-2));
	 }

	 @Test
	 public void testVaiInStanzaMagicaEInvertiOggetto() {
		 List<String> istruzioni = new ArrayList<String>();
		 istruzioni.add("prendi chiave");
		 istruzioni.add("prendi osso");
		 istruzioni.add("prendi lanterna");
		 istruzioni.add("vai sud");
		 istruzioni.add("posa osso");
		 istruzioni.add("posa lanterna");
		 istruzioni.add("posa chiave");
		 istruzioni.add("guarda");
		 istruzioni.add("prendi evaihc");
		 simulatoreIO.setListaInput(istruzioni);
		 diadia.gioca();
		 assertTrue(this.diadia.getPartita().getGiocatore().getBorsa().hasAttrezzo("evaihc"));
		 assertEquals("Ti trovi qui: "
		 		+ "Stanza Magica\n"
				+ "\n"
		 		+ "Uscite: nord \n"
		 		+ "Attrezzi nella stanza: osso (1kg) anretnal (6kg) evaihc (0kg) "+"\n",this.simulatoreIO.getOutputList().get(this.simulatoreIO.getOutputList().size()-3));
	 }
	 
	 @Test
	 public void testVaiInStanzaBuiaSenzaAttrezzoIlluminante() {
		 List<String> istruzioni = new ArrayList<String>();
		 istruzioni.add("vai ovest");
		 istruzioni.add("guarda");
		 simulatoreIO.setListaInput(istruzioni);
		 diadia.gioca();
		 assertEquals("qui c'è un buio pesto",this.simulatoreIO.getOutputList().get(this.simulatoreIO.getOutputList().size()-2));
	 }
	 
	 @Test
	 public void testPrendiPassepartoutPerStanzaBloccataMaPrimaInvertiloNellaMagica() {
		 List<String> istruzioni = new ArrayList<String>();
		 istruzioni.add("prendi chiave");
		 istruzioni.add("prendi osso");
		 istruzioni.add("prendi lanterna");
		 istruzioni.add("vai sud");
		 istruzioni.add("posa osso");
		 istruzioni.add("posa chiave");
		 istruzioni.add("prendi evaihc");
		 istruzioni.add("vai nord");
		 istruzioni.add("vai est");
		 istruzioni.add("vai est");
		 simulatoreIO.setListaInput(istruzioni);
		 diadia.gioca();
		 assertEquals(this.giocatoreTest.getStanzaCorrente().getStanzaAdiacente("est"),this.giocatoreTest.getStanzaCorrente());
		 assertEquals("Ti trovi qui: "
		 		+ "Stanza Bloccata\n"
		 		+ "\n"
		 		+ "Uscite: est ovest \n"
		 		+ "Attrezzi nella stanza: nessun attrezzo\n"
		 		+ "Questa stanza ha la direzione est bloccata.\n"
		 		+ "Per passare devi posare l'oggetto chiave chiamato: chiave."+"\n",this.simulatoreIO.getOutputList().get(this.simulatoreIO.getOutputList().size()-2));
	 }
	 
	 
}
