package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPosa implements Comando {

	String attrezzo;
	
	@Override
	public void esegui(Partita partita) {
		if (partita.getGiocatore().getBorsa().hasAttrezzo(this.attrezzo)) {
			Attrezzo a = partita.getGiocatore().getBorsa().removeAttrezzo(this.attrezzo);
			boolean attrezzoAggiunto = partita.getStanzaCorrente().addAttrezzo(a);
			if (!attrezzoAggiunto) {
				partita.getGiocatore().getBorsa().addAttrezzo(a);
			}
		}
	}

	@Override
	public void setParametro(String parametro) {
		this.attrezzo = parametro;
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
