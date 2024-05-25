package it.uniroma3.diadia.comandi;

import java.util.Scanner;

public class FabbricaDiComandiRiflessiva implements FabbricaDiComandi  {

	@Override
	public AbstractComando costruisciComando(String comandoConParametro) {
		try {
			if(comandoConParametro == null)
				throw new IllegalArgumentException();

			Scanner scannerDiParole = new Scanner(comandoConParametro);
			String nomeComando = null;
			String parametro = null;
			AbstractComando comando = null;
			//AbstractComando comandoAstr = null;
			if(scannerDiParole.hasNext())
				nomeComando = scannerDiParole.next();
			if(scannerDiParole.hasNext())
				parametro = scannerDiParole.next();
			StringBuilder costruttoreDiStringhe = new StringBuilder("it.uniroma3.diadia.comandi.Comando");
			if(nomeComando == null) {
				costruttoreDiStringhe.append("NonValido");
			}else {
				costruttoreDiStringhe.append(Character.toUpperCase(nomeComando.charAt(0)));
				costruttoreDiStringhe.append(nomeComando.substring(1));
			}

			try {
				Class<?> classeComando = Class.forName(costruttoreDiStringhe.toString());
				comando = (AbstractComando) classeComando.newInstance();
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
				comando = new ComandoSconosciuto(e);
			}
			comando.setParametro(parametro);
			scannerDiParole.close();
			return comando;
		} catch(Exception unknown) {
			Class<?> classeErrore = unknown.getClass();
			throw new RuntimeException("Sappi che quando hai scritto questo codice ti � sfuggito un tipo di eccezione. classe: "+classeErrore,unknown);	
		}
	}
}
