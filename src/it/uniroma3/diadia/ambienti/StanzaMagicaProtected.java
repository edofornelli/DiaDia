package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaMagicaProtected extends StanzaProtected {
	
	static final int SOGLIA_MAGICA_DEFAULT=2;
	private int sogliaMagica;
	private int contatoreAttrezziPosati;
	
	public StanzaMagicaProtected(String nome) {
		this(nome,SOGLIA_MAGICA_DEFAULT);
	}
	
	public StanzaMagicaProtected(String nome,int sogliaMagica) {
		super(nome);
		this.numeroAttrezzi=0;
		this.contatoreAttrezziPosati=0;
		this.sogliaMagica=sogliaMagica;
	}
	
	private Attrezzo modificaAttrezzo(Attrezzo attrezzo) {
		StringBuilder nomeInvertito;
		int pesoX2 = attrezzo.getPeso()*2;
		nomeInvertito= new StringBuilder(attrezzo.getNome());
		nomeInvertito=nomeInvertito.reverse();
		attrezzo = new Attrezzo(nomeInvertito.toString(),pesoX2);
		return attrezzo;
	}
	
	@Override
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if(this.numeroAttrezzi < NUMERO_MASSIMO_ATTREZZI) {
			contatoreAttrezziPosati++;
			if(contatoreAttrezziPosati>sogliaMagica)
				attrezzo=this.modificaAttrezzo(attrezzo);
			this.attrezzi[numeroAttrezzi] = attrezzo;
			this.numeroAttrezzi++;
			return true;
		}
		else {
			return false;
		}
	}
}
