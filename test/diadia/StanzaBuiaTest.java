package diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.StanzaBuia;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaBuiaTest {
	
	private StanzaBuia stanza1 = new StanzaBuia("biblioteca", "lanterna");
	private Attrezzo tool = new Attrezzo("lanterna", 1);
	
	@Test
	void test_StanzaBuiaSenzaLanterna() {
		assertEquals("qui c'è buio pesto", stanza1.getDescrizione());
	}
	
	@Test
	void test_StanzaBuiaConLanterna() {
		assertEquals("qui c'è buio pesto", stanza1.getDescrizione());
		stanza1.addAttrezzo(tool);
		assertNotEquals("qui c'è buio pesto", stanza1.getDescrizione());
		assertEquals(stanza1.getDescrizione(), stanza1.getDescrizione());
	}
}