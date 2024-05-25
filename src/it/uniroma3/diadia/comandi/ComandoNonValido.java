package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoNonValido extends AbstractComando{

	private ReflectiveOperationException exception;
	
	public ComandoNonValido() {
		super("non valido");
	}
	
	public ComandoNonValido(ReflectiveOperationException e) {
		super("non valido");
		this.exception = e;
	}

	public ReflectiveOperationException getException() {
		return exception;
	}
	
	@Override
	public void esegui(Partita partita) {
		partita.getIO().mostraMessaggio("Inserire un comando");
	}
}
