package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPrendi implements Comando {

	String attrezzo;
	
	@Override
	public void esegui(Partita partita) {

		if (partita.getStanzaCorrente().hasAttrezzo(attrezzo)) {
		
		Attrezzo a = partita.getStanzaCorrente().getAttrezzo(attrezzo);
		partita.getStanzaCorrente().removeAttrezzo(a);
		
		//rimosso attrezzo, ora controllo se c'è spazio in borsa chiamando metodo add

		boolean attrezzoAggiunto = partita.getGiocatore().getBorsa().addAttrezzo(a);
		
		//se verifico che non si poteva aggiungere alla borsa, lo riaggiungo alla stanza
		
		if (!attrezzoAggiunto) {
			partita.getStanzaCorrente().addAttrezzo(a);
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
