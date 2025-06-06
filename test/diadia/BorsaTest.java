package diadia;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

class BorsaTest {
	
	Borsa bag = new Borsa(20);
	Attrezzo tool1 = new Attrezzo("ascia",1);
	Attrezzo tool2 = new Attrezzo("arco",2);
	Attrezzo tool3 = new Attrezzo("balestra",3);
	Attrezzo tool4 = new Attrezzo("pozione",4);
	Attrezzo tool5 = new Attrezzo("pietra",5);
	Attrezzo tool6 = new Attrezzo("guanti",6);
	Attrezzo tool7 = new Attrezzo("accendino", 3);
	Attrezzo tool8 = new Attrezzo("guanti", 4);


	@BeforeEach
	void setUpBorsa() { 
		bag.addAttrezzo(tool1);
		bag.addAttrezzo(tool2);
		bag.addAttrezzo(tool3);
		bag.addAttrezzo(tool4);
		
	} 
	
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
		assertTrue(bag.hasAttrezzo("ascia"));
		bag.removeAttrezzo(tool1.getNome());
		assertFalse(bag.hasAttrezzo("ascia"));
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
	
	//METODI PER VERIFICARE il metodo addAttrezzo
	
	@Test
	public void test_aggiungiAttrezzo() {
		assertFalse(bag.hasAttrezzo("pietra"));
		bag.addAttrezzo(tool5);
		assertTrue(bag.hasAttrezzo("pietra"));
	}
	
	@Test
	public void test_aggiungiAttrezzoPesoMaxRaggiunto() {
		assertFalse(bag.hasAttrezzo("pietra"));
		assertFalse(bag.hasAttrezzo("guanti"));
		bag.addAttrezzo(tool5);
		bag.addAttrezzo(tool6);
		assertTrue(bag.hasAttrezzo("pietra"));
		assertFalse(bag.hasAttrezzo("guanti"));
	}
	
	@Test
	public void BorsaVisualizzazioneInventario () {
		bag.addAttrezzo(tool5);
		bag.addAttrezzo(tool6);
		System.out.println(bag.toString());
	}
	
	/*Test getContenutoOrdinatoPerPeso()*/
	@Test
	public void test_getContenutoOrdinatoPerPeso() {
		List<Attrezzo> ordinati = bag.getContenutoOrdinatoPerPeso();
		assertEquals(ordinati.size(), 4);
		assertEquals(ordinati.get(0), tool1);
		assertEquals(ordinati.get(1), tool2);
		assertEquals(ordinati.get(2), tool3);
		assertEquals(ordinati.get(3), tool4);
	}
	
	@Test
	public void test_getContenutoOrdinatoPerPesoConPesoUguale() {
		bag.addAttrezzo(tool7);
		List<Attrezzo> ordinati = bag.getContenutoOrdinatoPerPeso();
		assertEquals(ordinati.size(), 5);
		assertEquals(ordinati.get(0), tool1);
		assertEquals(ordinati.get(1), tool2);
		assertEquals(ordinati.get(2), tool7);
		assertEquals(ordinati.get(3), tool3);
		assertEquals(ordinati.get(4), tool4);
	}
	
	/*Test getContenutoOrdinatoPerNome()*/
	@Test
	public void test_getContenutoOrdinatoPerNome() {
		SortedSet<Attrezzo> ordinati = bag.getContenutoOrdinatoPerNome();
		assertEquals(ordinati.size(), 4);
		List<Attrezzo> listaOrdinata = new ArrayList<>(ordinati);
		assertEquals(listaOrdinata.get(0), tool2);
		assertEquals(listaOrdinata.get(1), tool1);
		assertEquals(listaOrdinata.get(2), tool3);
		assertEquals(listaOrdinata.get(3), tool4);
		}
	
	@Test
	public void test_getContenutoOrdinatoPerNomeConAggiuntaOggettoConNomeUguale() {
		bag.addAttrezzo(tool6);
		bag.addAttrezzo(tool8);
		SortedSet<Attrezzo> ordinati = bag.getContenutoOrdinatoPerNome();
		/*LA dimensione rimane 4 perchè il tool 8 ha lo stesso nome */
		assertEquals(ordinati.size(), 5);
		List<Attrezzo> listaOrdinata = new ArrayList<>(ordinati);
		assertEquals(listaOrdinata.get(0), tool2);
		assertEquals(listaOrdinata.get(1), tool1);
		assertEquals(listaOrdinata.get(2), tool3);
		assertEquals(listaOrdinata.get(3), tool6);
		assertEquals(listaOrdinata.get(4), tool4);
		}
	
	/*Test getContenutoRaggruppatoPerPeso()*/
	@Test
	public void test_getContenutoRaggruppatoPerPeso() {
		bag.addAttrezzo(tool7);
		bag.addAttrezzo(tool8);
		Map<Integer, Set<Attrezzo>> raggruppati = bag.getContenutoRaggruppatoPerPeso();
		assertEquals(raggruppati.size(), 4);
		
		Set<Attrezzo> peso3 = raggruppati.get(3);
		assertEquals(peso3.size(),2);
		assertTrue(peso3.contains(tool3));
		assertTrue(peso3.contains(tool7));
		
		Set<Attrezzo> peso4 = raggruppati.get(4);
		assertEquals(peso4.size(),2);
		assertTrue(peso4.contains(tool4));
		assertTrue(peso4.contains(tool8));
		
		Set<Attrezzo> peso1 = raggruppati.get(1);
		assertEquals(peso1.size(), 1);
		assertTrue(peso1.contains(tool1));
		
		Set<Attrezzo> peso2 = raggruppati.get(2);
		assertEquals(peso2.size(), 1);
		assertTrue(peso2.contains(tool2));
	}
	
	/*test getSortedSetOrdinatoPerPeso()*/
	@Test
	public void test_getSortedSetOrdinatoPerPeso() {
		SortedSet<Attrezzo> ordinati = bag.getSortedSetOrdinatoPerPeso();
		assertEquals(ordinati.size(), 4);
		List<Attrezzo> listaOrdinata = new ArrayList<>(ordinati);
		assertEquals(listaOrdinata.get(0), tool1);
		assertEquals(listaOrdinata.get(1), tool2);
		assertEquals(listaOrdinata.get(2), tool3);
		assertEquals(listaOrdinata.get(3), tool4);
	}
	
	@Test
	public void test_getSortedSetOrdinatoPerPesoConPesoUguale() {
	    bag.addAttrezzo(tool7);
		SortedSet<Attrezzo> ordinati = bag.getSortedSetOrdinatoPerPeso();
		assertEquals(ordinati.size(),5);
		List<Attrezzo> listaOrdinata = new ArrayList<>(ordinati);
		assertEquals(listaOrdinata.get(0), tool1);
		assertEquals(listaOrdinata.get(1), tool2);
		assertEquals(listaOrdinata.get(2), tool7);
		assertEquals(listaOrdinata.get(3), tool3);
		assertEquals(listaOrdinata.get(4), tool4);
	}
}
