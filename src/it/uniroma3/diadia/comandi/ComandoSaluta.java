package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.*;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

public class ComandoSaluta extends AbstractComando{
	private final String CHI = "Chi dovrei salutare?...";
	
	public ComandoSaluta(IO io) {
		super(io);
	}

	@Override
	public void esegui(Partita partita) {
		AbstractPersonaggio personaggio = partita.getStanzaCorrente().getPersonaggio();
		if(personaggio!=null) {
			io.mostraMessaggio(personaggio.saluta());
		}
		else
			io.mostraMessaggio(CHI);
	}
}
