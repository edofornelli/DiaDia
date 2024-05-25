package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoAiuto extends AbstractComando{
	final private String[] elencoComandi = {"vai", "aiuto","guarda","borsa","prendi","posa","regala","interagisci","saluta","fine"};
	
	
	public ComandoAiuto() {
		super("aiuto");
//		 Reflections reflections = new Reflections("my.project.prefix");
//
//		 Set<Class<? extends Object>> allClasses = 
//		     reflections.getSubTypesOf(Object.class);
	}
	
	@Override
	public void esegui(Partita partita) {
		partita.getIO().mostraMessaggio("Comandi disponibili: ");
		if(elencoComandi!=null) {
		for(int i=0; i< elencoComandi.length; i++) 
			System.out.print(elencoComandi[i]+" ");
		}
		partita.getIO().mostraMessaggio("");
		
	}
}
