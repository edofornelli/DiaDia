package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoSaluta extends ComandoAstratto{

	@Override
	public void esegui(Partita partita) {
		partita.getStanzaCorrente().getPersonaggio().saluta();
	}

	@Override
	public String getNome() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getParametro() {
		// TODO Auto-generated method stub
		return null;
	}


}
