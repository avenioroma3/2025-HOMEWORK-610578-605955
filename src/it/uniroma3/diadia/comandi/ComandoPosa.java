package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.*;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPosa extends AbstractComando{
	
	public ComandoPosa (IO io) {
		super(io);
	}
	
	@Override
	public void esegui(Partita partita) {
		if (parametro == null) {
	        io.mostraMessaggio("Devi specificare quale oggetto posare.");
	        return;
	    }
		Attrezzo droppedtool = partita.giocatore.getBorsa().getAttrezzo(parametro);
		if(droppedtool==null) {
			io.mostraMessaggio("L'oggetto: "+parametro+" non è nell'inventario");
		}
		else {
			partita.giocatore.getBorsa().removeAttrezzo(parametro);
			partita.getStanzaCorrente().addAttrezzo(droppedtool);
			io.mostraMessaggio(parametro+ " posato e rimosso dall'inventario");
		}
	}
}
