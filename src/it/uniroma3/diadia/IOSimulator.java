package it.uniroma3.diadia;

import java.util.LinkedList;
import java.util.List;

public class IOSimulator implements IO{
	
	public List <String> listaComandi;
	public int contatore;
	public List <String> listaRisposte;
	
	public IOSimulator (List <String> listaComandi) {
		this.listaComandi = listaComandi;
		this.contatore = 0;
		this.listaRisposte = new LinkedList <String> ();
	}
	
	public void mostraMessaggio(String msg) {
		this.listaRisposte.add(msg);
		System.out.println(msg);
	}
	

	@Override
	public String leggiRiga() {
		System.out.println (listaComandi.get(contatore));
		return this.listaComandi.get(contatore++);
	}
}
