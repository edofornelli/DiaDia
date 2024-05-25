package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoRegala extends ComandoAstratto{

	String attrezzo;
	
	@Override
	public void esegui(Partita partita) {
		
		if (partita.getStanzaCorrente().getPersonaggio()!=null) {
			if (partita.getGiocatore().getBorsa().hasAttrezzo(this.attrezzo)) {
				Attrezzo a = partita.getGiocatore().getBorsa().removeAttrezzo(this.attrezzo);
				partita.getGiocatore().getBorsa().removeAttrezzo(attrezzo);
				partita.getStanzaCorrente().getPersonaggio().riceviRegalo(a, partita);
			}
			else {
				partita.ioConsole.mostraMessaggio("non hai questo attrezzo");
			}
		} else {
			partita.ioConsole.mostraMessaggio("non c'è nessuno a cui regalare questo attrezzo");
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
