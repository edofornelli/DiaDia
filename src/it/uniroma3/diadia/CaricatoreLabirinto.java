package it.uniroma3.diadia;

import java.io.*;
import java.util.*;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.ambienti.StanzaBloccata;
import it.uniroma3.diadia.ambienti.StanzaBuia;
import it.uniroma3.diadia.ambienti.StanzaMagica;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;
import it.uniroma3.diadia.personaggi.Cane;
import it.uniroma3.diadia.personaggi.Mago;

public class CaricatoreLabirinto {

	/* prefisso di una singola riga di testo contenente tutti i nomi delle stanze */
	private static final String STANZE_MARKER = "Stanze:";             

	private static final String STANZE_BLOCCATE_MARKER = "Stanze Bloccate:";

	private static final String STANZE_BUIE_MARKER = "Stanze Buie:";

	private static final String STANZE_MAGICHE_MARKER = "Stanze Magiche:";

	private static final String PERSONAGGIO_MARKER = "Personaggi:";

	/* prefisso di una singola riga contenente il nome della stanza iniziale */
	private static final String STANZA_INIZIALE_MARKER = "Inizio:";    

	/* prefisso della riga contenente il nome stanza vincente */
	private static final String STANZA_VINCENTE_MARKER = "Vincente:";  

	/* prefisso della riga contenente le specifiche degli attrezzi da collocare nel formato <nomeAttrezzo> <peso> <nomeStanza> */
	private static final String ATTREZZI_MARKER = "Attrezzi:";

	/* prefisso della riga contenente le specifiche dei collegamenti tra stanza nel formato <nomeStanzaDa> <direzione> <nomeStanzaA> */
	private static final String USCITE_MARKER = "Uscite:";

	/*
	 *  Esempio di un possibile file di specifica di un labirinto (vedi POO-26-eccezioni-file.pdf)

		Stanze: biblioteca, N10, N11
		Stanze Bloccate: N12 nord chiave, Bagno sud carta
		Stanze Buie: Corridoio torcia
		Stanze Magiche: 
		Inizio: N10
		Vincente: N11
		Attrezzi: martello 10 biblioteca, pinza 2 N10
		Uscite: biblioteca nord N10, biblioteca sud N11

					Tipo Stanza    Nome    Presentazione          regalo    Tipo Stz Nome CiboApprezzato
		Personaggi: Mago Corridoio Merlino #Sono un mago birichino# martello, Cane N10 Fido #bau bau# bistecca
	 */
	private LineNumberReader reader;

	private Map<String, Stanza> nome2stanza;

	private Stanza stanzaIniziale;
	private Stanza stanzaVincente;

	private Labirinto.LabirintoBuilder builder;

	public CaricatoreLabirinto(String nomeFile) throws FileNotFoundException {
		this.nome2stanza = new HashMap<String,Stanza>();
		this.reader = new LineNumberReader(new FileReader(nomeFile));
		this.builder = Labirinto.newBuilder();
	}

	public Labirinto carica() throws FormatoFileNonValidoException {
		try {
			this.leggiECreaStanze();
			this.leggiInizialeEvincente();
			this.leggiECollocaAttrezzi();
			this.leggiEImpostaUscite();
			this.leggiECollocaPersonaggi();
			return builder.getLabirinto();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}

	}

	private String leggiRigaCheCominciaPer(String marker) throws FormatoFileNonValidoException {
		try {
			String riga = this.reader.readLine();
			check(riga.startsWith(marker),"era attesa una riga che cominciasse per "+marker);
			return riga.substring(marker.length());
		} catch (IOException e) {
			throw new FormatoFileNonValidoException(e.getMessage());
		}
	}

	private void leggiECollocaPersonaggi() throws FormatoFileNonValidoException {
		String rigaDeiPersonaggi = this.leggiRigaCheCominciaPer(PERSONAGGIO_MARKER);
		List<String> listaRighePersonaggio = this.separaStringheAlleVirgole(rigaDeiPersonaggi);
		for(String rigaPersonaggio:listaRighePersonaggio) {
			Scanner scannerDiCaratteristiche = new Scanner(rigaPersonaggio);
			try {
				String tipoPersonaggio;
				String nomeStanzaCollocazione;
				String nomePersonaggio;
				String presentazionePersonaggio;
				String nomeAttrezzoRegalato = null; //Opzionale (Solo per mago e cane)
				int pesoAttrezzoRegalato = 0;
				String ciboGradito = null;//solo in caso di cane
				check(scannerDiCaratteristiche.hasNext(),msgTerminazionePrecoce("Specificare il tipo di personaggio"));
				tipoPersonaggio = scannerDiCaratteristiche.next();

				check(scannerDiCaratteristiche.hasNext(),msgTerminazionePrecoce("Specificare la stanza dentro dove inserire il personaggio"));
				nomeStanzaCollocazione = scannerDiCaratteristiche.next();

				check(scannerDiCaratteristiche.hasNext(),msgTerminazionePrecoce("Specificare il nome del personaggio"));
				nomePersonaggio = scannerDiCaratteristiche.next();

				check(scannerDiCaratteristiche.hasNext(),msgTerminazionePrecoce("Specificare la descrizione del personaggio"));
				presentazionePersonaggio = estraiDescrizione(rigaPersonaggio);

				//Attrezzo regalato
				if(tipoPersonaggio.equals("Mago") || tipoPersonaggio.equals("Cane")) {
					if(scannerDiCaratteristiche.hasNext()) {
						scannerDiCaratteristiche.findInLine("#");
						scannerDiCaratteristiche.findInLine("#");
						nomeAttrezzoRegalato = scannerDiCaratteristiche.next();
					}else {
						throw new FormatoFileNonValidoException("Specificare il nome dell'attrezzo da regalare");
					}
						
					if(scannerDiCaratteristiche.hasNext()) {
						pesoAttrezzoRegalato = Integer.parseInt(scannerDiCaratteristiche.next());
					}else
						throw new FormatoFileNonValidoException("Specificare il peso dell'attrezzo da regalare");
				}

				if(tipoPersonaggio.equals("Cane")) {
					check(scannerDiCaratteristiche.hasNext(),msgTerminazionePrecoce("Specificare il nome del cibo gradito dal cane"));
					ciboGradito = scannerDiCaratteristiche.next();
				}
				scannerDiCaratteristiche.close();
				Class<?> classePersonaggio = Class.forName("it.uniroma3.diadia.personaggi."+tipoPersonaggio);
				AbstractPersonaggio personaggio = (AbstractPersonaggio)classePersonaggio.newInstance();
				check(this.nome2stanza.containsKey(nomeStanzaCollocazione),msgTerminazionePrecoce("Non esiste la stanza specificata dove collocare il personaggio"));
				this.nome2stanza.get(nomeStanzaCollocazione).setPersonaggio(personaggio);
				personaggio.setNome(nomePersonaggio);
				personaggio.setPresentazione(presentazionePersonaggio);

				if(classePersonaggio.equals(Mago.class)) {
					Mago mago = (Mago) personaggio;
					mago.setAttrezzoDaRegalare(new Attrezzo(nomeAttrezzoRegalato,pesoAttrezzoRegalato));
				}

				if(classePersonaggio.equals(Cane.class)) {
					Cane cane = (Cane) personaggio;
					cane.setAttrezzoDaRegalare(new Attrezzo(nomeAttrezzoRegalato,pesoAttrezzoRegalato));
					cane.setNomeCiboAppetitoso(ciboGradito);
				}


			} catch (ClassNotFoundException e) {
				throw new FormatoFileNonValidoException("Tipo di personaggio non esistente");
			} catch (InstantiationException e) {
				System.out.println("Creare un costruttore no-args per i personaggi");
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				scannerDiCaratteristiche.close();
			}
		}
	}

	private String estraiDescrizione(String rigaDelPersonaggio) throws FormatoFileNonValidoException {
		Scanner scannerDiCancelletti = new Scanner(rigaDelPersonaggio);
		scannerDiCancelletti.useDelimiter("#");
		try (Scanner scannerDiParole = scannerDiCancelletti) {
			scannerDiParole.next();
			String descrizione=null;
			if(scannerDiParole.hasNext()) {
				descrizione = scannerDiParole.next();
			}else
				throw new FormatoFileNonValidoException("Aggiungere una descrizioneAlPersonaggio");
			scannerDiParole.close();
			return descrizione;
		}finally {
			scannerDiCancelletti.close();
		}
	}

	private void leggiECreaStanze() throws FormatoFileNonValidoException  {
		String nomiStanze = this.leggiRigaCheCominciaPer(STANZE_MARKER);
		String stanzeBloccate = this.leggiRigaCheCominciaPer(STANZE_BLOCCATE_MARKER);
		String stanzeBuie = this.leggiRigaCheCominciaPer(STANZE_BUIE_MARKER);
		String stanzeMagiche = this.leggiRigaCheCominciaPer(STANZE_MAGICHE_MARKER);

		//CREA STANZE CLASSICHE
		Scanner scannerDiParole;
		List<String> listaStanze = separaStringheAlleVirgole(nomiStanze);
		for(String nomeStanza : listaStanze) {
			nomeStanza = rimuoviPrimoSpazioSePresente(nomeStanza);
			Stanza stanza = new Stanza(nomeStanza);
			this.nome2stanza.put(nomeStanza, stanza);
		}
		//CREA STANZE BLOCCATE
		List<String>listaElementiBloccate = separaStringheAlleVirgole(stanzeBloccate);
		for(String elementiBloccate : listaElementiBloccate) {
			scannerDiParole = new Scanner(elementiBloccate);
			String nomeStanzaBloccata;
			String direzioneBloccata;
			String nomeAttrezzoSbloccante;
			if(scannerDiParole.hasNext()) {
				nomeStanzaBloccata=scannerDiParole.next();
				nomeStanzaBloccata = rimuoviPrimoSpazioSePresente(nomeStanzaBloccata);
			}else {
				scannerDiParole.close();
				throw new FormatoFileNonValidoException("Specificare un nome per la stanza bloccata");
			}
			if(scannerDiParole.hasNext()) {
				direzioneBloccata=scannerDiParole.next();
				direzioneBloccata = rimuoviPrimoSpazioSePresente(direzioneBloccata);
			}else {
				scannerDiParole.close();
				throw new FormatoFileNonValidoException("Specificare la direzione bloccata");
			}
			if(scannerDiParole.hasNext()) {
				nomeAttrezzoSbloccante = scannerDiParole.next();
				nomeAttrezzoSbloccante = rimuoviPrimoSpazioSePresente(nomeAttrezzoSbloccante);
			}else {
				scannerDiParole.close();
				throw new FormatoFileNonValidoException("Specificare la direzione bloccata");
			}
			this.nome2stanza.put(nomeStanzaBloccata, new StanzaBloccata(nomeStanzaBloccata,direzioneBloccata,nomeAttrezzoSbloccante));
		}
		//CREA STANZE BUIE
		List<String>listaElementiBuie = separaStringheAlleVirgole(stanzeBuie);
		for(String elementoBuie : listaElementiBuie) {
			scannerDiParole = new Scanner(elementoBuie);
			String nomeStanza = null;
			String nomeAttrezzoIlluminante = null;
			if(scannerDiParole.hasNext()) {
				nomeStanza = scannerDiParole.next();
			}else {
				scannerDiParole.close();
				throw new FormatoFileNonValidoException("Dare un nome alla stanza buia");
			}
			if(scannerDiParole.hasNext()) {
				nomeAttrezzoIlluminante=scannerDiParole.next();
			}else {
				scannerDiParole.close();
				throw new FormatoFileNonValidoException("Dare un nome all'attrezzo illuminante");
			}
			this.nome2stanza.put(nomeStanza, new StanzaBuia(nomeStanza,nomeAttrezzoIlluminante));
		}
		//CREA STANZE MAGICHE
		List<String>listaElementiMagiche = separaStringheAlleVirgole(stanzeMagiche);
		for(String elementoMagiche:listaElementiMagiche) {
			scannerDiParole = new Scanner(elementoMagiche);
			String nomeStanza = null;
			int sogliaMagica=0;
			if(scannerDiParole.hasNext()) {
				nomeStanza = scannerDiParole.next();
			}else {

			}
			if(scannerDiParole.hasNext()) {
				try {
					sogliaMagica = Integer.valueOf(scannerDiParole.next());
					this.nome2stanza.put(nomeStanza, new StanzaMagica(nomeStanza,sogliaMagica));
				}catch(NumberFormatException e) {
					scannerDiParole.close();
					throw new FormatoFileNonValidoException("Inserire un numero come "
							+ "soglia magica o non inserire niente se vuoi usare la "
							+ "soglia magica di default");
				}
			}else {
				//soglia magica di default
				this.nome2stanza.put(nomeStanza, new StanzaMagica(nomeStanza));
			}
		}
	}
	private List<String> separaStringheAlleVirgole(String string) {

		List<String> result = new LinkedList<>();
		Scanner scanner = new Scanner(string);
		scanner.useDelimiter(",");
		try (Scanner scannerDiParole = scanner) {
			while(scannerDiParole.hasNext())
				result.add(scannerDiParole.next());
		}
		return result;
	}


	private void leggiInizialeEvincente() throws FormatoFileNonValidoException {
		String nomeStanzaIniziale = null;
		nomeStanzaIniziale = this.leggiRigaCheCominciaPer(STANZA_INIZIALE_MARKER);
		nomeStanzaIniziale = rimuoviPrimoSpazioSePresente(nomeStanzaIniziale);
		check(this.isStanzaValida(nomeStanzaIniziale), nomeStanzaIniziale +" non definita");
		String nomeStanzaVincente = this.leggiRigaCheCominciaPer(STANZA_VINCENTE_MARKER);
		nomeStanzaVincente = rimuoviPrimoSpazioSePresente(nomeStanzaVincente);
		check(this.isStanzaValida(nomeStanzaVincente), nomeStanzaVincente + " non definita");
		this.stanzaIniziale = this.nome2stanza.get(nomeStanzaIniziale);
		this.stanzaVincente = this.nome2stanza.get(nomeStanzaVincente);
		this.builder.setStanzaIniziale(this.stanzaIniziale).setStanzaVincente(this.stanzaVincente);
	}

	private void leggiECollocaAttrezzi() throws FormatoFileNonValidoException {
		String specificheAttrezzi = this.leggiRigaCheCominciaPer(ATTREZZI_MARKER);

		for(String specificaAttrezzo : separaStringheAlleVirgole(specificheAttrezzi)) {
			String nomeAttrezzo = null;
			String pesoAttrezzo = null;
			String nomeStanza = null; 
			try (Scanner scannerLinea = new Scanner(specificaAttrezzo)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di un attrezzo."));
				nomeAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il peso dell'attrezzo "+nomeAttrezzo+"."));
				pesoAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza in cui collocare l'attrezzo "+nomeAttrezzo+"."));
				nomeStanza = scannerLinea.next();
			}				
			posaAttrezzo(nomeAttrezzo, pesoAttrezzo, nomeStanza);
		}
	}

	private void posaAttrezzo(String nomeAttrezzo, String pesoAttrezzo, String nomeStanza) throws FormatoFileNonValidoException {
		int peso;
		try {
			peso = Integer.parseInt(pesoAttrezzo);
			Attrezzo attrezzo = new Attrezzo(nomeAttrezzo, peso);
			check(isStanzaValida(nomeStanza),"Attrezzo "+ nomeAttrezzo+" non collocabile: stanza " +nomeStanza+" inesistente");
			this.nome2stanza.get(nomeStanza).addAttrezzo(attrezzo);
		}
		catch (NumberFormatException e) {
			check(false, "Peso attrezzo "+nomeAttrezzo+" non valido");
		}
	}


	private boolean isStanzaValida(String nomeStanza) {
		return this.nome2stanza.containsKey(nomeStanza);
	}

	private void leggiEImpostaUscite() throws FormatoFileNonValidoException {
		List<String> listaUscite = separaStringheAlleVirgole(leggiRigaCheCominciaPer(USCITE_MARKER));
		for(String specificheUscite:listaUscite) {
			specificheUscite = rimuoviPrimoSpazioSePresente(specificheUscite);
			try (Scanner scannerDiLinea = new Scanner(specificheUscite)) {			

				while (scannerDiLinea.hasNext()) {
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("le uscite di una stanza."));
					String stanzaPartenza = scannerDiLinea.next();
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la direzione di una uscita della stanza "+stanzaPartenza));
					String dir = scannerDiLinea.next();
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la destinazione di una uscita della stanza "+stanzaPartenza+" nella direzione "+dir));
					String stanzaDestinazione = scannerDiLinea.next();
					impostaUscita(stanzaPartenza, dir, stanzaDestinazione);
				}
			} 
		}
	}

	private String msgTerminazionePrecoce(String msg) {
		return "Terminazione precoce del file prima di leggere "+msg;
	}

	private void impostaUscita(String stanzaDa, String dir, String nomeA) throws FormatoFileNonValidoException {
		check(isStanzaValida(stanzaDa),"Stanza di partenza sconosciuta "+dir);
		check(isStanzaValida(nomeA),"Stanza di destinazione sconosciuta "+ dir);
		Stanza partenzaDa = this.nome2stanza.get(stanzaDa);
		Stanza arrivoA = this.nome2stanza.get(nomeA);
		partenzaDa.impostaStanzaAdiacente(dir, arrivoA);
	}


	private String rimuoviPrimoSpazioSePresente(String stringa) {
		String partialRes = null;
		Scanner scannerDiSpazi = new Scanner(stringa);
		if(scannerDiSpazi.hasNext())
			partialRes=scannerDiSpazi.next();
		else
			partialRes=stringa;
		String res=partialRes;
		while(scannerDiSpazi.hasNext()) {
			res=res+" "+scannerDiSpazi.next();
		}

		scannerDiSpazi.close();
		return res;
	}

	final private void check(boolean condizioneCheDeveEsseraVera, String messaggioErrore) throws FormatoFileNonValidoException {
		if (!condizioneCheDeveEsseraVera)
			throw new FormatoFileNonValidoException("Formato file non valido [" + this.reader.getLineNumber() + "] "+messaggioErrore);		
	}

	public Stanza getStanzaIniziale() {
		return this.stanzaIniziale;
	}

	public Stanza getStanzaVincente() {
		return this.stanzaVincente;
	}
}