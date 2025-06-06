package diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.*;

class PersonaggiTest {
	
	private AbstractPersonaggio mago;
	private AbstractPersonaggio strega;
	private AbstractPersonaggio cane;
	private Attrezzo tool1, tool2, tool3, tool4, tool5;
	private Partita partita;
	private Stanza stanzaIniziale;
	private Stanza stanzaBuona;
	private Stanza stanzaCattiva;
	
	@BeforeEach
	void setUp() throws Exception {
		this.tool1 = new Attrezzo("asta", 3);
		this.tool2= new Attrezzo("chiave", 2);
		this.tool3 = new Attrezzo("osso", 3);
		this.tool4 = new Attrezzo("libro", 1);
		this.tool5 = new Attrezzo("pietra", 8);
		this.mago = new Mago("mago", "Ciao sono il mago", tool1);
		this.strega = new Strega("strega", "Ciao sono la strega");
		this.cane = new Cane("cane", "Ciao sono il cane", tool3);
		this.partita = new Partita();
		this.stanzaIniziale= new Stanza("atrio");
		this.partita.setStanzaCorrente(stanzaIniziale);
		this.stanzaBuona = new Stanza("biblioteca");
		this.stanzaCattiva = new Stanza("campus");
		}

	@Test
	void test_magoBorsaVuota() {
		assertNull(this.stanzaIniziale.getPersonaggio());
		this.stanzaIniziale.setPersonaggio(mago);
		assertEquals(this.stanzaIniziale.getPersonaggio(), mago);
		assertEquals(this.stanzaIniziale.getPersonaggio().getNome(), "mago");
		assertEquals(this.stanzaIniziale.getPersonaggio().getPresentazione(), "Ciao sono il mago");
		assertEquals(this.stanzaIniziale.getPersonaggio().saluta(), "Ciao io sono mago.Ciao sono il mago");
		assertEquals(this.stanzaIniziale.getPersonaggio().saluta(), "Ciao io sono mago.Ci siamo gia salutati!");
		assertNull(this.partita.getGiocatore().getBorsa().getAttrezzo(tool1.getNome()));
		this.stanzaIniziale.getPersonaggio().agisci(partita);
		assertEquals(this.partita.getGiocatore().getBorsa().getAttrezzo("asta"), tool1);
	}
	
	@Test
	void test_magoBorsaPiena() {
		assertNull(this.stanzaIniziale.getPersonaggio());
		this.stanzaIniziale.setPersonaggio(mago);
		this.partita.getGiocatore().getBorsa().addAttrezzo(tool5);
		assertEquals(this.stanzaIniziale.getPersonaggio(), mago);
		assertEquals(this.stanzaIniziale.getPersonaggio().getNome(), "mago");
		assertEquals(this.stanzaIniziale.getPersonaggio().getPresentazione(), "Ciao sono il mago");
		assertEquals(this.stanzaIniziale.getPersonaggio().saluta(), "Ciao io sono mago.Ciao sono il mago");
		assertEquals(this.stanzaIniziale.getPersonaggio().saluta(), "Ciao io sono mago.Ci siamo gia salutati!");
		assertNull(this.partita.getGiocatore().getBorsa().getAttrezzo(tool1.getNome()));
		this.stanzaIniziale.getPersonaggio().agisci(partita);
		assertNull(this.partita.getGiocatore().getBorsa().getAttrezzo(tool1.getNome()));
		this.partita.getGiocatore().getBorsa().removeAttrezzo(tool5.getNome());
		this.stanzaIniziale.getPersonaggio().agisci(partita);
		assertEquals(this.partita.getGiocatore().getBorsa().getAttrezzo(tool1.getNome()), tool1);
	}
	
	@Test
	void test_strega() {
		assertNull(this.stanzaIniziale.getPersonaggio());
		this.stanzaIniziale.setPersonaggio(strega);
		assertEquals(this.stanzaIniziale.getPersonaggio(), strega);
		assertEquals(this.stanzaIniziale.getPersonaggio().getNome(), "strega");
		assertEquals(this.stanzaIniziale.getPersonaggio().getPresentazione(), "Ciao sono la strega");
		assertEquals(this.stanzaIniziale.getPersonaggio().saluta(), "Ciao io sono strega.Ciao sono la strega");
		assertEquals(this.stanzaIniziale.getPersonaggio().saluta(), "Ciao io sono strega.Ci siamo gia salutati!");
	}
	
	@Test
	void test_stregaAgisciNonSalutato() {
		this.partita.setStanzaCorrente(stanzaIniziale);
		this.stanzaIniziale.setPersonaggio(strega);
		this.partita.getStanzaCorrente().impostaStanzaAdiacente("nord", stanzaBuona);
		this.partita.getStanzaCorrente().impostaStanzaAdiacente("sud", stanzaCattiva); 
		this.stanzaBuona.addAttrezzo(tool2);
		this.stanzaBuona.addAttrezzo(tool3);
		this.stanzaCattiva.addAttrezzo(tool4);
		assertEquals(this.partita.getStanzaCorrente().getNome(), this.stanzaIniziale.getNome());
		this.stanzaIniziale.getPersonaggio().agisci(partita);
		assertEquals(this.partita.getStanzaCorrente().getNome(), this.stanzaCattiva.getNome());
	}
	
	@Test
	void test_stregaAgisciHaSalutato() {
		this.partita.setStanzaCorrente(stanzaIniziale);
		this.stanzaIniziale.setPersonaggio(strega);
		this.partita.getStanzaCorrente().impostaStanzaAdiacente("nord", stanzaBuona);
		this.partita.getStanzaCorrente().impostaStanzaAdiacente("sud", stanzaCattiva); 
		this.stanzaBuona.addAttrezzo(tool2);
		this.stanzaBuona.addAttrezzo(tool3);
		this.stanzaCattiva.addAttrezzo(tool4);
		assertEquals(this.partita.getStanzaCorrente().getNome(), this.stanzaIniziale.getNome());
		this.stanzaIniziale.getPersonaggio().saluta();
		this.stanzaIniziale.getPersonaggio().agisci(partita);
		assertEquals(this.partita.getStanzaCorrente().getNome(), this.stanzaBuona.getNome());
	}
	
	@Test
	void test_cane() {
		assertNull(this.stanzaIniziale.getPersonaggio());
		this.stanzaIniziale.setPersonaggio(cane);
		assertEquals(this.stanzaIniziale.getPersonaggio(), cane);
		assertEquals(this.stanzaIniziale.getPersonaggio().getNome(), "cane");
		assertEquals(this.stanzaIniziale.getPersonaggio().getPresentazione(), "Ciao sono il cane");
		assertEquals(this.stanzaIniziale.getPersonaggio().saluta(), "Ciao io sono cane.Ciao sono il cane");
		assertEquals(this.stanzaIniziale.getPersonaggio().saluta(), "Ciao io sono cane.Ci siamo gia salutati!");
		assertFalse(this.stanzaIniziale.hasAttrezzo(tool3.getNome()));
		this.stanzaIniziale.getPersonaggio().riceviRegalo(tool3, partita);
		assertTrue(this.stanzaIniziale.hasAttrezzo(tool3.getNome()));
		assertEquals(this.partita.getGiocatore().getCfu(), 20);
		this.stanzaIniziale.getPersonaggio().agisci(partita);
		assertEquals(this.partita.getGiocatore().getCfu(), 19);
	}

}
