package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.ConfigurazioneDiadia;

public class Giocatore {
	

	private int cfu;
	public Borsa borsa;
	
	public Giocatore() {
		this.cfu = ConfigurazioneDiadia.getCfuIniziali();
	}
	
	public void setCfu(int cfu) {
		this.cfu=cfu;
	}

	public void setBorsa(Borsa borsa) {
		this.borsa=borsa;
	}
	
	public int getCfu() {
		return this.cfu;
	}
	
	public Borsa getBorsa() {
		return this.borsa;
	}
	
	/**
	 * Restituisce vero se e solo se il giocatore ha ancora dei CFU
	 * @return vero se il giocatore è vivo
	 */
	public boolean isVivo( ) {
		return (getCfu() != 0);
	}
}

