package it.uniroma3.diadia;

import it.uniroma3.diadia.comandi.FabbricaDiComandi;
import it.uniroma3.diadia.comandi.FabbricaDiComandiRiflessiva;

import java.io.FileNotFoundException;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.comandi.AbstractComando;
/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il letodo gioca
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
	private IO io;

	public DiaDia(IO io) {
		this.partita = new Partita(io);
		this.io = io;
	}
	
	public DiaDia(IO io, Labirinto maze) {
		this.partita = new Partita(io,maze);
		this.io = io;
	}
	/**
	 * Principale loop di gioco
	 */
	public void gioca()  {
		String istruzione; 
		//io.mostraMessaggio("Come ti chiami: ");
		//partita.getGiocatore().setNome(io.leggiRiga());		
		io.mostraMessaggio("Benvenuto "+partita.getGiocatore().getNome()+".\n"+ MESSAGGIO_BENVENUTO);
		do {
			istruzione = io.leggiRiga();
		}while(!processaIstruzione(istruzione));
	}   

	/**
	 * Processa una istruzione 
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 */

	private boolean processaIstruzione(String istruzione) {
		FabbricaDiComandi fabbricaDiComandi= new FabbricaDiComandiRiflessiva();
		AbstractComando comandoDaEseguire = fabbricaDiComandi.costruisciComando(istruzione);
		comandoDaEseguire.esegui(partita);
		if(this.partita.getStatoPartita()) {
			if(!this.partita.getGiocatore().isVivo()) {
				io.mostraMessaggio("Sei morto");
				return true;
			}else if (this.vinta()) {
				io.mostraMessaggio("Hai vinto!");
				return true;
			}else
				return false;
		}else{
			return true;
		}
	}   

	/*
	 * Metodo che controlla che la stanza del player � la stanza vincente
	 */

	private boolean vinta() {
		return this.partita.getGiocatore().getStanzaCorrente()==this.partita.getLabirinto().getStanzaVincente();
	}
	
	public Partita getPartita() {
		return partita;
	}
	
	public static void main(String[] argc) {
		try {
		IO io = new IOConsole();
		CaricatoreLabirinto caricatore = new CaricatoreLabirinto("Labirinto.txt");
		Labirinto labirinto = caricatore.carica();
		DiaDia gioco = new DiaDia(io,labirinto);
		gioco.gioca();
		}catch(FileNotFoundException  e) {
			e.printStackTrace();
		} catch (FormatoFileNonValidoException e) {
			e.printStackTrace();
		}
	}
}