package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoSenzaCostruttoreNoArgsPubblico extends AbstractComando {
	
	public ComandoSenzaCostruttoreNoArgsPubblico(int a) {
		super(null);
		//Questo per evitare che venga creato il costruttore noargs
	}
	
	@Override
	public void esegui(Partita partita) {
		// TODO Auto-generated method stub

	}
}
