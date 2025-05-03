package diadia;



import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

class PartitaTest {
	
	Partita partita = new Partita();
	Stanza stanza1 = new Stanza("aula");
	Stanza stanza2 = new Stanza("mensa");
	
	
	
	@BeforeEach 
	public void setUpPartita() {
		partita.setStanzaVincente(stanza1);
	}
	
	
	// METODI PER VERIFICARE il metodo "isFinita"

	@Test
	void VerificaVittoriaStanzaCorrente() {
		partita.setStanzaCorrente(stanza1);
		assertTrue(partita.isFinita());
	}
	
	@Test
	void VerificaPartitaNonVinta() {
		partita.setStanzaCorrente(stanza2);
		assertFalse(partita.isFinita());
	}
	
	@Test
	void VerificaPartitaNonFinitaCFU() {
		partita.giocatore.setCfu(3);
		assertFalse(partita.isFinita());
	}
	
	@Test
	void  VerificaPartitaVintaPerCFU() {
		partita.giocatore.setCfu(0);
		assertTrue(partita.isFinita());
		
	}

	
}
