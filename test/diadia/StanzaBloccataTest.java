package diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import it.uniroma3.diadia.ambienti.*;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaBloccataTest {
	private Stanza stanza1 = new Stanza("biblioteca");
	private StanzaBloccata stanza2 = new StanzaBloccata("atrio","nord" ,"chiave");
	private Stanza stanza3 = new Stanza("aula");
	private Attrezzo tool = new Attrezzo("chiave", 1);
	
	@BeforeEach
	void setUp() throws Exception {
		stanza1.impostaStanzaAdiacente("sud", stanza2);
		stanza2.impostaStanzaAdiacente("nord", stanza1);
		stanza3.impostaStanzaAdiacente("est", stanza2);
		stanza2.impostaStanzaAdiacente("ovest", stanza3);
	}

	/*passo da stanza 3 a stanza 2, e viceversa, senza bisogno di un oggetto perchè la porta
	 * non è bloccata in quella direzione
	 */
	@Test
	void test_stanzaAdiacenteNonBloccata() {
		assertEquals(stanza3, stanza2.getStanzaAdiacente("ovest"));
		assertEquals(stanza2, stanza3.getStanzaAdiacente("est"));
		assertEquals(stanza2, stanza1.getStanzaAdiacente("sud"));
		assertEquals(null, stanza2.getStanzaAdiacente("est"));
	}
	
	@Test
	void test_stanzaAdiacenteBloccata() {
		assertEquals(stanza2, stanza2.getStanzaAdiacente("nord"));
	}
	
	@Test
	void test_stanzaAdiacenteBloccataEPoiSbloccata() {
		assertEquals(stanza2, stanza2.getStanzaAdiacente("nord"));
		stanza2.addAttrezzo(tool);
		assertEquals(stanza1, stanza2.getStanzaAdiacente("nord"));
	}
}
