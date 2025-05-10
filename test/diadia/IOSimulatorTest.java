package diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.DiaDia;
import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.Partita;

class IOSimulatorTest {

	
	String comandi[] = {"posa qualcosa","vai sud","prendi qualcosa","test"};
	IOSimulator iosim = new IOSimulator(comandi,100);
	
	DiaDia partita = new DiaDia(iosim);
	

	
	
//	@Test
//	public void test() {
//		partita.gioca();
//		iosim.leggiarray();
//	}
//	
	
	// attenzione nell array mostrati vengono salvati i messaggi che dovrebbero apparire a schermo.
	
	@Test
	public void TestDirezione() {
		String vai[] = {"vai sud"};
		IOSimulator iosim = new IOSimulator(vai,100);
		
		partita = new DiaDia(iosim);
		partita.gioca();
		
		assertTrue(iosim.VerificaPresenzaStringa(partita.getPartita().getStanzaCorrente().getNome()));
	}
	
	@Test
	public void TestGuarda() {
		String guarda[] = {"guarda"};
		IOSimulator iosim = new IOSimulator(guarda,100);
		
		partita = new DiaDia(iosim);
		partita.gioca();
		
		assertTrue(iosim.VerificaPresenzaStringa(partita.getPartita().getStanzaCorrente().getDescrizione()));
	}
	
	@Test 
	public void run1() {
		
		String run1[] = {"vai est","prendi qualcosa","posa qualcosa"};
		IOSimulator iosim = new IOSimulator(run1,100);
		
		partita = new DiaDia(iosim);
		partita.gioca();
		
		assertTrue(iosim.VerificaPresenzaStringa(partita.getPartita().getStanzaCorrente().getNome()));
		assertTrue(iosim.VerificaPresenzaStringa("L'attrezzo che hai inserito non è presente..."));
		assertTrue(iosim.VerificaPresenzaStringa("L'oggetto da posare non è presente nella borsa..."));
		
	}
	
//	@Test 
//	public void run2() {
//		
//		String run2[] = {"vai sud","guarda","prendi lanterna","vai est","guarda","posa lanterna"};
//		IOSimulator iosim = new IOSimulator(run2,100);
//		
//		partita = new DiaDia(iosim);
//		partita.gioca();
//		
//		iosim.leggiarray(); 
//		
//		assertTrue(iosim.VerificaPresenzaStringa(partita.getPartita().getStanzaCorrente().getNome()));
//		assertTrue(iosim.VerificaPresenzaStringa(partita.getPartita().getStanzaCorrente().getDescrizione()));
//		assertTrue(iosim.VerificaPresenzaStringa("Hai raccolto: "+partita.getPartita().getStanzaCorrente().getAttrezzo("lanterna")));
//		assertTrue(iosim.VerificaPresenzaStringa(partita.getPartita().getStanzaCorrente().getNome()));
//		assertTrue(iosim.VerificaPresenzaStringa(partita.getPartita().getStanzaCorrente().getDescrizione()));
//		assertTrue(iosim.VerificaPresenzaStringa("Hai posato nella stanza: "+partita.getPartita().getStanzaCorrente().getAttrezzo("lanterna")));
//
//	}
	
	

}
