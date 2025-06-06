/*
	 1) creo un oggetto
	 2) setto i parametri
	 3) effettuo il testing
	 
	 note:
	 uso @test per marcare i test case
	 test come prefisso dei metodi buona norma ex testCostruzioneComandiInvalidi
	 
	 
	 definizione: fixture -> stato iniziale o set di dati necessario per eseguire un testt
	 
	 @BeforeEach  indica quali metodi eseguire prima di ciascuna invocazione di un test case, quindi appunto magari la costruzione di oggetti
	 
	 // metodo set up -> creazione degli oggetti
	 //	setup ha senso solo se creo almeno 2 classi che utilizzato il set up 
	 ///
	 ///fare test minimali, non essere dispersivi con i casi ed esempio se devo verificare il massimo meglio 2 valori che 5 
	 */
package diadia;


	import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaTest {
	
	// FIXTURE -->  preset variabili
	Stanza stanza1 = new Stanza("stanza1");
	Stanza stanza2 = new Stanza("stanza2");
	Stanza stanza3 = new Stanza("stanza3");
	
	Attrezzo tool1 = new Attrezzo("ascia",8);
	Attrezzo tool2 = new Attrezzo("spada",5);
	Attrezzo tool3 = new Attrezzo("osso",3);
	Attrezzo tool4 = new Attrezzo("fionda",2);
	Attrezzo tool5 = new Attrezzo("martello",4);
	Attrezzo tool6 = new Attrezzo("polvere",1);
	Attrezzo tool7 = new Attrezzo("pozione",2);
	Attrezzo tool8 = new Attrezzo("arco",4);
	Attrezzo tool9 = new Attrezzo("pietra",2);
	Attrezzo tool10 = new Attrezzo("bastone",1);
	
		
	@BeforeEach
	public void setUpStanza() { // inizializzo lo stato degli oggetti
		
		stanza1.impostaStanzaAdiacente("Nord", stanza2);
		stanza1.impostaStanzaAdiacente("Est", stanza3);
		stanza3.impostaStanzaAdiacente("Ovest", stanza1);
		stanza2.impostaStanzaAdiacente("Sud", stanza1);
		
		
		stanza1.addAttrezzo(tool1);
		stanza1.addAttrezzo(tool2);
		
		stanza2.addAttrezzo(tool2);
		
		stanza3.addAttrezzo(tool3);


	}
	
	
	// METODI PER VERIFICARE il metodo "getAttrezzo"
	
	@Test
	
	public void VerificaRiferimentoAttrezzo() {
		assertEquals(tool1,stanza1.getAttrezzo(tool1.getNome()));
	}
	
	
	@Test
	public void VerificaRiferimentoAttrezzoConAttributoTramiteStringa() {
		assertEquals(tool1.getPeso(),stanza1.getAttrezzo("ascia").getPeso());

	}
	
	@Test
	public void VerificaRiferimentoAttrezzoNonEsistente() {
		assertNotEquals(tool1,stanza1.getAttrezzo(tool2.getNome()));
	}
	
	
	

	
	// METODI PER VERIFICARE il metodo "HasAttrezzo"

		@Test
		public void VerificaPresenzaAttrezzo() {
		assertTrue(stanza1.hasAttrezzo(tool1.getNome()));
		}	
		
		
		@Test 
		public void VeriificaAttrezzoNonEsistente() {
		assertFalse(stanza1.hasAttrezzo(tool5.getNome()));
		}
		
		@Test
		public void VerificaPresentaPiuAttrezziStessaStanza() {
		assertTrue(stanza1.hasAttrezzo(tool1.getNome()));
		assertTrue(stanza1.hasAttrezzo(tool2.getNome()));
		assertFalse(stanza1.hasAttrezzo(tool3.getNome())); // tool3 non è presente nella stanza 1
			
		}
		
		// METODI PER VERIFICARE il metodo "removeAttrezzo"

		@Test 
		public void VerificaRimozioneAttrezzo () {
			assertTrue(stanza1.hasAttrezzo(tool1.getNome()));
			stanza1.removeAttrezzo(tool1);
			assertFalse(stanza1.hasAttrezzo(tool1.getNome()));
		}
		
		@Test
		public void VerificaRimozioneAttrezzoInesitente() {
			assertFalse(stanza1.hasAttrezzo(tool10.getNome()));
			assertFalse(stanza1.removeAttrezzo(tool10));
			assertFalse(stanza1.hasAttrezzo(tool10.getNome()));
		}
		
		@Test
		public void VerificaRimozioneInventarioVuoto() {
			stanza1.removeAttrezzo(tool1);
			stanza1.removeAttrezzo(tool2); // rimozione attrezzi impostati nel setup
			assertFalse(stanza1.removeAttrezzo(tool1)); 
		}
		
		@Test
		public void verificaEsistenzaStanzeAdiacenti() {
			assertEquals(stanza2,stanza1.getStanzaAdiacente("Nord"));
			assertEquals(stanza3,stanza1.getStanzaAdiacente("Est"));
			assertEquals(stanza1,stanza3.getStanzaAdiacente("Ovest"));
		}
	
		
	}



	
	