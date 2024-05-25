package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaMagica extends Stanza{
	/*
	 * La stanza magica è una stanza che dopo un numero N di attrezzi posati in essa, attiva un suo comportamento magico.
	 * Il comportamento della stanza magica inverte il nome e raddoppia il peso degli attrezzi posati.
	 */
	
	// limiteNumeroAttrezzi è la quantità di attrezzi da posare affinchè la stanza magica venga attivata
	static final int SOGLIA_MAGICA_DEFAULT = 2;
	private int contatoreAttrezziPosati;
	private int sogliaMagica;
	
	public StanzaMagica(String nome) {
		this(nome,SOGLIA_MAGICA_DEFAULT);
	}
	
	public StanzaMagica(String nome,int sogliaMagica) {
		super(nome);
		this.sogliaMagica=sogliaMagica;
		this.contatoreAttrezziPosati=0;
	}
	
	private Attrezzo modificaAttrezzo(Attrezzo attrezzo) {
		StringBuilder nomeInvertito;
		int pesoX2 = attrezzo.getPeso()*2;
		nomeInvertito= new StringBuilder(attrezzo.getNome());
		nomeInvertito=nomeInvertito.reverse();
		attrezzo = new Attrezzo(nomeInvertito.toString(),pesoX2);
		return attrezzo;
	}
	
	
	// Metodo che dichiara che la stanza è magica (per testing)
	public boolean isMagica() {
		return true;
	}
	
	@Override
	public boolean addAttrezzo(Attrezzo attrezzo) {
		this.contatoreAttrezziPosati++;
		if(this.contatoreAttrezziPosati>sogliaMagica) {
			attrezzo=this.modificaAttrezzo(attrezzo);
		}
		return super.addAttrezzo(attrezzo);
    }
	
}
