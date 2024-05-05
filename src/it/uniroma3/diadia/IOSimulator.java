package it.uniroma3.diadia;

import java.util.List;

public class IOSimulator implements IO{
	
	public List <String> listaComandi;
	public int contatore;
	
	public IOSimulator (List <String> listaComandi) {
		this.listaComandi = listaComandi;
		this.contatore = 0;
	}
	
	public void mostraMessaggio(String msg) {
		System.out.println(msg);
	}
	

	@Override
	public String leggiRiga() {
		System.out.println (listaComandi.get(contatore));
		return this.listaComandi.get(contatore++);
	}
}
