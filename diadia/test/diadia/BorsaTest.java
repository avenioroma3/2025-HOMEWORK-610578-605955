package diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

class BorsaTest {
	
	Borsa bag = new Borsa();
	Attrezzo tool1 = new Attrezzo("ascia",8);
	Attrezzo tool2 = new Attrezzo("arco",4);
	Attrezzo tool3 = new Attrezzo("balestra",4);
	Attrezzo tool4 = new Attrezzo("pozione",2);
	Attrezzo tool5 = new Attrezzo("pietra",2);
	
	Attrezzo tool6 = new Attrezzo("guanti",2);



	@BeforeEach
	void setUpBorsa() { // nb: la capacità massima della borsa è 10
		bag.addAttrezzo(tool1);
		bag.addAttrezzo(tool2);
		bag.addAttrezzo(tool3);
		bag.addAttrezzo(tool4);
		bag.addAttrezzo(tool5);
	}
	
	// 
	
	// METODI PER VERIFICARE il metodo "getAttrezzo"
	
	@Test
	public void BorsaRicercaAttrezzo() {
		assertEquals(tool1, bag.getAttrezzo(tool1.getNome()));
	}
	
	@Test
	public void BorsaRicercaAttrezzoInesistente() {
		assertNull(bag.getAttrezzo(tool6.getNome()));
	}
	
	@Test
	public void BorsaRicercaAttrezzoInesistenteTramiteStringa() {
		assertNull(bag.getAttrezzo("osso"));
	}
	
	@Test
	public void BorsaRicercaAttrezzoramiteStringa() {
		assertEquals(tool1,bag.getAttrezzo("ascia"));
	}
	
	
	// METODI PER VERIFICARE il metodo "hasAttrezzo"
	
	@Test
	public void BorsaPresenzaAttrezzo() {
		assertTrue(bag.hasAttrezzo(tool1.getNome()));
	}
	
	@Test
	public void BorsaPresenzaAttrezzoInesistente() {
		assertFalse(bag.hasAttrezzo(tool6.getNome()));
	}
	
	// METODI PER VERIFICARE il metodo "removeAttrezzo"

	@Test
	public void BorsaRimozioneOggetto() {
		assertTrue(bag.removeAttrezzo(tool1.getNome()));
	}
	
	@Test
	public void BorsaRimozioneOggettoInesistente() {
		assertFalse(bag.removeAttrezzo(tool6.getNome()));
	}
	
	@Test 
	public void BorsaVuotaRimozione() {
		Borsa borsa2 = new Borsa();
		assertFalse(borsa2.removeAttrezzo(tool1.getNome()));
		}
	
	
	@Test
	public void BorsaVisualizzazioneInventario () {
		System.out.println(bag.toString());
	}
	
	

}
