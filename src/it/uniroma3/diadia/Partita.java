package it.uniroma3.diadia;

import java.io.FileNotFoundException;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Borsa;
import it.uniroma3.diadia.giocatore.Giocatore;

/**
 * Questa classe modella una partita del gioco
 *
 * @author  docente di POO
 * @see Stanza
 * @version base
 */

public class Partita {

	private Stanza stanzaCorrente;
	private Stanza stanzaVincente;
	private boolean finita;
	public Giocatore giocatore;
	public Labirinto labirinto; 
	public Borsa borsa;

	
	public Partita(){
		this.giocatore=new Giocatore();
		this.giocatore.setBorsa(new Borsa());
		try {
		    this.labirinto = Labirinto.caricaDaFile("labirinto.txt");
		} catch (FileNotFoundException | FormatoFileNonValidoException e) {
		    e.printStackTrace();
		    this.labirinto = null;
		}
		this.finita = false;
		this.stanzaCorrente = labirinto.getStanzaIniziale();
		this.stanzaVincente = labirinto.getStanzaVincente();
	}
	
	public Partita(Labirinto labirinto) {
		this();
	    this.labirinto = labirinto;
	    this.stanzaCorrente = labirinto.getStanzaIniziale();
	    this.stanzaVincente = labirinto.getStanzaVincente();
	}

	public Stanza getStanzaVincente() {
		return stanzaVincente;
	}
	
	public void setStanzaVincente(Stanza stanzaVincente) {
		this.stanzaVincente=stanzaVincente;
	}

	public void setStanzaCorrente(Stanza stanzaCorrente) {
		this.stanzaCorrente = stanzaCorrente;
	}

	public Stanza getStanzaCorrente() {
		return this.stanzaCorrente;
	}
	
	public void setLabirinto(Labirinto labirinto) {
		this.labirinto = labirinto;
		this.stanzaCorrente = labirinto.getStanzaIniziale();
	    this.stanzaVincente = labirinto.getStanzaVincente();
	}
	/**
	 * Restituisce vero se e solo se la partita e' stata vinta
	 * @return vero se partita vinta
	 */
	public boolean vinta() {	// *****!!IMPORTANTE!!***** 
		return this.getStanzaCorrente() == this.getStanzaVincente();
	}

	/**
	 * Restituisce vero se e solo se la partita e' finita
	 * @return vero se partita finita
	 */
	public boolean isFinita() { //*****!!IMPORTANTE!!***** 
		return finita || vinta() || (giocatore.getCfu() == 0);
	}
	
	/**
	 * Imposta la partita come finita
	 *
	 */
	public void setFinita() {
		this.finita = true;
	}

	public Giocatore getGiocatore() {
		return this.giocatore;
	}
}
