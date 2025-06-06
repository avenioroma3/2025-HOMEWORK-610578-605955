package diadia;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.*;
import it.uniroma3.diadia.ambienti.*;
import it.uniroma3.diadia.personaggi.*;

import java.io.*;

class CaricatoreLabirintoTest {

	@Test
    void testMonolocale() throws FileNotFoundException {
        
        String labirintoMonolocale =
              "Stanze: N10\n"
        	+ "Magiche:\n"
            + "Bloccate:\n"
        	+ "Buie:\n"
        	+ "Personaggi:\n"
            + "Inizio: N10\n"
            + "Vincente: N10\n"
            + "Attrezzi: Osso 5 N10\n"
            + "Uscite:\n";

        Reader in = new StringReader(labirintoMonolocale);

        CaricatoreLabirinto caricatore = new CaricatoreLabirinto(in);

        Labirinto lab = null;
        try {
            caricatore.carica();         
            lab = caricatore.getLabirinto();
        } catch (FormatoFileNonValidoException e) {
            fail("Non doveva lanciare FormatoFileNonValidoException: " + e.getMessage());
        }

        Stanza iniziale = lab.getStanzaIniziale();
        Stanza vincente  = lab.getStanzaVincente();
        assertEquals("N10", iniziale.getNome());
        assertEquals("N10", vincente.getNome());

        assertTrue(iniziale.hasAttrezzo("Osso"));
        assertEquals(5, iniziale.getAttrezzo("Osso").getPeso());

        assertTrue(iniziale.getDirezioni().isEmpty());
    }

	@Test 
	void test_bilocale() throws FileNotFoundException {
		String labirintoBilocale = 
				"Stanze: atrio, biblioteca\n"
				+ "Magiche:\n"
			    + "Bloccate:\n"
			    + "Buie:\n"
			    + "Personaggi:\n"
				+ "Inizio: atrio\n"
				+ "Vincente: biblioteca\n"
				+ "Attrezzi: osso 5 atrio\n"
				+ "Uscite: atrio nord biblioteca\n";
		
		Reader in = new StringReader(labirintoBilocale);
		
		CaricatoreLabirinto caricatore = new CaricatoreLabirinto(in);
		
		Labirinto lab = null;
		try {
			caricatore.carica();
			lab = caricatore.getLabirinto();
		} catch (FormatoFileNonValidoException e) {
			fail("Non doveva lanciare FormatoFileNonValidoException: " + e.getMessage());
		}
		Stanza iniziale = lab.getStanzaIniziale();
        Stanza vincente  = lab.getStanzaVincente();
        assertEquals("atrio", iniziale.getNome());
        assertEquals("biblioteca", vincente.getNome());

        assertTrue(iniziale.hasAttrezzo("osso"));
        assertEquals(5, iniziale.getAttrezzo("osso").getPeso());
        assertTrue(vincente.getAttrezzi().isEmpty());

        assertTrue(iniziale.getDirezioni().size()==1);
        assertEquals(iniziale.getDirezioni().get(0), "nord");
        assertEquals(iniziale.getStanzaAdiacente("nord").getNome(), "biblioteca");
	}
	
	@Test 
	void test_stanzaSenzaUscite() throws FileNotFoundException {
		String labirinto = "Stanze: atrio, biblioteca\n"
				+"Magiche:\n"
				+"Bloccate:\n"
				+"Buie:\n"
				+"Personaggi:\n"
				+"Inizio: atrio\n"
				+"Vincente: biblioteca\n"
				+"Attrezzi: osso 2 atrio\n"
				+"Uscite:\n";
Reader in = new StringReader(labirinto);
		
		CaricatoreLabirinto caricatore = new CaricatoreLabirinto(in);
		
		Labirinto lab = null;
		try {
			caricatore.carica();
			lab = caricatore.getLabirinto();
		} catch (FormatoFileNonValidoException e) {
			fail("Non doveva lanciare FormatoFileNonValidoException: " + e.getMessage());
		}
		Stanza iniziale = lab.getStanzaIniziale();
      
        assertEquals("atrio", iniziale.getNome());

        assertTrue(iniziale.hasAttrezzo("osso"));
        assertEquals(2, iniziale.getAttrezzo("osso").getPeso());

        assertTrue(iniziale.getDirezioni().size()==0);
	}
	
	@Test 
	void test_stanzaConpiuAttrezzi() throws FileNotFoundException {
		String labirinto = 
				"Stanze: atrio, biblioteca\n"
				+"Magiche:\n"
				+"Bloccate:\n"
				+"Buie:\n"
				+"Personaggi:\n"
				+"Inizio: atrio\n"
				+"Vincente: biblioteca\n"
				+"Attrezzi: osso 2 atrio, libro 1 atrio, chiave 3 atrio\n"
				+"Uscite:\n";
		
Reader in = new StringReader(labirinto);
		
		CaricatoreLabirinto caricatore = new CaricatoreLabirinto(in);
		
		Labirinto lab = null;
		try {
			caricatore.carica();
			lab = caricatore.getLabirinto();
		} catch (FormatoFileNonValidoException e) {
			fail("Non doveva lanciare FormatoFileNonValidoException: " + e.getMessage());
		}
		Stanza iniziale = lab.getStanzaIniziale();
        
        assertEquals("atrio", iniziale.getNome());

        assertEquals(iniziale.getAttrezzi().size(), 3);
        assertTrue(iniziale.hasAttrezzo("osso"));
        assertTrue(iniziale.hasAttrezzo("chiave"));
        assertTrue(iniziale.hasAttrezzo("libro"));
        assertEquals(2, iniziale.getAttrezzo("osso").getPeso());
        assertEquals(3, iniziale.getAttrezzo("chiave").getPeso());
        assertEquals(1, iniziale.getAttrezzo("libro").getPeso());

        assertTrue(iniziale.getDirezioni().size()==0);
	}
	
	@Test
	public void test_stanzaMagica() throws FileNotFoundException {
		String labirinto = "Stanze:biblioteca, atrio\n"
				+"Magiche: atrio 1\n"
				+"Bloccate:\n"
				+"Buie:\n"
				+"Personaggi:\n"
				+"Inizio: atrio\n"
				+"Vincente: biblioteca\n"
				+"Attrezzi: osso 2 atrio, libro 1 biblioteca, chiave 3 atrio\n"
				+"Uscite: atrio nord biblioteca\n";
		
		Reader in = new StringReader(labirinto);
		
CaricatoreLabirinto caricatore = new CaricatoreLabirinto(in);
		
		Labirinto lab = null;
		try {
			caricatore.carica();
			lab = caricatore.getLabirinto();
		} catch (FormatoFileNonValidoException e) {
			fail("Non doveva lanciare FormatoFileNonValidoException: " + e.getMessage());
		}
		Stanza iniziale = lab.getStanzaIniziale();
		Stanza finale = lab.getStanzaVincente();
		
		assertEquals("atrio", iniziale.getNome());
		assertEquals(iniziale.getClass(), StanzaMagica.class);
		
		assertEquals(iniziale.getAttrezzi().size(), 2);
        assertTrue(iniziale.hasAttrezzo("osso"));
        assertTrue(iniziale.hasAttrezzo("evaihc"));
        assertTrue(finale.hasAttrezzo("libro"));
        assertEquals(2, iniziale.getAttrezzo("osso").getPeso());
        assertEquals(6, iniziale.getAttrezzo("evaihc").getPeso());
        assertEquals(1, finale.getAttrezzo("libro").getPeso());

        assertTrue(iniziale.getDirezioni().size()==1);
        assertEquals(iniziale.getStanzaAdiacente("nord"), finale);
	}
	
	@Test
	public void test_stanzaBloccataSenzaAttrezzo() throws FileNotFoundException {
		String labirinto = "Stanze:biblioteca, atrio\n"
				+"Magiche:\n"
				+"Bloccate: atrio nord chiave\n"
				+"Buie:\n"
				+"Personaggi:\n"
				+"Inizio: atrio\n"
				+"Vincente: biblioteca\n"
				+"Attrezzi: osso 2 atrio, libro 1 biblioteca\n"
				+"Uscite: atrio nord biblioteca\n";
		
		Reader in = new StringReader(labirinto);
		
CaricatoreLabirinto caricatore = new CaricatoreLabirinto(in);
		
		Labirinto lab = null;
		try {
			caricatore.carica();
			lab = caricatore.getLabirinto();
		} catch (FormatoFileNonValidoException e) {
			fail("Non doveva lanciare FormatoFileNonValidoException: " + e.getMessage());
		}
		Stanza iniziale = lab.getStanzaIniziale();
		Stanza finale = lab.getStanzaVincente();
		
		assertEquals("atrio", iniziale.getNome());
		assertEquals(iniziale.getClass(), StanzaBloccata.class);
		
		assertEquals(iniziale.getAttrezzi().size(), 1);
        assertTrue(iniziale.hasAttrezzo("osso"));
        
        assertTrue(finale.hasAttrezzo("libro"));
        assertEquals(2, iniziale.getAttrezzo("osso").getPeso());
       
        assertEquals(1, finale.getAttrezzo("libro").getPeso());

        assertTrue(iniziale.getDirezioni().size()==1);
        assertEquals(iniziale.getStanzaAdiacente("nord"), iniziale);
	}
	
	@Test
	public void test_stanzaBloccataConAttrezzo() throws FileNotFoundException {
		String labirinto = "Stanze:biblioteca, atrio\n"
				+"Magiche:\n"
				+"Bloccate: atrio nord chiave\n"
				+"Buie:\n"
				+"Personaggi:\n"
				+"Inizio: atrio\n"
				+"Vincente: biblioteca\n"
				+"Attrezzi: osso 2 atrio, libro 1 biblioteca, chiave 3 atrio\n"
				+"Uscite: atrio nord biblioteca\n";
		
		Reader in = new StringReader(labirinto);
		
CaricatoreLabirinto caricatore = new CaricatoreLabirinto(in);
		
		Labirinto lab = null;
		try {
			caricatore.carica();
			lab = caricatore.getLabirinto();
		} catch (FormatoFileNonValidoException e) {
			fail("Non doveva lanciare FormatoFileNonValidoException: " + e.getMessage());
		}
		Stanza iniziale = lab.getStanzaIniziale();
		Stanza finale = lab.getStanzaVincente();
		
		assertEquals("atrio", iniziale.getNome());
		assertEquals(iniziale.getClass(), StanzaBloccata.class);
		
		assertEquals(iniziale.getAttrezzi().size(), 2);
        assertTrue(iniziale.hasAttrezzo("osso"));
        assertTrue(iniziale.hasAttrezzo("chiave"));
        assertTrue(finale.hasAttrezzo("libro"));
        assertEquals(2, iniziale.getAttrezzo("osso").getPeso());
        assertEquals(3, iniziale.getAttrezzo("chiave").getPeso());
        assertEquals(1, finale.getAttrezzo("libro").getPeso());

        assertTrue(iniziale.getDirezioni().size()==1);
        assertEquals(iniziale.getStanzaAdiacente("nord"), finale);
	}
	
	@Test
	public void test_stanzaBuiaSenzaAttrezzo() throws FileNotFoundException {
		String labirinto = "Stanze:biblioteca, atrio\n"
				+"Magiche:\n"
				+"Bloccate:\n"
				+"Buie: atrio lanterna\n"
				+"Personaggi:\n"
				+"Inizio: atrio\n"
				+"Vincente: biblioteca\n"
				+"Attrezzi: osso 2 atrio, libro 1 biblioteca, chiave 3 atrio\n"
				+"Uscite: atrio nord biblioteca\n";
		
		Reader in = new StringReader(labirinto);
		
CaricatoreLabirinto caricatore = new CaricatoreLabirinto(in);
		
		Labirinto lab = null;
		try {
			caricatore.carica();
			lab = caricatore.getLabirinto();
		} catch (FormatoFileNonValidoException e) {
			fail("Non doveva lanciare FormatoFileNonValidoException: " + e.getMessage());
		}
		Stanza iniziale = lab.getStanzaIniziale();
		Stanza finale = lab.getStanzaVincente();
		
		assertEquals("atrio", iniziale.getNome());
		assertEquals(iniziale.getClass(), StanzaBuia.class);
		
		assertEquals(iniziale.getAttrezzi().size(), 2);
        assertTrue(iniziale.hasAttrezzo("osso"));
        assertTrue(iniziale.hasAttrezzo("chiave"));
        assertTrue(finale.hasAttrezzo("libro"));
        assertEquals(2, iniziale.getAttrezzo("osso").getPeso());
        assertEquals(3, iniziale.getAttrezzo("chiave").getPeso());
        assertEquals(1, finale.getAttrezzo("libro").getPeso());

        assertTrue(iniziale.getDirezioni().size()==1);
        assertEquals(iniziale.getStanzaAdiacente("nord"), finale);
        assertEquals(iniziale.getDescrizione(), "qui c'è buio pesto");
	}
	
	@Test
	public void test_stanzaBuiaConAttrezzo() throws FileNotFoundException {
		String labirinto = "Stanze:biblioteca, atrio\n"
				+"Magiche:\n"
				+"Bloccate:\n"
				+"Buie: atrio lanterna\n"
				+"Personaggi:\n"
				+"Inizio: atrio\n"
				+"Vincente: biblioteca\n"
				+"Attrezzi: lanterna 2 atrio, libro 1 biblioteca, chiave 3 atrio\n"
				+"Uscite: atrio nord biblioteca\n";
		
		Reader in = new StringReader(labirinto);
		
CaricatoreLabirinto caricatore = new CaricatoreLabirinto(in);
		
		Labirinto lab = null;
		try {
			caricatore.carica();
			lab = caricatore.getLabirinto();
		} catch (FormatoFileNonValidoException e) {
			fail("Non doveva lanciare FormatoFileNonValidoException: " + e.getMessage());
		}
		Stanza iniziale = lab.getStanzaIniziale();
		Stanza finale = lab.getStanzaVincente();
		
		assertEquals("atrio", iniziale.getNome());
		assertEquals(iniziale.getClass(), StanzaBuia.class);
		
		assertEquals(iniziale.getAttrezzi().size(), 2);
        assertTrue(iniziale.hasAttrezzo("lanterna"));
        assertTrue(iniziale.hasAttrezzo("chiave"));
        assertTrue(finale.hasAttrezzo("libro"));
        assertEquals(2, iniziale.getAttrezzo("lanterna").getPeso());
        assertEquals(3, iniziale.getAttrezzo("chiave").getPeso());
        assertEquals(1, finale.getAttrezzo("libro").getPeso());

        assertTrue(iniziale.getDirezioni().size()==1);
        assertEquals(iniziale.getStanzaAdiacente("nord"), finale);
        assertEquals(iniziale.getDescrizione(), "atrio\n"
        		+ "Uscite: nord\n"
        		+ "Attrezzi nella stanza: lanterna (2kg) chiave (3kg) \nPersonaggi nella stanza: Nessun personaggio");
	}
	
	@Test
	public void testCaricamentoMago() throws FileNotFoundException {
	    String labirinto =
	         "Stanze: atrio, biblioteca\n" 
	         +"Magiche:\n" 
	         +"Bloccate:\n" 
	         +"Buie:\n" 
	         +"Personaggi: Mago nomeMago atrio bastone 3\n" 
	         +"Inizio: atrio\n" 
	         +"Vincente: biblioteca\n" 
	         +"Attrezzi:\n" 
	         +"Uscite:\n";

	    Reader in = new StringReader(labirinto);
	    CaricatoreLabirinto caricatore = new CaricatoreLabirinto(in);
	    Labirinto lab = null;
	    try {
	        caricatore.carica();
	        lab = caricatore.getLabirinto();
	    } catch (FormatoFileNonValidoException e) {
	        fail("Non doveva lanciare eccezione: " + e.getMessage());
	    }

	    Stanza iniziale = lab.getStanzaIniziale();
	   
	    assertNotNull(iniziale.getPersonaggio());
	    assertTrue(iniziale.getPersonaggio() instanceof Mago);
	    assertEquals("nomeMago", iniziale.getPersonaggio().getNome());

	    Mago m = (Mago) iniziale.getPersonaggio();
	    assertNotNull(m.getAttrezzo());
	    assertEquals("bastone", m.getAttrezzo().getNome());
	    assertEquals(3, m.getAttrezzo().getPeso());
	}
	
	@Test
	public void testCaricamentoCane() throws FileNotFoundException {
	    String labirinto =
	         "Stanze: atrio, biblioteca\n" 
	         +"Magiche:\n" 
	         +"Bloccate:\n" 
	         +"Buie:\n" 
	         +"Personaggi: Cane nomeCane atrio osso 3\n" 
	         +"Inizio: atrio\n" 
	         +"Vincente: biblioteca\n" 
	         +"Attrezzi:\n" 
	         +"Uscite:\n";

	    Reader in = new StringReader(labirinto);
	    CaricatoreLabirinto caricatore = new CaricatoreLabirinto(in);
	    Labirinto lab = null;
	    try {
	        caricatore.carica();
	        lab = caricatore.getLabirinto();
	    } catch (FormatoFileNonValidoException e) {
	        fail("Non doveva lanciare eccezione: " + e.getMessage());
	    }

	    Stanza iniziale = lab.getStanzaIniziale();
	   
	    assertNotNull(iniziale.getPersonaggio());
	    assertTrue(iniziale.getPersonaggio() instanceof Cane);
	    assertEquals("nomeCane", iniziale.getPersonaggio().getNome());

	    Cane c = (Cane) iniziale.getPersonaggio();
	    assertNotNull(c.getAttrezzo());
	    assertEquals("osso", c.getAttrezzo().getNome());
	    assertEquals(3, c.getAttrezzo().getPeso());
	}
	
	@Test
	public void testCaricamentoStrega() throws FileNotFoundException {
	    String labirinto =
	         "Stanze: atrio, biblioteca\n" 
	         +"Magiche:\n" 
	         +"Bloccate:\n" 
	         +"Buie:\n" 
	         +"Personaggi: Strega nomeStrega atrio\n" 
	         +"Inizio: atrio\n" 
	         +"Vincente: biblioteca\n" 
	         +"Attrezzi:\n" 
	         +"Uscite:\n";

	    Reader in = new StringReader(labirinto);
	    CaricatoreLabirinto caricatore = new CaricatoreLabirinto(in);
	    Labirinto lab = null;
	    try {
	        caricatore.carica();
	        lab = caricatore.getLabirinto();
	    } catch (FormatoFileNonValidoException e) {
	        fail("Non doveva lanciare eccezione: " + e.getMessage());
	    }

	    Stanza iniziale = lab.getStanzaIniziale();
	   
	    assertNotNull(iniziale.getPersonaggio());
	    assertTrue(iniziale.getPersonaggio() instanceof Strega);
	    assertEquals("nomeStrega", iniziale.getPersonaggio().getNome());
	    }
	
	@Test
	public void testCaricamentoPersonaggi() throws FileNotFoundException {
	    String labirinto =
	         "Stanze: atrio, biblioteca, magia\n" 
	         +"Magiche:\n" 
	         +"Bloccate:\n" 
	         +"Buie:\n" 
	         +"Personaggi: Cane nomeCane atrio osso 3, Mago nomeMago magia bastone 3, Strega nomeStrega biblioteca\n" 
	         +"Inizio: atrio\n" 
	         +"Vincente: biblioteca\n" 
	         +"Attrezzi:\n" 
	         +"Uscite: atrio nord magia\n";

	    Reader in = new StringReader(labirinto);
	    CaricatoreLabirinto caricatore = new CaricatoreLabirinto(in);
	    Labirinto lab = null;
	    try {
	        caricatore.carica();
	        lab = caricatore.getLabirinto();
	    } catch (FormatoFileNonValidoException e) {
	        fail("Non doveva lanciare eccezione: " + e.getMessage());
	    }

	    Stanza iniziale = lab.getStanzaIniziale();
	   
	    assertNotNull(iniziale.getPersonaggio());
	    assertTrue(iniziale.getPersonaggio() instanceof Cane);
	    assertEquals("nomeCane", iniziale.getPersonaggio().getNome());

	    Cane c = (Cane) iniziale.getPersonaggio();
	    assertNotNull(c.getAttrezzo());
	    assertEquals("osso", c.getAttrezzo().getNome());
	    assertEquals(3, c.getAttrezzo().getPeso());
	    
	    Stanza magia = lab.getStanzaIniziale().getStanzaAdiacente("nord");
	    assertNotNull(magia.getPersonaggio());
	    assertTrue(magia.getPersonaggio() instanceof Mago);
	    assertEquals("nomeMago", magia.getPersonaggio().getNome());

	    Mago m = (Mago) magia.getPersonaggio();
	    assertNotNull(m.getAttrezzo());
	    assertEquals("bastone", m.getAttrezzo().getNome());
	    assertEquals(3, m.getAttrezzo().getPeso());
	    
	    Stanza finale = lab.getStanzaVincente();
		   
	    assertNotNull(finale.getPersonaggio());
	    assertTrue(finale.getPersonaggio() instanceof Strega);
	    assertEquals("nomeStrega", finale.getPersonaggio().getNome());
	}
}
