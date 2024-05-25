package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoSenzaCostruttoreNoArgs extends AbstractComando {
	
	private ComandoSenzaCostruttoreNoArgs() {
		super(null);
		//Questo per scatenare una IllegalAccessException
	}

	@Override
	public void esegui(Partita partita) {
		// TODO Auto-generated method stub
	}
}
