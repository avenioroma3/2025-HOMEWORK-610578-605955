package diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comandi.ComandoPrendi;
import it.uniroma3.diadia.comandi.ComandoVai;
import it.uniroma3.diadia.comandi.FabbricaDiComandiFisarmonica;

class ComandoPrendiTest {
	
	Partita partita = new Partita(); // 
	FabbricaDiComandiFisarmonica factory = new FabbricaDiComandiFisarmonica();
	
	Stanza s1 = new Stanza("Laboratorio");
	Stanza s2 = new Stanza("Mensa");
	
	Attrezzo a1= new Attrezzo("torcia",2);

	
	
	@BeforeEach  
	public void SetUpLab() {
		partita.setStanzaCorrente(s1);
		s1.impostaStanzaAdiacente("nord", s2);
		s1.addAttrezzo(a1);
		partita.setIOConsole(new IOConsole());
	}
	
	@Test 
	// verifica che raccolga un oggetto presente nella stanza in modo corretto
	public void RaccogliAttrezzoEsistente() {
		ComandoPrendi comando = (ComandoPrendi) factory.costruisciComando("prendi torcia");
		comando.esegui(partita);
		assertEquals(a1,partita.giocatore.getBorsa().getAttrezzo("torcia"));
	}
	
	@Test 
	// verifica che raccolga un oggetto INESISTENTE tra i presenti, di conseguenza non deve aggiugnere nulla alla borsa.
	public void RaccogliAttrezzoInesistente() {
		ComandoPrendi comando = (ComandoPrendi) factory.costruisciComando("prendi orologio");
		comando.esegui(partita);
		assertEquals(null,partita.giocatore.getBorsa().getAttrezzo("torcia"));
	}
	
	@Test 
	// verifica che raccolga un oggetto in una STANZA VUOTA , di conseguenza non deve aggiugnere nulla alla borsa.
	public void RaccogliAttrezzoStanzaVuota() {
		ComandoVai comandovai = (ComandoVai) factory.costruisciComando("vai nord"); // mi sposto in una stanza dove non è presente nessun oggetto
		comandovai.esegui(partita);
		ComandoPrendi comandoprendi = (ComandoPrendi) factory.costruisciComando("prendi qualcosa"); // la stanza non ha oggetti
		comandoprendi.esegui(partita);
		assertTrue(partita.giocatore.getBorsa().isEmpty()); // verifico che la borsa sia vuota  -> non abbia preso nulla
	}
	
	

}
