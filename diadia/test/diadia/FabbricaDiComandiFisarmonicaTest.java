package diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.*;
import it.uniroma3.diadia.comandi.ComandoAiuto;
import it.uniroma3.diadia.comandi.ComandoFine;
import it.uniroma3.diadia.comandi.ComandoGuarda;
import it.uniroma3.diadia.comandi.ComandoNonValido;
import it.uniroma3.diadia.comandi.ComandoPosa;
import it.uniroma3.diadia.comandi.ComandoPrendi;
import it.uniroma3.diadia.comandi.ComandoVai;
import it.uniroma3.diadia.comandi.FabbricaDiComandiFisarmonica;


class FabbricaDiComandiFisarmonicaTest {
	
	
	FabbricaDiComandiFisarmonica factory = new FabbricaDiComandiFisarmonica();
	Partita partita = new Partita();
	
	
	// TEST PER VERIFCARE IL CORRETTO RICONOSCIMENTO DEI COMANDI (6 comandi + 1 verifica di comando non valido)


	@Test
	// Test comando: vai *direzione*
	void ComandoVaiTest() {
		ComandoVai vai = (ComandoVai) factory.costruisciComando("vai nord");
		assertEquals(vai.getNome(),"vai");
		assertEquals(vai.getParametro(),"nord");
	}
	
	@Test
	// Test comando: prendi *nome_oggetto*
	void ComandoPrendiTest() {
		ComandoPrendi prendi = (ComandoPrendi) factory.costruisciComando("prendi osso");
		assertEquals(prendi.getNome(),"prendi");
		assertEquals(prendi.getParametro(),"osso");
	}
	
	@Test
	// Test comando: posa *nome_oggetto*
	void ComandoPosaTest() {
		ComandoPosa posa = (ComandoPosa) factory.costruisciComando("posa lanterna");
		assertEquals(posa.getNome(),"posa");
		assertEquals(posa.getParametro(),"lanterna");
	}
	

	@Test
	// Test comando: guarda
	void ComandoGuardaTest() {
		ComandoGuarda guarda = (ComandoGuarda) factory.costruisciComando("guarda");
		assertEquals(guarda.getNome(),"guarda");
		assertNull(guarda.getParametro());

	}
	
	
	@Test
	// Test comando: aiuto
	void ComandoAiutoTest() {
		ComandoAiuto aiuto = (ComandoAiuto) factory.costruisciComando("aiuto");
		assertEquals(aiuto.getNome(),"aiuto");
		assertNull(aiuto.getParametro());

	}

	@Test
	// Test comando non valido -> i parametri sono nulli
	void ComandoNonValidoTest() {
		ComandoNonValido comnonvalido = (ComandoNonValido) factory.costruisciComando("distruggi");
		assertNull(comnonvalido.getNome());
		assertNull(comnonvalido.getParametro());
		
	}
	
	@Test
	// Test comando: fine -> termina la partita 
	void ComandoFineTest() {
		ComandoFine fine = (ComandoFine) factory.costruisciComando("fine");
		assertEquals (fine.getNome(),"fine");
		assertNull(fine.getParametro());
	}
	

}
