package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.*;

public class ComandoNonValido extends AbstractComando{
	
	public ComandoNonValido(IO io) {
		super(io);
	}
	@Override
	public void esegui(Partita partita) {
		io.mostraMessaggio("Il comando non esiste\n");
		io.mostraMessaggio("Ecco una lista dei comandi: ");
		for(DiaDia.elencoComandi comando : DiaDia.elencoComandi.values()) {
			io.mostraMessaggio(comando.getDescrizione());
		}
	}
}
