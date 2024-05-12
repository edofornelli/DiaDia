package it.uniroma3.diadia.attrezzi;

import java.util.Comparator;

import it.uniroma3.diadia.ambienti.Stanza;

/**
 * Una semplice classe che modella un attrezzo.
 * Gli attrezzi possono trovarsi all'interno delle stanze
 * del labirinto.
 * Ogni attrezzo ha un nome ed un peso.
 *
 * @author  docente di POO
 * @see Stanza
 * @version base
 */
public class Attrezzo {

	private String nome;
	private Integer peso;

	/**
	 * Crea un attrezzo
	 * @param nome il nome che identifica l'attrezzo
	 * @param peso il peso dell'attrezzo
	 */
	public Attrezzo(String nome, int peso) {
		this.peso = peso;
		this.nome = nome;
	}


	/**
	 * Restituisce il nome identificatore dell'attrezzo
	 * @return il nome identificatore dell'attrezzo
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * Restituisce il peso dell'attrezzo
	 * @return il peso dell'attrezzo
	 */
	public Integer getPeso() {
		return this.peso;
	}

	/**
	 * Restituisce una rappresentazione stringa di questo attrezzo
	 * @return la rappresentazione stringa
	 */
	public String toString() {
		return this.getNome()+" ("+this.getPeso()+"kg)";
	}
	
	
    // External comparator for comparing based on weight
    public static Comparator<Attrezzo> ComparatorePesiAttrezzo = new Comparator<Attrezzo>() {
        @Override
        public int compare(Attrezzo a1, Attrezzo a2) {
            return a1.getPeso().compareTo(a2.getPeso());
        }
    };

    // New comparator for comparing based on name
    public static Comparator<Attrezzo> ComparatoreNomiAttrezzi = new Comparator<Attrezzo>() {
        @Override
        public int compare(Attrezzo a1, Attrezzo a2) {
            return a1.getNome().compareTo(a2.getNome());
        }
    };
	
    public static Comparator<Attrezzo> ComparatoreNomiEPesoAttrezzi = new Comparator<Attrezzo>() {
        
    	@Override
        public int compare(Attrezzo a1, Attrezzo a2) {
            if (a1.getPeso().equals(a2.getPeso())) {
            		return a1.getNome().compareTo(a2.getNome());
            }
            else {
            	return a1.getPeso().compareTo(a2.getPeso());
            }
        }
    };
	
	
	@Override
	public int hashCode() {
		return this.getPeso() + this.getNome().hashCode();
	}

	@Override
	public boolean equals(Object o) {
		Attrezzo that = (Attrezzo) o;
		return this.getPeso() == that.getPeso() && this.getNome().equals(that.getNome());		
	}
		
}