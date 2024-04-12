package it.uniroma3.diadia;


import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il metodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author  docente di POO 
 *         (da un'idea di Michael Kolling and David J. Barnes) 
 *          
 * @version base
 */

public class DiaDia {

	static final private String MESSAGGIO_BENVENUTO = ""+
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissa!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.";

	static final private String[] elencoComandi = {"1) vai: comando che ti permette di muoverti nelle varie stanze", "2) aiuto: ti elenca i comandi disponibili", "3) fine: concludi la partita", "4) prendi: raccogli attrezzo da stanza e lo metti in borsa", "5) posa: posi attrezzo da borsa e lo metti in stanza",   "6) guarda: guardati intorno nella stanza attuale"};

	private Partita partita;
	public IOConsole ioConsole;

	public DiaDia() {
		this.partita = new Partita();
		this.ioConsole = new IOConsole ();

	}

	public void gioca() {
		String istruzione; 

		ioConsole.mostraMessaggio(MESSAGGIO_BENVENUTO);		
		do		
			istruzione = ioConsole.leggiRiga();
		while (!processaIstruzione(istruzione));
	}   


	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 */
	private boolean processaIstruzione(String istruzione) {
		Comando comandoDaEseguire = new Comando(istruzione);
		
		if (istruzione.isEmpty()) {
			ioConsole.mostraMessaggio("Metticelo un comando però sveglione...");
		}
		else if (comandoDaEseguire.getNome().equals("fine")) {
			this.fine(); 
			return true;
		} else if (comandoDaEseguire.getNome().equals("vai"))
			this.vai(comandoDaEseguire.getParametro());
		else if (comandoDaEseguire.getNome().equals("aiuto"))
			this.aiuto();
		else if (comandoDaEseguire.getNome().equals("prendi"))
			this.prendi(comandoDaEseguire.getParametro());
		else if (comandoDaEseguire.getNome().equals("posa"))
			this.posa(comandoDaEseguire.getParametro());
		else if (comandoDaEseguire.getNome().equals("guarda"))
			this.guarda();
		else
			ioConsole.mostraMessaggio("Comando Sconosciuto");
		if (this.partita.vinta()) {
			ioConsole.mostraMessaggio("Hai vinto!");
			return true;
		} else if (partita.isFinita()) {
			ioConsole.mostraMessaggio("Hai perso! avresti dovuto studiare invece di giocare con gli attrezzi...");
			return true;
		}		
		return false;
	}   

	// implementazione del comando posa:

	private void guarda() {
		ioConsole.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
	}

	private void posa(String nomeAttrezzo) {
		if (this.partita.getGiocatore().getBorsa().hasAttrezzo(nomeAttrezzo)) {
			Attrezzo a = this.partita.getGiocatore().getBorsa().removeAttrezzo(nomeAttrezzo);
			boolean attrezzoAggiunto = this.partita.getStanzaCorrente().addAttrezzo(a);
			if (!attrezzoAggiunto) {
				this.partita.getGiocatore().getBorsa().addAttrezzo(a);
			}
		}
	}

	private void prendi(String nomeAttrezzo) {
			//controllo se in quella stanza c'è quell'attrezzo, se c'è trovo rif. oggetto da stringa
		
			if (this.partita.getStanzaCorrente().hasAttrezzo(nomeAttrezzo)) {
			
			Attrezzo a = this.partita.getStanzaCorrente().getAttrezzo(nomeAttrezzo);
			this.partita.getStanzaCorrente().removeAttrezzo(a);
			
			//rimosso attrezzo, ora controllo se c'è spazio in borsa chiamando metodo add

			boolean attrezzoAggiunto = this.partita.getGiocatore().getBorsa().addAttrezzo(a);
			
			//se verifico che non si poteva aggiungere alla borsa, lo riaggiungo alla stanza
			
			if (!attrezzoAggiunto) {
				this.partita.getStanzaCorrente().addAttrezzo(a);
			}
		}		
	}

	/**
	 * Stampa informazioni di aiuto.
	 */
	private void aiuto() {
		for(int i=0; i< elencoComandi.length; i++) 
			ioConsole.mostraMessaggio(elencoComandi[i]+" ");
		ioConsole.mostraMessaggio("");
	}

	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
	 * e ne stampa il nome, altrimenti stampa un messaggio di errore
	 */
	private void vai(String direzione) {
		if(direzione==null)
			ioConsole.mostraMessaggio("Dove vuoi andare ?");
		Stanza prossimaStanza = null;
		prossimaStanza = this.partita.getStanzaCorrente().getStanzaAdiacente(direzione);
		if (prossimaStanza == null)
			ioConsole.mostraMessaggio("Direzione inesistente");
		else {
			this.partita.setStanzaCorrente(prossimaStanza);
			this.partita.getGiocatore().setCfu(-1);
		}
		ioConsole.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
	}

	/**
	 * Comando "Fine".
	 */
	private void fine() {
		ioConsole.mostraMessaggio("Grazie di aver giocato!");  // si desidera smettere
	}

	public static void main(String[] argc) {
		DiaDia gioco = new DiaDia();
		gioco.gioca();
	}
}