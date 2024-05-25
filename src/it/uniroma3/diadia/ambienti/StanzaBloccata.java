package it.uniroma3.diadia.ambienti;

public class StanzaBloccata extends Stanza{
	private Direzione direzioneBloccata;
	private String nomePassepartout;
	static final String NOME_PASSEPARTOUT_DEFAULT = "passepartout";
	
	/*
	 * Builder che costruisce una stanza bloccata. Non viene specificato il nome dell'attrezzo
	 * capace di sbloccare l'uscita. Si utilizza il nome di default "passepartout".
	 * @param nome della stanza
	 * @param direzione bloccata della stanza
	 */
	
	public StanzaBloccata(String nome,String direzioneBloccata) {
		this(nome, direzioneBloccata, NOME_PASSEPARTOUT_DEFAULT);
	}
	
	/*
	 * Costruttore di stanza bloccata. Costruisce una stanza che ha una direzione specificata bloccata e 
	 * il nome dell'attrezzo capace di sbloccarla.
	 * @param nome della stanza bloccata
	 * @param direzione bloccata della stanza
	 * @param nome dell'attrezzo capace di sbloccare la direzione bloccata
	 */
	
	public StanzaBloccata(String nome, String direzioneBloccata, String passepartout){
		super(nome);
		try {
			this.direzioneBloccata=Direzione.valueOf(direzioneBloccata);
		}catch(IllegalArgumentException e) {
			this.direzioneBloccata = null;
			throw new IllegalArgumentException();
		}
		this.nomePassepartout=passepartout;
	}

	public void setDirezioneBloccata(String direzioneBloccata) {
		try {
			this.direzioneBloccata=Direzione.valueOf(direzioneBloccata);
		}catch(IllegalArgumentException e) {
			this.direzioneBloccata = null;
			throw new IllegalArgumentException();
		}
	}

	public Direzione getDirezioneBloccata() {
		return direzioneBloccata;
	}

	public void setPasspartout(String nomeAttrezzo) {
		this.nomePassepartout=nomeAttrezzo;
	}

	public String getNomePasspartout() {
		return nomePassepartout;
	}

	// Non sarebbe più opportuno modificare toString?
	@Override
	public String getDescrizione() {
		StringBuilder risultato = new StringBuilder();
		risultato.append(super.toString()).toString();
		risultato.append("Questa stanza ha la direzione "+direzioneBloccata+" bloccata.\n");
		risultato.append("Per passare devi posare l'oggetto chiave chiamato: "+nomePassepartout+".\n");
		return risultato.toString();
	}

//	@Override
//	public String toString() {
//		StringBuilder risultato = new StringBuilder();
//		risultato.append("Questa stanza ha la direzione "+direzioneBloccata+" bloccata.\n");
//		risultato.append("Per passare devi posare l'oggetto chiave chiamato: "+nomePassepartout+".\n");
//		risultato.append(super.toString());
//		return risultato.toString();
//	}

	@Override
	public Stanza getStanzaAdiacente(String dir) {
		Stanza stanzaAdiacente = super.getStanzaAdiacente(dir);
		if(this.direzioneBloccata.equals(Direzione.valueOf(dir)) && stanzaAdiacente!=null && this.hasAttrezzo(nomePassepartout))
			return stanzaAdiacente;

		if(!this.direzioneBloccata.equals(Direzione.valueOf(dir)) && stanzaAdiacente!=null)
			return stanzaAdiacente;
		
		return this;
	}
}
