package it.uniroma3.diadia.comandi;

import java.util.Scanner;

import it.uniroma3.diadia.IO;

public class FabbricaDiComandiRiflessiva implements FabbricaDiComandi {
	
	@Override
	public Comando costruisciComando(String istruzione , IO io) {
		
		Scanner scannerDiParole = new Scanner(istruzione);
		
		String nomeComando = null;
		String parametro = null;
		
		Comando comando = null;
		
		if (scannerDiParole.hasNext())
			nomeComando = scannerDiParole.next(); // prima parola: nome del comando
		if (scannerDiParole.hasNext())
			parametro = scannerDiParole.next(); // seconda parola: eventuale parametro
		
		try {
			String nomeClasse = "it.uniroma3.diadia.comandi.Comando";
			
			//con questi due metodi porziono la prima parola della stringa presa dallo scannerDiParole
			//prendo la prima lettera, la boxo in un Character e la rendo maiuscola
			nomeClasse += Character.toUpperCase(nomeComando.charAt(0));
			//costruisco il resto della stringa usando metodo substring partendo dal secondo char
			nomeClasse += nomeComando.substring(1);
			
			//a questo punto costruisco il comando:
			comando = (Comando)Class.forName(nomeClasse).newInstance();
			//e ne setto il parametro:
			comando.setParametro(parametro);
			
		} 
		// se il blocco superiore non funziona è perche ho fornito un comando non valido, gestisco manualmente l'eccezione come sotto
		//prendo l'eccezione generica nel catch come parametro, e forzo la creazione di un comando non valido.
		catch (Exception e) {
			comando = new ComandoNonValido();
			io.mostraMessaggio("Scrivi un comando sensato....");
		}
		return comando;
		
	}
	
}