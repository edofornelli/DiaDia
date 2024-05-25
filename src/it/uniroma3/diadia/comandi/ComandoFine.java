package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoFine extends ComandoAstratto {

	@Override
	public void esegui(Partita partita) {
		partita.getIoConsole().mostraMessaggio("Grazie di aver giocato!");  // si desidera smettere
		partita.setFinita();
	}


	@Override
	public String getNome() {
		return "comandoFine";
	}

	@Override
	public String getParametro() {
		return null;
	}

}
