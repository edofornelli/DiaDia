package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public abstract class AbstractComando {
	private String nome = null;
	private String parametro = null;
	
	public AbstractComando(String nomeComando) {
		this.nome = nomeComando;
	}
	
	public AbstractComando(String nomeComando, String param) {
		this.nome = nomeComando;
		this.parametro = param;
	}
	
	public abstract void esegui(Partita partita);
	
	public String getNome() {
		return nome;
	}
	
	public void setParametro(String parametro) {
		this.parametro = parametro;
	}
	
	public String getParametro() {
		return parametro;
	}
}
