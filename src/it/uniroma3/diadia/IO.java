package it.uniroma3.diadia;

/*
 * L'interfaccia IO è un interfaccia che si occupa di gestire in modo polimorfo l' input/output
 * del gioco.
 */

public interface IO {
	public void mostraMessaggio(String msg);
	public String leggiRiga();
}
