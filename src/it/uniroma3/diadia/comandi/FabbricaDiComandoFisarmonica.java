package it.uniroma3.diadia.comandi;

import java.util.Scanner;

public class FabbricaDiComandoFisarmonica implements FabbricaDiComandi {
	
	@Override
	public AbstractComando costruisciComando(String comandoConParametro) {
		Scanner scannerDiParole = new Scanner (comandoConParametro);
		String nomeComando = null;
		String parametro = null;
		if(scannerDiParole.hasNext()) {
			nomeComando = scannerDiParole.next();
		}
		if(scannerDiParole.hasNext()) {
			parametro = scannerDiParole.next();
		}
		scannerDiParole.close();
		if(nomeComando==null) {
			return new ComandoNonValido();
		}
		else if (nomeComando.equals("fine"))
			return new ComandoFine();
		else if (nomeComando.equals("vai"))
			return new ComandoVai(parametro);
		else if (nomeComando.equals("aiuto"))
			return new ComandoAiuto();
		else if (nomeComando.equals("guarda"))
			return new ComandoGuarda();
		else if (nomeComando.equals("borsa"))
			return new ComandoBorsa();
		else if (nomeComando.equals("prendi"))
			return new ComandoPrendi(parametro);
		else if (nomeComando.equals("posa"))
			return new ComandoPosa(parametro);
		else
			return new ComandoSconosciuto();
	}

}
