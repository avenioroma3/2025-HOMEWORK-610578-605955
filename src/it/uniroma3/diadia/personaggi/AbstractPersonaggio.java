package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public abstract class AbstractPersonaggio {

	private String nome;
	private String presentazione;
	private boolean haSalutato;
	
	public AbstractPersonaggio(String nome, String presentazione) {
		this.nome = nome;
		this.presentazione = presentazione;
		this.haSalutato = false;
		
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPresentazione() {
		return presentazione;
	}
	
	public boolean haSalutato() {
		return this.haSalutato;
	}
	
	public String saluta() {
		StringBuilder risposta = new StringBuilder("Ciao io sono ");
		risposta.append(this.getNome()+".");
		if(this.haSalutato)
			risposta.append("Ci siamo gia salutati!");
		else {
			risposta.append(this.getPresentazione());
			this.haSalutato=true;
		}
		return risposta.toString();
	}
	
	abstract public String agisci(Partita partita);
	/**
	 * Da un regalo al personaggio nella stanza corrente
	 * @param attrezzo da regalare
	 * @param partita
	 */
	abstract public String riceviRegalo(Attrezzo attrezzo, Partita partita);
	
	@Override
	public String toString() {
		return this.getNome();
	}
}
