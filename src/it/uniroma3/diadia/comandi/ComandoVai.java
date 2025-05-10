package it.uniroma3.diadia.comandi;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Borsa;
import it.uniroma3.diadia.giocatore.Giocatore;


public class ComandoVai implements Comando {
	private String direzione;

	public ComandoVai(String direzione) {
		this.direzione = direzione;
	}

	/**
	 * esecuzione del comando
	 */
	@Override 
	
	public void esegui (Partita partita) { 
	IO ioconsole = partita.getIOConsole();
	Stanza stanzaCorrente = partita.getStanzaCorrente(); 
	Stanza prossimaStanza = null; 
	if (direzione==null) { 
	ioconsole.mostraMessaggio("Dove vuoi andare? Devi specificare una direzione"); 
	return; 
	} 
	prossimaStanza = stanzaCorrente.getStanzaAdiacente (this.direzione); 
	if (prossimaStanza==null) { 
	ioconsole.mostraMessaggio("Direzione inesistente"); 
	return; 
	} 
	partita.setStanzaCorrente (prossimaStanza); 
	ioconsole.mostraMessaggio(partita.getStanzaCorrente().getNome()); 
	partita.getGiocatore().setCfu (partita.getGiocatore().getCfu()-1); 
	}
	
	@Override 
	public void setParametro(String parametro) {
		this.direzione=parametro;
	}

	@Override
	public String getNome() {
		// TODO Auto-generated method stub
		return "vai";
	}

	@Override
	public String getParametro() {
		// TODO Auto-generated method stub
		return direzione;
	}

	
}