package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoGuarda implements Comando {

	@Override
	public void esegui(Partita partita) {
		partita.getIoConsole().mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
		partita.getIoConsole().mostraMessaggio(partita.getGiocatore().getBorsa().toString());

	}

	@Override
	public void setParametro(String parametro) {
		return;
	}

	@Override
	public String getNome() {
		return "comandoGuarda";
	}

	@Override
	public String getParametro() {
		return null;
	}

}
