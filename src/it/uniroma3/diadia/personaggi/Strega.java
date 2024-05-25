package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public final class Strega extends AbstractPersonaggio {

	public Strega() {
		super();
	}
	
	public Strega(String nome, String presentazione) {
		super(nome, presentazione);
	}

	@Override
	public String agisci(Partita partita) {
		StringBuilder messaggioFinale = new StringBuilder();
		Stanza stanzaDestinazione = null;
		Stanza stanzaCorrente = partita.getGiocatore().getStanzaCorrente();
		messaggioFinale.append("Strega: ");
		if(this.haSalutato()) {
			if(stanzaCorrente.getMapStanzeAdiacenti().size()>0) {
				messaggioFinale.append("Dato che mi hai salutato e non tutti lo fanno, ti teletrasporterò in un posto che ti gioverà\n");
				for(Stanza stanza : stanzaCorrente.getMapStanzeAdiacenti().values()) {
					if(stanzaDestinazione == null)
						stanzaDestinazione = stanza;
					else {
						if(stanzaDestinazione.getNumeroAttrezzi()<stanza.getNumeroAttrezzi()) {
							stanzaDestinazione = stanza;
						}
					}
				}
			}else {
				messaggioFinale.append("Mi hai salutato ma non ci sono stanze adiacenti in cui posso teletrasportarti... ma chi lo ha fatto sto labirinto?");
				stanzaDestinazione = stanzaCorrente;
			}
		}else {
			if(stanzaCorrente.getMapStanzeAdiacenti().size()>0) {
				messaggioFinale.append("Non mi hai nemmeno salutato e adesso ne pagherai le conseguenze\n");
				for(Stanza stanza : stanzaCorrente.getMapStanzeAdiacenti().values()) {
					if(stanzaDestinazione == null)
						stanzaDestinazione = stanza;
					else {
						if(stanzaDestinazione.getNumeroAttrezzi()>stanza.getNumeroAttrezzi()) {
							stanzaDestinazione = stanza;
						}
					}
				}
			}else {
				messaggioFinale.append("Non mi hai salutato e avrei voluto punirti per questo teletrasportandoti "
						+ "in una stanza adiacente con meno attrezzi possibile ma"
						+ "colui che ha fatto questo labirinto ha messo una sola stanza con me dentro");
				stanzaDestinazione = stanzaCorrente;
			}
		}
		partita.getGiocatore().setStanzaCorrente(stanzaDestinazione);
		return messaggioFinale.toString();
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		return "HAHAHAHAHAHA... pensavi ti dessi qualcosa in cambio?\n";
	}

	@Override
	public String getTipoPersonaggio() {
		return "una Strega";
	}
}
