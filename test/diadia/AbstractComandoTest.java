package diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.*;
import it.uniroma3.diadia.comandi.AbstractComando;

class AbstractComandoTest {

	private AbstractComando cmd;
	
	@BeforeEach
    void setUp() {
        //devo creare un istanza anonima
        cmd = new AbstractComando(null) {
            @Override
            public void esegui(Partita partita) {
            }
        };
    }
	@Test
    void testParametroInizialmenteNull() {
        assertNull(cmd.parametro);
    }

    @Test
    void testSetParametroMemorizzaValore() {
        cmd.setParametro("prova");
        assertEquals("prova", cmd.parametro);
    }

    @Test
    void testSetParametroSovrascriveValorePrecedente() {
        cmd.setParametro("uno");
        cmd.setParametro("due");
        assertEquals("due", cmd.parametro);
    }
}
