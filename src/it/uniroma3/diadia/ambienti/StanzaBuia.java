package it.uniroma3.diadia.ambienti;

public class StanzaBuia extends Stanza{
	public String nomeAttrezzo;
	
	public StanzaBuia(String nome, String nomeAttrezzo) {
		super(nome);
		this.nomeAttrezzo=nomeAttrezzo;
	}
	
	@Override
	public String getDescrizione() {
		if(this.hasAttrezzo(this.nomeAttrezzo))
			return super.getDescrizione();
		else
			return "qui c'è un buio pesto";
	}
}
