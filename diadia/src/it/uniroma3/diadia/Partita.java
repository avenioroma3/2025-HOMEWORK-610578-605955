package it.uniroma3.diadia;

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

	private IO ioConsole;

	private Stanza stanzaCorrente;
	private Stanza stanzaVincente;
	private boolean finita;
	public Giocatore giocatore;
	public Labirinto labirinto; // attenzione a mettere le classi come attributo PUBBLICO se vuoi accedere ai metodi
	public Borsa borsa;

	
	public Partita(){
//		Labirinto labirinto = new Labirinto(); // ERRORE: NON HAI ASSEGNATO GLI OGGETTI AGLI ATTRIBUTI DELLA PARTITA
//		Giocatore giocatore = new Giocatore();
		this.giocatore=new Giocatore();
		this.giocatore.setBorsa(new Borsa());
		this.labirinto=new Labirinto();
		this.finita = false;
//		giocatore.setCfu(CFU_INIZIALI); // settato direttamente alla creazione del giocatore
//		System.out.println("ho cfu: "+giocatore.getCfu());
		this.stanzaCorrente=labirinto.getEntrata();
		this.stanzaVincente=labirinto.getUscita();
//		this.ioConsole=io; // prima non avevo messo la console, motivo ch
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
	

	public boolean giocatoreIsVivo() {
		return this.giocatore.getCfu()!=0; // verifica se ha 0hp
	}

	public Giocatore getGiocatore() {
		return this.giocatore;
	}
	
	
	// metodi per fare in modo di poter usare l'unica istanza di IOConsole in DiaDia in tutto il codice.
	
	public IO getIOConsole() {
		return ioConsole;
	}

	public void setIOConsole(IO ioConsole) {
		this.ioConsole = ioConsole;
	}

}
