package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.*;

/**
 * «guarda» stampa le informazioni sulla stanza corrente e sullo stato della partita
*/

public class ComandoGuarda extends AbstractComando{
	
	public ComandoGuarda(IO io) {
		super(io);
	}
	
	@Override
	public void esegui(Partita partita) {
		io.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
		io.mostraMessaggio("CFU: "+partita.getGiocatore().getCfu());
		io.mostraMessaggio(""+partita.getGiocatore().getBorsa().toString());
	}
}
