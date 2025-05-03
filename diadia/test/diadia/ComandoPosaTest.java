package diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comandi.ComandoPosa;
import it.uniroma3.diadia.comandi.ComandoPrendi;
import it.uniroma3.diadia.comandi.ComandoVai;
import it.uniroma3.diadia.comandi.FabbricaDiComandiFisarmonica;

class ComandoPosaTest {

	Partita partita = new Partita(); // 
	FabbricaDiComandiFisarmonica factory = new FabbricaDiComandiFisarmonica();
	
	Stanza s1 = new Stanza("Laboratorio");
	Stanza s2 = new Stanza("Mensa");
	
	Attrezzo roomtool1 = new Attrezzo("torcia",2);
	
	Attrezzo playertool1 = new Attrezzo("libro",2);
	Attrezzo playertool2 = new Attrezzo("ciarpame",1);



	
	
	@BeforeEach  
	public void SetUpLab() {
		partita.setStanzaCorrente(s1);
		s1.impostaStanzaAdiacente("nord", s2);
		s1.addAttrezzo(roomtool1);
		
		partita.giocatore.getBorsa().addAttrezzo(playertool1);
		partita.giocatore.getBorsa().addAttrezzo(playertool2);
		partita.setIOConsole(new IOConsole()); // prima dava errore perche effettivmaente non aveva una console la partita. bisogna passargliela cosi. SOLO PER FAR GIRARE I TEST.

	}
	
	@Test
	// verifica che un oggetto venga posato correttamente nella stanza (con già un oggetto)
	public void PosaOggettoStanzaNonVuota() {
		ComandoPosa comandoposa = (ComandoPosa) factory.costruisciComando("posa libro"); //playertool1
		comandoposa.esegui(partita);	
		assertTrue(partita.getStanzaCorrente().hasAttrezzo(playertool1.getNome()));
	}
	
	
	@Test
	// verifica che un oggetto venga posato correttamente in una stanza VUOTA
	public void PosaOggettoStanzaVuota() {
		ComandoVai comandovai = (ComandoVai) factory.costruisciComando("vai nord");
		comandovai.esegui(partita);
		
		ComandoPosa comandoposa = (ComandoPosa) factory.costruisciComando("posa ciarpame"); //playertool2
		comandoposa.esegui(partita);	
		assertTrue(partita.getStanzaCorrente().hasAttrezzo(playertool2.getNome()));
	}
	
	
	@Test
	// verifica che un oggetto INESISTENTE nell'inventario non venga aggiunto nella stanza -> non deve aggiungere nulla
	public void PosaOggettoNonPresenteInventario() {
		ComandoPosa comandoposa = (ComandoPosa) factory.costruisciComando("posa oggettoinesistente"); //playertool2
		comandoposa.esegui(partita);	
		assertFalse(partita.getStanzaCorrente().hasAttrezzo("oggettoinesistente"));
	}
	
}
