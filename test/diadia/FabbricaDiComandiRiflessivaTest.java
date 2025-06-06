package diadia;

import it.uniroma3.diadia.comandi.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FabbricaDiComandiRiflessivaTest {

    private FabbricaDiComandiRiflessiva fabbrica;

    @BeforeEach
    void setUp() {
        fabbrica = new FabbricaDiComandiRiflessiva(null);
    }

    @Test
    void testComandoVai() {
    	AbstractComando cmd1 = fabbrica.costruisciComando("vai");
    	AbstractComando cmd2 = fabbrica.costruisciComando("vai nord");
        assertTrue(cmd1 instanceof ComandoVai, "" +
            "‘vai’ dovrebbe produrre ComandoVai senza parametro");
        assertTrue(cmd2 instanceof ComandoVai, "" +
            "‘vai nord’ dovrebbe produrre ComandoVai con parametro");
    }

    @Test
    void testComandoPrendi() {
    	AbstractComando cmd1 = fabbrica.costruisciComando("prendi");
    	AbstractComando cmd2 = fabbrica.costruisciComando("prendi lanterna");
        assertTrue(cmd1 instanceof ComandoPrendi, "‘prendi’ dovrebbe produrre ComandoPrendi");
        assertTrue(cmd2 instanceof ComandoPrendi, "‘prendi lanterna’ dovrebbe produrre ComandoPrendi");
    }

    @Test
    void testComandoPosa() {
    	AbstractComando cmd = fabbrica.costruisciComando("posa chiave");
        assertTrue(cmd instanceof ComandoPosa, "‘posa chiave’ dovrebbe produrre ComandoPosa");
    }

    @Test
    void testComandoGuarda() {
    	AbstractComando cmd = fabbrica.costruisciComando("guarda");
        assertTrue(cmd instanceof ComandoGuarda, "‘guarda stanza’ dovrebbe produrre ComandoGuarda");
    }

    @Test
    void testComandoAiuto() {
    	AbstractComando cmd = fabbrica.costruisciComando("aiuto");
        assertTrue(cmd instanceof ComandoAiuto, "‘aiuto’ dovrebbe produrre ComandoAiuto");
    }

    @Test
    void testComandoFine() {
    	AbstractComando cmd = fabbrica.costruisciComando("fine");
        assertTrue(cmd instanceof ComandoFine, "‘fine’ dovrebbe produrre ComandoFine");
    }

    @Test
    void testComandoNonValido() {
    	AbstractComando cmd1 = fabbrica.costruisciComando("");
    	AbstractComando cmd2 = fabbrica.costruisciComando("foo");
    	AbstractComando cmd3 = fabbrica.costruisciComando("foo bar");
        assertTrue(cmd1 instanceof ComandoNonValido, "Stringa vuota dovrebbe produrre ComandoNonValido");
        assertTrue(cmd2 instanceof ComandoNonValido, "‘foo’ dovrebbe produrre ComandoNonValido");
        assertTrue(cmd3 instanceof ComandoNonValido, "‘foo bar’ dovrebbe produrre ComandoNonValido");
    }
}
