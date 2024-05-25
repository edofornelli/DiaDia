package it.uniroma3.diadia;
import java.util.Scanner;

/*
 * Classe che si occupa della gestione input/output tramite console testuale.
 * Implementa l'interfaccia IO
 */

public class IOConsole implements IO{
	
	Scanner scannerDiLinee;
	
	/*
	 * Costruttore della classe IOConsole che crea uno scanner testuale di input da tastiera
	 */
	
	public IOConsole() {
		this.scannerDiLinee = new Scanner(System.in);
	}
	/*
	 * Mostra un messaggio in console
	 * @param msg Messaggio di tipo String da mostrare
	 */
	
	@Override
	public void mostraMessaggio(String msg) {
		System.out.println(msg);	
	}
	
	/*
	 * Metodo che legge una riga da console testuale.
	 * La fine della riga è definita dalla pressione del tasto INVIO.
	 */
	
	@Override
	public String leggiRiga() {
		String riga = this.scannerDiLinee.nextLine();
		return riga;
	}
}