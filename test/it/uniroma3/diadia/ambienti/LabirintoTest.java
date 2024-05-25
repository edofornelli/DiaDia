package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class LabirintoTest {
    private Labirinto labirinto;
    private Stanza stanzaVincente;
    private Stanza stanzaIniziale;
    
    @Before
    public void creaLabirintoTest(){
    	stanzaIniziale = new Stanza("Stanza Iniziale");
    	stanzaVincente = new Stanza("Stanza Finale");
        this.labirinto = new Labirinto(stanzaIniziale, stanzaVincente);
    }
    
    @Test
    public void TestSetStanzaInizialeCambiata(){
        Stanza stanzaTest = new Stanza("Stanza Test");
        labirinto.setStanzaIniziale(stanzaTest);
        assertEquals(stanzaTest, this.labirinto.getStanzaIniziale());
    }
    @Test
    public void TestSetStanzaVincenteCambiata(){
        Stanza stanzaTest = new Stanza("Stanza Test");
        this.labirinto.setStanzaVincente(stanzaTest);
        assertEquals(stanzaTest, this.labirinto.getStanzaVincente());
    }
    
    @Test
    public void TestGetStanzaIniziale(){
        assertEquals(stanzaIniziale,this.labirinto.getStanzaIniziale());
    }
    @Test
    public void testGetStanzaInizialeNull() {
    	Stanza nulla = null;
    	this.labirinto.setStanzaIniziale(nulla);
    	assertNull(this.labirinto.getStanzaIniziale());
    }

    @Test
    public void TestGetStanzaVincente(){
        assertEquals(stanzaVincente,this.labirinto.getStanzaVincente());
    }
}