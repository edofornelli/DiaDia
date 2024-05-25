package it.uniroma3.diadia.personaggi;


import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Strega extends AbstractPersonaggio {

	private static final String MESSAGGIO_STREGA_IMBONITA_DA_SALUTO = "Cucciolino caro, ora ti porto in un'ottima stanza";
	private static final String MESSAGGIO_STREGA_INCAZZATISSIMA = "IHHIHIHIHIHHI ORA SONO CAZZI TUOI";
	private static final String MESSAGGIO_STREGA_CHE_TI_HA_INCULATO_ATTREZZO = "IHHIHIHIHIHHI MO'O TENGO IO";


	
	public Strega(String nome, String presentaz) {
		super(nome, presentaz);
	}

	@Override
	public String agisci(Partita partita) {
//		se interagiamo con una strega questa ci
//		trasferisce in una stanza tra quelle adiacenti.
//		Siccome è permalosa:
//		• se non l’abbiamo ancora salutata, ci «trasferisce» nella
//		stanza adiacente che contiene meno attrezzi
//		• altrimenti in quella che contiene più attrezzi
		
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		Stanza stanzaMaxAttrezzi = null;
		Stanza stanzaMinAttrezzi = null;


		int maxAttrezzi = 0;

		
		for (Stanza stanza : stanzaCorrente.getMapStanzeAdiacenti().values()) {
			int numeroAttrezzi = stanza.getAttrezzi().size();
			if (numeroAttrezzi >= maxAttrezzi) {
				maxAttrezzi = numeroAttrezzi;
				stanzaMaxAttrezzi = stanza;
			}
		}
		
		int minAttrezzi = maxAttrezzi;
		
		for (Stanza stanza : stanzaCorrente.getMapStanzeAdiacenti().values()) {
			int numeroAttrezzi = stanza.getAttrezzi().size();
			if (numeroAttrezzi <= minAttrezzi) {
				minAttrezzi = numeroAttrezzi;
				stanzaMinAttrezzi = stanza;
			}
		}
		
		if (this.haSalutato() == true) {
			partita.setStanzaCorrente(stanzaMaxAttrezzi);
			return MESSAGGIO_STREGA_IMBONITA_DA_SALUTO;
		}else {
			partita.setStanzaCorrente(stanzaMinAttrezzi);
			return MESSAGGIO_STREGA_INCAZZATISSIMA;

		}
	}
	
	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		return MESSAGGIO_STREGA_CHE_TI_HA_INCULATO_ATTREZZO;
	}

}
