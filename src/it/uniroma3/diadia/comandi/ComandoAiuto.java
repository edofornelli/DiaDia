package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoAiuto implements Comando {

	static final private String[] elencoComandi = {"1) vai: comando che ti permette di muoverti nelle varie stanze", "2) aiuto: ti elenca i comandi disponibili", "3) fine: concludi la partita", "4) prendi: raccogli attrezzo da stanza e lo metti in borsa", "5) posa: posi attrezzo da borsa e lo metti in stanza",   "6) guarda: guardati intorno nella stanza attuale"};

	
	@Override
	public void esegui(Partita partita) {
		for(int i=0; i< elencoComandi.length; i++) 
			System.out.println(elencoComandi[i]+" ");
		System.out.println("");
	}

	@Override
	public void setParametro(String parametro) {
		return;
	}

}
