package diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.*;
import it.uniroma3.diadia.attrezzi.Attrezzo;
class StanzaMagicaTest {
	
	StanzaMagica stanza1 = new StanzaMagica("atrio");
	StanzaMagica stanza2 = new StanzaMagica("biblioteca", 2);
	Attrezzo tool1 = new Attrezzo("osso", 2);
	Attrezzo tool2 = new Attrezzo("chiave", 1);
	Attrezzo tool3 = new Attrezzo("lanterna", 4);
	Attrezzo tool4 = new Attrezzo("libro", 3);
	Attrezzo tool5 = new Attrezzo("martello", 1);
	
	@BeforeEach
	void setUp() throws Exception {
		stanza1.impostaStanzaAdiacente("est", stanza2);
		stanza2.impostaStanzaAdiacente("ovest", stanza1);
		stanza1.addAttrezzo(tool1);
		stanza1.addAttrezzo(tool2);
	}

	/**
	 * Verifico che la stanza magica funzioni come una stanza normale
	 */
	@Test
	void test_Stanza() {
		assertEquals(stanza1.getStanzaAdiacente("est"), stanza2);
		assertEquals(stanza2.getStanzaAdiacente("ovest"), stanza1);
		assertTrue(stanza1.hasAttrezzo("osso"));
		assertTrue(stanza1.hasAttrezzo("chiave"));
		assertFalse(stanza2.hasAttrezzo("osso"));
	}
	
	@Test 
	void test_addAttrezzo() {
		assertTrue(stanza1.hasAttrezzo("osso"));
		assertFalse(stanza1.hasAttrezzo("libro"));
		stanza1.addAttrezzo(tool4);
		assertTrue(stanza1.hasAttrezzo("libro"));
	}
	
	@Test
	void test_removeAttrezzo() {
		assertTrue(stanza1.hasAttrezzo("osso"));
		stanza1.removeAttrezzo(tool1);
		assertFalse(stanza1.hasAttrezzo("osso"));
	}
	
	@Test 
	void test_addAttrezzoMagico() {
		assertTrue(stanza1.hasAttrezzo(tool1.getNome()));
		assertTrue(stanza1.hasAttrezzo(tool2.getNome()));
		assertFalse(stanza1.hasAttrezzo(tool3.getNome()));
		assertFalse(stanza1.hasAttrezzo(tool4.getNome()));
		assertFalse(stanza1.hasAttrezzo(tool5.getNome()));
		/*Nella stanza ho 2 oggetti e la soglia è 3 quindi uno viene aggiunto in maniera normale*/
		stanza1.addAttrezzo(tool3);
		assertTrue(stanza1.hasAttrezzo(tool3.getNome()));
		/*Inserisco il 4 oggetto e la stanza ne inverte in nome e ne raddoppia il peso*/
		stanza1.addAttrezzo(tool4);
		assertFalse(stanza1.hasAttrezzo(tool4.getNome()));
		assertFalse(stanza1.hasAttrezzo("libro"));
		assertTrue(stanza1.hasAttrezzo("orbil"));
		assertFalse(stanza1.getAttrezzo("orbil").getPeso()==3);
		assertTrue(stanza1.getAttrezzo("orbil").getPeso()==6);
		stanza1.addAttrezzo(tool5);
		assertFalse(stanza1.hasAttrezzo(tool5.getNome()));
		assertFalse(stanza1.hasAttrezzo("martello"));
		assertTrue(stanza1.hasAttrezzo("olletram"));
		assertFalse(stanza1.getAttrezzo("olletram").getPeso()==1);
		assertTrue(stanza1.getAttrezzo("olletram").getPeso()==2);
	}
	
	@Test 
	void test_addAttrezzoMagicoConRimozione() {
		assertTrue(stanza1.hasAttrezzo(tool1.getNome()));
		assertTrue(stanza1.hasAttrezzo(tool2.getNome()));
		assertFalse(stanza1.hasAttrezzo(tool3.getNome()));
		assertFalse(stanza1.hasAttrezzo(tool4.getNome()));
		assertFalse(stanza1.hasAttrezzo(tool5.getNome()));
		/*Nella stanza ho 2 oggetti e la soglia è 3 quindi uno viene aggiunto in maniera normale*/
		stanza1.addAttrezzo(tool3);
		assertTrue(stanza1.hasAttrezzo(tool3.getNome()));
		/*Inserisco il 4 oggetto e la stanza ne inverte in nome e ne raddoppia il peso*/
		stanza1.addAttrezzo(tool4);
		assertFalse(stanza1.hasAttrezzo(tool4.getNome()));
		assertFalse(stanza1.hasAttrezzo("libro"));
		assertTrue(stanza1.hasAttrezzo("orbil"));
		assertFalse(stanza1.getAttrezzo("orbil").getPeso()==3);
		assertTrue(stanza1.getAttrezzo("orbil").getPeso()==6);
		/*faccio in modo di ritornare a 2 oggetti nella stanza*/
		stanza1.removeAttrezzo(tool1);
		stanza1.removeAttrezzo(stanza1.getAttrezzo("orbil"));
		assertFalse(stanza1.hasAttrezzo(tool1.getNome()));
		assertFalse(stanza1.hasAttrezzo(tool4.getNome()));
		assertFalse(stanza1.hasAttrezzo("orbil"));
		/*ora il primo viene aggiunto normalmente, mentre il secondo subisce gli effetti di stanza magica*/
		stanza1.addAttrezzo(tool4);
		assertTrue(stanza1.hasAttrezzo(tool4.getNome()));
		assertTrue(stanza1.getAttrezzo(tool4.getNome()).getPeso()==3);
		assertFalse(stanza1.hasAttrezzo("orbil"));
		assertFalse(stanza1.hasAttrezzo("evaihc"));
		stanza1.addAttrezzo(tool2);
		assertTrue(stanza1.hasAttrezzo("evaihc"));
		assertTrue(stanza1.getAttrezzo("evaihc").getPeso()==2);
	}
	
}
