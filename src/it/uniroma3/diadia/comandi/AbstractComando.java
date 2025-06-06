package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.*;

public abstract class AbstractComando {
	
	public IO io;
	public String parametro;
	
	public AbstractComando(IO io) {
		this.io = io;
	}
	
	/**
	 * esecuzione del comando
	 */
	public abstract void esegui(Partita partita);
	/**
	 * Set parametro comando
	 * @param parametro
	 */
	public void setParametro(String parametro) {
		this.parametro = parametro;
	}

}
