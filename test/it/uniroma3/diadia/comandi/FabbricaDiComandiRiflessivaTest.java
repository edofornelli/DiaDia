package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class FabbricaDiComandiRiflessivaTest {

	private FabbricaDiComandiRiflessiva fabbrica;

	@Before
	public void setUp() throws Exception {
		this.fabbrica = new FabbricaDiComandiRiflessiva();
	}

	@Test
	public void testCostruisciComando_vai() {
		assertSame(ComandoVai.class,this.fabbrica.costruisciComando("vai sud").getClass());
	}
	@Test
	public void testCostruisciComando_comandoInesistente() {
		assertSame(ComandoSconosciuto.class,this.fabbrica.costruisciComando("comando sconosciuto").getClass());
	}

	@Test
	public void testCostruisciComando_comandoVuoto() {
		assertSame(ComandoNonValido.class,this.fabbrica.costruisciComando("").getClass());
	}
	
	@Test (expected = RuntimeException.class)
	public void testCostruisciComando_null() {
		this.fabbrica.costruisciComando(null);
	}
	
	@Test 
	public void testCostruisciComando_ClassNotFound() {
		AbstractComando comando = this.fabbrica.costruisciComando("inesistente");
		assertSame(ComandoSconosciuto.class,comando.getClass());
		assertSame(ClassNotFoundException.class,((ComandoSconosciuto)comando).getException().getClass());
	}
	@Test 
	public void testCostruisciComando_InstantiationException() {
		AbstractComando comando = this.fabbrica.costruisciComando("senzaCostruttoreNoArgsPubblico");
		assertSame(ComandoSconosciuto.class,comando.getClass());
		assertSame(InstantiationException.class,((ComandoSconosciuto)comando).getException().getClass());
	}
	@Test 
	public void testCostruisciComando_IllegalAccessException() {
		AbstractComando comando = this.fabbrica.costruisciComando("senzaCostruttoreNoArgs");
		assertSame(ComandoSconosciuto.class,comando.getClass());
		assertSame(IllegalAccessException.class,((ComandoSconosciuto)comando).getException().getClass());
	}

}
