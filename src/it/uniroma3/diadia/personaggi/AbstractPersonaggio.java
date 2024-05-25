package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public abstract class AbstractPersonaggio {
	private String nome = null;
	private String presentazione = null;
	private boolean haSalutato = false;
	
	public AbstractPersonaggio() {
	}
	
	public AbstractPersonaggio(String nome, String presentazione) {
		this.nome = nome;
		this.presentazione = presentazione;
		this.haSalutato = false;
	}
	
	public abstract String agisci(Partita partita);
	
	public String saluta() {
		StringBuilder risposta = new StringBuilder("Ciao io sono ");
		risposta.append(this.getNome()+". ");
		if(!this.haSalutato) {
			risposta.append(this.presentazione);
		}else {
			risposta.append("Ci siamo gia' presentati!");
		}
		this.haSalutato = true;
		return risposta.toString();
	}
	
	
	abstract public String riceviRegalo(Attrezzo attrezzo, Partita partita);

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setPresentazione(String presentazione) {
		this.presentazione = presentazione;
	}
	
	public String getPresentazione() {
		return presentazione;
	}
	
	protected void setSalutato(boolean haSalutato) {
		this.haSalutato = haSalutato;
	}
	
	protected boolean haSalutato() {
		return haSalutato;
	}
	
	abstract public String getTipoPersonaggio();
	
	@Override
	public String toString() {
		return this.getNome();
	}
}
