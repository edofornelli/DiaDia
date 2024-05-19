package it.uniroma3.diadia.ambienti;

public class Labirinto {
	private Stanza stanzaIniziale;
	private Stanza stanzaVincente;
	private String nome;
	
	
	public Labirinto (){
		//this.init();
	}
	
	public Labirinto(String nome) {
		this.nome=nome;
	}

	/**
	 * Crea tutte le stanze e le porte di collegamento
	 * 
	 */
	
	/*
	private void init() {
		Attrezzo lanterna = new Attrezzo("lanterna",3);
		Attrezzo osso = new Attrezzo("osso",1);
		Attrezzo spada = new Attrezzo("spada",5);
		Attrezzo chiave = new Attrezzo ("chiave",1);

		Stanza atrio = new Stanza("Atrio");
		Stanza aulaN11 = new Stanza("Aula N11");
		Stanza aulaN10 = new Stanza("Aula N10");
		Stanza laboratorio = new Stanza("Laboratorio Campus");
		Stanza biblioteca = new Stanza("Biblioteca");

		atrio.impostaStanzaAdiacente("nord", biblioteca);
		atrio.impostaStanzaAdiacente("est", aulaN11);
		atrio.impostaStanzaAdiacente("sud", aulaN10);
		atrio.impostaStanzaAdiacente("ovest", laboratorio);
		aulaN11.impostaStanzaAdiacente("est", laboratorio);
		aulaN11.impostaStanzaAdiacente("ovest", atrio);
		aulaN10.impostaStanzaAdiacente("nord", atrio);
		aulaN10.impostaStanzaAdiacente("est", aulaN11);
		aulaN10.impostaStanzaAdiacente("ovest", laboratorio);
		laboratorio.impostaStanzaAdiacente("est", atrio);
		laboratorio.impostaStanzaAdiacente("ovest", aulaN11);
		biblioteca.impostaStanzaAdiacente("sud", atrio);

		aulaN10.addAttrezzo(lanterna);
		aulaN11.addAttrezzo(spada);
		atrio.addAttrezzo(osso);
		atrio.addAttrezzo(chiave);

		this.stanzaIniziale = atrio;  
		this.setStanzaVincente(biblioteca);
	} 
*/

	public Stanza getStanzaVincente() {
		return stanzaVincente;
	}

	public void setStanzaVincente(Stanza stanzaVincente) {
		this.stanzaVincente = stanzaVincente;
	}

	public Stanza getStanzaIniziale() {
		return stanzaIniziale;
	}

	public void setStanzaIniziale(Stanza stanzaIniziale) {
		this.stanzaIniziale = stanzaIniziale;
	}


	
}
