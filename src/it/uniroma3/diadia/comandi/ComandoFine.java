package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.*;

public class ComandoFine extends AbstractComando{
	
	public ComandoFine(IO io) {
        super(io);
    }
	@Override
	public void esegui(Partita partita) {
		io.mostraMessaggio("Grazie per aver giocato!");
		partita.setFinita();
	}
}
