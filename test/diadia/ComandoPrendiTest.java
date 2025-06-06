package diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.*;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comandi.ComandoPrendi;

class ComandoPrendiTest {
	
	private ComandoPrendi comando;
    private Partita partita;
    private IO io;

    @BeforeEach
    void setUp() {
        partita = new Partita();
        io = new IOSimulator();
        comando = new ComandoPrendi(io);
        partita.setStanzaCorrente(new Stanza("atrio"));
    }
	
    @Test
    void test_SenzaParametro_NienteAvviene() {
        Stanza stanzaIniziale = partita.getStanzaCorrente();
        // Borsa inizialmente vuota
        assertTrue(partita.getGiocatore().getBorsa().isEmpty());

        comando.esegui(partita);

        // Stanza corrente e borsa rimangono invariate
        assertEquals(stanzaIniziale, partita.getStanzaCorrente());
        assertTrue(partita.getGiocatore().getBorsa().isEmpty());
    }

    @Test
    void test_OggettoNonPresente_NienteAvviene() {
        Stanza stanzaIniziale = partita.getStanzaCorrente();
        comando.setParametro("martello");
        
        comando.esegui(partita);

        // Stanza e borsa non cambiano, oggetto non aggiunto
        assertEquals(stanzaIniziale, partita.getStanzaCorrente());
        assertFalse(partita.giocatore.getBorsa().hasAttrezzo("martello"));
    }

    @Test
    void test_PresaAttrezzo_Successo() {
        Attrezzo attrezzo = new Attrezzo("chiave", 1);
        partita.getStanzaCorrente().addAttrezzo(attrezzo);

        comando.setParametro("chiave");
        comando.esegui(partita);

        // L'attrezzo viene rimosso dalla stanza e aggiunto alla borsa
        assertFalse(partita.getStanzaCorrente().hasAttrezzo("chiave"));
        assertTrue(partita.getGiocatore().getBorsa().hasAttrezzo("chiave"));
    }
}
