package diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.*;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comandi.ComandoPosa;

public class ComandoPosaTest {

    private ComandoPosa comando;
    private Partita partita;
    private IO io;
    private Stanza stanza;
    
    @BeforeEach
    void setUp() {
    	stanza = new Stanza("atrio");
        partita = new Partita();
        io = new IOSimulator();              
        comando = new ComandoPosa(io);
        partita.setStanzaCorrente(stanza);
    }

    @Test
    void test_SenzaParametro_NienteAvviene() {
        Stanza stanzaIniziale = partita.getStanzaCorrente();
        assertTrue(partita.getGiocatore().getBorsa().isEmpty());

        comando.esegui(partita);
        
        // Nessun oggetto nella borsa e stanza invariata
        assertTrue(partita.getGiocatore().getBorsa().isEmpty());
        assertEquals(stanzaIniziale, partita.getStanzaCorrente());
    }

    @Test
    void test_OggettoNonPresenteInBorsa_NienteAvviene() {
        comando.setParametro("libro");
        comando.esegui(partita);

        assertFalse(partita.getStanzaCorrente().hasAttrezzo("libro"));
        assertFalse(partita.getGiocatore().getBorsa().hasAttrezzo("libro"));
    }

    @Test
    void test_PosatoConSuccesso() {
        Attrezzo attrezzo = new Attrezzo("lanterna", 1);
        partita.getGiocatore().getBorsa().addAttrezzo(attrezzo);

        comando.setParametro("lanterna");
        comando.esegui(partita);

        assertFalse(partita.getGiocatore().getBorsa().hasAttrezzo("lanterna"));
        assertTrue(partita.getStanzaCorrente().hasAttrezzo("lanterna"));
    }
}
