package it.uniroma3.diadia;
import it.uniroma3.diadia.ambienti.*;
import it.uniroma3.diadia.giocatore.Giocatore;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class PartitaTest {
	private Stanza stanzaVincente;
	private Stanza stanzaIniziale;
    private Partita partita;
    private Labirinto maze;
    private Giocatore player;
    
    @Before
    public void setUp() {
    	this.stanzaVincente = new Stanza ("Stanza Vincente");
    	this.stanzaIniziale = new Stanza ("Stanza Iniziale");
    	this.player = new Giocatore(this.stanzaIniziale);
    	this.maze = new Labirinto(this.stanzaIniziale,stanzaVincente);
    	this.partita = new Partita(this.maze,this.player);
    }
    
    @Test
    public void testGetLabirinto() {
    	assertEquals(this.maze,this.partita.getLabirinto());
    }
    
    
    // testa anche il buon funzionamento del setter
    @Test
    public void testGetLabirintoDiverso() {
    	Stanza stanza1 = new Stanza("Stanza 1");
    	Stanza stanza2 = new Stanza("Stanza 2");
    	Labirinto newMaze = new Labirinto(stanza1,stanza2);
    	this.partita.setLabirinto(newMaze);
    	assertEquals(newMaze,this.partita.getLabirinto());
    }
    
    @Test
    public void testGetLabirintoNull() {
    	Labirinto nullMaze = null;
    	this.partita.setLabirinto(nullMaze);
    	assertNull(this.partita.getLabirinto());
    } 
    
    @Test
    public void testSetGiocatoreDiverso() {
    	Giocatore newPlayer = new Giocatore (stanzaIniziale);
    	this.partita.setGiocatore(newPlayer);
    	assertEquals(newPlayer,this.partita.getGiocatore());
    } 
}