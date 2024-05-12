package it.uniroma3.diadia;


import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.FabbricaDiComandi;
import it.uniroma3.diadia.comandi.FabbricaDiComandiFisarmonica;

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


	private Partita partita;
	public IO ioConsole;

	public DiaDia(IO io, Labirinto labirinto) {
		this.ioConsole =  io;
		this.partita = new Partita(ioConsole , labirinto);
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
		Comando comandoDaEseguire;
		FabbricaDiComandi factory = new FabbricaDiComandiFisarmonica();
				comandoDaEseguire = factory.costruisciComando(istruzione);
		comandoDaEseguire.esegui(this.partita);
		if (this.partita.vinta())
			this.ioConsole.mostraMessaggio("Hai Vinto");
		if (!(this.partita.getGiocatore().getCfu()>=0))
			this.ioConsole.mostraMessaggio("Hai esaurito i CFU...");
		return this.partita.isFinita();
	}


	public static void main(String[] argc) {
		IO io = new IOConsole();
		
		Labirinto labirinto = new Labirinto ("completo");
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
		
		DiaDia gioco = new DiaDia((IOConsole) io, labirinto);
		gioco.gioca();
	}


	public Partita getPartita() {
		return partita;
	}


	public void setPartita(Partita partita) {
		this.partita = partita;
	}
}