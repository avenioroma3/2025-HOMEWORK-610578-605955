package diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.giocatore.Giocatore;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.comandi.ComandoVai;
import it.uniroma3.diadia.comandi.FabbricaDiComandiFisarmonica;
import it.uniroma3.diadia.*;


class ComandoVaiTest {
	Partita partita = new Partita(); // al momento della creazione della partita creo il labirinto
	FabbricaDiComandiFisarmonica factory = new FabbricaDiComandiFisarmonica();
	Stanza s1 = new Stanza("Laboratorio");
	Stanza s2 = new Stanza("Mensa");
	int startcfuvalue = partita.giocatore.getCfu();

	
	@BeforeEach  
	public void SetUpLab() {
		partita.setStanzaCorrente(s1);
		s1.impostaStanzaAdiacente("nord", s2);
	}

	
	@Test
	// controllo che il comando sposti il giocatore in un altra stanza ESISTENTE
	public void ComandoVaiDirezioneEsistente() {
	ComandoVai comando = (ComandoVai) factory.costruisciComando("vai nord"); // da fare casting sul tipo di comando particolare
	comando.esegui(partita);
	assertEquals(s2.getNome(),partita.getStanzaCorrente().getNome());
	}
	
	@Test
	// controllo che il comando sposti il giocatore in una stanza INESISTENTE
	public void ComandoVaiDirezioneInesistente() {
	ComandoVai comando = (ComandoVai) factory.costruisciComando("vai sud"); 
	comando.esegui(partita);
	assertEquals(s1.getNome(),partita.getStanzaCorrente().getNome()); //  non si muove verso direzione inesistenti si rimane nella stessa stanza.
	}
	
	@Test
	// controllo che i cfu vengano diminuiti a ogni spostamento di stanza
	public void CheckCFU() {
		ComandoVai comando = (ComandoVai) factory.costruisciComando("vai nord"); // da fare casting sul tipo di comando particolare
		comando.esegui(partita);
		assertEquals(startcfuvalue-1,partita.giocatore.getCfu());
	}
	
	

}
