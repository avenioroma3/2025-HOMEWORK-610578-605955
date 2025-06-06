package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.*;

public class ComandoAiuto extends AbstractComando{
	
	public ComandoAiuto(IO io) {
		super(io);
	}
	
	@Override
	public void esegui(Partita partita) {
		io.mostraMessaggio("Il tuo obiettivo è di raggiungere la biblioteca prima di esaurire i CFU.\n"
				+"Ti consiglio di guardarti intorno prima di iniziare");
		io.mostraMessaggio("Comandi:");
		for(DiaDia.elencoComandi comando : DiaDia.elencoComandi.values()) {
			io.mostraMessaggio(comando.getDescrizione());
		}
	}
}
