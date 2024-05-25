package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoSconosciuto extends AbstractComando {

	private ReflectiveOperationException exception;
	
	public ComandoSconosciuto() {
		super("sconosciuto");
	}
	
	public ComandoSconosciuto(ReflectiveOperationException e) {
		super("sconosciuto");
		this.exception = e;
	}

	public ReflectiveOperationException getException() {
		return exception;
	}

	@Override
	public void esegui(Partita partita) {
		partita.getIO().mostraMessaggio("Comando non esistente");
	}
}
